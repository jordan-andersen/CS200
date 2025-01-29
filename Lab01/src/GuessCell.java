import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static java.util.Map.entry;

/**
 *  A guess cell holds data about one 'slot' of a mastermind guessing game. A
 * guess cell can be created for one of four colors: red, yellow, green, or blue.
 * 
 * @author Prof. Matthew Obetz
 * @version 1.0
 */
public class GuessCell {

    /**
     * Convenience map of ASCII color codes. This is used to look up the terminal code for a color given its name.
     */
    private static final Map<String, String> colors = Map.ofEntries(
            entry("R", "\u001B[31m"),
            entry("Y", "\u001B[33m"),
            entry("G", "\u001B[32m"),
            entry("B", "\u001B[34m")
    );
    
    private static final String RESET =  "\u001B[0m"; 
    private final String color;
    
    /**
    * The constructor for GuessCell. This method is private so that we can
    * validate that guesses always use a valid color. Use GuessCell.createCell(color)
    * or GuessCell.createRandomCell() instead.
     */
    private GuessCell(String givenColor) {
        this.color = givenColor;
    }
    
    
    /**
     * Creates a new cell of the given color. Valid colors are red, yellow,
     * green, or blue. If an invalid color is provided, will instead return null.
     */
    public static GuessCell createCell(String givenColor) {
        if ( colors.containsKey(givenColor)) {
            return new GuessCell(givenColor);
        } else {
            System.err.println("That's not a valid color!");
            return null;
        }
    }
    
    /**
     * Creates a new cell of a random color. 
     */
    
    public static GuessCell createRandomCell() {
        Set<String> colorNames = colors.keySet();
        List<String> colorList = colorNames.stream().toList();
        int numColors = colorList.size();
        int colorNum = new Random().nextInt(numColors);
        String targetColor = colorList.get(colorNum);
        return new GuessCell(targetColor);
    }
    
    /**
     * Checks whether a color is in the list of valid colors.
     * Returns true when the color is found, otherwise false.
     */
    public static boolean checkColorValidity(String colorName) {
        return colors.containsKey(colorName);
    }
    
    public String getColorName() {
        return this.color;
    }

    /**
     * Two guesses are equal when their color is the same.
     */
    @Override
    public boolean equals(Object o) {
        return o.getClass().equals(this.getClass())
               && ((GuessCell) o).color.equals(this.color);
    }
    
    /**
     * When a guess is printed out, display it as a colorful star.
     */
    @Override
    public String toString() {
        String colorCode = colors.get(color);
        return String.format("%s*%s", colorCode, RESET);
    }
}