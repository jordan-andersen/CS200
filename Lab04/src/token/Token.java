package token;

public abstract class Token {
    public static Token parse(String expression) {
        expression = checkParentheses(expression) ? processParentheses(expression) : expression;

        // Find the lowest precedence operator
        int i = getOperatorIndex(expression);
        int j =  expression.length();

        if (i != -1) {
            // Split the expression at the operator index based on if those sub-expressions are bracketed by parentheses
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

    private static String processParentheses(String expression) {
        int j = expression.length();
        int total = 0;
        int count = 0;
        int max = 0;
        for (int i = 0; i < j; i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                count ++;
                total ++;
            }
            if (c == ')') {
                count --;
                total ++;
            }
            if (max < count) {
                max = count;
            }
        }
        if (max == 1 && total > 2) {
            return expression;
        }
        return expression.substring(1, j-1);
    }

    private static boolean checkParentheses(String expression) {
        return expression.startsWith("(") && expression.endsWith(")");
    }

    public abstract double eval();

    @Override
    public abstract String toString();
}
