/* Card
 * Purpose: This class contains a static version of the deck,
 * 			Each Card is created via the constructor, then added
 * 			to the totalDeck field.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein 
 */
package clueGame;

import java.util.List;

public class Card {

	//Data Structures and member fields
	private String cardName;
	private String cardSymbol;
	private CardType cardtype = CardType.NONE;

	//Card copier???
	public Card(Card copyCard) {
		this.cardName = copyCard.cardName;
		this.cardtype = copyCard.cardtype;
		this.cardSymbol = copyCard.cardSymbol;
	}
	
	//Dumb String Constructor
	public Card(String newCardType, String newCardName, String newCardSymbol) {
		this.cardName = new String(newCardName);
		this.cardSymbol = new String(newCardSymbol);
		this.cardtype = CardType.findCardTypeByString(newCardType);
	}

	//ArrayConstructor //TODO concern about type
	public Card(List<String> newCardByArray) {
		this.cardtype = CardType.findCardTypeByString(newCardByArray.get(0));
		this.cardName = new String(newCardByArray.get(1));
		this.cardSymbol = new String(newCardByArray.get(2));
	}

	/*equals(...)
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

	//Getters
	public String getCardName() {
		return cardName;
	}
	public String getCardSymbol() {
		return cardSymbol;
	}
	public CardType getCardtype() {
		return cardtype;
	}
	public Card getThis() {
		return this;
	}
	
}