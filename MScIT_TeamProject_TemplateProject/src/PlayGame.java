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
	private ArrayList<Player> playersArrayList = new ArrayList<Player>();
	private static Card[] deck = Card.shipArrayFill(); // deck of cards
	private ComPile comPile = new ComPile();
	
	// Constructor that passes in the number of players
	// and creates a Player object for each of them
	public PlayGame(int noOfPlayers) {
		// set global variable to local
		setNoOfPlayers(noOfPlayers);
		// create the same amount of Player objects as noOfPlayers
		for (int i = noOfPlayers; i > 0; i--) {
			Player p = new Player();
			//add them to the plyersArrayList
			playersArrayList.add(p);
		}
	}

	// method to shuffle the deck of cards
	// which takes in the current deck and returns it reordered
	public Card[] shuffle(Card[] array) {
		Random rgen = new Random(); // Random number generator
		// loop the existing deck
		for (int i = 0; i < array.length; i++) {
			// generate a random number from the length of the array
			int randomPosition = rgen.nextInt(array.length);
			Card temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
			// set deck to the new reordered array
			deck = array;
		}
		return deck;
	}

	// method to deal the cards
	public void deal() {
		// call the shuffle method
		shuffle(deck);
		// set int for card count
		int cardCount = 0;
		// deal
		// cards divided by noOfPlayers times
		for (int i = 0; i < (40 / getNoOfPlayers()); i++) {
			// loop through the players in the ArrayList
			// add a card to each persons cardArrayList
			for (int p = 0; p < getNoOfPlayers(); p++) {
				playersArrayList.get(p).cardsArray.add(i, deck[cardCount]);
				cardCount++; // count how many cards are dealt
			}
			// if 3 players the cards don't divide equally
			// player 1 needs to get an extra card
			if (cardCount == 38) { // if only 39 cards have been dealt
				playersArrayList.get(0).cardsArray.set(13, deck[39]); // give the 40th card to player 1
				cardCount++;
			}
		}
	}

	// method to check and return who has won the round
	public String winRound() {
		String winner = "";
		int[] trumpsArray = new int[noOfPlayers];
		for (int i = 1; i <= getNoOfPlayers(); i++) {
			// check players trump value
			trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getFirepower();
		}
		return winner;
	}

	// method to see if any players have won the game
	public boolean gameWon() {
		// set win to false initially
		boolean win = false;
		// loop all players
		for (int i = 1; i <= getNoOfPlayers(); i++) {
			// if any player has all 40 cards
			if (playersArrayList.get(i).cardsArray.size() == 40) {
				// they have won the game
				gameWinner = "Player " + (i + 1) + " is the winner";
				setGameWinner(gameWinner);
				win = true;
			}
		}
		return win;
	}

	// getters and setters for attributes
	public ArrayList<Player> getPlayersArrayList() {
		return playersArrayList;
	}

	public void setPlayersArrayList(ArrayList<Player> playersArrayList) {
		this.playersArrayList = playersArrayList;
	}
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
}
