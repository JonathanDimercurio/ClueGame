/* HumanPlayer, implements parent class, Player.
 * Purpose:	
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HumanPlayer extends Player implements PlayerActions {
	private Set<Card> seenSet = new HashSet<Card>();

	public HumanPlayer(Card humPlayerFromCard) {
		super(humPlayerFromCard);
	}
	
	public void updateHand(Card newCard) {
		super.addCardToHand(newCard);
		this.seenSet.add(newCard);
	}

	@Override
	public void makeSuggestion() {
			
	}

	@Override
	public Card chooseReply(List<Card> cards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card findReply(Set<Card> suggestedCardList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Card> getSeenSet() {
		return this.seenSet;
	}
}	