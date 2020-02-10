////package commandline;
////import java.util.ArrayList;
////
////public class MainTests {
////
//	public static void main(String[] args) {
////		PlayGame play = new PlayGame(4);
////
////		// test shuffle method by printing cards, shuffle and print again
//////		for (int i = 0; i < 40; i++) {
//////			System.out.println(play.deck[i].toString());
//////		}
//////		// shuffle
//////		play.shuffle(play.deck);
//////		// print again
//////		for (int i = 0; i < 40; i++) {
//////			System.out.println(play.deck[i].toString());
//////		}
////		
////		
////		// test deal by printing out player 1 cards
//////		play.deal();
//////		System.out.println(play.getPlayersArrayList().get(1).cardsArray.toString());
//////		play.setTrump("Firepower");
//////		System.out.println(play.getTrump());
////		
////		// test the add method in ComPile class
////		// Changed arrays to String to make it easier
////		// changed back after test
//////		ArrayList <String> names = new ArrayList<String>();
//////		names.add("Steph");
//////		names.add("Robyn");
//////		ComPile communal = new ComPile();
//////		communal.addCards(names);
//////		System.out.print(names +"\n");
//////		// test remove method
//////		// print out what the method returns
//////		System.out.print(communal.removeCards());
//////		// print out the empty comPile array
//////		System.out.println(communal.comPile);
////		
//////		// test maxNum and maxNumPos
//////		int [] numbers = new int[] {10, 4, 8, 24, 18};
//////		//print the position of first instance of max number
//////		System.out.println(play.maxNum(numbers));
//////		System.out.println(play.maxNumPos(numbers));
////		
////		//test checkRound method
////	//	play.deal(); // deals and shuffles cards
////		// print top cards for all players
//////		System.out.println(play.getPlayersArrayList().get(0).getCardsArray().get(0).toString());
//////		System.out.println(play.getPlayersArrayList().get(1).getCardsArray().get(0).toString());
//////		System.out.println(play.getPlayersArrayList().get(2).getCardsArray().get(0).toString());
//////		System.out.println(play.getPlayersArrayList().get(3).getCardsArray().get(0).toString());
//////		// call checkRound method and print winning player position
//////		System.out.println(play.checkRound());
//////		play.nextRound();
//////		System.out.println(play.getTrump());
//////		System.out.println(play.getPlayersArrayList().get(0).getCardsArray().get(0).toString());
//////		System.out.println(play.getPlayersArrayList().get(1).getCardsArray().get(0).toString());
//////		System.out.println(play.getPlayersArrayList().get(2).getCardsArray().get(0).toString());
//////		System.out.println(play.getPlayersArrayList().get(3).getCardsArray().get(0).toString());
//////		// call checkRound method and print winning player position
//////		System.out.println(play.checkRound());
////		play.deal(); 
////		play.askHuman();
////		play.checkRound();
////		do	 {
////			play.nextRound();
////			play.checkRound();
////			play.removePlayer();
////			if (play.getWinnerOfRound()>=0) {
////				play.setPrevWinRound(play.getWinnerOfRound());
////			}
////		} 
////		while (play.gameWon() == false);
////		System.out.println(play.gameOver());
////		
////	}
//		System.out.println("How many players are there? (2-5)");
//		int noPlayers = scanner.nextInt();
//		while(!(noPlayers < 2) || !(noPlayers > 5)) {
//			System.out.println("Please enter either '2', '3', '4' or '5'");
//		}
//		System.out.println("Game Start");
//		PlayGame play = new PlayGame(noPlayers);
//		play.deal();
//		while(!play.gameWon()==true) {
//			System.out.println("Round " + play.getNoOfRounds() + "\nRound " + play.getNoOfRounds() + ":Players have drawn their cards!\n"
//					+ "You drew " + play.showCard(0) + "\nThere are " + play.showCardSize(0) + " in your deck");
//			if(play.getWinnerOfRound() == 0) {
//				System.out.println("It is your turn to select a category, the categories are:"
//						+ "\n\t\t1: Size\n\t\t2: Speed\n\t\t3: Range\n\t\t4: Firepower\n\t\t5: Cargo"
//						+ "\nPlease enter the number of your chosen category: ");
//				int category = scanner.nextInt();
//				while(!(category<1) || !(category>5)) {
//					System.out.println("Please enter a number from 1 - 5");
//				}
//				play.setTrump(category);
//				if(play.getWinnerOfRound() == -1) {
//					play.setWinnerofRound(play.getPrevWinRound());
//					System.out.println("Round" + play.getNoOfRounds() + ": This round was a draw! \nThere are "
//							+ play.getComPile());
//				}
//				else if(play.getWinnerOfRound() == 0) {
//					System.out.println("Round" + play.getNoOfRounds() + ": You won this round!\n"
//							+ "The winning card was:\n" + play.showCard(0));
//				}
//				else {
//					System.out.println("Round" + play.getNoOfRounds() + ": Player " + (play.getWinnerOfRound() + 1) 
//								+ " has won this round!\n The winning card was:\n" + play.showCard(play.getWinnerOfRound())
//								+ "\n The winning category was: ");
//								if(play.getTrump() == 0) {
//									System.out.print("Size");
//								}else if(play.getTrump() == 1) {
//									System.out.println("Speed");
//								}else if(play.getTrump() == 2) {
//									System.out.println("Range");
//								}else if(play.getTrump() == 3) {
//									System.out.println("Firepower");
//								}else if(play.getTrump() == 4) {
//									System.out.println("Cargo");
//								}
//				}
//			}else {
//				//what happens if the winner of last round was AI?
//				play.aiPick(play.getPlayersArrayList().get(play.getWinnerOfRound()));
//			}
//			//update model, checkWin
//			play.checkRound();
//			play.removePlayer();
//			if (play.getWinnerOfRound()>=0) {
//				play.setPrevWinRound(play.getWinnerOfRound());
//			}
//		}	
//		System.out.println("Game over! Player " + (play.getWinnerOfRound()+1) + " has won the game.");
//		play.gameOver();
//}
