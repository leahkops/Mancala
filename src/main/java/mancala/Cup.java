package mancala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Cup extends JComponent {

	private static final long serialVersionUID = 1L;

	protected ArrayList<Image> cupsMarbles;
	protected int count;
	protected int x;
	protected int y;

	public Cup(int x, int y, Marbles possibleMarbles) {
		setPreferredSize(new Dimension(110, 250));
		cupsMarbles = new ArrayList<Image>();
		count = 4;
		cupsMarbles.add(possibleMarbles.getRandom());
		cupsMarbles.add(possibleMarbles.getRandom());
		cupsMarbles.add(possibleMarbles.getRandom());
		cupsMarbles.add(possibleMarbles.getRandom());

		this.x = x;
		this.y = y;
	}

	public void setCount(int count) {
		this.count = count;
		repaint();
	}

	public void emptyCup() {
		count = 4;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		int piecesDrawn = 0, piecesPerRow = 4, location = 0, rows = 1;
		// g.drawRect(25, 90, this.getWidth()/2, this.getHeight()/14);
		g.fillRoundRect(20, 89, this.getWidth()/2, this.getHeight()/14, 12, 10);
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD , 18));
		g.drawString(String.valueOf(this.cupsMarbles.size()), 42, 105);
		for (int i = 0; i < count; i++) {
			if (piecesDrawn % piecesPerRow == 0) {
				rows++;
				location = 0;
			}
			g.drawImage(this.cupsMarbles.get(piecesDrawn), rows * 10,
					location * 15, this);
			piecesDrawn++;
			location++;

		}

	}

	public void addPiece(Image piece) {
		count++;
		cupsMarbles.add(piece);

	}

	public int getCount() {
		return count;
	}

	public Image[] removePieces() {
		Image[] pieces = new Image[count];
		int upTo = 0;
		for (Image marble : cupsMarbles) {
			pieces[upTo++] = marble;
		}
		count = 0;
		cupsMarbles.clear();
		return pieces;
	}

	public void reset() {
		count = 4;
		if (cupsMarbles.size() != 4) {
			System.out.println("in methodd reset of sups");
			// cupsMarbles.clear();
			// cupsMarbles.add();
			// cupsMarbles.add();
			// cupsMarbles.add();
			// cupsMarbles.add();
		}

	}

	public int getComponentX() {
		return this.x;
	}

	public int getComponentY() {
		return this.y;
	}
}