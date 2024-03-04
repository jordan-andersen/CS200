import java.util.Scanner;

public class Main {
    // GAME CONSTANTS
    public static final int GAME_WIDTH = 12;
    public static final int GAME_HEIGHT = 12;
    public static final int CONTESTANT_X = 2;
    public static final int CONTESTANT_Y = 2;
    public static final int PRIZE_X = 10;
    public static final int PRIZE_Y = 10;
    public static final int TORTOISE_SPEED = 1;
    public static final int HARE_SPEED = 2;

    // STRING CONSTANTS
    private static final String USER_PROMPT = "Press ENTER to continue...";
    private static final String WIN_PROMPT = "The winner is: ";

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        PlayingField game = new PlayingField(GAME_WIDTH, GAME_HEIGHT);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println(game);
            System.out.println(USER_PROMPT);
            game.stepGame();
            userInput.nextLine();
            continueProgram = !game.checkWin();
        }

        System.out.println(WIN_PROMPT + game.getWinner());
    }
}
