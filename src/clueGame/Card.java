/* Card
 * Purpose: This class contains a static version of the deck,
 * 			Each Card is created via the constructor, then added
 * 			to the totalDeck field.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein 
 */
package clueGame;

public class Card implements GameControl{

	//Data Structures and member fields
	private String cardName;
	private String cardSymbol;
	private CardType cardtype = CardType.NONE;


	//Card copier???
	public Card(Card thisCard) {
		this.cardName = thisCard.cardName;
		this.cardtype = thisCard.cardtype;
		this.cardSymbol = thisCard.cardSymbol;
	}
	
	//Constructor
	public Card(String newCardType, String newCardName, String newCardSymbol) {
		this.cardName = new String(newCardName);
		this.cardSymbol = new String(newCardSymbol);
		this.cardtype = GameControl.findCardTypeByString(newCardType);
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
	
}