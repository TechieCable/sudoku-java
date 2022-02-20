import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	private static int r1 = 0, c1 = 0;
	private static Color[] c = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA,
			Color.PINK, Color.DARK_GRAY, Color.WHITE };

	int row, col;
	int x, y;
	int value;
	private boolean display, highlight, active;

	public Tile() {
		{
			this.row = r1;
			this.col = c1;
			c1++;
			if (c1 > 8) {
				c1 = 0;
				r1++;
			}
			if (r1 > 8) {
				r1 = 0;
			}
		}

		x = 10 + (110 * col);
		y = 10 + (110 * row);
		value = 0;
		display = true;
	}

	public void paint(Graphics g) {
		if (active) {
			g.setColor(Color.BLUE);
		} else if (highlight) {
			g.setColor(Color.CYAN);
		} else {
			g.setColor(Color.LIGHT_GRAY);
		}
		g.fillRect(x, y, 110, 110);

		g.setColor(Color.WHITE);
		g.drawRect(x - 1, y - 1, 110 + 2, 110 + 2);

		if (display) {
			if (value != 0) {
				g.setColor(c[value - 1]);
			}
			g.drawString(value + "", x + 38, y + 70);
		}
	}

	public String toString() {
		return "Tile [row=" + row + ", col=" + col + ", value=" + value + "]";
	}

	public void display(boolean x) {
		display = x;
	}

	public void highlight(boolean x) {
		highlight = x;
		active = false;
	}

	public void active(boolean x) {
		active = x;
		highlight = false;
	}

}
