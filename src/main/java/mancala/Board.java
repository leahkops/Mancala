package mancala;

import java.awt.Image;
import java.util.Arrays;

//logic of a computer mancala game 

public class Board {

	private Cup[] cups;
	private int piecesWon;// by both combined
	private Players players;
	protected static final int GOAL1 = 6;
	protected static final int GOAL2 = 13;
	private Marbles marbles;

	public Board(Players players) {
		this.players = players;
		piecesWon = 0;
		marbles = new Marbles();
		// create cups/goals
		cups = new Cup[14];
		for (int i = 0; i < cups.length; i++) {
			if (i == GOAL1 || i == GOAL2) {
				cups[i] = new Goal(647, 1010, marbles);
			} else {
				if (i < GOAL1) {
					cups[i] = new Cup(639, 1560, marbles);
				} else {
					cups[i] = new Cup(639, 1560, marbles);
				}
			}
			// cups[i].setToolTipText(Integer.toString(getContent(i)));

		}
	}

	public void resetBoard() {
		for (int i = 0; i < cups.length; i++) {
			cups[i].reset();
		}
		piecesWon = 0;
	}

	public boolean checkGame() {
		if (piecesWon == 48) {
			return true;
		} else {
			return false;
		}
	}

	public int calculateWinner() {

		if (getContent(GOAL1) > getContent(GOAL2)) {
			return 1;
		} else if (getContent(GOAL1) < getContent(GOAL2)) {
			return 2;
		}
		return 0;// no winner
	}

	public int getContent(int i) {
		return cups[i].getCount();
	}

	public boolean distribute(int startPosit, PieceAnimation animator) {
		/*
		 * start = startPosit; Image[] pieces = cups[start].removePieces();
		 * 
		 * for (int i = 0, position = start + 1; i < pieces.length; i++,
		 * position++) { if (position != GOAL1 && position != GOAL2) {
		 * cups[position].addPiece(pieces[i]); } else { if
		 * (currentPlayersGoal(position)) { piecesWon++; ((Goal)
		 * cups[position]).addPiece(pieces[i]);
		 * 
		 * } else { i--; } // board[position].repaint(); }
		 * 
		 * start = position; if (position == cups.length - 1) { position = -1;//
		 * 0 after increment } } // pieces done being distributed
		 * 
		 * return checkTurn();
		 */
		return animator.distibute(startPosit, this);

	}

	public boolean currentPlayersGoal(int position) {
		return (position == GOAL1 && players.currentPlayerNum() == 1)
				|| (position == GOAL2 && players.currentPlayerNum() == 2);
	}

	// checks to see if landed in a goal or landed in an empty cup

	public boolean checkEmptyCup(int landedSpot) {

		if (getContent(landedSpot) == 1) {
			if (landedSpot > -1 && landedSpot < 6 && players.currentPlayerNum() == 1) {
				Image[] pieces = cups[landedSpot].removePieces();
				Image[] otherPieces = cups[Math.abs(landedSpot - 12)].removePieces();
				Image[] allPieces = Arrays.copyOf(pieces, pieces.length
						+ otherPieces.length);
				System.arraycopy(otherPieces, 0, allPieces, pieces.length,
						otherPieces.length);
				// System.out.println("amount is " + amount);
				piecesWon = piecesWon + allPieces.length;
				((Goal) cups[6]).addToGoal(allPieces);
			} else if (landedSpot > 6 && landedSpot < 13
					&& players.currentPlayerNum() == 2) {
				Image[] pieces = cups[landedSpot].removePieces();
				Image[] otherPieces = cups[Math.abs(landedSpot - 12)].removePieces();
				Image[] allPieces = this.combineTwoArrays(pieces, otherPieces);
				piecesWon = piecesWon + allPieces.length;
				((Goal) cups[13]).addToGoal(allPieces);
			}
		}
		return false;

	}

	// add to piecesWon and make it return the player pieces added to
	public int checkForMoves() {
		boolean found = false;
		int amount = 0;
		for (int i = 0; i < 6; i++) {
			if (cups[i].getCount() != 0) {
				found = true;
				break;
			}
		}
		if (!found) {
			Image[] pieces = null;
			for (int i = 7; i < 13; i++) {
				pieces = cups[i].removePieces();
				amount += pieces.length;
			}
			((Goal) cups[GOAL2]).addToGoal(pieces);
			piecesWon += amount;
			return 2;
		} // currentPlayer is player2
		found = false;
		amount = 0;
		for (int i = 7; i < 13; i++) {
			if (cups[i].getCount() != 0) {
				found = true;
				break;
			}
		}
		if (!found) {
			Image[] pieces = null;
			for (int i = 0; i < 6; i++) {
				Image[] otherPieces = cups[i].removePieces();
				pieces = combineTwoArrays(pieces, otherPieces);
			}
			((Goal) cups[GOAL1]).addToGoal(pieces);
			piecesWon += pieces.length;
			return 1;
		}
		return 0;
	}

	private Image[] combineTwoArrays(Image[] pieces, Image[] otherPieces) {
		Image[] allPieces = Arrays.copyOf(pieces, pieces.length
				+ otherPieces.length);
		System.arraycopy(otherPieces, 0, allPieces, pieces.length,
				otherPieces.length);
		return allPieces;

	}

	public Cup getCup(int cupNum) {
		return cups[cupNum];
	}

	
	

	public Goal getGoal(int cupNum) {
		if (cupNum == GOAL1 || cupNum == GOAL2) {
			return (Goal) cups[cupNum];
		}
		return null;

	}

	public void disableAllCups() {

		for (Cup cup : cups) {
			cup.setEnabled(false);
		}

	}

	public int getPiecesWon() {
		return this.piecesWon;
	}

	public void setPiecesWon(int value) {
		this.piecesWon = value;
	}

	public Cup[] getCups() {
		return this.cups;
	}

}
