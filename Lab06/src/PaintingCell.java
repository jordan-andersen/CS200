// PROVIDED BY PROFESSOR OBETZ, MODIFIED BY JORDAN ANDERSEN
import java.util.*;
import static java.util.Map.entry;

/**
 *  A painting cell holds data about one individual "pixel" of an ASCII image. A painting cell has two values:
 *  a color code as a string, and a boolean that represents whether the cell should be filled in.
 *
 * @author Prof. Matthew Obetz
 * @version 1.0
 */
public class PaintingCell {

    /**
     * Convenience map of ASCII color codes. This is used to look up the terminal code for a color given its name.
     */
    private static final Map<String, String> colors = Map.ofEntries(
            entry("red", "\u001B[31m"),
            entry("yellow", "\u001B[33m"),
            entry("green", "\u001B[32m"),
            entry("cyan", "\u001B[36m"),
            entry("blue", "\u001B[34m"),
            entry("purple", "\u001B[35m"),
            entry("black","\u001B[30m"),
            entry("white", "\u001B[37m")
            );

    /**
     * The reset color code. This special code reverts the cursor back to the default color.
     */
    private static final String RESET = "\u001B[0m";


    /**
     * This variable stores the color for the pixel. Set only by the constructor and the pixel.changeColor() method.
     */
    private String color_code;

    /**
     * This variable stores whether a pixel should be displayed or not when a cell is printed. True = will be displayed.
     */
    private boolean visibility;

    /**
     *  Constructor to create new PaintingCell.*
     *  Example Usage:
     *  <pre>
     *      PaintingCell new_cell = new PaintingCell();
     *  </pre>
     *
     *  This snippet will create a new empty cell.
     *
     */
    public PaintingCell() {
        this.visibility = false;
        this.changeColor("black");
    }

    /**
     * This method can be used to change the color of an already existing cell.
     * Note: if the user provides an invalid color, the cell will be defaulted to invisible.*
     * Example Usage:
     * <pre>
     *      PaintingCell new_cell = new PaintingCell();
     *      new_cell.changeColor("red");
     * </pre>
     *
     * This code snippet takes a cell that was previously blue, and changes the color of that cell to red.
     *
     * @param new_color the name of the new color that should be used for a cell.
     * @return true if the color was successfully set. False if the user provided an invalid color.
     */
    public boolean changeColor(String new_color) {
        new_color = new_color.toLowerCase();
        boolean valid_choice = colorExists(new_color);
        this.color_code = colors.getOrDefault(new_color, "");

        // the cell should be visible only if it was previously set to visible AND if it has a valid color.
        this.visibility = this.visibility && valid_choice;

        return valid_choice;
    }

    public void setColor(String color_code) { this.color_code = color_code; }
    public String getColor() { return this.color_code; }

    /**
     * Change the visibility of this cell when it is printed.*
     * Example Usage:
     * <pre>
     *      PaintingCell new_cell = new PaintingCell();
     *      new_cell.setVisibility(false);
     * </pre>
     *
     * This code snippet takes a cell that could previously be seen, and 'erases' that cell from the image.
     *
     * @param is_visible if true, cell will display a colored *. If false, only blank space will be displayed.
     */
    public void setVisibility(boolean is_visible) {
        this.visibility = is_visible;
    }
    public boolean getVisibility() { return this.visibility; }

    /**
     * Utility function to check whether a color_name exists. Can be used to validate user input before calling
     * pixel.changeColor().*
     * Example Usage:
     * <pre>
     *     String color_name = "lorem_ipsum";
     *      if ( !PaintingCell.colorExists(color_name) ) {
     *          System.out.println("That's not a valid color!");
     *      }
     * </pre>
     *
     * This code snippet checks whether the color name stored in color_name is valid, and prints a warning message if
     * it is not.
     *
     * @param color_name the name of the color to check for.
     * @return true if color does exist, otherwise false.
     */
    public static boolean colorExists(String color_name) {
        return colors.containsKey(color_name.toLowerCase());
    }

    public static String getColorCode(String color_name) {
        return colorExists(color_name) ? colors.get(color_name) : "";
    }

    /**
     * Returns a valid random color from the list of colors.*
     * Example Usage:
     * <pre>
     *     String color_name = PaintingCell.getRandomColor();
     *     PaintingCell new_cell = new PaintingCell();
     *     new_cell.changeColor(color_name);
     * </pre>
     *
     * This code snippet creates a newly visible cell of a random color.
     *
     * @return String representing the color name
     */
    public static String getRandomColor() {
        List<String> color_list = new ArrayList<>(colors.keySet());
        Collections.shuffle(color_list);
        return color_list.get(0);
    }

    /**
     * Overrides the default behavior when this cell is coerced into a string.
     * Example Usage:
     * <pre>
     *     PaintingCell new_cell = new PaintingCell();
     *     new_cell.changeColor("blue");
     *     System.out.print(new_cell);
     * </pre>
     *
     * This will cause a blue * to be printed to the console.
     */
    @Override
    public String toString() {
        return ( this.visibility ) ? this.color_code + "* " + RESET : "  ";
    }
}
