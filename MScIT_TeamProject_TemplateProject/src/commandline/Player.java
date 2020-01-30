package commandline;
import java.util.ArrayList;

public class Player {
	
//	attributes of the player class
	protected String playerStatus;
	protected int countWins;
	protected ArrayList<Card> cardsArray = new ArrayList<Card>();
	
	public Player() {
		
	}

	public String getPlayerStatus() {
		return playerStatus;
	}

	public int getCountWins() {
		return countWins;
	}
}