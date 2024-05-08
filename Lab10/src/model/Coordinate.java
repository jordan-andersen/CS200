package model;

public class Coordinate {

    private final Degree latitude;
    private final Degree longitude;

    public Coordinate(Degree latitude, Degree longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Degree getLatitude() {
        return latitude;
    }
    
    public Degree getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return this.latitude + ", " + this.longitude;
    }
}
