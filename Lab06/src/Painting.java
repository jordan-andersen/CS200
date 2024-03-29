// PROVIDED BY PROFESSOR OBETZ, MODIFIED BY JORDAN ANDERSEN
public class Painting {
	private final int width;
	private final int height;
	private final PaintingCell[][] grid;
	private final HistoryList<PaintingChange> history;

	public Painting(int given_height, int given_width) {
		this.width = given_width;
		this.height = given_height;

		this.grid = new PaintingCell[this.height][this.width];

		for (int r = 0; r < this.height; r++) {
			for (int c = 0; c < this.width; c++) {
				this.grid[r][c] = new PaintingCell();
			}
		}

		this.history = new HistoryList<>();
	}


	public boolean checkBoundaries(int row, int col) {
		boolean is_inside_horizontal = 0 <= col && col < this.width;
		boolean is_inside_vertical = 0 <= row && row < this.height;

		return is_inside_vertical && is_inside_horizontal;
	}


	public void paintCell(int row, int col, String color) {
		if ( checkBoundaries(row, col) ) {
			PaintingCell target_cell = this.grid[row][col];
			if (!PaintingCell.getColorCode(color).equals(target_cell.getColor())) {
				history.push(new PaintingChange(row, col, target_cell.getColor(), target_cell.getVisibility()));
			}
			target_cell.changeColor(color);
			target_cell.setVisibility(true);
		}

	}

	public void eraseCell(int row, int col) {
		if ( checkBoundaries(row, col) ) {
			PaintingCell target_cell = this.grid[row][col];
			history.push(new PaintingChange(row, col, target_cell.getColor(), target_cell.getVisibility()));
			target_cell.setVisibility(false);
		}
	}

	public void undoLast() {
		if (!history.isEmpty()) {
			PaintingChange change = history.pop();
			PaintingCell target_cell = grid[change.getRow()][change.getCol()];
			target_cell.setVisibility(change.getVisibility());
			target_cell.setColor(change.getColor());
		}
	}

    @Override
	public String toString() {
		String full_painting_as_text = "";

		for ( int r=0; r<this.height; r++ ) {
			for ( int c=0; c<this.width; c++ ) {
				full_painting_as_text = full_painting_as_text + this.grid[r][c];
			}
			full_painting_as_text = full_painting_as_text + "\n";
		}

		return full_painting_as_text;

	}
}