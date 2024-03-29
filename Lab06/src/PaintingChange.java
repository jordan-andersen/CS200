public class PaintingChange {
    private final int row;
    private final int col;
    private final String colorCode;
    private final boolean visibility;

    public PaintingChange(int row, int col, String color_code, boolean visibility) {
        this.row = row;
        this.col = col;
        this.colorCode = color_code;
        this.visibility = visibility;
    }

    public int getRow(){ return row; }
    public int getCol(){ return col; }
    public String getColor() {return colorCode; }
    public boolean getVisibility() { return visibility; }
}
