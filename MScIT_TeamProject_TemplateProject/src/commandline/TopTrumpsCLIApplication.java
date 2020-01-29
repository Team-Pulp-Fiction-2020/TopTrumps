package commandline;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			System.out.println("Game Start");
			while(PlayGame.gameWon() == false) {
				// I think we need a variable or method to declare which player is the one in charge for this round
				System.out.println("Round " + PlayGame.noOfRounds);
				System.out.println("Round " + PlayGame.noOfRounds + ": Players have drawn their cards");
				System.out.println("You drew " + PlayGame.deck[0]); // Might be written different, should take card at top of array
				// but might be an easier way to format, should print out the card details, may need to make new class/method for this
				System.out.println("There are " +  deck.size() + " cards in your deck");
				// might need to make an int like int decksize = deck.size(); or something to that effect
				// If statement needed to only offer you category selection if you are player in question
				if(Player.player = p1) {
					System.out.println("It is your turn to select a category, the categories are" + CategoryList.list() + "\nEnter the number for your attribute:");
					// Prints out that it is the player to select a category, and to select what category to be used
					Scanner scanner (system.in);
					// Scanner to have player select 1-5 for which category
					
				}
				PlayGame.winRound();
				// Was going to do an if statement for ifDraw but winRound seems to already cover that and move onto the draw method
				// Either add "The winning card was 'card name' in here, or add it to the winRound method, probably better to add
				// to winRound, which will then print out the stats with the winning stat highlighted in some manner
				// At the end of the round, need a method to change the player in charge of selecting to the winner of the round
				// Also need a method of having the AI Players intelligently pick the card with the greatest chance of winning
				
				
				
			}
			
//			Card shipArray[] = Card.shipArrayFill();

			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
