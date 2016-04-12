package mancala;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PieceAnimation extends JPanel {

	private static final long serialVersionUID = 1L;
	private int cupNumber;
	private int pieceAmount;
	private int yAxis;
	private int xAxis;
	private int yAxisTemp;
	private Timer timer;
	private Cup[] cupComponents;
	private Image[] pieces;

	public PieceAnimation() {

	}

	public void animate(Cup[] cups, int cupNumberIndex, int pieceAmount) {
		this.cupComponents = cups;
		// cupComponents[cupNumber].cupsMarbles();
		this.pieceAmount = cups[cupNumberIndex].getCount();
		this.cupNumber = cupNumberIndex;
		switch (this.cupNumber) {
		case 0:
			xAxis = 215;
			yAxis = 423;
			break;
		case 1:
			xAxis = 328;
			yAxis = 423;
			break;
		case 2:
			xAxis = 441;
			yAxis = 423;
			break;
		case 3:
			xAxis = 554;
			yAxis = 423;
			break;
		case 4:
			xAxis = 667;
			yAxis = 423;
			break;
		case 5:
			xAxis = 780;
			yAxis = 423;
			break;
		case 6: // goal
			xAxis = 880;
		case 7:
			xAxis = 780;
			yAxis = 290;
			break;
		case 8:
			xAxis = 667;
			yAxis = 290;
			break;
		case 9:
			xAxis = 554;
			yAxis = 290;
			break;
		case 10:
			xAxis = 441;
			yAxis = 290;
			break;
		case 11:
			xAxis = 328;
			yAxis = 290;
			break;
		case 12:
			xAxis = 215;
			yAxis = 290;
			break;
		case 13:// goal 2
			xAxis = 115;
		}

		yAxisTemp = yAxis;
		repaint();

		ActionListener animater = new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		};
		timer = new Timer(2, animater);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < pieceAmount; i++) {
			if(pieces.length ==0){
				return;
			}
			//keep this line you need it for animation i commented it out for testing
		//	g.drawImage(pieces[i], yAxis, (i * 15) + xAxis, this);
			setOpaque(false);

			if (cupNumber == 12 && yAxis == yAxisTemp + 100) {
				pieceAmount--;
				// add piece to goal
				((Goal) this.cupComponents[6]).addPiece(cupComponents[3]
						.removePieces()[1]);
				yAxisTemp -= 100;
				dropPieceSound();
			}
			// if marbles reach next cup to the left(assume each cup is 100
			// away)
			if (yAxis == yAxisTemp - 112) {
				yAxisTemp -= 112;
				pieceAmount--;

				((Goal) this.cupComponents[6]).addPiece(cupComponents[3]
						.removePieces()[1]);
				cupNumber++;
				dropPieceSound();

			} // if marbles reach next cup to the right (assume each cup is 100
				// away)
			else if (yAxis == yAxisTemp + 112) {
				yAxisTemp += 112;
				pieceAmount--;
				cupNumber++;
				dropPieceSound();

			}

			// if this is the last marble stop timer
			if (pieceAmount == 0) {
				timer.stop();
				g.clearRect(0, 0, getWidth(), getHeight());
			}
		}

		// if its not the last marble need to move marble(s)
		// if in cup 2-6 or 9-13 just move left/right
		if (cupNumber < 12 && cupNumber > 6) {
			yAxis -= 4;
		} else if (cupNumber >= 0 && cupNumber > 5) {
			yAxis += 4;
		}
		// if in goal1 move upper left
		else if (cupNumber == 6) {
			yAxis -= 4;
			xAxis -= 4;
		}
		// if in goal2 move lower right
		else if (cupNumber == 13) {
			yAxis += 4;
			xAxis += 4;
			cupNumber = 0;
		}
		// if in cup one move lower left
		else if (cupNumber == 12) {
			yAxis -= 4;
			xAxis += 4;
		}
		// if in cup eight move upper right
		else if (cupNumber == 6) {
			yAxis += 4;
			xAxis -= 4;
		}
	}

	private void dropPieceSound() {

		new Thread(new Runnable() {

			public void run() {
				Applet.newAudioClip(getClass().getResource("/marbleDrop.wav"))
						.play();
			}
		}).start();
	}

	public boolean distibute(int start, Board board) {

		this.pieces = this.cupComponents[start].removePieces();
		int position = start + 1;
		for (int i = 0; i < pieces.length; i++) {
			if (position != Board.GOAL1 && position != Board.GOAL2) {
				this.cupComponents[position].addPiece(pieces[i]);
			} else {
				if (board.currentPlayersGoal(position)) {
					board.setPiecesWon(board.getPiecesWon() + 1);
					((Goal) this.cupComponents[position]).addPiece(pieces[i]);
				} else {
					i--;
				}
			}

			start = position;
			if (position == this.cupComponents.length - 1) {
				position = -1;// 0 after increment
			}
			position++;

		} // pieces done being distributed
			// check if the person should go again
		if (position - 1 == Board.GOAL1 || position == 0) {
			return true;
		}
		return board.checkEmptyCup(start);

	}

}
