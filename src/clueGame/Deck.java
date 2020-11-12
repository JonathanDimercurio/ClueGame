package clueGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Deck implements GameControl{
	
	private static Set<Card> completeDeck;
	private static Set<Card> totalPeopleDeck;
	private static Set<Card> totalWeaponDeck;
	private static Set<Card> totalRoomDeck;
	
	public Deck() {
		if (completeDeck == null) {
			Deck.completeDeck = new HashSet<Card>();
			constructCompleteDeck(ClueFileIO.getFormattedSetupFile());
			Deck.totalPeopleDeck = new HashSet<Card>();
			Deck.totalWeaponDeck = new HashSet<Card>();
			Deck.totalRoomDeck = new HashSet<Card>();
			createSeperateTypeDecks();
		}
	}
	
	/* constructComepleteDeck ~
	 * This method will construct a static deck, which will be used later to deal cards,
	 * instantiated players, and form the Solution.
	 */
	private void constructCompleteDeck(ArrayList<String[]> SetupFileData) {
		String tempCardType, tempCardName, tempCardSymbol;
		for (int i = 0; i < SetupFileData.size(); ) {
			tempCardType = new String(SetupFileData.get(i)[0]);
			tempCardName = new String(SetupFileData.get(i)[1]);
			tempCardSymbol = new String(SetupFileData.get(i)[2]);
			Card tempCard = new Card(tempCardType, tempCardName, tempCardSymbol);
			completeDeck.add(tempCard);
		}
	}
	
	/* placeInTypeDeck ~
	 * This simple method places the newly crafted card into
	 * the correct deck, by CardType.
	 */
	private void createSeperateTypeDecks() {
		for (Card tempCard: Deck.completeDeck) {
			switch (tempCard.getCardtype()) {
				case PERSON:
					Deck.totalPeopleDeck.add(tempCard);
				case WEAPON:
					Deck.totalWeaponDeck.add(tempCard);
				case ROOM:
					Deck.totalRoomDeck.add(tempCard);
				default:				
					break;
			}
		}
	}

	/* dealersDeck() 	~ Returns: List<Card>
	 * Purpose: 
	 */
	public static List<Card> dealersDeck() {
		List<Card> dealersDeck = new Vector<Card>();
		dealersDeck.addAll(Deck.completeDeck);
		Collections.shuffle(dealersDeck);
		return dealersDeck;
	}

	
	//GENERIC	getters
	public static Set<Card> getCompleteDeck() {
		return completeDeck;
	}

	public static Set<Card> getTotalPeopleDeck() {
		return totalPeopleDeck;
	}

	public static Set<Card> getTotalWeaponDeck() {
		return totalWeaponDeck;
	}

	public static Set<Card> getTotalRoomDeck() {
		return totalRoomDeck;
	}
	
	
}
