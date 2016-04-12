package mancala;

public class Player {

	private String name;
	private int number;
	private int gamesWon;

	public Player(String name, int number) {
		this.name = name;
		this.number = number;
		gamesWon = 0;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void increaseGamesWon() {
		gamesWon++;
	}

}
