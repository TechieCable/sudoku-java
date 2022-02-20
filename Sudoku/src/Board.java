import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;

public class Board {
	Tile[][] tiles = new Tile[9][9];

	public Board() {
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				tiles[r][c] = new Tile();
			}
		}

		generateBoard();
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);

		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Monospaced", Font.BOLD, 60));

				tiles[r][c].paint(g);
			}
		}

	}

	private void generateBoard() {
		// 0,0 upper-left
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				generateDiagonals(r, c);
			}
		}

		// 1,1 center-center
		for (int r = 3; r < 6; r++) {
			for (int c = 3; c < 6; c++) {
				generateDiagonals(r, c);
			}
		}

		// 2,2 lower-right
		for (int r = 6; r < 9; r++) {
			for (int c = 6; c < 9; c++) {
				generateDiagonals(r, c);
			}
		}

		generate(0, 3);
	}

	private void generateDiagonals(int r, int c) {
		if (r < 3) {
			if (c > 3) {
				r++;
				c = 0;
			}
		}

		Value options = new Value();

		options.remove(row(r).has());
		options.remove(col(c).has());
		options.remove(square(r, c).has());

		if (options.size() == 0) {
			System.err.println("error: no options");
			return;
		}

		tiles[r][c].value = options.get();
	}

	private boolean generate(int r, int c) {
		if (c >= 9 && r < 8) { // end of row, reset to next row
			r++;
			c = 0;
		}
		if (r >= 9 && c >= 9) { // end of puzzle
			return true;
		}

		if (r < 3) {
			if (c < 3) { // inside first square, bump right
				c = 3;
			}
		} else if (r < 6) {
			if (c == (int) (r / 3) * 3) { // inside middle square, bump right
				c += 3;
			}
		} else {
			if (c == 6) { // inside bottom-right square, reset to next row
				r++;
				c = 0;
				if (r >= 9) // end of puzzle
					return true;
			}
		}

		// find all available options for the current tile
		Value options = new Value();

		options.remove(row(r).has());
		options.remove(col(c).has());
		options.remove(square(r, c).has());

		for (int i = 1; i <= 9; i++) {
			if (options.contains(i)) {
				tiles[r][c].value = i;
				if (generate(r, c + 1))
					return true;

				tiles[r][c].value = 0;
			}
		}
		return false;
	}

	public boolean isFull() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (tiles[r][c].value == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public Section row(int n) {
		return new Section(tiles[n]);
	}

	public Section col(int n) {
		Tile[] res = new Tile[9];
		for (int r = 0; r < tiles[0].length; r++) {
			res[r] = tiles[r][n];
		}
		return new Section(res);
	}

	public Section square(int row, int col) {
		row /= 3;
		col /= 3;
		Tile[] res = new Tile[9];
		int x = 0;
		for (int r = row * 3; r < (row + 1) * 3; r++) {
			for (int c = col * 3; c < (col + 1) * 3; c++, x++) {
				res[x] = tiles[r][c];
			}
		}
		return new Section(res);
	}
}
