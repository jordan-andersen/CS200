import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.print("Enter a path to map: ");
            String path = userInput.nextLine().strip();

            if (path.equalsIgnoreCase("quit")) {
                continueProgram = false;
            } else {
                File parentDirectory = new File(path);
                TreeNode parentNode = new TreeNode(parentDirectory);
                System.out.println("\n" + parentNode);
            }
        }
    }
}