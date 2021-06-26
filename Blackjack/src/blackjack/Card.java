package blackjack;

public class Card {

	//card includes a suit and a value;
	private Suit suit;
	private Value val;
	//constructor
	public Card(Suit suit, Value val) {
		this.suit = suit;
		this.val = val;
	}
	
	public String toString() {
		if(this.suit.toString().equals("DIAMOND")) {
			return this.val.toString() + "\u2666" ;
		}
		else if(this.suit.toString().equals("CLUB")) {
			return this.val.toString() + "\u2663";
		}
		else if(this.suit.toString().equals("SPADE")) {
			return this.val.toString() + "\u2660";
		}
		else {
			return this.val.toString() + "\u2665";
		}
		
	}
	
	/*
	 * getter methods
	 */
	public Value getValue() {
		return this.val;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
}
