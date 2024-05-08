package model;

import java.time.LocalDateTime;

public class Datapoint {

    private final LocalDateTime moment;

    private final Coordinate northern;
    private final Coordinate southern;
    private final Coordinate central;


    public Datapoint(LocalDateTime time, Coordinate n, Coordinate s, Coordinate c) {
        this.moment = time;
        this.northern = n;
        this.southern = s;
        this.central = c;
    }

    public Coordinate getNorthern() {
        return northern;
    }

    public Coordinate getSouthern() {
        return southern;
    }

    public Coordinate getCentral() {
        return central;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    @Override
    public String toString() {
        return "Eclipse data for " + this.moment + ": (N: " + this.northern + "), (S: " + this.southern + "), (C:" + this.central + ")";
    }
    
}
