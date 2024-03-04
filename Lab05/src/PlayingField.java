import java.util.ArrayList;
import java.util.List;

public class PlayingField {
    // ATTRIBUTES
    private final int width;
    private final int height;
    private final Prize prize;
    private final List<Contestant> contestantList;
    private final List<IMovable> movableList;
    private final List<IDisplayable> displayableList;
    private Contestant winner;

    // METHODS

    public PlayingField(int width, int height){
        this.width = width;
        this.height = height;
        this.prize = new Prize(Main.PRIZE_X, Main.PRIZE_Y);
        this.contestantList = new ArrayList<>();
        this.movableList = new ArrayList<>();
        this.displayableList = new ArrayList<>();
        this.winner = null;
        initializeGame();
    }

    private void initializeGame(){
        Tortoise tortoise = new Tortoise(Main.CONTESTANT_X, Main.CONTESTANT_Y);
        Hare hare = new Hare(Main.CONTESTANT_X, Main.CONTESTANT_Y);
        tortoise.chooseTarget(prize);
        hare.chooseTarget(prize);
        contestantList.add(tortoise);
        contestantList.add(hare);
        movableList.add(tortoise);
        movableList.add(hare);
        displayableList.add(tortoise);
        displayableList.add(hare);
        displayableList.add(prize);
    }

    public void stepGame(){
        for (IMovable movableObject : movableList) {
            movableObject.step();
        }
    }

    public boolean checkWin(){
        int prizeX = prize.getCoordinateX();
        int prizeY = prize.getCoordinateY();
        for (Contestant contestant : contestantList) {
            int contestantX = contestant.getCoordinateX();
            int contestantY = contestant.getCoordinateY();
            if (contestantX == prizeX && contestantY == prizeY) {
                winner = contestant;
                return true;
            }
        }
        return false;
    }

    public String getWinner(){ return winner.getSymbol(); }
    public String toString(){
        StringBuilder grid = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid.append(" ");
                boolean cell_filled = false;
                for (IDisplayable displayObject : displayableList) {
                    int objectX = displayObject.getCoordinateX();
                    int objectY = displayObject.getCoordinateY();
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
