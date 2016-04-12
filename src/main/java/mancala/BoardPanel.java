package mancala;

import java.awt.BorderLayout;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BoardPanel extends JPanel implements
		ComputerAI.ComputerMoveListener {

	private static final long serialVersionUID = 1L;
	private JPanel cupPanel1, cupPanel2, cupsPanel, goalPanel1, goalPanel2,
			decriptionPanel;
	private Board board;
	private Players players;
	private int winner;
	private boolean mouseEnabled;
	boolean goAgain;
	private ComputerAI computerAI;
	private int computerMove;
	private PieceAnimation animation;
	private JLabel description;

	public BoardPanel(Players players, PieceAnimation animate) {
		this.players = players;
		this.animation = animate;
		this.setLayout(new BorderLayout());
		createComponents();
		formatComponents();
		addComponents();
		this.changeDescription(1);

		this.mouseEnabled = true;
		this.goAgain = false;
		this.computerAI = new ComputerAI(animation, board);

	}

	// called by action listener
	public void turn(int index) {

		boolean winner = winner();
		animation.animate(board.getCups(), index, 4);
		// setPlayersEnabled();

		if (players.getCurrentPlayer() == 0) {
			goAgain = animation.distibute(index, board);
			setPlayersEnabled();
			repaint();
		} else if (players.getCurrentPlayer() == 1) {
			mouseEnabled = false;
			computerAI.run();
			goAgain = computerAI.goAgain();

		}

		int piecesAdded = board.checkForMoves();
		if (piecesAdded != 0) {
			JOptionPane.showMessageDialog(null, "Left over peices added to "
					+ players.playersName(piecesAdded) + "'s goal!!");
			repaint();
		}
		if (!winner) {
			if (goAgain) {
				changeDescription(3);
				goalTurn();
			} else if (!goAgain) {
				goAgain = false;
				players.switchPlayers();
				mouseEnabled = true;
			}
			changeDescription(1);
		}
	}

	private void goalTurn() {
		// changeDescription(3);
		if (mouseEnabled == false) {
			computerAI.run();
			goAgain = computerAI.goAgain();
			System.out.println("computer goes again");
			players.switchPlayers();

		} else {
			goAgain = true;
			System.out.println("player one goes again");
			return;
		}
	}

	public void onMove(int move) {
		this.computerMove = move;
	}

	public int getCompMove() {
		return this.computerMove;
	}

	private boolean winner() {

		if (board.checkGame()) {
			winner = board.calculateWinner();
			switch (winner) {
			case 0:
				changeDescription(4);
				break;
			case 1:
				players.increaseWins(1);
				displayWinner();
				return true;
			case 2:
				players.increaseWins(2);
				displayWinner();
				return true;
			}
			repaint();

			if (!goAgain) {
				players.switchPlayers();
				changeDescription(1);
				setPlayersEnabled();
				repaint();
			}
			return true;
		}
		return false;
	}

	private void setPlayersEnabled() {
		// if its playeres 1s turn first 6 are enabled if its players 2s turn
		// second 6 are enabled
		boolean enabled = (players.currentPlayerNum() == 1);
		for (int i = 0; i < 6; i++) {
			board.getCup(i).setEnabled(enabled);
		}
		for (int i = 7; i < 13; i++) {
			board.getCup(i).setEnabled(!enabled);
		}

	}

	private void createComponents() {
		cupsPanel = new JPanel();
		cupsPanel.setLayout(new BorderLayout());
		cupPanel1 = new JPanel(new FlowLayout());
		cupPanel2 = new JPanel(new FlowLayout());
		goalPanel1 = new JPanel(new GridBagLayout());
		goalPanel2 = new JPanel(new GridBagLayout());
		board = new Board(players);
		description = new JLabel(" ");
		this.changeDescription(1);
		decriptionPanel = new JPanel();
		addCups();

	}

	private void addCups() {
		for (int i = 0; i < Board.GOAL1; i++) {

			cupPanel1.add(board.getCup(i));

		}
		for (int i = 12; i >= 7; i--) {

			cupPanel2.add(board.getCup(i));

		}

	}

	private void formatComponents() {
		cupsPanel.setLayout(new BoxLayout(cupsPanel, BoxLayout.Y_AXIS));
		cupsPanel.setOpaque(false);
		cupPanel1.setOpaque(false);
		cupPanel2.setOpaque(false);
		cupPanel1.setPreferredSize(new Dimension(700,50));
		cupPanel2.setPreferredSize(new Dimension(700,80));
		goalPanel1.setOpaque(false);
		goalPanel2.setOpaque(false);
		goalPanel1.setMinimumSize(new Dimension(150, 700));
		goalPanel2.setMinimumSize(new Dimension(120, 700));
		description.setFont(new Font("Calibri", Font.PLAIN, 38));
		description.setForeground(Color.BLUE);
		description.setHorizontalAlignment(SwingConstants.CENTER);
		this.decriptionPanel.setBackground(Color.BLACK);


	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(
				new ImageIcon(getClass().getResource("/MancalaBoardFinal.jpg"))
						.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	private void addComponents() {
		GridBagConstraints c = new GridBagConstraints();
		addSpaceHolder(cupsPanel, 200, 243);
		cupsPanel.add(cupPanel2, BorderLayout.SOUTH);
		cupsPanel.add(cupPanel1, BorderLayout.CENTER);
		addSpaceHolder(cupsPanel, 200, 10);
		goalPanel1.add(board.getGoal(Board.GOAL1));
		goalPanel2.add(board.getGoal(Board.GOAL2));

		JPanel west = new JPanel();
		west.setLayout(new GridBagLayout());
		west.setOpaque(false);
		c.insets = new Insets(450, 150, 0, 0);
		west.setPreferredSize(new Dimension(152, 700));
		west.add(goalPanel2, c);

		JPanel east = new JPanel();
		east.setLayout(new GridBagLayout());
		east.setOpaque(false);
		c.insets = new Insets(450, 0, 0, 20);
		east.add(goalPanel1, c);

		this.decriptionPanel.add(this.description);
		add(east, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(cupsPanel, BorderLayout.CENTER);
		add(this.decriptionPanel, BorderLayout.SOUTH);
		// this.setBackground(Color.RED);
	}

	private void addSpaceHolder(JPanel panel, int width, int height) {
		JPanel spaceHolder = new JPanel();
		spaceHolder.setPreferredSize(new Dimension(width, height));
		spaceHolder.setOpaque(false);
		panel.add(spaceHolder);

	}

	public int getQtyMarbles(int cup) {
		return board.getContent(cup);
	}

	public boolean distributePieces(int startCup) {
		return board.distribute(startCup, animation);
	}

	public int checkForMoves() {
		return board.checkForMoves();
	}

	public boolean checkGame() {
		return board.checkGame();
	}

	public int calculateWinner() {
		return board.calculateWinner();
	}

	public void resetCups() {
		for (int i = 0; i < 14; i++) {
			if ((i != Board.GOAL1) && (i != Board.GOAL2)) {
				board.getCup(i).setCount(board.getContent(i));
			} else {
				board.getGoal(i).setCount(board.getContent(i));
			}
		}
		repaint();
	}

	public void resetBoard() {
		board.resetBoard();
		changeDescription(1);

		repaint();
		for (int i = 0; i < 6; i++) {
			board.getCup(i).setEnabled(true);
		}
		for (int i = 7; i < 13; i++) {
			board.getCup(i).setEnabled(false);
		}
	}

	public int getWinner() {
		return winner;
	}

	public void changeDescription(int code) {
		switch (code) {
		case 1:
			this.description.setText(players.currentPlayersName()
					+ "'s Turn...");
			break;
		case 2:
			this.description.setText("**** GREAT JOB "
					+ players.playersName(winner) + "!!! ****");
			break;
		case 3:
			this.description.setText(players.currentPlayersName()
					+ " landed in the goal - player goes again");
			break;
		case 4:
			this.description.setText("Tie Game - no one wins!");
		}
		resetCups();
	}

	public void displayWinner() {
		changeDescription(2);
		JOptionPane.showMessageDialog(
				null,
				players.playersName(1) + ": " + board.getContent(6)
						+ " points\n" + players.playersName(2) + ": "
						+ board.getContent(13) + " points\n\n"
						+ players.playersName(winner) + " won!!");
		// statsLabel1.setText(players.playersName(1) + " Wins: " +
		// players.gamesWon(1));
		// statsLabel2.setText(players.playersName(2) + " Wins: " +
		// players.gamesWon(2));
	}

	public Cup getCup(int cupNum) {
		return board.getCup(cupNum);
	}

	public boolean isMouseEnabled() {
		return this.mouseEnabled;
	}

	public boolean goAgain() {
		return this.goAgain;
	}

	public void setMouseEnabled(boolean isEnabled) {
		this.mouseEnabled = isEnabled;
	}

	// disable all cups when a turn is in progress so no events can happen
	public void disableAllCups() {
		board.disableAllCups();
	}
}