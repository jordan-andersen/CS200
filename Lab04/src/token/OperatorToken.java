package token;

public abstract class OperatorToken extends Token{
    protected final Token leftToken;
    protected final Token rightToken;

    protected OperatorToken(Token leftToken, Token rightToken) {
        this.leftToken = leftToken;
        this.rightToken = rightToken;
    }
}
