public class Tortoise extends Contestant{
    // METHODS
    public Tortoise(int coordinateX, int coordinateY){
        super(coordinateX, coordinateY, "T");
    }

    @Override
    public void step() {
        coordinateX = evalCoordinate(coordinateX, target.getCoordinateX(), Main.TORTOISE_SPEED);
        coordinateY = evalCoordinate(coordinateY, target.getCoordinateY(), Main.TORTOISE_SPEED);
    }
}
