package clueGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CardGlossary {

	private Map<String, Card> knownCardsByName = new HashMap<String, Card>();
	private Map<CardType, Vector<Card>> knownCardsByType = new HashMap<CardType, Vector<Card>>();
	private Map<String, Card> knownCardsBySymbol = new HashMap<String, Card>();
	
	static CardGlossary theGlossary = new CardGlossary();
	
	private CardGlossary() {
	}	
	
	public static CardGlossary getGlossary() {
		return theGlossary;
	}
	
	public void newKnownCard(Card newCard) {
		if(!knownCardsByName.values().contains(newCard)) {
			addNewKnownCardToNameMap(newCard);
			addNewKnownCardToSymbolMap(newCard);
			addNewKnownCardToTypeMap(newCard);
		}
	}
	
	//************* adders
	public void addNewKnownCardToNameMap(Card newCard) {
		knownCardsByName.put(newCard.getCardName(), newCard);
	}

	public void addNewKnownCardToSymbolMap(Card newCard) {
		knownCardsBySymbol.put(newCard.getCardSymbol(), newCard);

	}

	public void addNewKnownCardToTypeMap(Card newCard) {
		if(!knownCardsByType.containsKey(newCard.getCardtype())) {
			Vector<Card> tempVec = new Vector<>();
			tempVec.add(newCard);
			knownCardsByType.put(newCard.getCardtype(), tempVec);
		}
	}

	//************* Below are basic getters/setters

	public Map<String, Card> getKnownCards_NAME_HASHMAP() {
		return knownCardsByName;
	}
	
	public Map<CardType, Vector<Card>> getKnownCardsByType_CARDTYPE_HASHMAP() {
		return knownCardsByType;
	}
	
	public Map<String, Card> getKnownCards_CARDSYMBOL_HASHMAP(){
		return knownCardsBySymbol;
	}
}