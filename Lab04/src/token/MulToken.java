package token;

public class MulToken extends OperatorToken {
    protected MulToken(Token leftToken, Token rightToken) {
        super(leftToken, rightToken);
        if (DEBUG_MODE) { System.out.println("Creating MUL TOKEN: " + leftToken + " * " + rightToken); }
    }

    @Override
    public double eval() {
        double leftEval = leftToken.eval();
        double rightEval = rightToken.eval();
        double result = leftEval * rightEval;
        if (VERBOSE_MODE) { System.out.println("Evaluating: " + leftEval + " * " + rightEval + " = " + result);}
        return result;
    }

    @Override
    public String toString() { return leftToken.toString() + " * " + rightToken.toString(); }
}
