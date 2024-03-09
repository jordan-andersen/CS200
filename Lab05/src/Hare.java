public class Hare extends Contestant {
    // GAME CONSTANTS
    public static final int HARE_SPEED = 2;
    private static final String HARE_SYMBOL = "R";

    // ATTRIBUTES
    private int energy;

    // METHODS
    public Hare(int coordinateX, int coordinateY) {
        super(coordinateX, coordinateY, HARE_SYMBOL);
        this.energy = 3;
    }

    @Override
    public void step() {
        if (energy < 1) {
           energy = 3;
        } else {
            Coordinate targetOffset = position.getOffset(target.getPosition());
            if (targetOffset.x() != 0) {
                position = new Coordinate(evalCoordinate(position.x(), targetOffset.x(), HARE_SPEED), position.y());
            } else {
                position = new Coordinate(position.x(), evalCoordinate(position.y(), targetOffset.y(), HARE_SPEED));
            }
            energy --;
        }
    }
}
