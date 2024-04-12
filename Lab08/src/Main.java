import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String WELCOME_MSG = "Welcome to the 2024 Total Solar Eclipse checker!";
    private static final String FILE_PROMPT = "\nInput data file location: ";
    private static final String LAT_PROMPT = "\nInput latitude: ";
    private static final String LONG_PROMPT = "Input longitude: ";
    private static final String USER_QUIT = "quit";
    private static final String QUIT_MSG = "\nExiting...";

    public static void main(String[] args) {
        List<PathNode> pathNodes = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        System.out.println(WELCOME_MSG);

        boolean validFile = false;
        while (!validFile) {
            System.out.println(FILE_PROMPT);
            String filePath = userInput.nextLine().trim();
            pathNodes = DataParser.parseFile(filePath);
            if (!pathNodes.isEmpty()) {
                validFile = true;
            }
        }

        DataSearcher searcher = new DataSearcher(pathNodes);
        boolean continueProgram = true;
        while (continueProgram) {
            System.out.println(LAT_PROMPT);
            String latitudeInput = userInput.nextLine();
            if ( latitudeInput.equalsIgnoreCase(USER_QUIT)) {
                continueProgram = false;
                continue;
            }

            System.out.println(LONG_PROMPT);
            String longitudeInput = userInput.nextLine();
            if (longitudeInput.equalsIgnoreCase(USER_QUIT)) {
                continueProgram = false;
                continue;
            }

            double latitude = Coordinate.convertCoordinate(latitudeInput);
            double longitude = Coordinate.convertCoordinate(longitudeInput);
            if (latitude != 0.0 && longitude != 0.0) {
                System.out.println(searcher.searchData(latitude, longitude));
            }
        }

        System.out.println(QUIT_MSG);
        userInput.close();
    }
}
