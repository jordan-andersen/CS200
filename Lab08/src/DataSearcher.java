import java.util.List;

public class DataSearcher {
    private static final String ECLIPSE_NOT_FOUND = "\nThe total eclipse was not visible.\n";
    List<PathNode> pathNodes;

    public DataSearcher(List<PathNode> pathNodes) { this.pathNodes = pathNodes; }

    public String searchData(double latitude, double longitude) {
        StringBuilder output = new StringBuilder();
        boolean eclipseFound = false;
        for (PathNode pathNode : pathNodes) {
            if (pathNode.checkCoordinate(latitude, longitude)) {
                output.append(pathNode).append("\n");
                eclipseFound = true;
            }
        }
        return eclipseFound ? output.toString() : ECLIPSE_NOT_FOUND;
    }
}
