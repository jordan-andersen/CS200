/**
 * @param x ATTRIBUTES
 */
public record Coordinate(int x, int y) {
    // METHODS

    public Coordinate getOffset(Coordinate compare) {
        return new Coordinate(compare.x - x, compare.y - y);
    }
}
