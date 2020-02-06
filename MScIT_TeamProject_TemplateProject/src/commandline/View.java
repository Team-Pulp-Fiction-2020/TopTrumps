package commandline;

import java.util.Scanner;

public class View {
	// adds the model
	PlayGame play = new PlayGame();

	public void printGameStats() {// print out the options for the start of the game
		System.out.println(
				"Do you want to see past results or play game?\n1:Print same statistics \n2:Play game \nEnter the number for your selections");
	}

	public void printSelectPlayerNo() { // Asks how many players
		System.out.println("Please select the number of players between 2 and 5");
	}

	public void printGameStart() { // prints Game started
		System.out.println("Game started!");
	}

	public void printRound() { // prints round no and players have drawn their cards
		System.out.println("Round: " + play.getNoOfRounds());
		System.out.println("Players have drawn their cards.");
	}

	public void printPlayerTopCard() {// prints humans top card
		System.out.println("\nPlayer 1 : " + play.getPlayersArrayList().get(0).cardsArray.get(0).toString() + "\nYou have "
				+ play.getPlayersArrayList().get(0).cardsArray.size()+" cards left in your deck.");
	}

	public void printCategories() {// prints categories to select
		System.out.println("\nPlease select a category:\n1. Size\n2. Speed\n3. Range\n4. Firepower\n5. Cargo");
	}

	public void printWinnerorRound() { // prints the winner of the round
		if (!(play.getWinnerOfRound()==-1)) {
		System.out.println("\nPlayer " + (play.getWinnerOfRound() + 1) + " has won the round with the following card:\n"
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
}
