/* Card
 * Purpose: This class contains a static version of the deck,
 * 			Each Card is created via the constructor, then added
 * 			to the totalDeck field.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein 
 */

package clueGame;

import java.util.HashMap;
import java.util.Map;

public class Card {

	static private String totalRooms = new String();
	static private String totalPersons = new String();
	static private String totalWeapons = new String();
	private String cardName;
	private String cardSymbol;

	static private Map<String, Card> totalDeck = new HashMap<>();
	CardType cardtype = CardType.NONE;

		
	public Card(String cardType, String cardID, String cardSym) {
		this.cardName = new String(cardID);
		this.cardSymbol = new String(cardSym);
		Card.totalDeck.put(cardSym, this);
		checkType(cardType);
	}
	
	/* checkType(String)
	 * Purpose:	This method checks, and sets each card type.
	 * 			If an incorrect card type attempts to enter
	 * 			the deck, an exception is created.
	 */
	private void checkType(String checkType) {
		switch (checkType) {
			case "Person":
				this.cardtype = CardType.PERSON;
				Card.totalPersons.concat(this.cardSymbol + '-');
				break;
			case "Weapon":
				this.cardtype = CardType.WEAPON;
				Card.totalWeapons.concat(this.cardSymbol + '-');
				break;
			case "Room":
				this.cardtype = CardType.ROOM;
				Card.totalRooms.concat(this.cardSymbol + '-');
				break;
			default:
				new BadConfigFormatException ("Card is not valid, please check ClueSetup.txt for errors.");
				break;
		}
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