package token;

public abstract class Token {
    public static Token parse(String expression) {
        // Find the lowest precedence operator
        int i = getOperatorIndex(expression);
        int j =  expression.length();

        if (i != -1) {
            // Split the expr and recursively parse
            boolean leftParentheses = checkParentheses(expression.substring(0, i));
            boolean rightParentheses = checkParentheses(expression.substring(i+1, j));

            String leftString = leftParentheses ? expression.substring(1, i-1) : expression.substring(0, i);
            String rightString = rightParentheses ? expression.substring(i+2, j-1) : expression.substring(i+1, j);

            Token leftToken = leftParentheses ? new ParaToken(parse(leftString)) : parse(leftString);
            Token rightToken = rightParentheses ? new ParaToken(parse(rightString)) : parse(rightString);

            // Create appropriate Operator token
            switch (expression.charAt(i)) {
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

    private static int getOperatorIndex(String expression) {
        int precedenceValue = 0;
        int operatorIndex = -1;
        int parenthesisCount = 0;
        boolean isPreviousDigit;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            isPreviousDigit = Character.isDigit(c);

            if ( c == '(' ) {
                parenthesisCount++;
            } else if ( c == ')' ) {
                parenthesisCount--;
            }

            if (parenthesisCount == 0) {
                if ((c == '+' || (c == '-' && isPreviousDigit) ) && precedenceValue != 1) {
                    precedenceValue = 1;
                    operatorIndex = i;
                } else if ((c == '*' || c == '/') && precedenceValue != 1) {
                    precedenceValue = 2;
                    operatorIndex = i;
                }
            }
        }
        return operatorIndex;
    }

    private static boolean checkParentheses(String expression) {
        return expression.length() > 1 && expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')';
    }

    public abstract double eval();

    @Override
    public abstract String toString();
}
