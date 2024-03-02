package token;

public class NumToken extends Token {
    protected final double value;

    protected NumToken(double value) {
        this.value = value;
        if (DEBUG_MODE) { System.out.println("Creating NUM-TOKEN: " + value); }
    }

    @Override
    public double eval() { return value; }

    @Override
    public String toString() { return String.valueOf(value); }

}
