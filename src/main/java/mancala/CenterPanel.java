package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenterPanel extends JPanel {

	private JPanel board;
	private JLabel[] cupsTop;
	private JLabel[] cupsBot;
	private TopPanel topp;
	private BottomPanel botp;
	private MouseListener listenerTop, listenerBot;

	public CenterPanel(TopPanel topP, BottomPanel botP) {
		super(new BorderLayout());
		setBackground(this);

		topp = topP;
		botp = botP;

		listeners();
		setUpLayout();

	}

	private void setBackground(JPanel panel) {
		panel.setBackground(new Color(0, 0, 0, 0));
	}

	private void setUpLayout() {
		addSidePanel();
		board = new JPanel();
		board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
		setBackground(board);

		JPanel top = new JPanel();
		JPanel bot = new JPanel();
		setBackground(top);
		setBackground(bot);

		cupsTop = new JLabel[6];
		cupsBot = new JLabel[6];

		for (int i = 0; i < 6; i++) {
			cupsTop[i] = new JLabel();
			cupsTop[i].setName(String.valueOf(i));
			cupsTop[i].setPreferredSize(new Dimension(115, 250));
			cupsTop[i].addMouseListener(listenerTop);
			top.add(cupsTop[i]);
			cupsBot[i] = new JLabel();
			cupsBot[i].setName(String.valueOf(i));
			cupsBot[i].setPreferredSize(new Dimension(115, 250));
			cupsBot[i].addMouseListener(listenerBot);
			bot.add(cupsBot[i]);
		}
		add(board, BorderLayout.CENTER);
		board.add(top);
		board.add(bot);
	}

	private void addSidePanel() {
		JLabel side = new JLabel();
		side.setPreferredSize(new Dimension(30, 1000));
		add(side, BorderLayout.WEST);
	}

	private void listeners() {
		listenerTop = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				JLabel label = (JLabel) e.getSource();
				int pos = Integer.parseInt(label.getName()) + 1;
				int pieces = topp.reset(pos--);
				while (pieces > 0 && pos >= 0) {
					topp.addValue(pos--);
					pieces--;
				}
			}
		};

		listenerBot = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				JLabel label = (JLabel) e.getSource();
				int pos = Integer.parseInt(label.getName());
				int pieces = botp.reset(pos++);
				while (pieces > 0 && pos < 7) {
					botp.addValue(pos++);
					pieces--;
				}
			}

		};

	}

	public static void main(String[] args) {
		new BoardScreen().setVisible(true);
	}
}
