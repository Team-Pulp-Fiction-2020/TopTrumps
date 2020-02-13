package commandline;

import java.util.Scanner;

// controller class that calls methods from both the model and the view
public class Controller {
// attributes
	View view = new View(); // creates an instance of the view class
// attributes for user input
	private Scanner humanSelect = new Scanner(System.in); // Create a Scanner object
	private Scanner humanSelect2 = new Scanner(System.in); // Create a Scanner object
	private Scanner humanSelect3 = new Scanner(System.in); // Create a Scanner object
	private int h;
	private int h2;
	private int h3;

	public void play() { // play method that calls everything

		do {// ask the user if they want to see stats or play game
			view.printGameStats(); // user needs to enter 1 or 2
			h = humanSelect.nextInt(); // h = the user input
		} while ((h != 1 && h != 2)); // keep looping until the correct number is entered

		if (h == 1) { // if 1 call statistics ****** UPDATE WITH DATABASE STUFF
//			try {
//				view.returnStatistics();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			this.play();
		} else if (h == 2) { // if 2 play game
			do {
				view.printSelectPlayerNo(); // ask how many players there are
				h2 = humanSelect2.nextInt(); // h2 = user input
			} while (!(h2 > 1 && h2 < 6)); // keep asking until the correct no is entered, 2-5
			view.getPlay().setPlayers(h2); // set the number of players to the user entry
		}
		view.getPlay().deal(); // deal the cards
		view.printGameStart(); // print Game started
		view.getPlay().randomPlayerStart();// randomly select a player to start the game

		do { // keep doing this until the game has been won
			view.printRound(); // prints round number and players cards have been drawn
			// if the human player has cards left print out the one at position 0
			if (view.getPlay().getPlayersArrayList().get(0).getCardsArray().isEmpty() == false) {
				view.printPlayerTopCard();
			}
			if (view.getPlay().getWinnerOfRound() == -1) {// if it was a draw
				// set winner of round to previous winner
				view.getPlay().setWinnerOfRound(view.getPlay().getPrevWinRound());
			}
			if (view.getPlay().getWinnerOfRound() == 0) {// if human player has won
				do { // they need to select a category
					view.printCategories();// print the categories to choose from
					h3 = humanSelect3.nextInt();// h3 = user input
				} while (!(h3 > 0 && h3 < 6)); // keep asking until the correct number is entered 1-5
				if (h3 == 1) { // if 1 is selected
					view.getPlay().setTrump(0); // set trumps to size
				} else if (h3 == 2) { // if 2 is selected
					view.getPlay().setTrump(1); // set trumps to speed
				} else if (h3 == 3) { // if 3 is selected
					view.getPlay().setTrump(2);// set trumps to range
				} else if (h3 == 4) {// if 4 is selected
					view.getPlay().setTrump(3);// set trumps to firepower
				} else if (h3 == 5) {// if 5 is selected
					view.getPlay().setTrump(4);// set trumps to cargo
				}
			} else { // if ai player has won call the aiPick method passing in the winner of the
						// round
				view.getPlay().aiPick(view.getPlay().getPlayersArrayList().get(view.getPlay().getWinnerOfRound()));
				view.printTrumps(); // print out what trumps is
			}
			view.getPlay().checkRound();// check to see who has won the round
			if (view.getPlay().getWinnerOfRound() == -1) { // if it's a draw
				view.getPlay().isDraw();// call the draw method
			}
			view.printWinnerOfRound(); // print the winner of the round / draw
			if (view.getPlay().getWinnerOfRound() >= 0) { // if the last round was not a draw
				view.getPlay().setPrevWinRound(view.getPlay().getWinnerOfRound());// set knew previous winner to winner
				view.getPlay().cardsRound();// add the cards from the round to the winners deck
			}
			view.getPlay().addNoOfRounds(); // increment the no of rounds
		} while (view.getPlay().gameWon() == false); // keep looping while game has not been won
		view.printGameOver();// if game has been won, print game over, winner etc
		// view.getPlay().gameOver(); // send stats to database ******* NEEDS UPDATED TO
		// DO THIS!!*****

// humanSelect.close();// close all the scanners  THESE THREW AN ERROR SO COMMENTED OUT
// humanSelect2.close();
// humanSelect3.close();
	}
}
