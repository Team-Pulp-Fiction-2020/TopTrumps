package commandline;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayGame {
	// attributes
	private int noOfPlayers;
	private int noOfRounds = 1;
	private int noOfDraws = 0;
	private int prevWinRound;
	private int winnerOfRound;
	private int gameWinner;
	private int trump;
	private int highPos;
	private int highVal;
	private ArrayList<Player> playersArrayList = new ArrayList<Player>();// array of players
	private Card[] deck = Card.shipArrayFill(); // deck of cards
	private ComPile comPile = new ComPile(); // create a class object this will hold communal pile
	// array to hold categories
	private ArrayList<Integer> statsArray = new ArrayList<Integer>();

	
	// method that takes in the number of players
	// and creates a player object for each one
	public void setPlayers(int noOfPlayers) {
		setNoOfPlayers(noOfPlayers); // set global var to local
		for (int i = noOfPlayers; i > 0; i--) { // loop the player
			Player p = new Player(); // create object
			playersArrayList.add(p); // add them to the plyersArrayList
		}
	}

	
	// method to randomise the player who goes first
	public void randomPlayerStart() {
		Random rgen = new Random(); // Random number generator
		int randomPosition = rgen.nextInt(noOfPlayers);//generate a number from the no of players
		setWinnerOfRound(randomPosition); // this is how the next player is selected
		setPrevWinRound(randomPosition); // this is incase the first round is a draw
	}

	
	// method to shuffle the deck of cards
	// which takes in the current deck and returns it reordered
	public Card[] shuffle(Card[] array) {
		Random rgen = new Random(); // Random number generator
		// loop the length of the array passed in
		for (int i = 0; i < array.length; i++) {
			// generate a random number from its length
			int randomPosition = rgen.nextInt(array.length);
			Card temp = array[i]; // put the current pos i into a temp card
			array[i] = array[randomPosition]; // add a random card from the array to pos i
			array[randomPosition] = temp;// add temp value to the ranPos
			// set deck to the new reordered array
			deck = array;
		}
		return deck;
	}

	// method to deal the cards
	// calls the shuffle method within it
	public void deal() {
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
			// if there are 3 players the cards don't divide equally
			if (cardCount == 39) { // if only 39 cards have been dealt
				// select a player randomly to get the extra card
				Random rgen = new Random(); // Random number 
				int randomPosition = rgen.nextInt(noOfPlayers); // between the no of players
				playersArrayList.get(randomPosition).cardsArray.add(deck[39]); // give the 40th card to the rand player
				cardCount++; // increment the card count
			}
		}
	}

	
	// method that returns the number of times a number appears in an array
	// to be used for determining a draw
	public int countValue(int[] values, int num) { // pass in the array of categories being checked and the highest number
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == num) { // if the position being checked = the highest number
				count++; // increment the count, if the count goes above 1 it is a draw
			}
		}
		return count;
	}

	
	// method that returns the position of the largest
	// value in an int Array
	public int maxNumPos(int[] array) { // pass in the array to be checked
		int largest = 0; // set the largest no to 0
		for (int i = 1; i < array.length; i++) { // loop the array
			if (array[i] > array[largest]) // checking if the position value is greater than the largest number
				largest = i; // if it is set the largest to that position
		}
		highPos = largest; // set global variable to largest
		return largest;
	}

	
	// method that returns the highest
	// value in an array of ints
	public int maxNum(int[] array) { // pass in the array to be checked
		int largest = 0; // set largest to 0
		for (int i = 1; i < array.length; i++) { // loop the array
			if (array[i] > array[largest]) // if the position i value is greater than largest
				largest = i; // then set largest to the value of i
		}
		highVal = largest;
		return array[largest]; // highest value
	}

	
	// method to remove players top cards and return them as an array list
	public ArrayList<Card> cardsWon() {
		ArrayList<Card> cardsPlayed = new ArrayList<Card>();
		for (int i = 0; i < noOfPlayers; i++) { // loop the players
			// if a player has no cards left, skip them
			if (playersArrayList.get(i).cardsArray.size() == 0)
				continue;
			// else add their top card to the cardsPlayed arrayList
			cardsPlayed.add(playersArrayList.get(i).cardsArray.get(0));
			// then remove it from their pile
			playersArrayList.get(i).cardsArray.remove(0);
			// call shuffle method to reorder the cards
		}
		return cardsPlayed;
	}

	
	// method that when called removes every players top card
	// and adds them to the communal pile
	public void isDraw() {
		// call the cardsWon method which removes cards from the players
		// and returns them as an array
		comPile.addCards(cardsWon()); // add that array to the communal pile
		noOfDraws++; // Increment the number of draws
	}

	
	// method to check and return who has won the round
	// calls on various other methods to achieve this
	public int checkRound() {
		// create an array to hold the players trump category
		int[] trumpsArray = new int[noOfPlayers];
		// loop the number of players
		for (int i = 0; i < getNoOfPlayers(); i++) {
			// skipping players if they have no cards left
			if (playersArrayList.get(i).cardsArray.size() == 0)
				continue;
			// add the players trump value to trumpsArray
			// by calling the getters on the card class
			if (getTrump() == 0) { // if trumps is 0 compare size
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getSize();
			} else if (getTrump() == 1) { // if trumps is 1 compare speed
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getSpeed();
			} else if (getTrump() == 2) { // if trumps is 2 compare range
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getRange();
			} else if (getTrump() == 3) { // if trumps is 3 compare firepower
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getFirepower();
			} else if (getTrump() == 4) { // if trumps is 4 compare cargo
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getCargo();
			}
		}
		// find the first highest number position in the array
		// by calling the maxNumPos method
		highPos = maxNumPos(trumpsArray);
		// find the highest value in the array
		// by calling the maxNum method
		highVal = maxNum(trumpsArray);
		// call the countValue method to see if it's a draw
		if (countValue(trumpsArray, highVal) >= 2) {
		// if it is set winner to position -1
			winnerOfRound = -1;
		} else { // set the winner to the player in the highPos
			winnerOfRound = highPos;
		}
		return winnerOfRound;
	}

	// add all the cards in the round to the winners pile
	// by calling the cardsWon method which removed them from the players hand and returns them as an array
	public void cardsRound() {
		// add the array to the winners pile
		playersArrayList.get(highPos).cardsArray.addAll(cardsWon());
		winnerOfRound = highPos; // update the winnerOfRound
		// call the addRound method to increments the players winRound attribute
		playersArrayList.get(winnerOfRound).addRound();
	}

	
	// method for an ai player to select their highest value category
	// and set it as trump via calling setTrump method
	public void aiPick(Player p) { // pass in the winning player
		// add all their categories values to an array
		int[] topCat = new int[] { p.cardsArray.get(0).getSize(), p.cardsArray.get(0).getSpeed(),
				p.cardsArray.get(0).getRange(), p.cardsArray.get(0).getFirepower(), p.cardsArray.get(0).getCargo(), };
		// set trump to the highest value position in the array
		// by calling the maxNumPos method
		if (maxNumPos(topCat) == 0) {
			setTrump(0);
		} else if (maxNumPos(topCat) == 1) {
			setTrump(1);
		} else if (maxNumPos(topCat) == 2) {
			setTrump(2);
		} else if (maxNumPos(topCat) == 3) {
			setTrump(3);
		} else if (maxNumPos(topCat) == 4) {
			setTrump(4);
		}
	}

	
	// method to check if any players have won the game
	public boolean gameWon() {
		// set win to false initially
		boolean win = false;
		// loop all players
		for (int i = 0; i < getNoOfPlayers(); i++) {
			// if any player has all 40 cards
			if (playersArrayList.get(i).cardsArray.size() == 40) {
				// they have won the game
				setGameWinner(i + 1);
				win = true;
				break;
			}
		}
		return win;
	}

	
	// gameOver method which will send all the stats to the database
	public void gameOver() {
		int humanwins = playersArrayList.get(0).getRoundsWons();
		int computerwins = 0;
		for (int i = 1; i < noOfPlayers; i++) {
			computerwins = computerwins + playersArrayList.get(i).getRoundsWons();
		}
		if (gameWon() == true) {
//			try {
//				DatabaseConnection DC = new DatabaseConnection();
//				DC.sendStatisticsDB(gameWinner, humanwins, computerwins, noOfDraws, noOfRounds - 1);
//			} catch (SQLException e) {
// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		/*
		 * statsArray.add(noOfDraws); statsArray.add(gameWinner);
		 * statsArray.add(noOfRounds - 1); for (int i = 0; i < noOfPlayers; i++) {
		 * statsArray.add(playersArrayList.get(i).getRoundsWons());
		 */
	}
	// }
//return statsArray;

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

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	public int getNoOfRounds() {
		return noOfRounds;
	}

	public void addNoOfRounds() {
		noOfRounds++;
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

	public void setWinnerOfRound(int winnerOfRound) {
		this.winnerOfRound = winnerOfRound;
	}

	public int getGameWinner() {
		return gameWinner;
	}

	public void setGameWinner(int gameWinner) {
		this.gameWinner = gameWinner;
	}

	public int getTrump() {
		return trump;
	}

	public void setTrump(int trump) {
		this.trump = trump;
	}

	public int getPrevWinRound() {
		return prevWinRound;
	}

	public void setPrevWinRound(int prevWinRound) {
		this.prevWinRound = prevWinRound;
	}

	public int getComPileSize() {
		return comPile.getComPileSize();
	}
	public void removeComPile(Player p) {
		p.getCardsArray().addAll(comPile.removeCards());
	}
	
	
	public ComPile getComPile() {
		return comPile;
	}

	public Card[] getDeck() {
		return deck;
	}
	
	public String toString(){
		return "Hello";
	}

	public int getHighPos() {
		return highPos;
	}

	public void setHighPos(int highPos) {
		this.highPos = highPos;
	}

	public int getHighVal() {
		return highVal;
	}

	public void setHighVal(int highVal) {
		this.highVal = highVal;
	}

}
