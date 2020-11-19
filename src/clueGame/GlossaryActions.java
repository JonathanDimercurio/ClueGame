package clueGame;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public interface GlossaryActions {

	public static final CardGlossary cardLib = CardGlossary.getGlossary();
	
	public static Collection<Card> allKnownCardsSet() {
		return new Vector<Card>(cardLib.getKnownCards_NAME_HASHMAP().values());
	}
	
	public static Card findCardByName(String cardName) {
		return new Card(cardLib.getKnownCards_NAME_HASHMAP().get(cardName));
	}
	
	public static List<Card> findCardByList(String ... cardName) {
			Vector<Card> tempC = new Vector<Card>();
			Arrays.stream(cardName).forEach(String ->{
				tempC.add(findCardByName(cardName.toString()
			));});
			return tempC;
	}
	
	public static Card findCardBySymbol(String cardSymbol) {
		return new Card(cardLib.getKnownCards_CARDSYMBOL_HASHMAP().get(cardSymbol));
	}
	
	public static void addCard(Card cardToAdd) {
		cardLib.addNewKnownCardToTypeMap(cardToAdd);
	}

	public static List<Card> allKnownGameCardsToList(CardType targetType) {
		return new Vector<Card>(cardLib.getKnownCardsByType_CARDTYPE_HASHMAP().get(targetType));
	}
	
	public static void createGlossaryFromDeck(Deck inputDeck) {
		for (Card newCard: inputDeck.getDeck()) {
			cardLib.newKnownCard(newCard);
		}
	}
	
}
