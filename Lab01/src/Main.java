import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        final Scanner userInput = new Scanner(System.in);

        final int gameLimit = 7;           // constant that controls game duration
        final int passcodeLength = 4;      // constant that controls passcode length
        Game mainGame = new Game(gameLimit, passcodeLength);
        boolean continueGame = true;

        while ( continueGame ) {
            // Game prompt and user input
            System.out.println(mainGame.getGameStatus());
            System.out.print("Enter a 4 color passcode: ");
            String[] userGuess = userInput.nextLine().toLowerCase().split("\\s+");

            // Check to ensure each user entry matches a valid color
            int validInputs = 0;
            for (String color : userGuess) {
                if (GuessCell.checkColorValidity(color)) {
                    validInputs += 1;
                }
            }

            // Ensures user has input the correct number of valid inputs.
            if (validInputs == passcodeLength) {
                mainGame.inputUserGuess(userGuess);
                System.out.println(mainGame.getResultsMessages());
                continueGame = mainGame.checkGameEnd();
            } else {
                // Check for quit command.
                if ( userGuess[0].equalsIgnoreCase("quit") ) {
                    continueGame = false;
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("\nInvalid input!\n");
                }
            }
        }
        userInput.close();
    }
}
