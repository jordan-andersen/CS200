public class Prize implements Displayable {
    // GAME CONSTANTS
    public static final int START_X = 9;
    public static final int START_Y = 9;
    private static final String PRIZE_SYMBOL = "*";

    // ATTRIBUTES
    protected Coordinate position;
    protected final String symbol;

    // METHODS
    public Prize(int coordinateX, int coordinateY) {
        this.position = new Coordinate(coordinateX, coordinateY);
        this.symbol = PRIZE_SYMBOL;
    }

    @Override
    public Coordinate getPosition() { return position; }

    @Override
    public String getSymbol() { return symbol; }
}
