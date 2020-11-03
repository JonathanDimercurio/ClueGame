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
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Card {

	//Data Structures and member fields
	static private List<Card> undealtRooms = new Vector<Card>();
	static private List<Card> undealtPeople = new Vector<Card>();
	static private List<Card> undealtWeapons = new Vector<Card>();
	private String cardName;
	private String cardSymbol;
	CardType cardtype = CardType.NONE;
	static private Map<String, Card> totalDeck = new HashMap<>();

	//Constructor
	public Card(String cardType, String cardID, String cardSym) {
		this.cardName = new String(cardID);
		this.cardSymbol = new String(cardSym);
		Card.totalDeck.put(cardSym, this);
		checkType(cardType);
	}
	
	/*checkType(String)
	 * Purpose:	This method checks, and sets each card type.
	 * 			If an incorrect card type attempts to enter
	 * 			the deck, an exception is created.
	 */
	private void checkType(String checkType) {
		switch (checkType) {
			case "Person":
				this.cardtype = CardType.PERSON;
				Card.undealtPeople.add(this);
				break;
			case "Weapon":
				this.cardtype = CardType.WEAPON;
				Card.undealtWeapons.add(this);
				break;
			case "Room":
				this.cardtype = CardType.ROOM;
				Card.undealtRooms.add(this);
				break;
			default:
				new BadConfigFormatException ("Card is not valid, please check ClueSetup.txt for errors.");
				break;
		}
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
	public static List<Card> getUndealtRooms() {
		return undealtRooms;
	}

	public static List<Card> getUndealtPeople() {
		return undealtPeople;
	}

	public static List<Card> getUndealtWeapons() {
		return undealtWeapons;
	}

	public String getCardName() {
		return cardName;
	}

	public String getCardSymbol() {
		return cardSymbol;
	}


	public CardType getCardtype() {
		return cardtype;
	}
	public static Map<String, Card> getTotalDeck() {
		return totalDeck;
	}

}