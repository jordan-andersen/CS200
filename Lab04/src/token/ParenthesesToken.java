package token;

public class ParenthesesToken extends Token {

    protected final Token token;

    protected ParenthesesToken(Token token) {
        this.token = token;
    }

    @Override
    public double eval() { return token.eval(); }

    @Override
    public String toString() { return "( " + token.toString() + " )"; }
}
