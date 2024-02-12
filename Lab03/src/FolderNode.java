import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class FolderNode extends TreeNode {
    // ATTRIBUTES
    private final List<TreeNode> children;

    // METHODS

    protected FolderNode(File path, TreeNode parent) {
        super(path, parent);
        this.children = new ArrayList<>();
        for (File child : path.listFiles() ) {
            if (child != null) {
                children.add(TreeNode.createNode(child, this));
            }
        }
        children.sort(Comparator.comparing(TreeNode::getName));
    }

    @Override
    public String toString() {
        final String depthIndent = "|   ";
        final String dirIndicator = "+ ";
        String nodeString = depthIndent.repeat(Math.max(0, this.getDepth())) +
                dirIndicator + name + "\n";
        for (TreeNode child : children) {
            nodeString += child.toString();
        }
        return nodeString;
    }
}
