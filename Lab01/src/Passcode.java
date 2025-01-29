/**
 *  A passcode holds a number of guess cells and is the primary data of a mastermind guessing game. A
 * passcode can be created either by random selection for the game's reference passcode, or by user input
 *
 */
public class Passcode {

    // ATTRIBUTES
    private final GuessCell[] cellArray;

    // METHODS
    /** 
     * The constructor for Passcode. This method is private to allow for various creation methods, either by user input
     * or random selection.
     */
    private Passcode(GuessCell[] givenArray) { this.cellArray = givenArray; }


    /** 
     * Creates a randomly generated game reference passcode.
     */
    public static Passcode createGamePasscode(int length) {
        GuessCell[] passcodeArray = new GuessCell[length];

        for ( int i = 0; i < length; i++) {
            passcodeArray[i] = GuessCell.createRandomCell();
        }

        return new Passcode(passcodeArray);
    }

    /** 
     * Creates a user generated guess passcode.
     */
    public static Passcode createGuessPasscode(int length, String[] colors) {
        if (length == colors.length) {
            GuessCell[] passcodeArray = new GuessCell[length];

            for (int i = 0; i < colors.length; i++) {
                GuessCell new_guesscell = GuessCell.createCell(colors[i]);
                if (new_guesscell != null) {
                    passcodeArray[i] = new_guesscell;
                } else {
                    System.err.println("PASSCODE.createNewPasscode: Invalid color passed!");
                }
            }

            return new Passcode(passcodeArray);
        } else {
            System.err.println("PASSCODE.createNewPasscode: Unable to created new passcode!");
            return null;
        }
    }

    /** 
     * Returns a GuessCell object from a specified index.
     */
    public GuessCell getCell(int i) {
        if ( i < this.cellArray.length ) {
            return this.cellArray[i];
        }
        return null;
    }

    /** 
     * Compares a passed Passcode with self and returns a two integer array of the number of correct places and
     * correct colors. Should only be called on the reference passcode.
     */
    public int[] comparePasscode(Passcode comparandPasscode) {
        int correctPlaceCounter = 0;
        int correctColorCounter = 0;
        boolean[] checkedReference = new boolean[this.cellArray.length];
        boolean[] checkedComparand = new boolean[this.cellArray.length];

        for (int i = 0; i < this.cellArray.length; i++ ) {
            GuessCell referenceCell = this.cellArray[i];
            GuessCell comparandCell = comparandPasscode.getCell(i);
            if ( comparandCell.equals(referenceCell) ) {
                correctPlaceCounter += 1;
                checkedReference[i] = true;
                checkedComparand[i] = true;
            }
        }


        for (int i = 0; i < this.cellArray.length; i++ ) {
            if ( !checkedComparand[i] ) {
                GuessCell comparandCell = comparandPasscode.getCell(i);
                for (int j = 0; j < this.cellArray.length; j++ ) {
                    GuessCell reference_cell = this.cellArray[j];
                    if ( !checkedReference[j] && !checkedComparand[i] && comparandCell.equals(reference_cell) ) {
                        correctColorCounter += 1;
                        checkedReference[j] = true;
                        checkedComparand[i] = true;
                    }
                }
            }
        }
        return new int[]{correctPlaceCounter, correctColorCounter};
    }

    /** 
     * Converts the passcode to series of colored stars with spaces between each.
     */
    @Override
    public String toString(){
        StringBuilder passcodeString = new StringBuilder();

        for (GuessCell cell : this.cellArray) {
            passcodeString.append(cell.toString());
            passcodeString.append(" ");
        }

        return passcodeString.toString();
    }
}
