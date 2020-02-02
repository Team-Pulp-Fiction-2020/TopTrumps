package commandline;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		final DatabaseConnection connection = new DatabaseConnection();
		Scanner scanner = new Scanner(System.in);

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			System.out.println("\nDo you want to see past results or play a game?\n\t\t"
					+ "1: Print Game Statistics\n\t\t2: Play Game\n"
					+ "Enter the number for your selection:");
			int playerInput = scanner.nextInt();
			while(!(playerInput == 1) || !(playerInput == 2)) {
				System.out.println("Please enter either '1' or '2'");
			}
			if (playerInput == 1) {
				System.out.println("\n\n\nGame Statistics:\n"
						+ "\t\t\tNumber of Games: " + connection.getGamesPlayed()
						+ "\n\t\tNumber of Human Wins: " + connection.getHumanWins()
						+ "\n\t\tNumber of AI Wins: " + connection.getComputerWins()
						+ "\n\t\tAverage Number of Draws: " + connection.getAvgDraws()
						+ "\n\t\tLongest Game: " + connection.getLongestGame());
			}
			if (playerInput == 2) {
				System.out.println("How many players are there? (2-5)");
				int noPlayers = scanner.nextInt();
				while(!(noPlayers < 2) || !(noPlayers > 5)) {
					System.out.println("Please enter either '1', '2', '3', '4' or '5'");
				}
				System.out.println("Game Start");
				PlayGame play = new PlayGame(noPlayers);
				while(!play.gameWon()) {
					play.deal();
					System.out.println("Round " + play.getNoOfRounds() + "\nRound " + play.getNoOfRounds() + ":Players have drawn their cards!\n"
							+ "You drew " + play.showCard(0) + "\nThere are " + play.showCardSize(0) + " in your deck");
					if(play.getWinnerOfRound() == 0) {
						System.out.println("It is your turn to select a category, the categories are:"
								+ "\n\t\t1: Size\n\t\t2: Speed\n\t\t3: Range\n\t\t4: Firepower\n\t\t5: Cargo"
								+ "\nPlease enter the number of your chosen category: ");
						int category = scanner.nextInt();
						while(!(category<1) || !(category>5)) {
							System.out.println("Please enter a number from 1 - 5");
						}
						play.setTrump(category);
						if(play.getWinnerOfRound() == -1) {
							play.setWinnerofRound(play.getPrevWinRound());
							System.out.println("Round" + play.getNoOfRounds() + ": This round was a draw! \nThere are "
									+ play.getComPile());
						}
						else if(play.getWinnerOfRound() == 0) {
							System.out.println("Round" + play.getNoOfRounds() + ": You won this round!\n"
									+ "The winning card was:\n" + play.showCard(0));
						}
						else {
							System.out.println("Round" + play.getNoOfRounds() + ": Player " + (play.getWinnerOfRound() + 1) 
										+ " has won this round!\n The winning card was:\n" + play.showCard(play.getWinnerOfRound())
										+ "\n The winning category was: ");
										if(play.getTrump() == 0) {
											System.out.print("Size");
										}else if(play.getTrump() == 1) {
											System.out.println("Speed");
										}else if(play.getTrump() == 2) {
											System.out.println("Range");
										}else if(play.getTrump() == 3) {
											System.out.println("Firepower");
										}else if(play.getTrump() == 4) {
											System.out.println("Cargo");
										}
						}
					}else {
						//what happens if the winner of last round was AI?
					}
					//update model, checkWin
				}	
			} 
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
