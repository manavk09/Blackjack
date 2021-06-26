package blackjack;

import java.util.Scanner;

public class Blackjack {
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Manav's BlackJack Table!");
		Deck deck = new Deck();
		deck.generateDeck();
		deck.shuffle();
		//int ddCount = 0;
		
		//player and dealer deck
		Deck playerDeck = new Deck();
		Deck dealerDeck = new Deck();
		
		double pMoney = 100.00;
		
		Scanner userIn = new Scanner(System.in);
		
		//starting the game
		while(pMoney > 0) {
			System.out.println("You Have $" + pMoney);
			System.out.println("(1)Start Round or (2)Quit");
			int r = userIn.nextInt();
			if(r == 1) {
				System.out.println( "You Have $" + pMoney +" How Much Would You Like to bet");
				double bet = userIn.nextDouble();
				
				
				if(bet>pMoney) {
					System.out.println("You dont have that much money to bet please try again!");
					bet = userIn.nextDouble();
				}
				boolean end = false;
				/*
				 * after bet is valid start dealing
				 * player gets 2 cards
				 * dealer gets 2 cards
				 */
				
				playerDeck.drawCard(deck);
				playerDeck.drawCard(deck);
				
				dealerDeck.drawCard(deck);
				dealerDeck.drawCard(deck);
				
				
				//hit logic
				while(true) {
					System.out.println("Your Hand: ");
					System.out.println(playerDeck.toString());
					System.out.println("Your Hand Value: " + playerDeck.handValue());
					
					//display dealer card
					System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString());
					
					/*
					 * if the player can double down present the option for double down
					 * bet*2 < pMoney - bet;
					 * player decision
					 */
					if(bet>pMoney - bet) {
						System.out.println("Would you like to (1)Hit or (2)Stand?");
					}
					else {
						System.out.println("Would you like to (1)Hit or (2)Stand or (3)Double Down");
					}

					
					
					int reply = userIn.nextInt(); 
					
							
					if(reply == 1) {
						playerDeck.drawCard(deck);
						System.out.println("You Drew: " + playerDeck.getCard(playerDeck.deckSize()).toString());
						
						if(playerDeck.handValue() > 21) {
							System.out.println("You bust! Current Hand Value = " + playerDeck.handValue());
							//subtract the money
							pMoney -= bet;
							end = true;
							break;
						}

					}
					if(reply == 2) {
						break;
					}
					/*
					 * if the player chooses to double down
					 * increase the double down count
					 * we double bet
					 * draw one card
					 * then show
					 */
					if(reply == 3) {
						//ddCount++;
						bet *= 2;
						playerDeck.drawCard(deck);
						System.out.println("You Drew: " + playerDeck.getCard(playerDeck.deckSize()).toString());
						System.out.println("Current Hand Value = " + playerDeck.handValue());
						if(playerDeck.handValue() > 21) {
							System.out.println("You bust! Current Hand Value = " + playerDeck.handValue());
							//subtract the money
							pMoney -= bet;
							end = true;
							break;
						}
						else {
							//System.out.println("You bust! Current Hand Value = " + playerDeck.handValue());
							break;
						}
						
						
					}
				}
				
				//Show
				System.out.println("Dealer Cards: " + dealerDeck.toString());
				
				if(dealerDeck.handValue() > playerDeck.handValue() && end == false) {
					System.out.println("Dealer Wins!");
					pMoney -= bet;
					end = true;
				}
				
				//dealer keeps drawing till 16, and stand at 17
				while((dealerDeck.handValue() < 17) && end != true) {
					dealerDeck.drawCard(deck);
					System.out.println("Dealer Drew: " + dealerDeck.getCard(dealerDeck.deckSize()).toString());
				}
				
				System.out.println("Dealer's Hand Value: " + dealerDeck.handValue());
				
				if((dealerDeck.handValue() > 21) && end == false) {
					System.out.println("Dealer Bust!");
					pMoney += bet;
					end = true;
				}
				
				//if push
				
				if((playerDeck.handValue() == dealerDeck.handValue()) && end == false){
					
					System.out.println("Push!!!");
					end = true;
				}
				
				if((playerDeck.handValue() > dealerDeck.handValue()) && end == false) {
					System.out.println("You Win!!!!");
					pMoney += bet;
					end = true;
				}
				else if(end == false) {
					System.out.println("You Lose!");
					pMoney -= bet;
					end = true;
				}
				
				playerDeck.move(deck);
				dealerDeck.move(deck);
				System.out.println("End of Round!!!!");
			}
			
			else if(r == 2) {
				System.out.println("Have A Nice Day! Hope to See You Again!");
				break;
			}	
			
		}
		if(pMoney == 0) {
			System.out.println("You are Out of Money$$$$$");
		}
		userIn.close();
		
	}

}
