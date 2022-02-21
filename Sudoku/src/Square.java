import java.awt.Graphics;

public class Square {
	private static int r1 = 0, c1 = 0;
	private static final int size = 330;

	int row, col;
	int x, y;

	public Square() {
		{
			this.row = r1;
			this.col = c1;
			c1++;
			if (c1 > 2) {
				c1 = 0;
				r1++;
			}
			if (r1 > 2) {
				r1 = 0;
			}
		}

		x = 10 + (col * size);
		y = 10 + (row * size);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 5; i++) {
			g.drawRect(x - i, y - i, size + (i * 2), size + (i * 2));
		}
	}
}
