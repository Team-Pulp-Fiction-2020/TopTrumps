import java.util.ArrayList;

public class ComPile {
	private static ArrayList <Card> comPile = new ArrayList<Card>();
	
	public void addCards(ArrayList al) {
		for (int i=0; i<al.size(); i++) {
			comPile.addAll(i, al);
		}
	}
	
	public void removeCards {
		
	}
	 
}
