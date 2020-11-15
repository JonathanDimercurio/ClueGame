package clueGame;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

public interface GlossaryActions {

	public static Collection<Card> allKnownCardsSet() {
		return new Vector<Card>(CardGlossary.getKnownCards_NAME_HASHMAP().values());
	}
	
	public static Card findCardByName(String cardName) {
		return new Card(CardGlossary.getKnownCards_NAME_HASHMAP().get(cardName));
	}
	
	public static Card findCardBySymbol(String cardSymbol) {
		return new Card(CardGlossary.getKnownCards_CARDSYMBOL_HASHMAP().get(cardSymbol));
	}
	
	public static void addCard(Card cardToAdd) {
		CardGlossary.addNewKnownCardToTypeMap(cardToAdd);
	}

	public static List<Card> allKnownGameCardsToList(CardType targetType) {
		return new Vector<Card>(CardGlossary.getKnownCardsByType_CARDTYPE_HASHMAP().get(targetType));
	}
}
