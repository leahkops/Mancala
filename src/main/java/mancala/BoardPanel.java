package mancala;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel cupPanel1, cupPanel2, cupsPanel, goalPanel1, goalPanel2;
	private Board board;
	private Players players;
	private int winner;
	private String turnDescriptioin;

	public BoardPanel(Players players) {
		this.players = players;
		this.setLayout(new BorderLayout());
		createComponents();
		formatComponetents();
		addComponets();
		this.changeDescription(1);

	}

	// called by action listener
	public void turn(int index) {
		boolean goalTurn = board.distribute(index);
		// returns true if landed in a goal
		setPlayersEnabled();
		repaint();

		int piecesAdded = board.checkForMoves();
		if (piecesAdded != 0) {
			JOptionPane.showMessageDialog(null, "Left over peices added to "
					+ players.playersName(piecesAdded) + "'s goal!!");
			repaint();
		}
		if (board.checkGame()) {
			winner = board.calculateWinner();
			switch (winner) {
			case 0:
				changeDescription(4);
				break;
			case 1:
				players.increaseWins(1);
				displayWinner();
				break;
			case 2:
				players.increaseWins(2);
				displayWinner();
				break;
			}
			repaint();
			return;
		}
		if (!goalTurn) {
			players.switchPlayers();
			changeDescription(1);
			setPlayersEnabled();
			repaint();
			return;
		}

		changeDescription(3);
	}

	private void setPlayersEnabled() {
		//if its playeres 1s turn first 6 are enabled if its players 2s turn second 6 are enabled
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

	private void formatComponetents() {
		cupsPanel.setLayout(new BoxLayout(cupsPanel, BoxLayout.Y_AXIS));
		Dimension cupPanelDimension = new Dimension(700, 20);
		cupsPanel.setOpaque(false);
		cupPanel1.setOpaque(false);
		cupPanel2.setOpaque(false);
		cupPanel1.setPreferredSize(cupPanelDimension);
		cupPanel2.setPreferredSize(cupPanelDimension);
		goalPanel1.setOpaque(false);
		goalPanel2.setOpaque(false);
		goalPanel1.setMinimumSize(new Dimension(150, 700));
		goalPanel2.setMinimumSize(new Dimension(120, 700));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		BoardImgPanel imgPanel = new BoardImgPanel();
		g.drawImage(imgPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	private void addComponets() {
		GridBagConstraints c = new GridBagConstraints();
		addSpaceHolder(cupsPanel, 200, 190);
		cupsPanel.add(cupPanel2, BorderLayout.SOUTH);
		cupsPanel.add(cupPanel1, BorderLayout.CENTER);
		addSpaceHolder(cupsPanel, 200, 10);
		goalPanel1.add(board.getGoal(Board.GOAL1));
		goalPanel2.add(board.getGoal(Board.GOAL2));

		JPanel west = new JPanel();
		west.setLayout(new GridBagLayout());
		west.setOpaque(false);
		c.insets = new Insets(380, 150, 0, 0);
		west.setPreferredSize(new Dimension(152, 700));
		west.add(goalPanel2, c);

		JPanel east = new JPanel();
		east.setLayout(new GridBagLayout());
		east.setOpaque(false);
		c.insets = new Insets(380, 0, 0, 20);
		east.add(goalPanel1, c);
		add(east, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(cupsPanel, BorderLayout.CENTER);
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
		return board.distribute(startCup);
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
		for (int i = 0; i < 14; i++) {
			board.getCup(i).setToolTipText(
					Integer.toString(board.getContent(i)));
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
			turnDescriptioin = players.currentPlayersName() + "'s Turn...";
			break;
		case 2:
			turnDescriptioin = "**** GREAT JOB " + players.playersName(winner)
					+ "!!! ****";
			break;
		case 3:
			turnDescriptioin = players.currentPlayersName()
					+ " landed in the goal - player goes again";
			break;
		case 4:
			turnDescriptioin = "Tie Game - no one wins!";
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

	public String description() {
		return this.turnDescriptioin;
	}

	public Cup getCup(int cupNum) {
		return board.getCup(cupNum);
	}

	// disable all cups when a turn is in progress so no events can happen
	public void disableAllCups() {
		board.disableAllCups();
	}
}