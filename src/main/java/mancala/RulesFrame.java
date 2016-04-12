package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class RulesFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel background;
	private JTextArea textArea;
	private JButton button;

	public RulesFrame() {
		setTitle("HOW TO PLAY!");
		setSize(1100, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);

		background = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/sunset.jpg")).getImage()
				.getScaledInstance(getWidth(), getHeight(), 10)));
		textArea = new JTextArea();
		button = new JButton("Got It?");

		setText();
		addListener();
		addFormat();
	}

	private void addFormat() {

		Font font1 = new Font("Calibri", Font.BOLD, 20);
		Font font2 = new Font("Rockwell Extra Bold", Font.PLAIN, 30);

		background.setLayout(new BorderLayout());

		textArea.setFont(font1);
		textArea.setForeground(Color.yellow);
		textArea.setSize(getWidth(), getHeight());

		button.setFont(font2);
		button.setForeground(Color.yellow);
		button.setBackground(Color.black);
		button.setBorder(new LineBorder(Color.orange, 3, true));

		background.add(textArea, BorderLayout.NORTH);
		background.add(button, BorderLayout.LINE_START);
		add(background);
	}

	private void addListener() {
		button.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void setText() {
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);

		StringBuilder builder = new StringBuilder();
		builder.append("\n*  Four pieces are placed in each of the 12 holes.");
		builder.append("\n*  Each player has a goal to the right side of the Mancala board.");
		builder.append("\n*  The game begins with one player selecting a hole on his side to pick up all of the pieces in that hole.");
		builder.append("\n*  Moving counter-clockwise, the computer deposits one of the stones in each hole until the stones run out.");
		builder.append("\n*  If you run into your own goal, the computer deposits one piece in it. ");
		builder.append("\n*  If you run into your opponent's goal, the computer skips it.");
		builder.append("\n*  If the last piece you drop is in your own goal, you get a free turn.");
		builder.append("\n*  If the last piece you drop is in an empty hole on your side, you capture that piece and any pieces in the hole directly opposite.");
		builder.append("\n*  All captured pieces are placed in your goal.");
		builder.append("\n*  The game ends when all six spaces on one side of the Mancala board are empty.");
		builder.append("\n*  The player who still has pieces on his side of the board when the game ends captures all of those pieces.");
		builder.append("\n*  All the pieces in each goal is counted. The winner is the player with the most pieces.");
		builder.append("\n\n			 *** Try to plan two or three moves into the future.");
		builder.append("			 		 *** Hover the mouse over a hole or goal to see the amount.");
		textArea.setText(builder.toString());

	}

	/*
	 * public static void main(String[] args) { RulesFrame frame = new
	 * RulesFrame(); frame.setVisible(true); }
	 */
}
