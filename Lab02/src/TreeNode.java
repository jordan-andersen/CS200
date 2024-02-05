import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class TreeNode {
    // ATTRIBUTES
    private final String name;
    private final boolean isDirectory;
    private final int depth;
    private final List<TreeNode> children;

    // METHODS

    public TreeNode(File path) {
        this(path,0);
    }

    public TreeNode(File path, int depth) {
        this.name = path.getName();
        this.isDirectory = path.isDirectory();
        this.depth = depth;
        this.children = new ArrayList<>();
        if (path.isDirectory()) {
            for (File child : path.listFiles() ) {
                if (child != null) {
                    this.children.add(new TreeNode(child, depth+1));
                }
            }
        }
        this.children.sort(Comparator.comparing(TreeNode::getName));
    }

    public String getName() { return this.name; }

    @Override
    public String toString() {
        StringBuilder nodeString = new StringBuilder();
        nodeString.append("    ".repeat(Math.max(0, this.depth))).append(this.name).append("\n");
        if (isDirectory) {
            for (TreeNode child : this.children) {
                nodeString.append(child.toString());
            }
        }
        return nodeString.toString();

    }

}
