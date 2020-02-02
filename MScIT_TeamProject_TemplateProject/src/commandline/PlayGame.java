package commandline;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayGame {
	// create attributes
	private int noOfPlayers;
	private int noOfRounds=0;
	private int noOfDraws=0;
	private int prevWinRound = 0;
	private int winnerOfRound;
	private String gameWinner = "";
	private String trump = "";
	private ArrayList<Player> playersArrayList = new ArrayList<Player>();// array of players
	private static Card[] deck = Card.shipArrayFill(); // deck of cards
	private ComPile comPile = new ComPile(); // create a class object this will hold communal pile
	// values for trump categories
	// array to hold categories

	// Constructor that passes in the number of players
	// and creates a Player object for each of them
	public PlayGame(int noOfPlayers) {
		// set global variable to local
		this.noOfPlayers=noOfPlayers;
		// create the same amount of Player objects as noOfPlayers
		for (int i = noOfPlayers; i > 0; i--) {
			Player p = new Player();
			// add them to the plyersArrayList
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
	// calls the shuffle method within it
	public void deal() {
		// calls the shuffle method
		shuffle(deck);
		// set int for card count
		int cardCount = 0;
		// deal
		// cards divided by noOfPlayers times
		for (int i = 0; i < (40 / getNoOfPlayers()); i++) {
			// loop through the players in the ArrayList
			// add a card to each persons cardArrayList
			for (int p = 0; p < getNoOfPlayers(); p++) {
				playersArrayList.get(p).cardsArray.add(deck[cardCount]);
				cardCount++; // count how many cards are dealt
			}
			// if 3 players the cards don't divide equally
			// player 1 needs to get an extra card
			if (cardCount == 38) { // if only 39 cards have been dealt
				playersArrayList.get(0).cardsArray.add(deck[39]); // give the 40th card to player 1
				cardCount++;
			}
		}
	}

	public void askHuman() {
		// ask for player1 trump card and then set to desired category
		showCard(0);
		Scanner humanSelect = new Scanner(System.in); // Create a Scanner object
		System.out.println("Please select your trump category.");
		setTrump(humanSelect.nextLine()); // Read user input
	}

	// method to check and return who has won the round
	public int checkRound() {
		noOfRounds++;
		// for testing System.out.println("calling checkRound");
		int[] trumpsArray = new int[noOfPlayers];
		winnerOfRound = -1;
		// loop the number of players
		for (int i = 0; i < getNoOfPlayers(); i++) {
			// skip player if they have no cards left
			if (playersArrayList.get(i).cardsArray.size() == 0)
				continue;
			// add the players trump value to trumpsArray
			if (getTrump().equals("size")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getSize();
			} else if (getTrump().equals("speed")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getSpeed();
			} else if (getTrump().equals("range")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getRange();
			} else if (getTrump().equals("firepower")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getFirepower();
			} else if (getTrump().equals("cargo")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getCargo();
			}
		}
		// find the first highest number position in the array
		// by calling the maxNumPos method
		int highPos = maxNumPos(trumpsArray);
		// find the highest value in the array
		// by calling the maxNum method
		int highVal = maxNum(trumpsArray);
		// call the countValue method to see if it's a draw
		if (countValue(trumpsArray, highVal) >= 2) {
			// if it is set winner to position -1
			winnerOfRound = -1;
			// and call the isDraw method
			isDraw();
		} else {
			// print out the winner of the round
			System.out.println("\n Player " + (highPos + 1) + " has won the round with the following card:\n"
					+ playersArrayList.get(highPos).getCardsArray().get(0).toString() + "\n");
			// add all cards in the round to the winners pile
			playersArrayList.get(highPos).cardsArray.addAll(cardsWon());
			// give winner any cards in the comPile
			playersArrayList.get(highPos).cardsArray.addAll(comPile.removeCards());
			winnerOfRound = highPos;
			playersArrayList.get(winnerOfRound).addRound();
		}
		return winnerOfRound;
	}

	// method that returns the number of times a number appears in an array
	public int countValue(int[] values, int num) {
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == num) {
				count++;
			}
		}
		return count;
	}

	// method that returns the position of the largest
	// value in an int Array
	public int maxNumPos(int[] array) {
		int largest = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[largest])
				largest = i;
		}
		return largest; // position of the first largest found
	}

	// method that returns the highest
	// value in an array of ints
	public int maxNum(int[] array) {
		int largest = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[largest])
				largest = i;
		}
		return array[largest]; // highest value
	}

	// method to add all the players top card to an ArrayList
	// and then delete them from the players array
	// returning the new array of cards
	public ArrayList<Card> cardsWon() {
		// for testing System.out.println("calling cardsWon");
		ArrayList<Card> cardsPlayed = new ArrayList<Card>();
		for (int i = 0; i < noOfPlayers; i++) {
			if (playersArrayList.get(i).cardsArray.size() == 0)
				continue;
			cardsPlayed.add(playersArrayList.get(i).cardsArray.get(0));
			playersArrayList.get(i).cardsArray.remove(0);
		}
		return cardsPlayed;
	}

	// method that when called removes all players top card
	// and adds them to the communal pile
	public void isDraw() {
		// for testing System.out.println("calling isDraw");
		System.out.println("The round was a draw.");
		// call the cardsWon method adding them to the comPile
		comPile.addCards(cardsWon()); // removes players cards and returns them
		noOfDraws++;
		nextRound();
	}

	// method for an ai player to select their highest value
	// category and set it as trump via calling setTrump method
	public void aiPick(Player p) {
		// for testing System.out.println("calling aiPick");
		int[] topCat = new int[] { p.cardsArray.get(0).getSize(), p.cardsArray.get(0).getSpeed(),
				p.cardsArray.get(0).getRange(), p.cardsArray.get(0).getFirepower(), p.cardsArray.get(0).getCargo(), };
		// set trump to the highest value
		if (maxNumPos(topCat) == 0) {
			setTrump("size");
		} else if (maxNumPos(topCat) == 1) {
			setTrump("speed");
		} else if (maxNumPos(topCat) == 2) {
			setTrump("range");
		} else if (maxNumPos(topCat) == 3) {
			setTrump("firepower");
		} else if (maxNumPos(topCat) == 4) {
			setTrump("cargo");
		}
		for (int i = 0; i < noOfPlayers; i++) {
			if (playersArrayList.get(i).cardsArray.size() == 0)
				continue;
			System.out.println(playersArrayList.get(i).cardsArray.get(0).toString());
			System.out.println(playersArrayList.get(i).cardsArray.size());
		}
		System.out.println("Trumps is :" + getTrump());
	}


	// method that checks who won the last round
	// if human asks what trump category they want
	// if ai calls the aiPick method
	public void nextRound() {
		// for testing System.out.println("calling nextRound");
		if (winnerOfRound == -1) {
			winnerOfRound = prevWinRound;
		}
		if (winnerOfRound == 0) {
			showCard(0);
			Scanner humanSelect = new Scanner(System.in); // Create a Scanner object
			System.out.println("Please select your trump category.");
			setTrump(humanSelect.nextLine()); // Read user input
		} else {
			aiPick(playersArrayList.get(winnerOfRound));
		}
	}

	// method to see if any players have won the game
	public boolean gameWon() {
		// for testing System.out.println("calling gameWon");
		// set win to false initially
		boolean win = false;
		// loop all players
		for (int i = 0; i < getNoOfPlayers(); i++) {
			// if any player has all 40 cards
			if (playersArrayList.get(i).cardsArray.size() == 40) {
				// they have won the game
				System.out.println(gameWinner = "Game Over! Player " + (i + 1) + " has won the game!");
				setGameWinner("Player "+(i+1));
				win = true;
				break;
			}
		}
		return win;
	}
	
	// method for printing the human players cards
	public String showCard(int i) {
		String s ="";
			s = "Player 1 : " + playersArrayList.get(i).cardsArray.get(0).toString();
		return s;
	}
	
	public int showCardSize(int i) {
		return playersArrayList.get(i).cardsArray.size();
	}

	// gameOver method which will send all the stats to the database
	public String gameOver() {
		// for testing System.out.println("calling gameOver");
		String gameStats = "";
		if (gameWon() == true) {
			gameStats = noOfDraws + " , " + gameWinner + " , " + noOfRounds;
			for (int i = 0; i < noOfPlayers; i++) {
				gameStats += " , "+playersArrayList.get(i).getRoundsWons();
			}
		}
		return gameStats;
	}

	// method to remove players from the game
	// with no cards left
	public void removePlayer() {
		// for testing System.out.println("calling removePlayer");
		// loop the players
		for (int i = 0; i < playersArrayList.size(); i++) {
			// if their card array is of length 0
			// System.out.println(playersArrayList.get(i).getCardsArray().size());
			if (playersArrayList.get(i).cardsArray.size() == 0) {
				// playersArrayList.remove(i); //******not working*******
				System.out.println("Player " + (i + 1) + " has no cards left and is out of the game.");
			}
		}
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

	public int getWinnerOfRound() {
		return winnerOfRound;
	}

	public void setWinnerofRound(int winnerOfRound) {
		this.winnerOfRound = winnerOfRound;
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

	public int getPrevWinRound() {
		return prevWinRound;
	}

	public void setPrevWinRound(int prevWinRound) {
		this.prevWinRound = prevWinRound;
	}
	//toString method to return humans current card
	public String getCurrentCard() {
		return playersArrayList.get(0).cardsArray.get(0).toString();
	}
	//method returns the number of cards left in human players hand
	public int sizeOfDeck() {
	return playersArrayList.get(0).cardsArray.size();
	}
}
