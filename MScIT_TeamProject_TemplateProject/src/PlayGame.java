import java.util.ArrayList;
import java.util.Random;

public class PlayGame {
	// create attributes
	private int noOfPlayers;
	private int noOfRounds;
	private int noOfDraws;
	private String winnerofRound;
	private String gameWinner = "";
	private String trump = "";
	// ******** CHANGE TO CARD ARRAY ***********
	protected static int[] deck = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // this will be the deck of cards

	// Constructor that passes in the number of players
	// and creates a Player object for each of them
	public PlayGame(int noOfPlayers) {
		// set global variable to local
		setNoOfPlayers(noOfPlayers);
		// create the same amount of Player objects
		for(int i = noOfPlayers; i > 0; i--) {
			Player p[i] = new Player ();
		}
	}

	// method to shuffle the deck of cards
	// which takes in the current deck and returns it reordered
	public int[] shuffle(int[] array) {
		Random rgen = new Random(); // Random number generator
		// loop the existing deck
		for (int i = 0; i < array.length; i++) {
			int randomPosition = rgen.nextInt(array.length);
			int temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
			// set deck to the new reordered array
			deck = array;
		}
		return deck;
	}

	// method to deal the cards
	public void deal() {
		int j = 0;
		// call the shuffle method
		shuffle(deck);
		// create an arrayList of Players
		ArrayList<Player> playersArrayList = new ArrayList<Player>();
		// add the players to the playersArrayList
		for (int i = 1; i <= getNoOfPlayers(); i++) {
			playersArrayList.add(player[i]);
		}
		// set int for card count
		int cardCount = 0;
		// deal by looping through the players
		// cards divided by noOfPlayers times
		for (int i = 0; i < (40 / getNoOfPlayers()); i++) {
			// loop through the players in the ArrayList
			// add a card to each persons cardArrayList
			for (int p = 0; p < getNoOfPlayers(); p++)
				playersArrayList[p].cardsArray[i].add(deck[cardCount]);
			cardCount++; // count how many cards are dealt
		}
		// if 3 players the cards don't divide equally
		// player 1 needs to get an extra card
		if (cardCount == 38) { // if 39 (inc 0) cards have been dealt
			playersArrayList[0].cardsArray[13].add(deck[39]); // give the 40th card to player 1
		}
	}

	// method to check and return who has won the round
	public String winRound() {
		String winner = "";
		//
		for (int i = 1; i <= getNoOfPlayers(); i++) {
			// check players trump value
			p[i].getTrump().value();
		} // need to do a compareTo
		return winner;
	}

	// method to see if any players have won the game
	public boolean gameWon() {
		boolean win = false;
		// loop all players
		for (int i = 1; i <= getNoOfPlayers(); i++) {
			// if any player has all 40 cards
			if (player[i].cardArray.length == 40) {
				// the game has been won
				win = true;
			}
			return win;
		}
	}

	// getters and setters for attributes
	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	private void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	public int getNoOfRounds() {
		return noOfRounds;
	}

	public void setNoOfRounds(int noOfRounds) {
		this.noOfRounds = noOfRounds;
	}

	public int getNoOfDraws() {
		return noOfDraws;
	}

	public void setNoOfDraws(int noOfDraws) {
		this.noOfDraws = noOfDraws;
	}

	public String getWinnerofRound() {
		return winnerofRound;
	}

	public void setWinnerofRound(String winnerofRound) {
		this.winnerofRound = winnerofRound;
	}

	public String getGameWinner() {
		return gameWinner;
	}

	public void setGameWinner(String gameWinner) {
		this.gameWinner = gameWinner;
	}

	public String getTrump() {
		return trump;
	}

	public void setTrump(String trump) {
		this.trump = trump;
	}

	public static void main(String[] args) {
		// test the shuffle
		PlayGame play = new PlayGame(4);
		// print out the deck before shuffle
		for (int i = 0; i < play.deck.length; i++) {
			System.out.println(play.deck[i]);
		}
		// shuffle and print again
		play.shuffle(deck);
		for (int i = 0; i < play.deck.length; i++) {
			System.out.println(play.deck[i]);
		}
	}
}
