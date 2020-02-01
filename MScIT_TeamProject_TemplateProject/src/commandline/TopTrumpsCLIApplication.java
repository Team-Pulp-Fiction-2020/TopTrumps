package commandline;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {
	private static int gameCounter;

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
			int playerInput = 0;
			while(!(playerInput == 1) || !(playerInput == 2)) {
				if (scanner.nextInt() == 1) {
					playerInput = 1;
					System.out.println("\n\n\nGame Statistics:\n"
							+ "\t\t\tNumber of Games: " + connection.getGamesPlayed()
							+ "\n\t\tNumber of Human Wins: " + connection.getHumanWins()
							+ "\n\t\tNumber of AI Wins: " + connection.getComputerWins()
							+ "\n\t\tAverage Number of Draws: " + connection.getAvgDraws()
							+ "\n\t\tLongest Game: " + connection.getLongestGame());
					}
				if (scanner.nextInt() == 2) {
					playerInput = 2;
					int noPlayers = 0;
					while(!(noPlayers < 2) || !(noPlayers > 5)) {
						System.out.println("How many players are there? (2-5)");
						if(scanner.nextInt() >= 2 && scanner.nextInt() <= 5) {
							noPlayers = scanner.nextInt();
							PlayGame play = new PlayGame(noPlayers);
							while(!play.gameWon())
								play.deal();
								System.out.println("Game Start\nRound1\nRound 1:Players have drawn their cards!\n"
										+ "you drew " + play.getCurrentCard() + "\nThere are " + play.sizeOfDeck() + " in your deck");
								
								
						}else System.out.println("Please enter either '1', '2', '3', '4' or '5'");
					}
				} else System.out.println("Please enter either '1' or '2'");
			}
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
