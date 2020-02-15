package commandline;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	private BufferedWriter bw = null;
	boolean writeGameLogsToFile = false;
	// this string will be what is sent to the file
	String testLog = "";

	public Controller(String s) {
		if (s.equals("true")) {
			writeGameLogsToFile = true;
		}
	}

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

		try {
			// Specify the file name and path here
			File file = new File("toptrumps.log");
			// creates a file if it doesn't already exist
			if (!file.exists()) {
				file.createNewFile();
			}
			// create an instance of FileWriter and point it to the toptrumps.log file
			FileWriter fw = new FileWriter(file);
			// set the bufferwriter to take in filewriter
			bw = new BufferedWriter(fw);

			// for the test log file
			// add the cards in the deck
			testLog += view.tlCardsInDeck();

			// shuffle the cards
			view.getPlay().shuffle(view.getPlay().getDeck());

			// and send the deck again to log file
			testLog += view.tlAfterShuffle();

			view.getPlay().deal(); // deal the cards

			// send all players top card to the log file
			testLog += view.tlPlayerCards();

			view.printGameStart(); // print Game started
			view.getPlay().randomPlayerStart();// randomly select a player to start the game

			do { // keep doing this until the game has been won

				// for the test log send players top card to the test log file
				testLog += view.tlCardsInPlay();

				// if the human player has cards left print out their top card
				if (view.getPlay().getPlayersArrayList().get(0).getCardsArray().isEmpty() == false) {
					view.printPlayerTopCard();
				}

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
				testLog += view.tlGetTrumps();
				view.getPlay().checkRound();// check to see who has won the round
				if (view.getPlay().getWinnerOfRound() == -1) { // if it's a draw
					view.getPlay().isDraw();// call the draw method
					testLog += view.tlComPile(); // add the cards in the comPile to the logFile
				}
				view.printWinnerOfRound(); // print the winner of the round / draw
				if (view.getPlay().getWinnerOfRound() >= 0) { // if the last round was not a draw
					view.getPlay().setPrevWinRound(view.getPlay().getWinnerOfRound());// set knew previous winner to
					// winner
					view.getPlay().cardsRound();// add the cards from the round to the winners deck
					// give the winner any cards in the communal pile by calling the removeCards method
					// which removes the cards from the communal pile and returns the removed cards as an array
					view.getPlay().removeComPile(view.getPlay().getPlayersArrayList().get(view.getPlay().getWinnerOfRound()));
				}

				// send each players deck of cards to the test log
				testLog += view.tlCardsAtEndOfRound();

				view.getPlay().addNoOfRounds(); // increment the no of rounds
			} while (view.getPlay().gameWon() == false); // keep looping while game has not been won
			view.printGameOver();// if game has been won, print game over, winner etc
			testLog += view.tlWinner();
			// view.getPlay().gameOver(); // send stats to database ******* NEEDS UPDATED TO
			// DO THIS!!*****

			if (writeGameLogsToFile == true) {
				bw.write(testLog); // write the testLog string to the bufferwriter
				System.out.println("File written Successfully");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error in closing the BufferedWriter" + ex);
			}
		}

	}
}
