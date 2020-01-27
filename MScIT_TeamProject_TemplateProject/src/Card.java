import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
//import java.util.Arrays;


public class Card {
	
	public Card(String name, int size, int speed, int range, int firepower, int cargo) {
		this.name = name;
		this.size = size;
		this.speed = speed;
		this.range = range;
		this.firepower = firepower;
		this.cargo = cargo;
		
		}
	
	public static void main(String[]args) {
		Card shipArray[] = new Card[40];
		shipArrayFill();
	}
////		shipArray[0] = new Card("350r", 1, 9, 2, 3, 0);
////		System.out.println(shipArray[0]);
//		int nShips = 0;
//		
//		
//		FileReader fr = null;
//		try {
//			fr = new FileReader("C:\\Users\\atrai\\TeamProject\\Workspace\\MScIT_TeamProject_TemplateProject\\MScIT_TeamProject_TemplateProject/TopTrumpsCsv.csv");
//
////			fr = new FileReader("C:\\Users\\atrai\\TeamProject\\Workspace\\MScIT_TeamProject_TemplateProject\\MScIT_TeamProject_TemplateProject/StarCitizenDeck.txt");
////			String fN = "C:\\Users\\atrai\\TeamProject\\Workspace\\MScIT_TeamProject_TemplateProject\\MScIT_TeamProject_TemplateProject/StarCitizenDeck.txt";
//			Scanner s = new Scanner(fr);
//			while(s.hasNextLine()) {
//				String line = s.nextLine();
//				String[] shipData = line.split(",");
//				String shipName = shipData[0];
//				int shipSize = Integer.parseInt(shipData[1]);
//				int shipSpeed = Integer.parseInt(shipData[2]);
//				int shipRange = Integer.parseInt(shipData[3]);
//				int shipFirepower = Integer.parseInt(shipData[4]);
//				int shipCargo = Integer.parseInt(shipData[5]);
//				shipArray[nShips++] = new Card(shipName, shipSize, shipSpeed, shipRange, shipFirepower, shipCargo);
//				
//				
//			}
////			fr = new FileReader(fN);
//		
//		}catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				fr.close();
//				
//			}catch(IOException e) {
//				e.printStackTrace();
//			}
//		}
//		if(nShips>0) {
//			for(int i=0; i<nShips; i++) {
//				System.out.println(shipArray[i]);
//			}
//		}
//		
//		
////		Card card = new Card("bob",1,2,3,4,5);
////		System.out.println(card);
//	}
	
	public static Card[] shipArrayFill() {
		Card shipArray[] = new Card[40];
//		shipArray[0] = new Card("350r", 1, 9, 2, 3, 0);
//		System.out.println(shipArray[0]);
		int nShips = 0;
		
		
		FileReader fr = null;
		try {
			fr = new FileReader("C:\\Users\\atrai\\TeamProject\\Workspace\\MScIT_TeamProject_TemplateProject\\MScIT_TeamProject_TemplateProject/TopTrumpsCsv.csv");

//			fr = new FileReader("C:\\Users\\atrai\\TeamProject\\Workspace\\MScIT_TeamProject_TemplateProject\\MScIT_TeamProject_TemplateProject/StarCitizenDeck.txt");
//			String fN = "C:\\Users\\atrai\\TeamProject\\Workspace\\MScIT_TeamProject_TemplateProject\\MScIT_TeamProject_TemplateProject/StarCitizenDeck.txt";
			Scanner s = new Scanner(fr);
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] shipData = line.split(",");
				String shipName = shipData[0];
				int shipSize = Integer.parseInt(shipData[1]);
				int shipSpeed = Integer.parseInt(shipData[2]);
				int shipRange = Integer.parseInt(shipData[3]);
				int shipFirepower = Integer.parseInt(shipData[4]);
				int shipCargo = Integer.parseInt(shipData[5]);
				shipArray[nShips++] = new Card(shipName, shipSize, shipSpeed, shipRange, shipFirepower, shipCargo);
				
				
			}
//			fr = new FileReader(fN);
		
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(nShips>0) {
			for(int i=0; i<nShips; i++) {
//				System.out.println(shipArray[i]);
//				return shipArray;
			}
		}
		return shipArray;
		
		
//		Card card = new Card("bob",1,2,3,4,5);
//		System.out.println(card);
	
	}
	
	public String toString() {
		return "Name: " + name + ", Size: " + size + ", Speed: " + speed + ", Range: " + range + ", Firepower: " +  firepower + ", Cargo: " + cargo;
		
	}
	public void cardArray() {
		
	}
	
	String name;
	int size;
	int speed;
	int range;
	int firepower;
	int cargo;
	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRange() {
		return range;
	}

	public int getFirepower() {
		return firepower;
	}

	public int getCargo() {
		return cargo;
	}
	
	

}
