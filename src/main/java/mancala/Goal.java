package mancala;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class Goal extends Cup {

	private static final long serialVersionUID = 1L;

	public Goal(int x, int y, Marbles marbles) {
		super(x, y,marbles);
		count = 0;
		setPreferredSize(new Dimension(120, 455));
	}

	@Override
	protected void paintComponent(Graphics g) {
		int piecesDrawn = 0, piecesPerRow = 10, location = 0, rows = 1;
		for (int i = 0; i < count; i++) {
			if (piecesDrawn % piecesPerRow == 0) {
				rows++;
				location = 0;
			}
			g.drawImage(cupsMarbles.get(piecesDrawn), (rows * 5) - 10,
					location * 15, this);
			piecesDrawn++;
			location++;

		}

	}

	public void reset() {
		count = 0;
		cupsMarbles.clear();
	}

	public void addToGoal(Image[] pieces) {
		count = count + pieces.length;
		for (int i = 0; i < pieces.length; i++) {
			cupsMarbles.add(pieces[i]);
		}
	}
}
