package Node;

import java.io.File;

public class TreeNode {
    // PROGRAM FORMAT CONSTANTS
    static final String DEPTH_INDENT = "|   ";
    static final String FILE_INDICATOR = "- ";

    // ATTRIBUTES
    protected final String name;
    protected final TreeNode parent;

    // METHODS
    protected TreeNode(File path, TreeNode parent) {
        this.name = path.getName();
        if (parent == null) {
            this.parent = this;
        } else {
            this.parent = parent;
        }
    }

    // Factory method, creates the Root Folder-node.
    public static TreeNode createNode(File path) {
        return new FolderNode(path, null);
    }

    // Factory method, determines whether to create a Folder-node, for a directory, or a Tree-node, for a file.
    public static TreeNode createNode(File path, TreeNode parent) {
        if ( path.isDirectory() ) { return new FolderNode(path, parent); }
        return new TreeNode(path, parent);
    }

    // Recursive function that calculates node depth.
    public int getDepth() {
        if (parent == this) { return 0; }
        return 1 + parent.getDepth();
    }

    // Getter for comparator sort function.
    public String getName() { return name; }

    @Override
    public String toString() {
        return DEPTH_INDENT.repeat(Math.max(0, this.getDepth())) + FILE_INDICATOR + name + "\n";
    }
}
