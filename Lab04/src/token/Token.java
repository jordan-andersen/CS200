package token;

public abstract class Token {
    public static Token parse(String expression) {
        // Find the lowest precedence operator
        int lowestIndex = getLowestIndex(expression);

        if (lowestIndex != -1) {
            // Split the expression and recursively parse
            String leftString = expression.substring(0, lowestIndex);
            String rightString = expression.substring(lowestIndex + 1);
            Token leftToken = parse(leftString);
            Token rightToken = parse(rightString);

            // Create appropriate Operator token
            switch (expression.charAt(lowestIndex)) {
                case '+':
                    return new AddToken(leftToken, rightToken);
                case '-':
                    return new SubToken(leftToken, rightToken);
                case '*':
                    return new MulToken(leftToken, rightToken);
                case '/':
                    return new DivToken(leftToken, rightToken);
            }
        }
        // Parse as a single number if no operator found.
        return new NumToken(Double.parseDouble(expression));
    }

    private static int getLowestIndex(String expression) {
        int precedence = 0;
        int lowestIndex = -1;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (( c == '+' || c == '-' ) && precedence != 1) {
                precedence = 1;
                lowestIndex = i;
            } else if (( c == '*' || c == '/' ) && precedence != 1) {
                precedence = 2;
                lowestIndex = i;
            }
        }
        return lowestIndex;
    }

    public abstract double eval();

    @Override
    public abstract String toString();
}
