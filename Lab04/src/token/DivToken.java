package token;

public class DivToken extends OpToken {
    protected DivToken(Token leftToken, Token rightToken) { super(leftToken, rightToken); }

    @Override
    public double eval() {
        return leftToken.eval() / rightToken.eval();
    }

    @Override
    public String toString() { return leftToken.toString() + " / " + rightToken.toString(); }
}
