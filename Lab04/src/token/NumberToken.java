package token;

public class NumberToken extends Token {
    protected final double value;

    protected NumberToken(double value) {
        this.value = value;
    }

    @Override
    public double eval() { return value; }

    @Override
    public String toString() { return String.valueOf(value); }

}
