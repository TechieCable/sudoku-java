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

	public int screenW = 1030, screenH = 1050;
	// screenW = 1030

	Board b;

	Square[] squares = new Square[9];

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Dialog", Font.PLAIN, 20));

		g.fillRect(0, 0, screenW, screenH);

		g.setColor(Color.WHITE);
		b.paint(g);
		
		for (int i = 0; i < squares.length; i++) {
			squares[i].paint(g);
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
		b = new Board();
		for (int i = 0; i < squares.length; i++) {
			squares[i] = new Square();
		}
	}

	Timer t = new Timer(16, this);

	public void actionPerformed(ActionEvent m) {
		repaint();
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void mouseClicked(MouseEvent arg0) {
		System.exit(0);
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
