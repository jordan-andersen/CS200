import java.util.Scanner;

public class Main {
    private static final String QUIT = "quit";
    private static final String QUIT_PROMPT = "Exiting...";
    private static final String GAME_PROMPT = "\nENTER SLOT 1-12: ";
    private static final String ERROR = "INVALID INPUT!";

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Game game = new Game();
        boolean userQuit = false;
        boolean continueGame = game.gameOver();
        while (continueGame) {
            System.out.println(game);
            System.out.println(game.getStatus());
            boolean validInput = false;
            while (!validInput) {
                System.out.println(GAME_PROMPT);
                String userSelection = userInput.nextLine();
                if (userSelection.equalsIgnoreCase(QUIT)) {
                    validInput = true;
                    userQuit = true;
                } else {
                    try {
                        int userIndex = Integer.parseInt(userSelection);
                        validInput = game.takeTurn(userIndex);

                    } catch (Exception e) {
                        System.out.println(ERROR);
                    }
                }
            }
            game.nextPlayer();
            continueGame = game.gameOver() && !userQuit;
        }
        if (!userQuit) {
            System.out.println(game);
            System.out.println(game.getWinner());
        } else {
            System.out.println(QUIT_PROMPT);
        }

    }
}
