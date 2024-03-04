public class Prize implements IDisplayable{
    // ATTRIBUTES
    protected int coordinateX;
    protected int coordinateY;
    protected String symbol;

    // METHODS
    public Prize(int coordinateX, int coordinateY){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.symbol = "*";
    }

    @Override
    public int getCoordinateX() { return coordinateX; }

    @Override
    public int getCoordinateY() { return coordinateY; }

    @Override
    public String getSymbol() { return symbol; }
}
