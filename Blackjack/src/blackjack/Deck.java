package blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> deckOfCards;
	
	public Deck() {
		this.deckOfCards = new ArrayList<Card>();
	}
	
	/*
	 * create the deck 
	 */
	public void generateDeck() {
		for(Suit cardSuit : Suit.values() ) {
			for(Value cardValue : Value.values()) {
				this.deckOfCards.add(new Card(cardSuit,cardValue));
			}
		}
	}
	
	/*
	 * shuffle 
	 */
	public void shuffle() {
		Random rand = new Random();
		int r = 0;
		ArrayList<Card> temp = new ArrayList<Card>();
		
		for(int i = 0; i < 52; i++) {
			r = rand.nextInt((this.deckOfCards.size()-1 - 0) + 1) + 0;
			Card tempCard = this.deckOfCards.get(r);
			temp.add(tempCard);
			this.deckOfCards.remove(r);
		}
		this.deckOfCards = temp;
		
	}
	
	public int deckSize() {
		return this.deckOfCards.size() - 1;
	}
	
	public void removeCard(int i) {
		this.deckOfCards.remove(i);
	}
	
	public Card getCard(int i) {
		return this.deckOfCards.get(i);
	}
	
	public void addCard(Card addCard) {
		this.deckOfCards.add(addCard);
	}
	
	public void drawCard(Deck input) {
		this.deckOfCards.add(input.getCard(0));
		input.removeCard(0);
	}
	
	
	public String toString() {
		String deck = "";
		for(Card card : this.deckOfCards) {
			deck += "\n" + card.toString();
		}
		return deck;
	}
	
	public int handValue() {
		int totalValue = 0;
		int A = 0;
		
		for(Card card : this.deckOfCards) {
			switch(card.getValue()) {
				case Two: totalValue += 2; break;
				case Three: totalValue += 3; break;
				case Four: totalValue += 4; break;
				case Five: totalValue += 5; break;
				case Six: totalValue += 6; break;
				case Seven: totalValue += 7; break;
				case Eight: totalValue += 8; break;
				case Nine: totalValue += 9; break;
				case Ten: totalValue += 10; break;
				case Jack: totalValue += 10; break;
				case Queen: totalValue += 10; break;
				case King: totalValue += 10; break;
				case Ace: A++; break;
			}
		}
		for(int i = 0; i < A; i++) {
			if(totalValue > 10) {
				totalValue += 1;
			}
			else {
				totalValue += 11;
			}
		}
		
		return totalValue;
	}
	
	public void move(Deck moveTo) {
		int deckSize = this.deckOfCards.size();
		
		for(int i = 0; i < deckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		
		for(int i = 0; i < deckSize; i++) {
			this.removeCard(0);
		}
		
	}
	
}
