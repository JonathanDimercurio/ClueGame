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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Card {

	//Data Structures and member fields
	static private Set<Card> totalRooms 	= new HashSet<Card>();
	static private Set<Card> totalPeople 	= new HashSet<Card>();
	static private Set<Card> totalWeapons 	= new HashSet<Card>();
	private String cardName;
	private String cardSymbol;
	private CardType cardtype = CardType.NONE;
	static private Map<String, Card> totalDeck = new HashMap<>();

	public Card(Card thisCard) {
		this.cardName = thisCard.cardName;
		this.cardtype = thisCard.cardtype;
		this.cardSymbol = thisCard.cardSymbol;
	}
	
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
	 void checkType(String checkType) {
		switch (checkType) {
			case "Person":
				this.cardtype = CardType.PERSON;
				Card.totalPeople.add(this);
				break;
			case "Weapon":
				this.cardtype = CardType.WEAPON;
				Card.totalWeapons.add(this);
				break;
			case "Room":
				this.cardtype = CardType.ROOM;
				Card.totalRooms.add(this);
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
	public static Set<Card> getTotalRooms() {
		return totalRooms;
	}
	public static Set<Card> getTotalPeople() {
		return totalPeople;
	}
	public static Set<Card> getTotalWeapons() {
		return totalWeapons;
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

		@Override
	public String toString() {
		return "Card [cardName=" + cardName + ", cardSymbol=" + cardSymbol + "]";
	}

	
}