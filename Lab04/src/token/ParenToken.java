package token;

public class ParenToken extends Token {

    protected final Token token;

    protected ParenToken(Token token) {
        this.token = token;
        if (DEBUG_MODE) { System.out.println("Creating PAREN-TOKEN: ( " + token + " )"); }
    }

    @Override
    public double eval() { return token.eval(); }

    @Override
    public String toString() { return "( " + token.toString() + " )"; }
}
