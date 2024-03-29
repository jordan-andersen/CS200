public class HistoryNode<T> {
    private final T element;
    private HistoryNode<T> nextNode;

    public HistoryNode(T element, HistoryNode<T> nextNode) {
        this.element = element;
        this.nextNode = nextNode;
    }

    public T getElement() { return element; }

    public HistoryNode<T> removeNode() {
        HistoryNode<T> newHeadNode = nextNode;
        nextNode = null;
        return newHeadNode;
    }
}
