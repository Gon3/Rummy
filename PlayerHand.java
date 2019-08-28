import java.util.*;
public class PlayerHand extends Deck {
	//CONSTRUCTOR
	public PlayerHand() {
		super(true); 
	}
	//CLASS METHODS
	public void dealIn(Deck d , int cardAmount) { //deals in this player hand
		 ArrayList<Card> deck = d.getTrueDeck();
		 ListIterator<Card> liter = deck.listIterator(); 
		 
		 while(liter.hasNext()) {
			 this.getTrueDeck().add(liter.next()); 
			 liter.remove();
			 if(liter.nextIndex() == --cardAmount)
				 break; 
		 }
		 
	}
	
	public void takeACard(Deck d) { //takes a card from the deck and inserts it into this player hand
		ArrayList<Card> deck = d.getTrueDeck();
		this.getTrueDeck().add(deck.get(0)); 
		deck.remove(0); 	
	}
	
	public void discard(Deck d , int c) { //discards a card from this player hand to the deck
		ArrayList<Card> deck = d.getTrueDeck(); 
		deck.add(0, this.getTrueDeck().get(c));
		this.getTrueDeck().remove(c); 
	}
	
	public String toString() { //allows for the player hand to be printed out correctly (and w/ index numbers)
		int amount = this.getTrueDeck().size();
		String numbers = "";
		for(int i = 0 ; i < amount ; i++)
			numbers += (i + 1) + "   ";
		return numbers + "\n" + super.toString();
	}
	
	

}
