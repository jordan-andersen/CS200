import java.io.File;

public class TreeNode {
    // ATTRIBUTES
    protected final String name;
    protected final TreeNode parent;

    // METHODS
    protected TreeNode(File path, TreeNode parent) {
        this.name = path.getName();
        if (parent == null) { this.parent = this; }
        else { this.parent = parent; }
    }

    // Constructor, used to create the Root Folder-node.
    public static TreeNode createNode(File path) {
        return new FolderNode(path, null);
    }

    // Constructor, determines whether to create a Folder-node, for a directory, or a Tree-node, for a file.
    public static TreeNode createNode(File path, TreeNode parent) {
        if ( path.isDirectory() ) { return new FolderNode(path, parent); }
        return new TreeNode(path, parent);
    }

    // Recursive function that calculates depth.
    public int getDepth() {
        if (parent == this) { return 0; }
        return 1 + parent.getDepth();
    }

    // Getter for comparator sort function.
    public String getName() { return name; }

    @Override
    public String toString() {
        return Main.DEPTH_INDENT.repeat(Math.max(0, this.getDepth())) + Main.FILE_INDICATOR + name + "\n";
    }
}
