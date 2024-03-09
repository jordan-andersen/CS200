public record Coordinate(int x, int y) {
    public Coordinate getOffset(Coordinate compare) {
        return new Coordinate(compare.x - x, compare.y - y);
    }
}
