package token;

public class NumToken extends Token {
    protected final double value;

    protected NumToken(double value) {
        this.value = value;
    }

    @Override
    public double eval() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
