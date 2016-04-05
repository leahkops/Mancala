package mancala;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TempMainForAnimation {

	private static Cup[] cupComponents;
	public static void main(String[] args) {
		Marbles marble = new Marbles();
		cupComponents = new Cup[14];
		cupComponents[0] = new Cup(215,290, marble);
		cupComponents[1] = new Cup(328,290, marble);
		cupComponents[2] = new Cup(441,290, marble);
		cupComponents[3] = new Cup(554,290, marble);
		cupComponents[4] = new Cup(667,290,marble);
		cupComponents[5] = new Cup(780,290,marble);
		cupComponents[6] = new Goal(300,800,marble);
		cupComponents[7] = new Cup(215,423,marble);
		cupComponents[8] = new Cup(328,423,marble);
		cupComponents[9] = new Cup(441,423,marble);
		cupComponents[10] = new Cup(554,423,marble);
		cupComponents[11] = new Cup(667,423,marble);
		cupComponents[13] = new Goal(300,423,marble);

	

		
		
		JComponent animation = new PieceAnimation(cupComponents, 12, 14);
		JFrame app = new JFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(animation);
		app.setSize(1000, 1000);
		app.setVisible(true);

	}

}
