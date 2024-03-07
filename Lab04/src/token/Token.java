package token;

public abstract class Token {
    public static boolean DEBUG_MODE = false;
    public static boolean VERBOSE_MODE = false;

    public static Token parse(String expression) {
        // Processes parentheses as necessary
        if (checkParentheses(expression)) {
            return new ParenthesesToken(parse(processParentheses(expression)));
        }
        // Find the lowest precedence operator
        int operatorIndex = getOperatorIndex(expression);

        if (operatorIndex != -1) {
            // Split the expression at the operator index
            String leftSide = expression.substring(0, operatorIndex);
            String rightSide = expression.substring(operatorIndex+1);

            if (DEBUG_MODE) {
                System.out.println(leftSide + " | " + rightSide);
            }

            // Recursively parses further tokens
            Token leftToken = parse(leftSide);
            Token rightToken = parse(rightSide);

            // Create appropriate Operator token
            switch (expression.charAt(operatorIndex)) {
                case '+':
                    return new AdditionToken(leftToken, rightToken);
                case '-':
                    return new SubtractionToken(leftToken, rightToken);
                case '*':
                    return new MultiplicationToken(leftToken, rightToken);
                case '/':
                    return new DivisionToken(leftToken, rightToken);
                case '^':
                    return new ExponentiationToken(leftToken, rightToken);
            }
        }
        // Parse as a number if no operator found.
        return new NumberToken(Double.parseDouble(expression));
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

            if (c =='(') {
                parenthesisCount++ ;
            }

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

    private static boolean checkParentheses(String expression) {
        if (!(expression.contains("(") || expression.contains(")"))) {
            return false;
        } else {
            return !expression.equals(processParentheses(expression));
        }
    }

    public static void setDebugMode(boolean b) { DEBUG_MODE = b; }

    public static void setVerboseMode(boolean b) { VERBOSE_MODE = b; }

    public abstract double eval();

    @Override
    public abstract String toString();
}
