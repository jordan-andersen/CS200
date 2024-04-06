public class Player {
    // ATTRIBUTES
    private final String name;
    private Player next;
    private Goal goal;

    // METHODS
    public Player(String name, Player next) {
        this.name = name;
        this.next = next;
        this.goal = null;
    }

    public String getName() { return name; }

    public void setNext(Player p) { next = p; }

    public Player getNext() { return next; }

    public void setGoal(Space s) { goal = (Goal) s;}

    public Goal getGoal() { return goal; }

    public String toString() {
        return String.valueOf(goal.getCount());
    }
}
