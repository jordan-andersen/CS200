import token.Token;
import java.util.Scanner;

public class Main {
    // STRING CONSTANTS
    private static final String USER_PROMPT = "Input arithmetic expression to evaluate (or 'quit' to exit): ";
    private static final String USER_QUIT = "quit";
    private static final String GIVEN_STRING = "\nGiven: ";
    private static final String RESULT_STRING = "Result: ";
    private static final String PARENTHESES_ERROR = "Expression contains ')('. " +
            "Please explicitly specify the desired operation.";
    private static final String INVALID_INPUT_ERROR = "\nInvalid input: unable to parse!\n";
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println(USER_PROMPT);
            String expression = userInput.nextLine().replaceAll("\\s+", "");

            if (expression.equalsIgnoreCase(USER_QUIT)) {
                continueProgram = false;
            } else if (expression.contains(")(")){
                System.out.println(PARENTHESES_ERROR);
            } else if (!expression.matches("[0-9+\\-*/()^.]+")) {
                System.out.println(INVALID_INPUT_ERROR);
            } else {
                Token result = Token.parse(expression);
                System.out.println(GIVEN_STRING + result);
                System.out.println(RESULT_STRING + result.eval() + "\n");
            }
        }
    }
}
