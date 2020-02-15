package commandline;

import java.sql.SQLException;
import java.util.Scanner;

public class View {

	// create an instance of the model class
	private PlayGame play = new PlayGame();

	public void printGameStats() {// print out the options for the start of the game
		System.out.print(
				"Do you want to see past results or play a game?\n\t1: Print Game statistics \n\t2: Play game \nEnter the number for your selection:");
	}

	public void printSelectPlayerNo() { // asks how many players there are
		System.out.print("Please select the number of players between 2 and 5:");
	}

	public void printGameStart() { // prints Game started
		System.out.println("\n\nGame Started");
	}

	public void printRound() { // prints round no and players have drawn their cards
		System.out.println("\nRound " + play.getNoOfRounds() + ": Players have drawn their cards");
	}

	public void printPlayerTopCard() {// prints humans top card and how many cards they have
		System.out.println(play.getPlayersArrayList().get(0).cardsArray.get(0).toString() + "\nYou have "
				+ play.getPlayersArrayList().get(0).getCardsArray().size() + " cards left.");
	}

	public void printCategories() {// prints categories to select
		System.out.println(
				"\nIt is your turn to select a category, the categories are:\n\t1. Size\n\t2. Speed\n\t3. Range\n\t4. Firepower\n\t5. Cargo\nEnter the number for your attribute: ");
	}

	public void printWinnerOfRound() { // prints
		if ((play.getWinnerOfRound() == -1)) {// if it was a draw
			System.out.println(
					"\nThe round was a draw. There are " + play.getComPileSize() + " cards in the communial pile.");
		} else {// or else prints the winner and their card
			System.out.println("\nPlayer " + (play.getWinnerOfRound() + 1)
					+ " has won the round with the following card:\n"
					+ play.getPlayersArrayList().get(play.getWinnerOfRound()).getCardsArray().get(0).toString() + "\n");
		}
	}

	public void printTrumps() { // prints what category is trumps
		System.out.println("Trumps is :" + (play.getTrump() + 1));
	}

	public void printGameOver() { // prints game over message, winner and stats
		System.out.println("Game over! Player " + play.getGameWinner() + " has won the game.\nNo of draws : "
				+ play.getNoOfDraws());
		for (int i = 0; i < play.getNoOfPlayers(); i++) {
			System.out.println(
					"Player " + (i + 1) + " has won " + play.getPlayersArrayList().get(i).getRoundsWons() + " rounds.");
		}
	}

	public PlayGame getPlay() {
		return play;
	}

//	public void returnStatistics() throws SQLException {
//	DatabaseConnection DC = new DatabaseConnection();
//	System.out.println("\tNumber of Games: " + DC.getGamesPlayed() + "\n\tNumber of Human Wins: "
//			+ DC.getHumanWins() + "\n\tNumber of AI Wins: " + DC.getComputerWins() + "\n\tAverage Number of Draws: "
//			+ DC.getAvgDraws() + "\n\tLongest Game: " + DC.getLongestGame() + "\n\n");
//
//}

	// the methods below are for the test log
	public String tlCardsInDeck() {
		String s = "The cards in the deck are : ";
		for (int i = 0; i < getPlay().getDeck().length; i++) {
			s += "\n" + getPlay().getDeck()[i].getName();
		}
		return s;
	}

	public String tlAfterShuffle() {
		return "\n------------------------------------------------------------------------\nAfter shuffling\n" + tlCardsInDeck();
	}

	public String tlPlayerCards() {
		String s = "\n------------------------------------------------------------------------\nEach players cards are as follows :\n";
		for (int i = 0; i < play.getNoOfPlayers(); i++) {
			if (play.getPlayersArrayList().get(i).getCardsArray().isEmpty() == true) {
				continue;
			}
			s += "\nPlayer " + (i + 1) + " cards are :\n" + play.getPlayersArrayList().get(i).getCardsArray().toString()
					+ "\n";
		}
		return s;
	}

	public String tlPCards() {
		return " \nEach players cards are as follows : \n" + tlPlayerCards();
	}

