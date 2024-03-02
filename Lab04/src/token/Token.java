package token;

public abstract class Token {
    public static Token parse(String expression) {
        // Removes parentheses as necessary
        expression = checkParentheses(expression) ? processParentheses(expression) : expression;

        // Find the lowest precedence operator
        int operatorIndex = getOperatorIndex(expression);

        if (operatorIndex != -1) {
            // Split the expression at the operator index
            String leftString = expression.substring(0, operatorIndex);
            String rightString = expression.substring(operatorIndex+1);

            // Check if sub-expressions are wrapped in parentheses and recursively parses further tokens
            Token leftToken = checkParentheses(leftString) ? new ParenToken(parse(leftString)) : parse(leftString);
            Token rightToken = checkParentheses(rightString) ? new ParenToken(parse(rightString)) : parse(rightString);

            // Create appropriate Operator token
            switch (expression.charAt(operatorIndex)) {
                case '+':
                    return new AddToken(leftToken, rightToken);
                case '-':
                    return new SubToken(leftToken, rightToken);
                case '*':
                    return new MulToken(leftToken, rightToken);
                case '/':
                    return new DivToken(leftToken, rightToken);
                case '^':
                    return new ExpToken(leftToken, rightToken);
            }
        }
        // Parse as a number if no operator found.
        return new NumToken(Double.parseDouble(expression));
    }

    private static int getOperatorIndex(String expression) {
        int precedenceValue = 0;
        int operatorIndex = -1;
        int parenthesisCount = 0;
        // Booleans necessary for negative number support: a subtraction will always follow a digit or parentheses.
        boolean isPreviousDigit = false;
        boolean isPreviousClosedParentheses = false;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            boolean notNegativeNumber = isPreviousClosedParentheses || isPreviousDigit;
            if (c =='(') { parenthesisCount++ ; }
            if (parenthesisCount == 0) {
                if (c == '^') {
                    if (precedenceValue == 0) {
                        operatorIndex = i;
                    }
                } else if (c == '*' || c == '/') {
                    if (precedenceValue <=1) {
                        precedenceValue = 1;
                        operatorIndex = i;
                    }
                } else if (c == '+' || (c == '-' && notNegativeNumber)) {
                    precedenceValue = 2;
                    operatorIndex = i;
                }
            }
            if ( c ==')' ) {
                parenthesisCount--;
                isPreviousClosedParentheses = true;
            }
            isPreviousDigit = Character.isDigit(c);
        }
        return operatorIndex;
    }

    private static String processParentheses(String expression) {
        int parenthesesLevel = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                parenthesesLevel++;
            } else if (c == ')') {
                parenthesesLevel--;
            } else if (parenthesesLevel == 0 && (c == '+' || c == '-' || c == '*' || c == '/' || c == '^')) {
                // case: ( a + b ) + ( c + d ) -> outer parentheses are not removed.
                return expression;
            }
        }
        // cases: ( a + b ) and ( a + b + ( c + d ) ) -> outer parentheses are removed.
        return expression.substring(1,expression.length()-1);
    }

    private static boolean checkParentheses(String s) { return s.startsWith("(") && s.endsWith(")"); }

    public abstract double eval();

    @Override
    public abstract String toString();
}
