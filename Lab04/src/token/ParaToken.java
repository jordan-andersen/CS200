package token;

public class ParaToken extends Token {

    protected final Token token;

    protected ParaToken(Token token) {
        this.token = token;
    }

    @Override
    public double eval() { return token.eval(); }

    @Override
    public String toString() { return "( " + token.toString() + " )"; }
}
