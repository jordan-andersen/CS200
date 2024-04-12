import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataParser {
    private static final String ERROR = "Invalid file input, file parsing has failed:\n";
    public static List<PathNode> parseFile(String filePath) {
        List<PathNode> pathNodes = new ArrayList<>();
        try {
            Path fileLocation = Path.of(filePath);
            List<String> fileLines = Files.readAllLines(fileLocation);
            fileLines.remove(0);  //<- Removes column header.
            for (String line : fileLines) {
                String[] data = line.split(",");
                LocalTime time = LocalTime.parse(data[0].strip());
                Coordinate northCoord = new Coordinate(data[1], data[2]);
                Coordinate southCoord = new Coordinate(data[3], data[4]);
                pathNodes.add(new PathNode(time, northCoord, southCoord));
            }
        } catch (Exception e) {
            System.out.println(ERROR + e.getMessage());
        }
        return pathNodes;
    }
}
