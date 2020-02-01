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
			try{ if (scanner.nextInt() == 1) {
					System.out.println("\n\n\nGame Statistics:\n"
							+ "\t\t\tNumber of Games: " + connection.getGamesPlayed()
							+ "\n\t\tNumber of Human Wins: " + connection.getHumanWins()
							+ "\n\t\tNumber of AI Wins: " + connection.getComputerWins()
							+ "\n\t\tAverage Number of Draws: " + connection.getAvgDraws()
							+ "\n\t\tLongest Game: " + connection.getLongestGame());
				}else {
					System.out.println("How many players are there? (2-5)");
					try { 
					PlayGame play = new PlayGame(scanner.nextInt());
					while(!play.gameWon())
					play.deal();
					System.out.println("Game Start\nRound1\nRound 1:Players have drawn their cards!\n"
							+ "you drew " ); // + getCard method? and use toString to print card
					}catch(Exception e) {
						System.out.println("please enter a number 2, 3, 4 or 5");
					}
			}
				}catch (Exception e) {
				System.out.println("please enter either 1 or 2");
			}
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
