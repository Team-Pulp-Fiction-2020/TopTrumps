 import java.awt.List;
import java.util.ArrayList;

public class Player {
	
//	attributes of the player class
	private String name;
	private String playerID;//because of the database primary key
	private String playerStatus;
	private int countWins;
	private int numCards;
	private GamePlay gameID;
	private List<Card> playerHands; // 
	private Card chosenCard;
	
	public Player(String name, String playerID, GamePlay gameID, Card[] playerHands, String playerStatus,
			int countWins) {
		
		this.name = name;
		this.playerID = playerID;
		this.playerStatus = playerStatus;
		this.countWins = countWins;
//		this.gameID = gameID;
//		this.playerHands = playerHands;
//		this.chosenCard = chosenCard;
		this.numCards = numCards;
		
		gameID = GamePlay();
		chosenCard = new Card();
//		List<String> list1 = new ArrayList<String>()
		playerHands = new ArrayList<Card>();
		
	}
	
	//update player table whose id = this.playerId
	public void updatePlayerTable() {
	
	}

	public String getName() {
		return name;
	}

	public String getPlayerID() {
		return playerID;
	}

	public GamePlay getGameID() {
		return gameID;
	}

	public List<Card> getPlayerHands() {
		return playerHands;
	}

	public String getPlayerStatus() {
		return playerStatus;
	}

	public int getCountWins() {
		return countWins;
	}

	public Card getChosenCard() {
		return chosenCard;
	}

	public int getNumCards() {
		return numCards;
	}
	
	public String toString () {
		return name;
	}

}



