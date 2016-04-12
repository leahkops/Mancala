package mancala;

public class Players {

	private Player[] players;
	private int currentPlayer;

	public Players(String player1Name, String player2Name) {
		players = new Player[2];
		players[0] = new Player(player1Name, 0);
		players[1] = new Player(player2Name, 1);
		currentPlayer = 0;

	}

	public Players(String player1Name) {
		// play against computer
		players = new Player[2];
		players[0] = new Player(player1Name, 0);
		players[1] = new Player("Computer", 1);
		currentPlayer = 0;
	}

	public void switchPlayers() {
		currentPlayer = currentPlayer == 1 ? 0 : 1;
	}

	public int getCurrentPlayer(){
		return this.currentPlayer;
	}
	/*
	 * @return 1 or 2 to show which players turn it is
	 */
	public int useCurrentPlayer() {
		int temp = currentPlayer++;
		switchPlayers();
		return temp;
	}

	public String currentPlayersName() {
		return players[currentPlayer].getName();
	}

	public String playersName(int playerNumber) {
		return players[--playerNumber].getName();
	}

	public void reset() {
		currentPlayer = 0;

	}

	public int gamesWon(int playerNumber) {
		return players[--playerNumber].getGamesWon();
	}

	public void increaseWins(int playerNumber) {
		players[--playerNumber].increaseGamesWon();
	}

	public int currentPlayerNum() {
		return currentPlayer + 1;
	}
}
