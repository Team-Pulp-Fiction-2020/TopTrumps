package commandline;
//package commandline;
import java.util.ArrayList;

public class ComPile {
	// create arrayList for the cards
	ArrayList <Card> comPile = new ArrayList<Card>();
	
	// method to take in an arrayList and add it to the comPile arrayList
	public void addCards(ArrayList al) {
			comPile.addAll(al);
	}
	
	// method to remove cards from the comPile and return the comPileTemp
	// which is a copy of the comPile
	public ArrayList<Card> removeCards (){
		ArrayList <Card> comPileTemp = new ArrayList<Card>();
		for(int i = 0; i<comPile.size(); i++) {
			comPileTemp.addAll(comPile);
			comPile.clear();
		}
		return comPileTemp;
	}

	public int getComPileSize() {
		int size = 0;
		if(!(comPile.isEmpty())) {
			size = comPile.size();
		}
		return size;
	}

	public ArrayList<Card> getComPile() {
		return comPile;
	}
	
}
