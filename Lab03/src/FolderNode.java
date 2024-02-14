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
        File[] child_files = path.listFiles();
        if (child_files != null ) {
            for (File child : child_files) {
                if (child != null) { children.add(TreeNode.createNode(child, this)); }
            }
            children.sort(Comparator.comparing(TreeNode::getName));
        }
    }

    @Override
    public String toString() {
        String nodeString = Main.DEPTH_INDENT.repeat(Math.max(0, this.getDepth())) + Main.DIR_INDICATOR + name + "\n";
        for (TreeNode child : children) { nodeString += child.toString(); }
        return nodeString;
    }
}
