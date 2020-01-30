package commandline;
import java.util.ArrayList;

public class MainTests {

	public static void main(String[] args) {
		PlayGame play = new PlayGame(4);

		// test shuffle method by printing cards, shuffle and print again
//		for (int i = 0; i < 40; i++) {
//			System.out.println(play.deck[i].toString());
//		}
//		// shuffle
//		play.shuffle(play.deck);
//		// print again
//		for (int i = 0; i < 40; i++) {
//			System.out.println(play.deck[i].toString());
//		}
		
		
		// test deal by printing out player 1 cards
//		play.deal();
//		System.out.println(play.getPlayersArrayList().get(1).cardsArray.toString());
//		play.setTrump("Firepower");
//		System.out.println(play.getTrump());
		
		// test the add method in ComPile class
		// Changed arrays to String to make it easier
		// changed back after test
//		ArrayList <String> names = new ArrayList<String>();
//		names.add("Steph");
//		names.add("Robyn");
//		ComPile communal = new ComPile();
//		communal.addCards(names);
//		System.out.print(names +"\n");
//		// test remove method
//		// print out what the method returns
//		System.out.print(communal.removeCards());
//		// print out the empty comPile array
//		System.out.println(communal.comPile);
		
		// test maxNum
		int [] numbers = new int[] {10, 4, 8, 24, 18};
		//print the position of first instance of max number
		System.out.println(play.maxNum(numbers));
	}
	
}
