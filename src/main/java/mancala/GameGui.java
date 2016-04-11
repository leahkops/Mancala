package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel optionsPanel, statsPanel;
	private JButton newGameButton, rulesButton;
	// private JLabel statsLabel1, statsLabel2;//, descriptionLabel;
	// private ComputerAI computerAI;
	private PieceAnimation animation;
	private Players players;
	private BoardPanel board;

	public GameGui(String name1, String name2) {

		setTitle("Mancala");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		optionsPanel = new JPanel();
		players = new Players(name1, name2);
		animation = new PieceAnimation();
		board = new BoardPanel(this.players, animation);
		// descriptionLabel = new JLabel(board.description());

		statsPanel = new JPanel(new BorderLayout());

		setPanels();

		newGameButton = new JButton("New Game");
		rulesButton = new JButton("Rules");
		// statsLabel1 = new JLabel(players.playersName(1) + " Wins: "
		// + players.gamesWon(1));
		// statsLabel2 = new JLabel(players.playersName(2) + " Wins: "
		// + players.gamesWon(2));

		this.setIconImage(new ImageIcon(getClass().getResource("/MancalaBoard.png")).getImage());

		add();
		format();
		addActionListeners();
		board.resetBoard();

		final Random rand = new Random();
		for (int i = 0; i < 14; i++) {
			if ((i != Board.GOAL1) && (i != Board.GOAL2)) {

				board.getCup(i).putClientProperty("index", i);
				board.getCup(i).addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent event) {
						Cup cup = (Cup) event.getSource();
						int humanIndex = (Integer) cup.getClientProperty("index");
						if ((!cup.isEnabled() || board.getQtyMarbles(humanIndex) == 0)) {
							return;
						}
						board.disableAllCups();
						board.turn(humanIndex);
						// descriptionLabel.setText(board.description());
						if (!board.goAgain()) {
							new Timer().schedule(new DelayTask(), 670);
						}
					}
				});
			}
		}

		/*
		 * board.addMouseListener(new MouseListener(){
		 * 
		 * public void mouseClicked(MouseEvent e) { System.out.println("X = " +
		 * e.getX()); System.out.println("Y = " + e.getY()); }
		 * 
		 * <<<<<<< HEAD class DelayTask extends TimerTask {
		 * 
		 * @Override public void run() { board.turn(1);
		 * descriptionLabel.setText(board.description()); System.out.println(
		 * "called timer task"); } } ======= public void mouseEntered(MouseEvent
		 * arg0) { // TODO Auto-generated method stub } >>>>>>> animation
		 * 
		 * public void mouseExited(MouseEvent arg0) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * public void mousePressed(MouseEvent e) { // TODO Auto-generated
		 * method stub // TODO Auto-generated method stub System.out.println(
		 * "X = " + e.getX()); System.out.println("Y = " + e.getY()); }
		 * 
		 * public void mouseReleased(MouseEvent e) { // TODO Auto-generated
		 * method stub
		 * 
		 * System.out.println("X = " + e.getX()); System.out.println("Y = " +
		 * e.getY());
		 * 
		 * }
		 * 
		 * });
		 */

		setGlassPane(animation);
		getGlassPane().setVisible(true);
		animation.setOpaque(false);
		repaint();
		pack();
	}

	private void setPanels() {
		optionsPanel = new JPanel();
		statsPanel = new JPanel(new BorderLayout());
	}

	public void format() {
		Font font1 = new Font("Rockwell Extra Bold", Font.PLAIN, 28);
		optionsPanel.setBackground(Color.BLACK);
		optionsPanel.setPreferredSize(new Dimension(1000, 40));
		newGameButton.setFont(font1);
		newGameButton.setBackground(Color.black);
		newGameButton.setForeground(Color.red);
		rulesButton.setFont(font1);
		rulesButton.setBackground(Color.black);
		rulesButton.setForeground(Color.red);

		statsPanel.setBackground(Color.BLACK);
		statsPanel.setPreferredSize(new Dimension(1000, 40));

		// statsLabel1.setFont(font1);
		// statsLabel1.setForeground(Color.MAGENTA);
		// statsLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		// statsLabel2.setFont(font1);
		// statsLabel2.setForeground(Color.MAGENTA);
		// statsLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		// descriptionLabel.setFont(font2);
		// descriptionLabel.setForeground(Color.BLUE);
		// descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

	}

	public void add() {
		optionsPanel.add(newGameButton);
		optionsPanel.add(rulesButton);
		add(optionsPanel, BorderLayout.NORTH);

		// JLayeredPane boardMid = new JLayeredPane();
		JPanel boardMid = new JPanel();
		// boardMid.add(animation);
		// boardMid.add(board);
		add(board, BorderLayout.CENTER);

		// statsPanel.add(statsLabel1, BorderLayout.EAST);
		// statsPanel.add(statsLabel2, BorderLayout.WEST);
		// statsPanel.add(descriptionLabel, BorderLayout.NORTH);
		// add(statsPanel, BorderLayout.SOUTH);
	}

	public void addActionListeners() {
		newGameButton.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				board.resetBoard();
				board.resetCups();
			}

		});
		rulesButton.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				RulesFrame rulesFrame = new RulesFrame();
				rulesFrame.setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		GameGui test = new GameGui("one", "two");
		test.setVisible(true);

	}

	class DelayTask extends TimerTask {
		@Override
		public void run() {
			board.turn(1);
			// descriptionLabel.setText(board.description());
			System.out.println("called timer task");
		}
	}

}