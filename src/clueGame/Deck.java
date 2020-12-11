package clueGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class Deck implements DeckActions, GlossaryActions{
	
	private List<Card> thisDeck = new Vector<Card>();
	
	public Deck() {
		
	}
	
	//ConstructDeck via List<Card>
	public Deck(List<Card> createFromThisList) {
		for (Card eachCard: createFromThisList) {
			this.thisDeck.add(eachCard);
			GlossaryActions.addCard(eachCard);
		}
	}
	
	//ConstructDeck via formatted config file
	public Deck(ArrayList<String[]> SetupFileData) {
		
		for (int i = 0; i < SetupFileData.size(); i++) {
			if(!SetupFileData.get(i)[0].contains("Space")) {
			this.thisDeck.add(new Card(Arrays.asList(SetupFileData.get(i))));
			}
		}
	}
	
	public Deck(Collection<Card> collectionCards) {
		collectionCards.forEach(card ->{
			this.thisDeck.add(card);
			GlossaryActions.addCard(card);
		});
	}

	public List<Card> getDeck() {
		return this.thisDeck;
	}
	
	public void addCard(Card newCard) {
		this.thisDeck.add(newCard);
	}
}
