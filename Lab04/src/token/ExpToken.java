package token;

public class ExpToken extends OperatorToken {
    protected ExpToken(Token leftToken, Token rightToken) { super(leftToken, rightToken); }

    @Override
    public double eval() { return Math.pow(leftToken.eval(),  rightToken.eval()); }

    @Override
    public String toString() { return leftToken.toString() + " ^ " + rightToken.toString(); }
}
