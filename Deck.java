import java.util.*; 


public class Deck {
	//INSTANCE VARIABLES
	private ArrayList<Card> deck; 
	//CONSTRUCTORS
	public Deck() {
		
		this(false); //deck is full by default
	
	}
	
	public Deck(boolean isEmpty) {
		
		if(isEmpty) { //makes an empty deck
			
			deck = new ArrayList<Card>(); 
			
		}
		else { //makes a full deck
			
			deck = new ArrayList<Card>();
			
			Rank[] ranks = Rank.values(); 
			Suit[] suits = Suit.values(); 
			
			for(int s = 0 ; s < suits.length ; s++) {
				for(int r = 0 ; r < ranks.length ; r++) 
					deck.add(new Card(ranks[r], suits[s])); 
			}	
			
		}
	}
	//ACCESSOR
	public ArrayList<Card> getTrueDeck() { return deck; }
	//CLASS METHODS
	private void swapCard(int a, int b) { //swaps card, only intended for use by shuffleDeck method
		 Card temp = deck.get(a); 
		 deck.set(a, deck.get(b)); 
		 deck.set(b, temp); 
	}
	
	public String toString(){ //allows deck to be printed correctly
		String deckString = ""; 
		for(Card c : deck) 
			deckString += c + "  ";
		
		return deckString; 
	}
	
	public void shuffleDeck() { //shuffles the deck
		Random gen = new Random(); 
		for(int i = 0 ; i < deck.size() ; i++) 
			swapCard(i, gen.nextInt(deck.size())); 
	}
	
	public void transferDeck(Deck d2) { //transfers the card from one deck to another
		ArrayList<Card> deck2 = d2.getTrueDeck(); 
		deck.addAll(deck2); 
		deck2.clear();
	}
	
	
	
	

}
