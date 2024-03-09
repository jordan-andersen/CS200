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
        Coordinate targetOffset = position.getOffset(target.getPosition());
        int newX = evalCoordinate(position.x(), targetOffset.x(), TORTOISE_SPEED);
        int newY = evalCoordinate(position.y(), targetOffset.y(), TORTOISE_SPEED);
        position = new Coordinate(newX, newY);
    }
}
