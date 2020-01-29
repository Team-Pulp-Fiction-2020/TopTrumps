
public class MainTests {

	public static void main(String[] args) {
		PlayGame play = new PlayGame(4);

		// print the deck of cards
//		for (int i = 0; i < 40; i++) {
//			System.out.println(play.deck[i].toString());
//		}
//		// shuffle
//		play.shuffle(play.deck);
//		// print again
//		for (int i = 0; i < 40; i++) {
//			System.out.println(play.deck[i].toString());
//		}
		
		// deal the cards and print out player 1 cards
		play.deal();
		System.out.println(play.getPlayersArrayList().get(1).cardsArray.toString());
		
		play.setTrump("Firepower");
		System.out.println(play.getTrump());
	}

}
