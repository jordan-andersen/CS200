package node;

import java.io.File;

public class TreeNode {
    // STRING CONSTANTS
    protected static final String DEPTH_INDENT = "|   ";
    protected static final String FILE_INDICATOR = "- ";

    // ATTRIBUTES
    protected final String name;
    protected final TreeNode parent;

    // METHODS
    // Constructor, utilizes ternary if-else statement to assign parent value.
    // If passed value is null, assign parent as self, else assign parent to passed value.
    protected TreeNode(File path, TreeNode parent) {
        this.name = path.getName();
        this.parent = parent == null ? this : parent;
    }

    // Factory method, creates the Root Folder-node.
    public static TreeNode createNode(File path) {
        return new FolderNode(path, null);
    }

    // Factory method, utilizes ternary if-else statement to control which type of node is created:
    // If path is a directory, create FolderNode, else create TreeNode.
    public static TreeNode createNode(File path, TreeNode parent) {
        return path.isDirectory() ? new FolderNode(path, parent) : new TreeNode(path, parent);
    }

    // Recursive method that calculates node depth.
    public int getDepth() {
        if (parent == this) { return 0; }
        return 1 + parent.getDepth();
    }

    @Override
    public String toString() {
        return DEPTH_INDENT.repeat(getDepth()) + FILE_INDICATOR + name + "\n";
    }
}
