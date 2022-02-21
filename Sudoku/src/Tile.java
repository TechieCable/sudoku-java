import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Tile {
	private static final int size = 110;
	private static final String[] c = { "c0c0c0", "ff0000", "ff8000", "ffff00", "14ee00", "096900", "0000ff", "00d4ff",
			"440099", "ffaac6" };

	private int row, col;
	private int x, y;
	private boolean highlight, active;

	int value;
	boolean colorsOnly, setValue;

	public Tile(int row, int col) {
		this.row = row;
		this.col = col;
		x = 10 + (col * size);
		y = 10 + (row * size);
		value = 0;
		highlight = false;
		active = false;
		colorsOnly = false;
		setValue = true;
	}

	public Tile() {
		this(0, 0);
	}

	public void paint(Graphics g) {
		if (colorsOnly) {
			g.setColor(hex(c[value]));
			g.fillRect(x, y, size, size);
			return;
		}
		// when:
		// active : background = white, text1 = numColor, text2 = white
		// highlight : background = numColor, text1 = white, text2 = none
		// regular : background = light gray, text1 = white, text2 = numColor
		if (active) {
			g.setColor(hex("e0e0e0"));
		} else if (highlight) {
			g.setColor(hex(c[value]));
		} else {
			if (setValue) {
				g.setColor(hex("a0a0a0"));
			} else {
				g.setColor(hex("c0c0c0"));
			}
		}

		g.fillRect(x, y, size, size);

		g.setColor(Color.WHITE);
		g.drawRect(x - 1, y - 1, size + 2, size + 2);

		if (value != 0) {
			if (!highlight) {
				g.drawString(value + "", x + 36, y + 68);
				g.setColor(hex(c[value]));
			}

			g.drawString(value + "", x + 38, y + 70);
		}
	}

	public String toString() {
		return "Tile [row=" + row + ", col=" + col + ", value=" + value + "]";
	}

	public boolean clicked(MouseEvent m) {
		return ((m.getX() > x) && (m.getX() < x + size) && (m.getY() > y) && (m.getY() < y + size));
	}

	public void highlight(boolean x) {
		highlight = x;
		active = false;
	}

	public void active(boolean x) {
		active = x;
		highlight = false;
	}

	private Color hex(String rgb) {
		return new Color(Integer.parseInt(rgb.substring(0, 2), 16), Integer.parseInt(rgb.substring(2, 4), 16),
				Integer.parseInt(rgb.substring(4, 6), 16));
	}

}
