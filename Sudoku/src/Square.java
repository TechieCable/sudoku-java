import java.awt.Graphics;

public class Square {
	private static final int size = 330;

	int row, col;
	int x, y;

	public Square(int row, int col) {
		this.row = row;
		this.col = col;
		x = 10 + (col * size);
		y = 10 + (row * size);
	}

	public Square() {
		this(0, 0);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 5; i++) {
			g.drawRect(x - i, y - i, size + (i * 2), size + (i * 2));
		}
	}
}
