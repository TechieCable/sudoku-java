import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Board {
	Tile[][] tiles = new Tile[9][9];
	private Square[] squares = new Square[9];
	int activeR = 4, activeC = 4;
	private boolean colorsOnly = false;

	public Board() {
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				tiles[r][c] = new Tile();
			}
		}
		for (int i = 0; i < squares.length; i++) {
			squares[i] = new Square();
		}

		generateDiagonals(0, 0);
		generate(0, 3);

		remove(30);
	}

	public void remove(int n) {
		for (int i = 0; i < n; i++) {
			int row = (int) (Math.random() * 9);
			int col = (int) (Math.random() * 9);
			tiles[row][col].value = 0;
			tiles[row][col].setValue = false;
		}
	}

	public void solve() {
		while (!isFull()) {
			for (int r = 0; r < tiles.length; r++) {
				for (int c = 0; c < tiles[r].length; c++) {
					Value options = options(r, c);
					if (options.size() == 0) {
						tiles[r][c].value = options.get();
					}
				}
			}
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);

		highlights();

		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Monospaced", Font.BOLD, 60));

				tiles[r][c].paint(g);
			}
		}

		for (int i = 0; i < squares.length; i++) {
			g.setColor(Color.BLACK);
			squares[i].paint(g);
		}

	}

	public void toggleColors() {
		colorsOnly = !colorsOnly;
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				tiles[r][c].colorsOnly = colorsOnly;
			}
		}
	}

	private void highlights() {
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				tiles[r][c].highlight(false);
				tiles[r][c].active(false);

				if (tiles[r][c].value == tiles[activeR][activeC].value) {
					tiles[r][c].highlight(true);
				}
			}
		}

		tiles[activeR][activeC].active(true);
	}

	public void active(int r, int c) {
		if (r < 0) {
			r = 8;
		} else if (r > 8) {
			r = 0;
		}
		if (c < 0) {
			c = 8;
		} else if (c > 8) {
			c = 0;
		}
		activeR = r;
		activeC = c;
	}

	public boolean isValid() {
		for (int i = 0; i < tiles.length; i++) {
			if (row(i).duplicate()) {
				System.err.println("Row#" + i + " has duplicates.");
				return false;
			}
			if (col(i).duplicate()) {
				System.err.println("Col#" + i + " has duplicates.");
				return false;
			}
			if (square(i / 3, i % 3).duplicate()) {
				System.err.println("Square#" + i + " (" + i / 3 + ", " + i % 3 + ") has duplicates.");
				return false;
			}
		}
		return true;
	}

	public void set(int n) {
		if (!tiles[activeR][activeC].setValue) { // if not a set value
			if (n == 0) {
				tiles[activeR][activeC].value = 0;
			}
			if (!row(activeR).has(n) && !col(activeC).has(n) && !square(activeR, activeC).has(n)) {
				tiles[activeR][activeC].value = n;
			}
		}
	}

	private void generateDiagonals(int r, int c) {
		{
			if (r < 3) {
				if (c >= 3) {
					c = 0;
					r++;
					generateDiagonals(r, c);
					return;
				}
			} else if (r < 6) {
				if (c < 3) {
					c = 3;
				} else if (c >= 6) {
					c = 3;
					r++;
					generateDiagonals(r, c);
					return;
				}
			} else if (r < 9) {
				if (c < 6) {
					c = 6;
				} else if (c >= 9) {
					c = 6;
					r++;
					generateDiagonals(r, c);
					return;
				}
			} else {
				return;
			}
		}

		generateDiagonals(r, c + 1);

		Value options = options(r, c);

		if (options.size() == 0) {
			System.err.println("error: no options");
			return;
		}

		tiles[r][c].value = options.get();
	}

	private boolean generate(int r, int c) {
		{
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
		}

		// find all available options for the current tile
		Value options = options(r, c);

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

	public Value options(int r, int c) {
		Value options = new Value();
		options.remove(row(r).has());
		options.remove(col(c).has());
		options.remove(square(r, c).has());
		return options;
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
		Tile[] res = new Tile[9];
		for (int r = (row / 3) * 3, x = 0; r < ((row / 3) + 1) * 3; r++) {
			for (int c = (col / 3) * 3; c < ((col / 3) + 1) * 3; c++, x++) {
				res[x] = tiles[r][c];
			}
		}
		return new Section(res);
	}
}
