/* HumanPlayer, implements parent class, Player.
 * Purpose:	
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HumanPlayer extends Player implements PlayerActions {
	public final char pType = 'H';
	private GuessHuman humanGuessLogic = new GuessHuman();  
	

	public HumanPlayer(Card humPlayerFromCard) {
		super(humPlayerFromCard);
	}
	
	public void updateHand(Card newCard) {
		super.addCardToHand(newCard);}



	@Override
	public Card chooseReply(List<Card> cards) {
		return null;
	}

	@Override
	public List<Card> getSeenSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guess makeSuggestion() {
		return null;
	}

	@Override
	public void updateKnownList() {
		humanGuessLogic.addListToSeen(getHand());}

	@Override
	public char getPType() {
		return this.pType;
	}




}	