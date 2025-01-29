import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  A game contains data about the game state of a mastermind guessing game. Provides functions
 *  that control the game state and the game rounds.
 */
public class Game {

    // CONSTANTS
    private static final String START_MSG = """
            Welcome to Mastermind!

            Colors: red (R), yellow (Y), green (G), blue (B)
            Example: RRGY
            """;
    private static final String ROUND_PROMPT = "Enter a 4 color passcode: ";
    private static final String INVALID_MSG = "\nInvalid input!\n";
    private static final String EXIT_COMMAND = "Q";
    private static final int GUESS_LIMIT = 7;           // constant that controls game duration
    private static final int PASSCODE_LENGTH = 4;       // constant that controls passcode length

    // ATTRIBUTES
    private int roundCounter;
    private boolean stopGame;
    private boolean correctGuess;
    private final List<Passcode> guessList;
    private final List<int[]> resultsList;
    private final Passcode referencePasscode;

    // METHODS
    public Game() {
        this.roundCounter = 0;
        this.stopGame = false;
        this.correctGuess = false;
        this.guessList = new ArrayList<>();
        this.resultsList = new ArrayList<>();
        this.referencePasscode = Passcode.createGamePasscode(PASSCODE_LENGTH);
    }

    public void play() {
        Scanner userInput = new Scanner(System.in);
        System.out.println(START_MSG);

        while (!stopGame) {
            boolean invalidInput = false;

            System.out.print(ROUND_PROMPT);
            String[] userGuess = userInput.nextLine().toUpperCase().split("");

            if (userGuess[0].equalsIgnoreCase(EXIT_COMMAND)) {
                break;
            }

            for (String color : userGuess) {
                invalidInput = invalidInput | !GuessCell.checkColorValidity(color);
            }

            if (!invalidInput) {
                processGuess(userGuess);
                System.out.println(getGameStatus());
            } else {
                System.out.println(INVALID_MSG);
            }
        }
        System.out.println("Goodbye!");
        userInput.close();
    }
    public void processGuess(String[] guessArray){
        // Create guess passcode, compares the guess passcode against the reference passcode and store results.
        Passcode guessPasscode = Passcode.createGuessPasscode(PASSCODE_LENGTH, guessArray);
        int[] comparisonResult = referencePasscode.comparePasscode(guessPasscode);

        // Update game history
        guessList.add(guessPasscode);
        resultsList.add(comparisonResult);

        // Check for end conditions.
        stopGame = comparisonResult[0] == PASSCODE_LENGTH | roundCounter == GUESS_LIMIT;
        correctGuess = comparisonResult[0] == PASSCODE_LENGTH;

        roundCounter++;
    }

    /**
     * Returns string detailing the current game state in the following format:
     * Guesses so far: Current Round
     * [Guess passcode] [Comparison result]
     * ...
     * Guesses remaining: Game Limit - Current Round
     */
    public String getGameStatus(){
        StringBuilder gameStatus = new StringBuilder();

        if (!stopGame){
            gameStatus.append("\nIncorrect! Try again!\n")
                    .append("\n Guesses so far: ").append(roundCounter).append("\n\n");

            for (int i = 0; i < roundCounter; i++ ) {
                gameStatus.append(guessList.get(i).toString())
                        .append("(")
                        .append(resultsList.get(i)[0])
                        .append(" correct color & place, ")
                        .append(resultsList.get(i)[1])
                        .append(" correct color only)\n");
            }

            gameStatus.append("\n Guesses remaining: ").append(GUESS_LIMIT - roundCounter).append("\n");
        } else {
            if (correctGuess) {
                gameStatus.append("\nYOU WIN!! The passcode was: ").append(referencePasscode.toString()).append("\n");
            } else {
                gameStatus.append("\nYOU LOSE!! The passcode was: ").append(referencePasscode.toString()).append("\n");
            }
        }

        return gameStatus.toString();
    }
}
