import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            // USER PROMPT AND INPUT
            System.out.print("Enter a path to map: ");
            String path = userInput.nextLine().strip();

            // INPUT VALIDATION
            File rootDirectory = new File(path);
            boolean isDirectory = rootDirectory.isDirectory();
            boolean directoryExists = rootDirectory.exists();

            // INPUT LOGIC
            if (path.equalsIgnoreCase("quit")) {
                continueProgram = false;
            } else if (isDirectory && directoryExists) {
                TreeNode parentNode = TreeNode.createNode(rootDirectory);
                System.out.println("\n" + parentNode);
            } else {
                System.out.println("\nInvalid input\n");
            }
        }
        userInput.close();
    }
}