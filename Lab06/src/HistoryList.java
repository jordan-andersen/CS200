public class HistoryList<E> {
    private HistoryNode<E> headNode;

    public HistoryList() {
        this.headNode = null;
    }

    public void push(E element) {
        headNode = new HistoryNode<>(element, headNode);
    }

    public E pop() {
        E removedElement = null;
        if (headNode != null) {
            removedElement = headNode.getElement();
            headNode = headNode.removeNode();
        }
        return removedElement;
    }

    public boolean isEmpty() {
        return headNode == null;
    }
}
