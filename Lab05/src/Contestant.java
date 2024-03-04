public abstract class Contestant implements IDisplayable, IMovable{
    // ATTRIBUTES
    protected int coordinateX;
    protected int coordinateY;
    protected String symbol;
    protected Prize target;

    // METHODS
    protected Contestant(int coordinateX, int coordinateY, String symbol){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.symbol = symbol;
    }

    public void chooseTarget(Prize target){
        this.target = target;
    }

    @Override
    public int getCoordinateX() { return coordinateX; }

    @Override
    public int getCoordinateY() { return coordinateY; }

    @Override
    public String getSymbol() { return symbol; }

    public Prize getTarget(){ return target; }

    protected static int evalCoordinate(int currentPosition, int targetPosition, int contestantSpeed) {
        int positionDifferential = Math.abs(targetPosition - currentPosition);
        int positionIncrement = positionDifferential == 1 ? 1 : contestantSpeed;
        if (currentPosition < targetPosition) {
            return currentPosition + positionIncrement;
        } else if (currentPosition > targetPosition) {
            return currentPosition - positionIncrement;
        } else {
            return currentPosition;
        }
    }
}
