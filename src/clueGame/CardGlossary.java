package clueGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CardGlossary {

	private static Map<String, Card> knownCardsByName = new HashMap<String, Card>();
	private static Map<CardType, Vector<Card>> knownCardsByType = new HashMap<CardType, Vector<Card>>();
	private static Map<String, Card> knownCardsBySymbol = new HashMap<String, Card>();
	
	
	private static CardGlossary cardLib = new CardGlossary();
	
	private CardGlossary() {
	}	
	
	public void newKnownCard(Card newCard) {
		if(!CardGlossary.knownCardsByName.values().contains(newCard)) {
			CardGlossary.addNewKnownCardToNameMap(newCard);
			CardGlossary.addNewKnownCardToSymbolMap(newCard);
			CardGlossary.addNewKnownCardToTypeMap(newCard);
		}
	}
	
	//************* adders
	private static void addNewKnownCardToNameMap(Card newCard) {
		CardGlossary.knownCardsByName.put(newCard.getCardName(), newCard);
	}

	private static void addNewKnownCardToSymbolMap(Card newCard) {
		CardGlossary.knownCardsBySymbol.put(newCard.getCardSymbol(), newCard);

	}

	public static void addNewKnownCardToTypeMap(Card newCard) {
		if(!CardGlossary.knownCardsByType.containsKey(newCard.getCardtype())) {
			Vector<Card> tempVec = new Vector<>();
			tempVec.add(newCard);
			CardGlossary.knownCardsByType.put(newCard.getCardtype(), tempVec);
		}
	}

	//************* Below are basic getters/setters
	public static CardGlossary getGlossary() {
		return CardGlossary.cardLib;
	}


	public static Map<String, Card> getKnownCards_NAME_HASHMAP() {
		return CardGlossary.knownCardsByName;
	}
	
	

	public static Map<CardType, Vector<Card>> getKnownCardsByType_CARDTYPE_HASHMAP() {
		return CardGlossary.knownCardsByType;
	}
	
	public static Map<String, Card> getKnownCards_CARDSYMBOL_HASHMAP(){
		return CardGlossary.knownCardsBySymbol;
	}
}