/* HumanPlayer, implements parent class, Player.
 * Purpose:	
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import userInterface.UICtrl;
import userInterface.UISeenCards;

public class HumanPlayer extends Player implements PlayerActions {
	public final char pType = 'H';
//	private GuessHuman humanGuessLogic = new GuessHuman();
//	public Map<Player, Vector<Card>> seenCards = new HashMap<Player, Vector<Card>>();
	

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
		Vector<Card> tempL = new Vector<Card>();
		UISeenCards.humanSeenCards.values().stream().forEach(cardSet->{
			tempL.addAll(cardSet);
		});
		return tempL;
	}

	@Override
	public Guess makeSuggestion() {
		return null;
	}

	//TODO
	@Override
	public void updateKnownList() {
		initSeenCardsMap();
		UISeenCards.humanSeenCards.get(this).addAll(this.getHand());
	}
	private void initSeenCardsMap() {
		UICtrl.playerList.forEach(player->{
			UISeenCards.humanSeenCards.put(player, new Vector<Card>());
		});		
	}

	@Override
	public char getPType() {
		return this.pType;
	}

//	public GuessHuman getHumLogic() {
//		return this.humanGuessLogic;
//	}


}	