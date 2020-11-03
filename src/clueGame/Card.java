package clueGame;

public class Card {
	String cardName;
	
	public Card(String cardType, String cardID, char cardSym) {
		this.cardName = new String(cardID);
	}
	
	/* equals(...)
	 * Purpose: The object compares a parameter Card type
	 * 			to it's self and returns true if it is the same
	 */
	public boolean equals(Card compareCard) {
		if (this == compareCard) {
			return true;
		} else {
			return false;
		}
	}
}
