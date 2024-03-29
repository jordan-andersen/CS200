import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String QUIT_COMMAND = "quit";
    private static final String PAINT_COMMAND = "paint";
    private static final String ERASE_COMMAND = "erase";
    private static final String UNDO_COMMAND = "undo";
    private static final String HELP_COMMAND = "help";
    private static final String HELP_PROMPT = """
            Displaying HELP prompt...\s
            COMMAND \t ARGUMENTS \t \t \t \t \t DESCRIPTION
            paint \t \t [row] [col] [color] \t \t Paints a cell
            erase \t \t [row] [column] \t \t \t Removes a cell
            undo \t \t \t \t \t \t \t \t \t Reverts previous change
            help \t \t \t \t \t \t \t \t \t Displays this prompt
            quit \t \t \t \t \t \t \t \t \t Quits this program
            """;

    private static final String QUITTING = "QUITTING...";
    private static final String INITIAL_PROMPT = "Please specify painting dimensions: ";
    private static final String INVALID_SIZE = "INVALID PARAMETERS! Input 2 integers separated by a space.";
    private static final String LOOP_PROMPT = "Input command (use 'help' to see available commands):";
    private static final String COMMAND_ERROR = "INVALID COMMAND! Use the 'help' command to see available commands.\n";
    private static final String ARGS_ERROR_EXPECTED = "INVALID NUMBER OF ARGS! Expected ";
    private static final String ARGS_ERROR_FOR = " arguments for command '";
    private static final String UNEXPECTED_ERROR = "UNEXPECTED ERROR!";
    private static final Map<String, Integer> EXPECTED_ARGS = Map.ofEntries(
            Map.entry("paint", 4),
            Map.entry("erase", 3),
            Map.entry("undo", 1),
            Map.entry("quit", 1),
            Map.entry("help", 1)
    );

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int[] size_array = {0, 0};

        boolean validSize = false;
        while (!validSize) {
            System.out.println(INITIAL_PROMPT);
            String[] input_array = userInput.nextLine().replace(",", "").split("\\s+");
            if (input_array.length == 2) {
                validSize = true;
                size_array[0] = Integer.parseInt(input_array[0]);
                size_array[1] = Integer.parseInt(input_array[1]);
            } else {
                System.out.println(INVALID_SIZE);
            }
        }

        Painting painting = new Painting(size_array[0], size_array[1]);

        boolean continueProgram = true;
        while (continueProgram) {
            System.out.println(painting);
            System.out.println(LOOP_PROMPT);
            String[] commandArray = userInput.nextLine().strip().split("\\s+");
            String command = commandArray[0].toLowerCase();
            boolean validCommand = EXPECTED_ARGS.containsKey(command);
            boolean argsCorrect = validCommand && commandArray.length == EXPECTED_ARGS.get(command);

            if (!validCommand) {
                System.out.println(COMMAND_ERROR);
            } else if (!argsCorrect) {
                System.out.println(ARGS_ERROR_EXPECTED + (EXPECTED_ARGS.get(command)-1) + ARGS_ERROR_FOR + command + "'.\n");
            } else if (command.equals(PAINT_COMMAND)) {
                int row = Integer.parseInt(commandArray[1])-1;
                int column = Integer.parseInt(commandArray[2])-1;
                String color = commandArray[3];
                painting.paintCell(row, column, color);
            } else if (command.equals(ERASE_COMMAND)) {
                int row = Integer.parseInt(commandArray[1])-1;
                int column = Integer.parseInt(commandArray[2])-1;
                painting.eraseCell(row, column);
            } else if (command.equals(UNDO_COMMAND)) {
                painting.undoLast();
            } else if (command.equals(HELP_COMMAND)){
                System.out.println(HELP_PROMPT);
            } else if (command.equals(QUIT_COMMAND)) {
                continueProgram = false;
                System.out.println(QUITTING);
            } else {
                System.out.println(UNEXPECTED_ERROR);
            }
        }


    }
}
