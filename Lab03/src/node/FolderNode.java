package node;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class FolderNode extends TreeNode {
    // STRING CONSTANTS
    protected static final String FOLDER_INDICATOR = "+ ";

    // CHILDREN COMPARATOR
    protected static final Comparator<TreeNode> NODE_COMPARATOR = (a, b) -> {
        boolean aIsFolder = a instanceof FolderNode;
        boolean bIsFolder = b instanceof FolderNode;
        if (aIsFolder && !bIsFolder) {
            return -1;
        } else if (!aIsFolder && bIsFolder) {
            return 1;
        } else {
            return a.name.compareTo(b.name);
        }
    };

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
            children.sort(NODE_COMPARATOR);
        }
    }

    @Override
    public String toString() {
        String nodeString = DEPTH_INDENT.repeat(getDepth()) + FOLDER_INDICATOR + name + "\n";
        for (TreeNode child : children) { nodeString += child.toString(); }
        return nodeString;
    }
}
