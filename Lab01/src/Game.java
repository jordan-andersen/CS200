import java.util.ArrayList;
import java.util.List;

public class Game {
    /**
     *  A game holds the various data about the game state of a mastermind guessing game. Provides functions
     *  that control the game state.
     *
     */

    // ATTRIBUTES
    private final int gameLimit;
    private final int passcodeLength;
    private int gameRound;
    private boolean continueGame;
    private boolean correctGuess;
    private final List<Passcode> guessList;
    private final List<int[]> resultsList;
    private final Passcode gamePasscode;

    // METHODS
    public Game(int givenLimit, int givenLength) {
        this.passcodeLength = givenLength;
        this.gameLimit = givenLimit - 1;
        this.gameRound = 0;
        this.continueGame = true;
        this.correctGuess = false;
        this.guessList = new ArrayList<>();
        this.resultsList = new ArrayList<>();
        this.gamePasscode = Passcode.createGamePasscode(givenLength);
    }

    public void inputUserGuess(String[] guessArray){
        // Create guess passcode, compares the guess passcode against the game passcode and store comparison results.
        Passcode guessPasscode = Passcode.createGuessPasscode(this.passcodeLength, guessArray);
        int[] comparisonResult = this.gamePasscode.comparePasscode(guessPasscode);

        // Adds guess passcode and the comparison results to their respective lists.
        guessList.add(guessPasscode);
        resultsList.add(comparisonResult);

        // Check for correct guess.
        if ( comparisonResult[0] == this.passcodeLength) {
            this.continueGame = false;
            this.correctGuess = true;
        }

        // Check for game limit.
        if ( gameRound == gameLimit) {
            this.continueGame = false;
        }

        this.gameRound += 1;
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

        if ( this.gameRound == 0 ) {
            gameStatus.append("\nWelcome to Mastermind!\n");
            gameStatus.append("\nColors: red, yellow, green, blue\n");
        } else {
            gameStatus.append(" Guesses so far: ").append(this.gameRound).append("\n\n");

            for (int i = 0; i < this.gameRound; i++ ) {
                gameStatus.append(this.guessList.get(i).toString())
                        .append("(")
                        .append(this.resultsList.get(i)[0])
                        .append(" correct color & place, ")
                        .append(this.resultsList.get(i)[1])
                        .append(" correct color only)\n");
            }

            gameStatus.append("\n Guesses remaining: ").append(this.gameLimit - this.gameRound + 1).append("\n");
        }

        return gameStatus.toString();
    }

    /**
     * Returns a guess result message to main based on 1 of 3 possible game state.
     * 1. User guessed correctly and won.
     * 2. User guessed incorrectly and ran out of guesses.
     * 3. User guessed incorrectly and still has guesses.
     */
    public String getResultsMessages(){
        if (correctGuess) {
            return "\nYOU WIN!! The passcode was: " + this.gamePasscode.toString() + "\n";
        }
        if ( !continueGame) {
            return "\nYOU LOSE!! The passcode was: " + this.gamePasscode.toString() + "\n";
        }
        return "\nIncorrect! Try again!\n";
    }

    public boolean checkGameEnd() { return continueGame; }
}
