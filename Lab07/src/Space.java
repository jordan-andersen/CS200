public class Space {
    // ATTRIBUTES
    private final int index;
    private int count;
    private Space next;

    // METHODS

    public Space(int index, int count, Space next) {
        this.index = index;
        this.count = count;
        this.next = next;
    }
    public void setNext(Space s) { next = s; }

    public Space getNext() { return next; }

    public int getCount() { return count; }

    public void incrementCount() { count++; }

    public void removeCount() { count = 0; }

    public int getIndex() { return index; }


    public String toString() { return " [" + count + "] "; }
}
