package clueGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Deck implements DeckActions, GlossaryActions{
	
	private List<Card> SetDeck = new Vector<Card>();
	
	public Deck() {
		
	}
	
	//ConstructDeck via List<Card>
	public Deck(List<Card> createFromThisList) {
		for (Card eachCard: createFromThisList) {
			this.SetDeck.add(eachCard);
			GlossaryActions.addCard(eachCard);
		}
	}
	
	//ConstructDeck via formatted config file
	public Deck(ArrayList<String[]> SetupFileData) {
		for (int i = 0; i < SetupFileData.size(); i++) {
			this.SetDeck.add(new Card(Arrays.asList(SetupFileData.get(i))));
		}
	}
	
	public List<Card> getDeck() {
		return this.getDeck();
	}
}
