public class Goal extends Space {
    private final Player owner;

    public Goal(Space next, Player owner) {
        super(0, 0, next);
        this.owner = owner;
    }

    public Player getOwner() { return owner;}
}
