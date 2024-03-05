package token;

public class AdditionToken extends OperatorToken {
    protected AdditionToken(Token leftToken, Token rightToken) {
        super(leftToken, rightToken);
        if (DEBUG_MODE) {
            System.out.println("Creating ADDITION TOKEN: " + leftToken + " + " + rightToken);
        }
    }

    @Override
    public double eval() {
        double leftEval = leftToken.eval();
        double rightEval = rightToken.eval();
        double result = leftEval + rightEval;
        if (VERBOSE_MODE) {
            System.out.println("Evaluating: " + leftEval + " + " + rightEval + " = " + result);
        }
        return result;
    }

    @Override
    public String toString() { return leftToken.toString() + " + " + rightToken.toString(); }
}
