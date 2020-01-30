import java.sql.SQLException;
import java.util.Scanner;

import commandline.TopTrumpsCLIApplication;

import online.TopTrumpsOnlineApplication;


public class TopTrumps {

	/** This is the main class for the TopTrumps Applications */
	public static void main(String[] args) throws SQLException{
		final DatabaseConnection connection = new DatabaseConnection();
		Scanner scanner = new Scanner(System.in);
		
	//	int SQLGames = connection.executeQuery("SELECT GamesPlayed FROM Player WHERE PlayerID = 0");
		
		System.out.println("--------------------");
		System.out.println("--- Top Trumps   ---");
		System.out.println("--------------------");
		
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
			//playGame()?
				System.out.println("Game Start\nRound1\nRound 1:Players have drawn their cards!\n"
						+ "you drew "); // + getCard method? and use toString to print card
		}
			}catch (Exception e) {
			System.out.println("please enter either 1 or 2");
		}
		
		// command line switches
		boolean onlineMode = false;
		boolean commandLineMode = false;
		boolean printTestLog = false;
		
		// check the command line for what switches are active
		for (String arg : args) {
			
			if (arg.equalsIgnoreCase("-t")) printTestLog=true;
			if (arg.equalsIgnoreCase("-c")) commandLineMode=true;
			if (arg.equalsIgnoreCase("-o")) onlineMode=true;
			
		}
		
		// We cannot run online and command line mode simultaneously
		if (onlineMode && commandLineMode) {
			System.out.println("ERROR: Both online and command line mode selected, select one or the other!");
			System.exit(0);
		}
		
		// Start the appropriate application
		if (onlineMode) {
			// Start the online application
			String[] commandArgs = {"server", "TopTrumps.json"};
			TopTrumpsOnlineApplication.main(commandArgs);
		} else if (commandLineMode) {
			// Start the command line application
			String[] commandArgs = {String.valueOf(printTestLog)};
			TopTrumpsCLIApplication.main(commandArgs);
		}
		
	}
	
}
