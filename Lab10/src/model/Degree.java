package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Degree {
    
    private final int degrees;
    private final int minutes;
    private final double seconds;

    private final boolean isLatitude;
    private final boolean isNegative;

    public Degree(int given_degrees, int given_minutes, double given_seconds, boolean isLatitude, boolean isNegative) {
        this.degrees = Math.abs(given_degrees);
        this.minutes = Math.abs(given_minutes);
        this.seconds = Math.abs(given_seconds);

        this.isLatitude = isLatitude;
        this.isNegative = isNegative;

    }

    public double asDouble() {
        double degrees_dec = this.degrees + ( this.minutes / 60.0) + (this.seconds / (60 * 60));
        if ( isNegative ) {
            degrees_dec = degrees_dec * -1;
        }

        return degrees_dec;
    }

    @Override
    public String toString() {

        String cardinal = ( this.isLatitude ) ?
        (isNegative) ? "S" : "N" :
        (isNegative) ? "W" : "E";


        return this.degrees + "° " + this.minutes + "' " + this.seconds + "\" " +  cardinal;
    }

    private static boolean cardinalToLatitude(String cardinal) {
        return cardinal.equalsIgnoreCase("S") || cardinal.equalsIgnoreCase("N");
    }

    private static boolean cardinalToNegative(String cardinal) {
        return cardinal.equalsIgnoreCase("S") || cardinal.equalsIgnoreCase("W");
    }

    public static Degree parse(String degreeString) {
        Pattern degree_template = Pattern.compile("\\s*(\\d+)\\s*°\\s*([\\d+.?]*)\\s*([NWSE])");
        Matcher matches = degree_template.matcher(degreeString);
        if ( matches.find() ) {
            int degrees = Integer.parseInt(matches.group(1));
            double minutes_decimal = Double.parseDouble(matches.group(2));
            int minutes = (int) Math.floor(minutes_decimal);

            double seconds = ( minutes_decimal - minutes ) * 60;
            seconds = Math.floor(seconds * 100) / 100;
            String cardinal = matches.group(3);

            return new Degree(degrees, minutes, seconds, cardinalToLatitude(cardinal), cardinalToNegative(cardinal));
            
        }       
        
        return null;

    }

}