	public String tlCardsInPlay() {
		String s = "\n------------------------------------------------------------------------\nThe cards in play are :";
		for (int i = 0; i < getPlay().getNoOfPlayers(); i++) {
			// if the player has no cards left skip
			if (getPlay().getPlayersArrayList().get(i).getCardsArray().isEmpty() == true) {
				continue;
			}
			s += "\nPlayer " + (i + 1) + "\n"
					+ getPlay().getPlayersArrayList().get(i).getCardsArray().get(0).toString();
		}
		return s;
	}

	public String tlComPile() {
		String s = "\n------------------------------------------------------------------------\nThe cards in the communal pile are :\n";
		for (int i = 0; i < getPlay().getComPileSize(); i++) {
			s += "\n" + getPlay().getComPile().getComPile().get(i).toString();
		}
		return s;
	}

	public String tlWinner() {
		return "\n------------------------------------------------------------------------\nThe winner of the game is player " + getPlay().getGameWinner();
	}

	public String tlCardsAtEndOfRound() {
		return "\n------------------------------------------------------------------------\nRound Over" + tlPlayerCards();
	}

	public String tlGetTrumps() {
		String s = "\n------------------------------------------------------------------------\nThe trump catagory is ";
		if (getPlay().getTrump() == 0) { // if trumps is size
			s += "size" + "\nPlayer values were :\n";
			// loop the players
			for (int i = 0; i < getPlay().getNoOfPlayers(); i++) {
				// if a player has no cards left
				if (getPlay().getPlayersArrayList().get(i).getCardsArray().isEmpty()) {
					s += "\nPlayer " + (i + 1) + " : " + "No cards";
				} else {// they do have cards left print their size value for their top card
					s += "\nPlayer " + (i + 1) + " : "
							+ getPlay().getPlayersArrayList().get(i).getCardsArray().get(0).getSize();
				}
			}
		} else if (getPlay().getTrump() == 1) {
			s += "speed" + "\nPlayer values were :";
			// loop the players
			for (int i = 0; i < getPlay().getNoOfPlayers(); i++) {
				// if a player has no cards left
				if (getPlay().getPlayersArrayList().get(i).getCardsArray().isEmpty()) {
					s += "\nPlayer " + (i + 1) + " : " + "No cards";
				} else {// they do have cards left print their size value for their top card
					s += "\nPlayer " + (i + 1) + " : "
							+ getPlay().getPlayersArrayList().get(i).getCardsArray().get(0).getSpeed();
				}
			}
		} else if (getPlay().getTrump() == 2) {
			s += "range" + "\nPlayer values were :";
			// loop the players
			for (int i = 0; i < getPlay().getNoOfPlayers(); i++) {
				// if a player has no cards left
				if (getPlay().getPlayersArrayList().get(i).getCardsArray().isEmpty()) {
					s += "\nPlayer " + (i + 1) + " : " + "No cards";
				} else {// they do have cards left print their size value for their top card
					s += "\nPlayer " + (i + 1) + " : "
							+ getPlay().getPlayersArrayList().get(i).getCardsArray().get(0).getRange();
				}
			}
		} else if (getPlay().getTrump() == 3) {
			s += "firepower" + "\nPlayer values were :";
			// loop the players
			for (int i = 0; i < getPlay().getNoOfPlayers(); i++) {
				// if a player has no cards left
				if (getPlay().getPlayersArrayList().get(i).getCardsArray().isEmpty()) {
					s += "\nPlayer " + (i + 1) + " : " + "No cards";
				} else {// they do have cards left print their size value for their top card
					s += "\nPlayer " + (i + 1) + " : "
							+ getPlay().getPlayersArrayList().get(i).getCardsArray().get(0).getFirepower();
				}
			}
		} else if (getPlay().getTrump() == 4) {
			s += "cargo" + "\nPlayer values were :";
			// loop the players
			for (int i = 0; i < getPlay().getNoOfPlayers(); i++) {
				// if a player has no cards left
				if (getPlay().getPlayersArrayList().get(i).getCardsArray().isEmpty()) {
					s += "\nPlayer " + (i + 1) + " : " + "No cards";
				} else {// they do have cards left print their size value for their top card
					s += "\nPlayer " + (i + 1) + " : "
							+ getPlay().getPlayersArrayList().get(i).getCardsArray().get(0).getCargo();
				}
			}
		}

		return s;
	}

}
