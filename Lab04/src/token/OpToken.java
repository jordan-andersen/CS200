package token;

public abstract class OpToken extends Token{
    protected final Token leftToken;
    protected final Token rightToken;

    protected OpToken (Token leftToken, Token rightToken) {
        this.leftToken = leftToken;
        this.rightToken = rightToken;
    }
}
