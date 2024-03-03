package token;

public class ExponentiationToken extends OperatorToken {
    protected ExponentiationToken(Token leftToken, Token rightToken) {
        super(leftToken, rightToken);
        if (DEBUG_MODE) { System.out.println("Creating EXPONENTIATION TOKEN: " + leftToken + " ^ " + rightToken); }
    }

    @Override
    public double eval() {
        double leftEval = leftToken.eval();
        double rightEval = rightToken.eval();
        double result = Math.pow(leftEval,  rightEval);
        if (VERBOSE_MODE) { System.out.println("Evaluating: " + leftEval + " ^ " + rightEval + " = " + result);}
        return result;
    }

    @Override
    public String toString() { return leftToken.toString() + " ^ " + rightToken.toString(); }
}
