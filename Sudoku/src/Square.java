import java.awt.Color;
import java.awt.Graphics;

public class Square {
	private static int r1 = 0, c1 = 0;

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

		x = 10 + (col * 330);
		y = 10 + (row * 330);
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 1; i < 5; i++) {
			g.drawRect(x - i, y - i, 330 + (i * 2), 330 + (i * 2));
		}
	}
}
