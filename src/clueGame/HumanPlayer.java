/* HumanPlayer, implements parent class, Player.
 * Purpose:	
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.util.List;

public class HumanPlayer extends Player implements PlayerActions {
	public final char pType = 'H';
//	private GuessHuman humanGuessLogic = new GuessHuman();
//	public Map<Player, Vector<Card>> seenCards = new HashMap<Player, Vector<Card>>();
	

	public HumanPlayer(Player choosenPlayer) {
		super(CardGlossary.getGlossary()
				.getKnownCards_NAME_HASHMAP()
				.get(choosenPlayer.getName()));
		
		choosenPlayer.hand.stream().forEach(cardInHand->{
			this.updateHand(cardInHand);
		});
	}
	
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
		return null;
	}

	@Override
	public Guess makeSuggestion() {
		return null;
	}

	//TODO
	@Override
	public void updateKnownList() {
	}
	private void initSeenCardsMap() {
	
	}

	@Override
	public char getPType() {
		return this.pType;
	}

//	public GuessHuman getHumLogic() {
//		return this.humanGuessLogic;
//	}


}	