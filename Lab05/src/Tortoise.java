public class Tortoise extends Contestant {
    // GAME CONSTANTS
    public static final int TORTOISE_SPEED = 1;
    private static final String TORTOISE_SYMBOL = "T";

    // METHODS
    public Tortoise(int coordinateX, int coordinateY) {
        super(coordinateX, coordinateY, TORTOISE_SYMBOL);
    }

    @Override
    public void step() {
        coordinateX = evalCoordinate(coordinateX, target.getCoordinateX(), TORTOISE_SPEED);
        coordinateY = evalCoordinate(coordinateY, target.getCoordinateY(), TORTOISE_SPEED);
    }
}
