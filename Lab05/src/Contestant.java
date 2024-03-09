public abstract class Contestant implements Displayable, Movable {
    // GAME CONSTANTS
    public static final int START_X = 2;
    public static final int START_Y = 2;

    // ATTRIBUTES
    protected Coordinate position;
    protected final String symbol;
    protected Prize target;

    // METHODS
    protected Contestant(int coordinateX, int coordinateY, String symbol) {
        this.position = new Coordinate(coordinateX, coordinateY);
        this.symbol = symbol;
    }

    public void setTarget(Prize target){ this.target = target;}

    public Prize getTarget(){ return target; }

    @Override
    public Coordinate getPosition() { return position; }

    @Override
    public String getSymbol() { return symbol; }

    protected static int evalCoordinate(int currentPosition, int targetPosition, int contestantSpeed) {
        int positionIncrement = Math.abs(targetPosition) == 1 ? 1 : contestantSpeed;
        if (targetPosition > 0) {
            return currentPosition + positionIncrement;
        } else if (targetPosition < 0) {
            return currentPosition - positionIncrement;
        } else {
            return currentPosition;
        }
    }
}
