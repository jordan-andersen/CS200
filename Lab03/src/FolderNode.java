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
        File[] childFiles = path.listFiles();
        if (childFiles != null ) {
            for (File child : childFiles) {
                if (child != null) { children.add(TreeNode.createNode(child, this)); }
            }
            children.sort(Comparator.comparing(TreeNode::getName));
        }
    }

    @Override
    public String toString() {
        String nodeString = TreeNode.DEPTH_INDENT.repeat(Math.max(0, this.getDepth()))
                + TreeNode.DIR_INDICATOR + name + "\n";
        for (TreeNode child : children) { nodeString += child.toString(); }
        return nodeString;
    }
}
