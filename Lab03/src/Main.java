import Node.TreeNode;
import java.io.File;
import java.util.Scanner;

public class Main {
    // STRING CONSTANTS
    static final String USER_PROMPT = "Enter a path to map: ";
    static final String USER_QUIT = "quit";
    static final String INVALID_INPUT_ERROR = "\nInvalid input!\n";

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            // USER PROMPT AND INPUT
            System.out.print(USER_PROMPT);
            String rootPath = userInput.nextLine().strip();

            // INPUT VALIDATION
            File rootDirectory = new File(rootPath);
            boolean isDirectory = rootDirectory.isDirectory();
            boolean directoryExists = rootDirectory.exists();

            // INPUT LOGIC
            if (rootPath.equalsIgnoreCase(USER_QUIT)) {
                continueProgram = false;
            } else if (isDirectory && directoryExists) {
                TreeNode rootNode = TreeNode.createNode(rootDirectory);
                System.out.println("\n" + rootNode);
            } else {
                System.out.println(INVALID_INPUT_ERROR);
            }
        }
        userInput.close();
    }
}