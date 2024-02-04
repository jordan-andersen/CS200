import java.util.ArrayList;
import java.util.List;

public class Game {
    /**
     *  A game holds the various data about the game state of a mastermind guessing game. Provides functions
     *  that control the game state.
     *
     */

    // ATTRIBUTES
    private final int game_limit;
    private final int passcode_length;
    private int game_round;
    private boolean game_end;
    private boolean correct_guess;
    private final List<Passcode> guess_history;
    private final List<int[]> guess_results;
    private final Passcode game_passcode;

    // METHODS
    public Game(int passed_game_limit, int passed_passcode_length) {
        this.passcode_length = passed_passcode_length;
        this.game_limit = passed_game_limit - 1;
        this.game_round = 0;
        this.game_end = false;
        this.correct_guess = false;
        this.guess_history = new ArrayList<>();
        this.guess_results = new ArrayList<>();
        this.game_passcode = Passcode.createGamePasscode(passed_passcode_length);
    }

    public void inputUserGuess(String[] guess_array){
        // Create guess passcode, compares the guess passcode against the game passcode and store comparison results.
        Passcode guess_passcode = Passcode.createGuessPasscode(this.passcode_length, guess_array);
        int[] comparison_result = this.game_passcode.comparePasscode(guess_passcode);

        // Adds guess passcode and the comparison results to their respective lists.
        guess_history.add(guess_passcode);
        guess_results.add(comparison_result);

        // Check for correct guess.
        if ( comparison_result[0] == this.passcode_length ) {
            this.game_end = true;
            this.correct_guess = true;
        }

        // Check for game limit.
        if ( game_round == game_limit ) {
            this.game_end = true;
        }

        this.game_round = this.game_round + 1;
    }

    /**
     * Returns string detailing the current game state in the following format:
     * Guesses so far: Current Round
     * [Guess passcode] [Comparison result]
     * ...
     * Guesses remaining: Game Limit - Current Round
     */
    public String getGameStatus(){
        StringBuilder game_status = new StringBuilder();

        if ( this.game_round == 0 ) {
            game_status.append("\nWelcome to Mastermind!\n");
            game_status.append("\nColors: red, yellow, green, blue\n");
        } else {
            game_status.append(" Guesses so far: ").append(this.game_round).append("\n\n");

            for (int i = 0; i < this.game_round; i++ ) {
                game_status.append(this.guess_history.get(i).toString())
                        .append("(")
                        .append(this.guess_results.get(i)[0])
                        .append(" correct color & place, ")
                        .append(this.guess_results.get(i)[1])
                        .append(" correct color only)\n");
            }

            game_status.append("\n Guesses remaining: ").append(this.game_limit - this.game_round + 1).append("\n");
        }

        return game_status.toString();
    }

    /**
     * Returns a guess result message to main based on 1 of 3 possible game state.
     * 1. User guessed correctly and won.
     * 2. User guessed incorrectly and ran out of guesses.
     * 3. User guessed incorrectly and still has guesses.
     */
    public String getResultsMessages(){
        if ( correct_guess ) {
            return "\nYOU WIN!! The passcode was: " + this.game_passcode.toString() + "\n";
        } else if ( game_end ) {
            return "\nYOU LOSE!! The passcode was: " + this.game_passcode.toString() + "\n";
        } else {
            return "\nIncorrect! Try again!\n";
        }
    }

    public boolean checkGameEnd() { return game_end; }
}
