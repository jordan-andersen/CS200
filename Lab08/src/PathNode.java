import java.time.LocalTime;
public class PathNode {
    private final LocalTime time;
    private final Coordinate northCoord;
    private final Coordinate southCoord;

    public PathNode(LocalTime time, Coordinate northCoord, Coordinate southCoord) {
        this.time = time;
        this.northCoord = northCoord;
        this.southCoord = southCoord;
    }

    public boolean checkCoordinate(double latitude, double longitude) {
        return rangeCheck(northCoord.getLatitude(), southCoord.getLatitude(), latitude) &&
                rangeCheck(southCoord.getLongitude(), northCoord.getLongitude(), longitude);
    }

    private static boolean rangeCheck(double upperValue, double lowerValue, double compValue) {
        return upperValue >= compValue && compValue >= lowerValue;
    }

    public String toString() {
        return "\nThe total eclipse was visible at: " + time.toString() + ".";
    }
}