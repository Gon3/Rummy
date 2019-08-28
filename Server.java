import java.util.*;

public abstract class Server{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in); 
		//creates all the players and their melds which might be potentially used
		PlayerHand p1 = new PlayerHand(); 
		Deck p1Meld1 = new Deck(true);
		Deck p1Meld2 = new Deck(true);
		PlayerHand p2 = new PlayerHand(); 
		Deck p2Meld1 = new Deck(true);
		Deck p2Meld2 = new Deck(true);
		PlayerHand p3 = new PlayerHand();
		Deck p3Meld1 = new Deck(true);
		Deck p3Meld2 = new Deck(true);
		PlayerHand p4 = new PlayerHand();
		Deck p4Meld1 = new Deck(true);
		Deck p4Meld2 = new Deck(true);
		//instantizes the client class
		playRummy play = new playRummy(); 
		//creates the two decks
		Deck mainPile = new Deck();
		Deck discardPile = new Deck(true); 
		//shuffles the main deck
		mainPile.shuffleDeck(); 
		int n; 
		//Error Checking for input
		while(true) {
		//asks how many people are playing a saves the number entered
		System.out.println("How many people are playing? (4 is the max)");
		n = scan.nextInt(); 
		if(n < 2 || n > 4) 
			System.out.println( "Incorrect input. Please try again. ");
		else
			break; 
		}
		// deals in the number of people that the user entered before
		switch(n) {
		case 2: p1.dealIn(mainPile, 7); p2.dealIn(mainPile, 7); break; 
		case 3: p1.dealIn(mainPile, 7); p2.dealIn(mainPile, 7); p3.dealIn(mainPile, 7); break; 
		case 4: p1.dealIn(mainPile, 7); p2.dealIn(mainPile, 7); p3.dealIn(mainPile, 7); p4.dealIn(mainPile, 7); break;
		}
		
		int player;
		while(true) {
				player = 1; //a mark that shows who wins the game
				System.out.println("It is Player 1's turn. \nYour hand:");
				//player draws a card (if its from the discard pile the program saves the card)
				Card discardedCard = play.drawACard(p1, mainPile, discardPile); 
				//prints out the deck with the newly drawed card
				System.out.println("Your deck with the drawed card: ");
				
				System.out.println(p1);
				//player builds/layoffs a meld if he/she wants or can
				boolean meldBuilt = play.buildMelds(p1, p1Meld1, p1Meld2); 
				//if the player's deck is empty, the player won 
				if(p1.getTrueDeck().isEmpty())
					break; 
				//if the player didn't build a meld, he/she will have to discard a card
				//(this is where the discard card the program saved will come in handy)
				if(!meldBuilt)
					play.discardACard(p1, discardPile, discardedCard);
				//the game advances to the next turn and the process is repeated for the next player
				System.out.println();
				
				player = 2; 
				
				System.out.println("It is Player 2's turn. \nYour hand:");
				
				discardedCard = play.drawACard(p2, mainPile, discardPile); 
				
				System.out.println("Your deck with the drawed card: ");
				
				System.out.println(p2);
				
				meldBuilt = play.buildMelds(p2, p2Meld1, p2Meld2); 
				
				if(p2.getTrueDeck().isEmpty())
					break; 
				
				if(!meldBuilt)
					play.discardACard(p2, discardPile, discardedCard);
				
				System.out.println(); 
				
				if(n < 3) //makes sure the correct amount of people entered is playing
					continue; 
				
				player = 3; 
				
				System.out.println("It is Player 3's turn. \nYour hand:");
				
				discardedCard = play.drawACard(p3, mainPile, discardPile); 
				
				System.out.println("Your deck with the drawed card: ");
				
				System.out.println(p3);
				
				meldBuilt = play.buildMelds(p3, p3Meld1, p3Meld2); 
				
				if(p3.getTrueDeck().isEmpty())
					break; 
				
				if(!meldBuilt)
					play.discardACard(p3, discardPile, discardedCard);
				
				System.out.println(); 
				
				if(n < 4) //makes sure the correct amount of people entered is playing
					continue; 
				
				player = 4; 
				
				System.out.println("It is Player 4's turn. \nYour hand:");
				
				discardedCard = play.drawACard(p4, mainPile, discardPile); 
				
				System.out.println("Your deck with the drawed card: ");
				
				System.out.println(p4);
				
				meldBuilt = play.buildMelds(p4, p4Meld1, p4Meld2); 
				
				if(p4.getTrueDeck().isEmpty())
					break; 
				
				if(!meldBuilt)
					play.discardACard(p4, discardPile, discardedCard);	
				
				System.out.println(); 
		}
		//notifies which player had one
		System.out.println("Congrats player " + player + ". You won the game!");
 }
}
