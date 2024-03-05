package token;

public class ParenthesesToken extends Token {

    protected final Token token;

    protected ParenthesesToken(Token token) {
        this.token = token;
        if (DEBUG_MODE) {
            System.out.println("Creating PARENTHESES TOKEN: ( " + token + " )");
        }
    }

    @Override
    public double eval() { return token.eval(); }

    @Override
    public String toString() { return "( " + token.toString() + " )"; }
}
