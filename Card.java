enum Rank {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
	}
	
enum Suit {
		DIAMOND, CLUB, SPADE, HEART
	}

public class Card implements Comparable<Card> {
	//INSTANCE VARIABLES
	private Rank cardRank; 
	private Suit cardSuit; 
	
	//CONSTRUCTOR
	public Card(Rank rank , Suit suit) {
		cardRank = rank; 
		cardSuit = suit; 
	}
	//ACCESSORS
	public Rank getRank() { return cardRank; } 
	
	public Suit getSuit() { return cardSuit; }
	//CLASS METHODS	
	public String toString() { //allows the cards to be printed out correctly
		
		String cardString = null;  
		
		switch(cardRank) {
		case TWO: cardString = "2"; break;
		case THREE: cardString = "3"; break; 
		case FOUR: cardString = "4"; break; 
		case FIVE: cardString = "5"; break; 
		case SIX: cardString = "6"; break; 
		case SEVEN: cardString = "7"; break; 
		case EIGHT: cardString = "8"; break; 
		case NINE: cardString = "9"; break; 
		case TEN: cardString = "T"; break; 
		case JACK: cardString = "J"; break; 
		case QUEEN: cardString = "Q"; break; 
		case KING: cardString = "K"; break; 
		case ACE: cardString = "A"; break; 	
		}
		
		switch(cardSuit) {
		case DIAMOND: cardString += "\u2666"; break; 
		case CLUB: cardString += "\u2663"; break;
		case SPADE: cardString += "\u2660"; break; 
		case HEART: cardString += "\u2665"; break; 
		}
		
		return cardString; 
	}
	
	public int compareTo(Card other) { //allows the cards to be sorted correctly
		if(cardRank.ordinal() > other.cardRank.ordinal())
			return 1; 
		else if(cardRank.ordinal() < other.cardRank.ordinal())
			return -1; 
		else
			return 0; 
	}
}
