public class Passcode {
    /**
     *  A passcode holds a number of guess cells and is the primary data of a mastermind guessing game. A
     * passcode can be created either by random selection for the game's reference passcode, or by user input
     *
     */

    // ATTRIBUTES
    private final GuessCell[] cell_array;

    // METHODS
    /** 
     * The constructor for Passcode. This method is private to allow for various creation methods, either by user input
     * or random selection.
     */
    private Passcode(GuessCell[] passed_array) { this.cell_array = passed_array; }


    /** 
     * Creates a randomly generated game reference passcode.
     */
    public static Passcode createGamePasscode(int length) {
        GuessCell[] passcode_array = new GuessCell[length];

        for ( int i = 0; i < length; i++) {
            passcode_array[i] = GuessCell.createRandomCell();
        }

        return new Passcode(passcode_array);
    }

    /** 
     * Creates a user generated guess passcode.
     */
    public static Passcode createGuessPasscode(int length, String[] colors) {
        if (length == colors.length) {
            GuessCell[] passcode_array = new GuessCell[length];

            for (int i = 0; i < colors.length; i++) {
                GuessCell new_guesscell = GuessCell.createCell(colors[i]);
                if (new_guesscell != null) {
                    passcode_array[i] = new_guesscell;
                } else {
                    System.err.println("PASSCODE.createNewPasscode: Invalid color passed!");
                }
            }

            return new Passcode(passcode_array);
        } else {
            System.err.println("PASSCODE.createNewPasscode: Unable to created new passcode!");
            return null;
        }
    }

    /** 
     * Returns a GuessCell object from a specified index.
     */
    public GuessCell getCell(int cell_index) {
        if ( cell_index < this.cell_array.length ) {
            return this.cell_array[cell_index];
        }
        return null;
    }

    /** 
     * Compares a passed Passcode with self and returns a two integer array of the number of correct places and
     * correct colors. Should only be called on the reference passcode.
     */
    public int[] comparePasscode(Passcode comparand_passcode) {
        int correct_place_counter = 0;
        int correct_color_counter = 0;
        boolean[] checked_reference = new boolean[this.cell_array.length];
        boolean[] checked_comparand = new boolean[this.cell_array.length];

        for ( int i = 0; i < this.cell_array.length; i++ ) {
            GuessCell reference_cell = this.cell_array[i];
            GuessCell comparand_cell = comparand_passcode.getCell(i);
            if ( comparand_cell.equals(reference_cell) ) {
                correct_place_counter = correct_place_counter + 1;
                checked_reference[i] = true;
                checked_comparand[i] = true;
            }
        }


        for ( int i = 0; i < this.cell_array.length; i++ ) {
            if ( !checked_comparand[i] ) {
                GuessCell comparand_cell = comparand_passcode.getCell(i);
                for ( int j = 0; j < this.cell_array.length; j++ ) {
                    GuessCell reference_cell = this.cell_array[j];
                    if ( !checked_reference[j] && !checked_comparand[i] && comparand_cell.equals(reference_cell) ) {
                        correct_color_counter = correct_color_counter + 1;
                        checked_reference[j] = true;
                        checked_comparand[i] = true;
                    }
                }
            }
        }
        return new int[]{correct_place_counter, correct_color_counter};
    }

    /** 
     * Converts the passcode to series of colored stars with spaces between each.
     */
    @Override
    public String toString(){
        StringBuilder cell_string = new StringBuilder();

        for (GuessCell cell : this.cell_array) {
            cell_string.append(cell.toString());
            cell_string.append(" ");
        }

        return cell_string.toString();
    }
}
