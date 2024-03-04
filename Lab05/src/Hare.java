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
            if (coordinateX != target.getCoordinateX()) {
                coordinateX = evalCoordinate(coordinateX, target.getCoordinateX(), HARE_SPEED);
            } else {
                coordinateY = evalCoordinate(coordinateY, target.getCoordinateY(), HARE_SPEED);
            }
            energy --;
        }
    }
}
