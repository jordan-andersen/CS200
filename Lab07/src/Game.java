public class Game {
    // ATTRIBUTES
    private Player currPlayer;
    private Player prevPlayer;
    private Space currSpace;
    private Space prevSpace;

    // METHODS
    public Game () {
        initializeGame();
    }

    private void initializeGame() {
        currPlayer = new Player("ONE", null);
        prevPlayer = new Player("TWO", currPlayer);
        currPlayer.setNext(prevPlayer);
        createBoard();
    }

    private void createBoard() {
        for (int i = 0; i < 14; i++) {
            addSpace(i);
        }
    }

    private void addSpace(int i) {
        Player p = i < 7 ? currPlayer : prevPlayer;
        Space s;
        if (currSpace == null) {
            s = new Goal(null, p);
            p.setGoal(s);
            currSpace = s;
            prevSpace = s;
            s.setNext(s);
        } else if (i == 7) {
            s = new Goal(currSpace, p);
            p.setGoal(s);
        } else {
            if (i > 7) { i = i - 1; }
            s = new Space(i, 4, currSpace);
        }
        prevSpace.setNext(s);
        prevSpace = s;
    }

    public void nextPlayer() {
        prevPlayer = currPlayer;
        currPlayer = currPlayer.getNext();
    }

    private void incrementSpace() {
        prevSpace = currSpace;
        currSpace = currSpace.getNext();
    }

    private void seekByIndex(int i) {
        while(i != currSpace.getIndex()) {
            incrementSpace();
        }
    }

    public boolean takeTurn(int i) {
        boolean validIndex = 1 <= i && i <= 12;
        if (validIndex) {
            seekByIndex(i);
            currSpace.removeCount();
            int j = 1;
            while (j <= 4) {
                incrementSpace();
                if (currSpace.getIndex() != 0) {
                    currSpace.incrementCount();
                    j++;
                } else {
                    if (currSpace.equals(currPlayer.getGoal())) {
                        currSpace.incrementCount();
                        j++;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean gameOver() {
        seekByIndex(1);
        int countA = findRowCount();
        seekByIndex(7);
        int countB = findRowCount();
        return countA == 0 | countB == 0;
    }

    private int findRowCount() {
        int count = 0;
        for (int i = 1; i <= 6; i++) {
            count += currSpace.getCount();
            incrementSpace();
        }
        return count;
    }

    public String getWinner() {
        int currPlayerScore = currPlayer.getGoal().getCount();
        int prevPlayerScore = prevPlayer.getGoal().getCount();
        String prompt = "\nTHE WINNER IS: ";
        String winner =  currPlayerScore > prevPlayerScore ? currPlayer.getName() : prevPlayer.getName();
        return prompt + winner;
    }

    public String getStatus() {
        StringBuilder status = new StringBuilder();
        boolean playerSwitch = currPlayer.getName().equals("ONE");
        String currPlayerScore = String.valueOf(currPlayer.getGoal().getCount());
        String prevPlayerScore = String.valueOf(prevPlayer.getGoal().getCount());
        String playerOneScore = playerSwitch ? currPlayerScore : prevPlayerScore;
        String playerTwoScore = playerSwitch ? prevPlayerScore : currPlayerScore;
        status.append("\nCURRENT PLAYER: ").append(currPlayer.getName())
                .append("\nCURRENT SCORE: ONE - ").append(playerOneScore).append(" | TWO - ").append(playerTwoScore);
        return status.toString();
    }
    public String toString() {
        seekByIndex(1);
        StringBuilder board = new StringBuilder();
        StringBuilder boardRow1 = new StringBuilder("\n\t");
        StringBuilder boardRow2 = new StringBuilder("\n\t");
        StringBuilder goalRow = new StringBuilder("\n");
        boolean playerSwitch = currPlayer.getName().equals("ONE");
        String currPlayerGoal = currPlayer.getGoal().toString();
        String prevPlayerGoal = prevPlayer.getGoal().toString();
        String playerOneGoal = playerSwitch ? currPlayerGoal : prevPlayerGoal;
        String playerTwoGoal = playerSwitch ? prevPlayerGoal : currPlayerGoal;
        boolean continueLoop = true;
        while (continueLoop) {
            if (!(currSpace.getIndex() == 0)) {
                if (currSpace.getIndex() < 7) {
                    boardRow1.append(currSpace.toString());
                } else {
                    boardRow2.insert(2, currSpace.toString());
                }
            }
            incrementSpace();
            continueLoop = currSpace.getIndex() != 1;
        }
        goalRow.append(playerOneGoal).append("=".repeat(boardRow1.length()-4)).append(playerTwoGoal);
        board.append(boardRow1).append(goalRow).append(boardRow2);
        return board.toString();
    }
}
