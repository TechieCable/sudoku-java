import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener {

	private static final int screenW = 1330, screenH = 1050;
	// screenW = 1030

	Board b;

	static final int sideX = 1010;

	String[] messages = new String[] { "Use the arrow keys or\n mouse click to select\n a tile.",
			"Press a number 1-9 to\n enter that value in the\n selected tile.",
			"Press 0 to remove the\n current value from the\n selected tile.",
			"Pressing c shows the\n board in color-only mode.\n Try it out!", "Press r to reset\n the game." };
	int count = 0, messageSpeed = 150;

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Monospaced", Font.PLAIN, 20));

		g.fillRect(0, 0, screenW, screenH);

		g.setColor(Color.WHITE);
		b.paint(g);

		g.setFont(new Font("Monospaced", Font.PLAIN, 20));
		g.setColor(Color.WHITE);

		printLines(g, b.status(), 30);

		{
			if (count > (messages.length) * messageSpeed - 3) {
				count = 0;
			}

			for (int i = 0; i < messages.length; i++) {
				if (count < (i + 1) * messageSpeed) {
					printLines(g, messages[i], screenH - 360);
					break;
				}
			}

			count++;
		}
	}

	public void printLines(Graphics g, String m, int y) {
		if (m.length() > 0) {
			int subIndex = m.indexOf("\n");
			g.drawString(m.substring(0, subIndex > -1 ? subIndex : m.length()), sideX, y);
			printLines(g, m.substring(subIndex > -1 ? subIndex + 2 : m.length()), y + 20);

		}
	}

	public static void main(String[] arg) {
		@SuppressWarnings("unused")
		Driver d = new Driver();
	}

	public Driver() {
		JFrame frame = new JFrame("Sudoku");
//		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(XXXXXX.class.getResource("/imgs/")));
		frame.setSize(screenW, screenH);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		t.start();

		generate();

		frame.setVisible(true);

	}

	public void generate() {
		int[][] values = new int[9][9];
		values[0] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		values[1] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		values[2] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		values[3] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		values[4] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		values[5] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		values[6] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		values[7] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		values[8] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		b = new Board();
	}

	Timer t = new Timer(16, this);

	public void actionPerformed(ActionEvent m) {
		repaint();
	}

	public void keyPressed(KeyEvent arg0) {
		try {
			int x = Integer.parseInt(arg0.getKeyChar() + "");
			if (x >= 0 && x <= 9) {
				b.set(x);
				return;
			}
		} catch (Exception e) {
		}

		switch (arg0.getKeyCode()) {
		case 38: // up
			b.active(b.activeR - 1, b.activeC);
			break;
		case 40: // down
			b.active(b.activeR + 1, b.activeC);
			break;
		case 37: // left
			b.active(b.activeR, b.activeC - 1);
			break;
		case 39: // right
			b.active(b.activeR, b.activeC + 1);
			break;
		case 67: // c
			b.toggleColors();
			break;
		case 82: // r
			generate();
			break;

		default:
			System.out.println("Unindentified " + arg0);
			break;
		}
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void mouseClicked(MouseEvent arg0) {
		switch (arg0.getButton()) {
		case 1:
			for (int r = 0; r < b.tiles.length; r++) {
				for (int c = 0; c < b.tiles[r].length; c++) {
					if (b.tiles[r][c].clicked(arg0)) {
						b.active(r, c);
						break;
					}
				}
			}
			break;
		case 3:
			System.exit(0);
			break;

		default:
			System.out.println(arg0);
			break;
		}

	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

}
