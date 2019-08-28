import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class playRummy {

	Scanner scan = new Scanner(System.in); 
	//method that sees if the meld entered is truly a meld
	private boolean validateMeld(Deck meld) {
		ArrayList<Card> Meld = meld.getTrueDeck(); 
		//checks for three of a kind first
		for(int i = 0 ; i < Meld.size() - 1 ; i++) {
			if(Meld.get(i).getRank() != Meld.get(i+1).getRank())
				break;
			if(i == Meld.size() - 2)
				return true; 
		}
		//checks for sequential order with same suits after
		Collections.sort(Meld);
		
		for(int i = 0 ; i < Meld.size() - 1 ; i++) {
			if(Meld.get(i).getSuit() != Meld.get(i+1).getSuit())
				break; 
			if(Meld.get(i).getRank().ordinal() != Meld.get(i+1).getRank().ordinal() - 1)
				break; 
			if(i == Meld.size() - 2)
				return true; 
		}
		
		return false; 
	}
	//method that plays out the part of the turn where the player draws a card
	//returns the card taken from the discard pile in order to emsure the player doesn't discards it later in in his/her turn
	public Card drawACard(PlayerHand player, Deck mainPile, Deck discardPile) {
		System.out.println(player);  
		//if mainpile is empty, shuffle discardPile and transfer to mainPile
		if(mainPile.getTrueDeck().isEmpty()) {
			discardPile.shuffleDeck();
			mainPile.transferDeck(discardPile);
		}
		//if discardPile is empty then noptify play and just take from the mainPile 
		//no card will be returned since discardPile is empty also
		if(discardPile.getTrueDeck().isEmpty()) {
			System.out.println("Since the discard Pile is empty you can only take from the main pile.");
		    player.takeACard(mainPile);
		    return null; 
		}
		//if both piles are full allow the player to make a choice between them
		//it will return the card the player chose from the discard pile if player choose discard pile
		//otherwise it will return null 
		else {
			System.out.println("The top card on the discard pile is: " + discardPile.getTrueDeck().get(0));
			System.out.println("Do you want to take from the main pile or the discard pile?");
			while(true) {
			String response = scan.nextLine(); 
			//Error Checking for Input
			if(response.equalsIgnoreCase("main")) {
				player.takeACard(mainPile);
				break; 
			}
			else if(response.equalsIgnoreCase("discard")) {
				player.takeACard(discardPile);
				return player.getTrueDeck().get(player.getTrueDeck().size()-1); 
			}
			else
				System.out.println( "Incorrect input. Please try again. ");
			}
		}
		
		return null; 
	}
	//method that plays out the part of the turn where the player builds melds and/or layoff on any already built meld
	//returns a boolean value to ensure that if the player built a meld he/she will not discard and if he/she did not build
	//a meld then he/she will need to discard
	public boolean buildMelds(PlayerHand player, Deck meld1, Deck meld2) {
		boolean meldBuilt = false; //boolean value being returned 
		//checks if the player has melds built in the first place
		if(meld1.getTrueDeck().isEmpty() && meld2.getTrueDeck().isEmpty()) {
			System.out.println("You have no melds built. Would you like to build a meld?");
			while(true) {
				String response2 = scan.nextLine(); 
				//Error checking for Input
				if(response2.equalsIgnoreCase("yes")) {
					meldBuilt = !meldBuilt; //switches the values to ensure that the player has created a meld
					System.out.println("Do you want 1 or 2 melds?");
					int numMelds;
					//Error Checking for input 
					while(true) {
					numMelds = scan.nextInt();
					scan.nextLine(); 
					if(numMelds < 1 || numMelds > 2)
						System.out.println("Incorrect Input. Try Again.");
					else
						break; 
					}
					for(int i = 0 ; i  < numMelds ; i++) {
						//loops that repeats until player has successfully entered cards that will form the number of melds he/she mentioned
						while(true) {
						System.out.println("For meld " + (i + 1) + " input the indexes of the cards you want to build the meld with.(It is the nunber above the card's name)");
						System.out.println("Do not include spaces or commas when inputting the indexes. (For Ex: 123 for indexes 1 , 2, and 3)"); 
						System.out.println(player);
						int cards = scan.nextInt(); 
						scan.nextLine();
						//loop that builds the melds
						while(cards % 10 != 0) {
							if(i == 0)
							player.discard(meld1, (cards % 10) - 1);
							else
							player.discard(meld2, (cards % 10) - 1);
							cards /= 10; 
						}
						//validation of each meld
						if(i == 0) {
							if(meld1.getTrueDeck().size() <= 2 || !validateMeld(meld1)) {
								System.out.println("Meld 1 is not valid. Try Again (Make sure it is 3 or more cards!) ");
								player.transferDeck(meld1);
							}
							else
								break; 
						}
						else {
							if(meld2.getTrueDeck().size() <= 2 || !validateMeld(meld2)) {
								System.out.println("Meld 2 is not valid. Try Again (Make sure it is 3 or more cards!) ");
								player.transferDeck(meld2);
							}
							else
								break; 
						}
						}
					}
					break; 
				}
	
				else if(response2.equalsIgnoreCase("no")) {
					break;
				}
				else
					System.out.println( "Incorrect input. Please try again. ");
				}
			}
			else {//this section plays out if both melds are not empty 
				//prints out melds and if one is empty asks player if he wants to build one more meld
				System.out.println("Your melds: ");
				if(meld2.getTrueDeck().isEmpty()) {
					System.out.println("Meld 1: " + meld1 + "\tMeld 2 is empty");
					System.out.println("Would you like to build another meld?");
					//Error Checking the input
					while (true) {
					String response = scan.nextLine(); 
					if(response.equalsIgnoreCase("yes")) { //if yes builds the meld the same way it was done above
						meldBuilt = !meldBuilt;
					while(true) {
						System.out.println("For meld 2 input the indexes of the cards you want to build the meld with.(It is the nunber above the card's name)");
						System.out.println("Do not include spaces or commas when inputting the indexes. (For Ex: 123 for indexes 1 , 2, and 3)"); 
						System.out.println(player);
						int cards = scan.nextInt(); 
						scan.nextLine();
						while(cards % 10 != 0) {
							player.discard(meld2, (cards % 10) - 1);
							cards /= 10; 
						}
							if(meld2.getTrueDeck().size() <= 2 || !(validateMeld(meld2))) {
								System.out.println("Meld 2 is not valid. Try Again (Make sure it is 3 or more cards!) ");
								player.transferDeck(meld2);
							}
							else
								break; 
					}
						break;
					}
					else if(response.equalsIgnoreCase("no")) 
							break;
					
					else
						System.out.println( "Incorrect input. Please try again. ");
					}
				}
				//if both melds are built it gives you the option to layoff on a meld
				System.out.println("Meld 1: " + meld1 + "\t" + "Meld 2: " + meld2);
				System.out.println("Would you like to layoff on a meld?" );
				//Error checking the input
				while(true) {
					String response = scan.nextLine(); 
					if(response.equalsIgnoreCase("yes")) {
						meldBuilt = !meldBuilt; //layoffs activates the switch too 
						//asks which meld player would like to layoff on and layoff on the meld
						System.out.println("Which Meld would you like to layoff on (1 for meld 1 , 2 for meld 2)?");
						int m; 
						//Error checking for input
						while(true) {
						m = scan.nextInt(); 
						scan.nextLine();
						if(m < 1 || m > 2)
							System.out.println("Incorrect Input. Try Again.");
						else
							break; 
						}
						if(m == 1) {
							//loops until the correct cards to layoff is input 
							while(true) {
							System.out.println(player);
							System.out.println("Type the index(es) of the card(s) you would like to layoff.");
							int cards = scan.nextInt(); 
							scan.nextLine();
							ArrayList<Card> layoffs = new ArrayList<>();
							while(cards % 10 != 0) {
								layoffs.add(player.getTrueDeck().get(cards % 10));
								player.discard(meld1, (cards % 10) - 1);
								cards /= 10; 
							}
							if(!validateMeld(meld1)) {
								System.out.println("Your layoff wasn't valid. Try Again.");
								meld1.getTrueDeck().removeAll(layoffs); 
								player.getTrueDeck().addAll(layoffs); 
							}
							else
								break; 
						}
						}
						else {
							while(true) { //loops until the correct cards to layoff for the second meld is input
								System.out.println(player);
								System.out.println("Type the index(es) of the card(s) you would like to layoff.");
								int cards = scan.nextInt(); 
								scan.nextLine();
								ArrayList<Card> layoffs = new ArrayList<>();
								while(cards % 10 != 0) {
									layoffs.add(player.getTrueDeck().get(cards % 10));
									player.discard(meld2, (cards % 10) - 1);
									cards /= 10; 
								}
								if(!validateMeld(meld2)) {
									System.out.println("Your layoff wasn't valid. Try Again.");
									meld2.getTrueDeck().removeAll(layoffs); 
									player.getTrueDeck().addAll(layoffs); 
								}
								else
									break; 
							}
							
						}
						
						break; 
					}
					else if(response.equalsIgnoreCase("no")) {
						break;
					}
					else
						System.out.println("Incorrect input. Please try again. ");
					}	
			}
		return meldBuilt; //returns the boolean value that acts as the switch
	}
	
	public void discardACard(PlayerHand player, Deck discardPile, Card discardedCard) {
		int c; 
		while(true) { //loops until player enters a valid card to discard
			System.out.println("Select the index of the card you want to discard (the index is the number above the name of the card): ");
			System.out.println(player);
			c = scan.nextInt(); 
			scan.nextLine(); 
			if(player.getTrueDeck().get(c - 1).equals(discardedCard)) //ensures player doesn't discard the card he/she picked from the discard pile
				System.out.println("You cannot discard the same card you took from the discard pile. Try Again.");
			else
				break; 	
		}
		player.discard(discardPile, (c - 1)); //discards the card the player entered
	}

}
