import java.io.File;

public class TreeNode {
    // ATTRIBUTES
    protected final String name;
    protected final TreeNode parent;


    // METHODS
    protected TreeNode(File path, TreeNode parent) {
        this.name = path.getName();
        this.parent = parent;
    }

    public static TreeNode createNode(File path) {
        return new FolderNode(path, null);
    }

    public static TreeNode createNode(File path, TreeNode parent) {
        TreeNode createdNode;
        if ( path.isDirectory() ) {
            createdNode = new FolderNode(path, parent);
        } else {
            createdNode = new TreeNode(path, parent);
        }
        return createdNode;
    }

    public int getDepth() {
        if (parent == null) {
            return 0;
        }
        return 1 + parent.getDepth();
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        final String depthIndent = "|   ";
        final String fileIndicator = "- ";
        return depthIndent.repeat(Math.max(0, this.getDepth())) +
                fileIndicator + name + "\n";
    }
}
