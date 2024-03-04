public class Prize implements IDisplayable {
    // GAME CONSTANTS
    public static final int START_X = 10;
    public static final int START_Y = 10;
    private static final String PRIZE_SYMBOL = "*";

    // ATTRIBUTES
    protected int coordinateX;
    protected int coordinateY;
    protected final String symbol;

    // METHODS
    public Prize(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.symbol = PRIZE_SYMBOL;
    }

    @Override
    public int getCoordinateX() { return coordinateX; }

    @Override
    public int getCoordinateY() { return coordinateY; }

    @Override
    public String getSymbol() { return symbol; }
}
