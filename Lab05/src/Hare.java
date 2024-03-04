public class Hare extends Contestant {
    // ATTRIBUTES
    private int energy;

    // METHODS
    public Hare(int coordinateX, int coordinateY){
        super(coordinateX, coordinateY, "R");
        this.energy = 3;
    }

    @Override
    public void step(){
        if (energy < 1) {
           energy = 3;
        } else {
            if (coordinateX != target.getCoordinateX()) {
                coordinateX = evalCoordinate(coordinateX, target.getCoordinateX(), Main.HARE_SPEED);
            } else {
                coordinateY = evalCoordinate(coordinateY, target.getCoordinateY(), Main.HARE_SPEED);
            }
            energy --;
        }
    }
}
