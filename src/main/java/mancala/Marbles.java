package mancala;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Marbles {

	private ArrayList<Image> possibleMarbles;
	private Random rand;
	
	public Marbles(){
		possibleMarbles = new ArrayList<Image>();
		addMarbles();
		rand = new Random();
	}
	
	private void addMarbles() {

		possibleMarbles.add(new ImageIcon(getClass().getResource("/BlueMarble.png"))
				.getImage());
		possibleMarbles.add(new ImageIcon(getClass().getResource("/YellowMarble.png"))
				.getImage());
		possibleMarbles.add(new ImageIcon(getClass().getResource("/PinkMarble.png"))
				.getImage());
		possibleMarbles.add(new ImageIcon(getClass().getResource("/GreenMarble.png"))
				.getImage());
	}
	
	public Image getRandom(){
		return possibleMarbles.get(rand.nextInt(possibleMarbles.size()));
	}

}
