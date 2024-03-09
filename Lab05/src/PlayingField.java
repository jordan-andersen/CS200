import java.util.ArrayList;
import java.util.List;

public class PlayingField {
    // ATTRIBUTES
    private final int width;
    private final int height;
    private final Prize prize;
    private final List<Contestant> contestantList;
    private final List<Movable> movableList;
    private final List<Displayable> displayableList;
    private Contestant winner;

    // METHODS
    public PlayingField(int width, int height) {
        this.width = width;
        this.height = height;
        this.prize = new Prize(Prize.START_X, Prize.START_Y);
        this.contestantList = new ArrayList<>();
        this.movableList = new ArrayList<>();
        this.displayableList = new ArrayList<>();
        initializeGame();
    }

    private void initializeGame() {
        Tortoise tortoise = new Tortoise(Contestant.START_X, Contestant.START_Y);
        Hare hare = new Hare(Contestant.START_X, Contestant.START_Y);
        tortoise.setTarget(prize);
        hare.setTarget(prize);
        contestantList.add(tortoise);
        contestantList.add(hare);
        movableList.add(tortoise);
        movableList.add(hare);
        displayableList.add(tortoise);
        displayableList.add(hare);
        displayableList.add(prize);
    }

    public void stepGame() {
        for (Movable movableObject : movableList) {
            movableObject.step();
        }
    }

    public boolean checkWin() {
        int prizeX = prize.getPosition().x();
        int prizeY = prize.getPosition().y();
        for (Contestant contestant : contestantList) {
            int contestantX = contestant.getPosition().x();
            int contestantY = contestant.getPosition().y();
            if (contestantX == prizeX && contestantY == prizeY) {
                winner = contestant;
                return true;
            }
        }
        return false;
    }

    public String getWinner() {
        return winner != null ? winner.getSymbol(): "NO ONE HAS WON YET!";
    }

    public String toString() {
        StringBuilder grid = new StringBuilder();
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                grid.append(" ");
                boolean cell_filled = false;
                for (Displayable displayObject : displayableList) {
                    int objectX = displayObject.getPosition().x();
                    int objectY = displayObject.getPosition().y();
                    if (objectX == x && objectY == y && !cell_filled) {
                        cell_filled = true;
                        grid.append(displayObject.getSymbol());
                    }
                }
                if (!cell_filled) {
                    grid.append(".");
                }
                grid.append(" ");
            }
            grid.append("\n");
        }
        return grid.toString();
    }
}
