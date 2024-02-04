import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        final Scanner user_input = new Scanner(System.in);

        final int game_limit = 7;           // constant that controls game duration
        final int passcode_length = 4;      // constant that controls passcode length
        Game main_game = new Game(game_limit, passcode_length);
        boolean end_game = false;

        while ( !end_game ) {
            // Game prompt and user input
            System.out.println(main_game.getGameStatus());
            System.out.print("Enter a 4 color passcode: ");
            String[] user_guess = user_input.nextLine().toLowerCase().split("\\s+");

            // Check to ensure each user entry matches a valid color
            int valid_inputs = 0;
            for (String color : user_guess) {
                if (GuessCell.checkColorValidity(color)) {
                    valid_inputs = valid_inputs + 1;
                }
            }

            // Ensures user has input the correct number of valid inputs.
            if (valid_inputs == passcode_length) {
                main_game.inputUserGuess(user_guess);
                System.out.println(main_game.getResultsMessages());
                end_game = main_game.checkGameEnd();
            } else {
                // Check for quit command.
                if ( user_guess[0].equalsIgnoreCase("quit") ) {
                    end_game = true;
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("\nInvalid input!\n");
                }
            }
        }
        user_input.close();
    }
}
