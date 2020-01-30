package commandline;
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
	private ArrayList<Player> playersArrayList = new ArrayList<Player>();// array of players
	private static Card[] deck = Card.shipArrayFill(); // deck of cards
	private ComPile comPile = new ComPile(); // create a class object
	private ArrayList<Card> comPileCards = new ArrayList<Card>(); // to hold cards from draws
	private int[] trumpsArray = new int[noOfPlayers]; // array to hold players values for trump categories
	private String[] categories = new String[] {"size", "speed", "range", "firepower", "cargo"}; // array to hold categories
	
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
		// loop the number of players
		for (int i = 0; i < getNoOfPlayers(); i++) {
			// add the players trump value to an array
			if (getTrump().equals("size")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(i).getSize(); 
			}
			else if(getTrump().equals("speed")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(i).getSpeed(); 
			}
			else if(getTrump().equals("range")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(i).getRange(); 
			}
			else if(getTrump().equals("firepower")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(i).getFirepower(); 
			}
			else if(getTrump().equals("cargo")) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(i).getCargo(); 
			}
		}
			// find the highest number position in the array
			int highest = maxNum(trumpsArray);
			winner = "Player " + highest + " is the winner";
			// call the countValue method to see if it's a draw
			if (countValue(trumpsArray, highest)>=2)
			{
				winner = "Draw";
				isDraw();
			}
			return winner;
		}
		
 

	    // method that returns the number of times a number appears in an array
	    public int countValue (int[] values, int num)
	    {
	      int count = 0;
	      for (int i = 0; i < values.length; i++) {
	        if (values[i] == num) {
	          count++;
	        }
	      }
	      return count;
	    }

	

public int maxNum( int[] array )
{
  if ( array == null || array.length == 0 ) return -1; // null or empty

  int largest = 0;
  for ( int i = 1; i < array.length; i++ )
  {
      if ( array[i] > array[largest] ) largest = i;
  }
  return largest; // position of the first largest found
}


	// method that updates cards if there is a draw
	public void isDraw() {
		noOfDraws++;
		// loop the number of players
		for(int i = 0; i < noOfPlayers; i++) {
		// add their card at position 0 to the comPileCards
		comPileCards.add(playersArrayList.get(i).cardsArray.get(0));
		// remove it from playersArrayList
		playersArrayList.get(i).cardsArray.remove(0);
		}
		// set the comPile cards to comPileCards
		comPile.addCards(comPileCards);
		//remove all cards from comPileCards
		comPileCards.clear();
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
