import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.print("Enter a path to map: ");
            String path = userInput.nextLine().strip();
            File parentDirectory = new File(path);
            boolean isDirectory = parentDirectory.isDirectory();
            boolean directoryExists = parentDirectory.exists();

            if (path.equalsIgnoreCase("quit")) {
                continueProgram = false;
            } else if (isDirectory && directoryExists) {
                TreeNode parentNode = new TreeNode(parentDirectory);
                System.out.println("\n" + parentNode);
            } else {
                System.out.println("\nInvalid input\n");
            }
        }
        userInput.close();
    }
}