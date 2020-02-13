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
					"\nThe round was a draw. There are " + play.getComPile() + " cards in the communial pile.");
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

	public void setPlay(PlayGame play) {
		this.play = play;
	}

//	public void returnStatistics() throws SQLException {
//		DatabaseConnection DC = new DatabaseConnection();
//		System.out.println("\tNumber of Games: " + DC.getGamesPlayed() + "\n\tNumber of Human Wins: "
//				+ DC.getHumanWins() + "\n\tNumber of AI Wins: " + DC.getComputerWins() + "\n\tAverage Number of Draws: "
//				+ DC.getAvgDraws() + "\n\tLongest Game: " + DC.getLongestGame() + "\n\n");
//
//	}
}
