package token;

public class SubToken extends OperatorToken {
    protected SubToken(Token leftToken, Token rightToken) { super(leftToken, rightToken); }

    @Override
    public double eval() { return leftToken.eval() - rightToken.eval(); }

    @Override
    public String toString() { return leftToken.toString() + " - " + rightToken.toString(); }
}
