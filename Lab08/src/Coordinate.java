public class Coordinate {
    private static final String INVALID_COORD = "Invalid coordinate input, unable to create Coordinate: ";
    double latitude;
    double longitude;

    public Coordinate(String latitude, String longitude) {
        this.latitude = convertCoordinate(latitude);
        this.longitude = convertCoordinate(longitude);
    }

    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public static double convertCoordinate(String coordinate) {
        try {
            if (coordinate.replace("-", "").isEmpty()) { return 0.0; }
            boolean isNegative = coordinate.contains("W") || coordinate.contains("S") || coordinate.contains("-");
            String[] coordParts = coordinate.replaceAll("[^0-9.]", " ")
                    .trim()
                    .split("\\s+");
            double degrees = Double.parseDouble(coordParts[0]);
            double minutes = coordParts.length > 1 ? Double.parseDouble(coordParts[1]) : 0;
            double seconds = coordParts.length > 2 ? Double.parseDouble(coordParts[2]) : 0;
            double decimalDegrees = degrees + (minutes / 60) + (seconds / 3600);
            return isNegative ? -decimalDegrees : decimalDegrees;
        } catch (Exception e) {
            System.out.println(INVALID_COORD + e.getMessage());
            return 0.0;
        }
    }
}
