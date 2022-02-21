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

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Dialog", Font.PLAIN, 20));

		g.fillRect(0, 0, screenW, screenH);

		g.setColor(Color.WHITE);
		b.paint(g);

		g.setFont(new Font("Dialog", Font.PLAIN, 20));
		g.setColor(Color.WHITE);

		g.drawString("Board is " + (b.isValid() ? "" : "invalid and ") + (b.isFull() ? "" : "in") + "complete", 1010,
				30);

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

		default:
			System.out.println(arg0);
			System.exit(0);
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
