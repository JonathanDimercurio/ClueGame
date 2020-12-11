/* HumanPlayer, implements parent class, Player.
 * Purpose:	
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package PlayerFiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import UserInterface.ControlPanel;
import UserInterface.SeenPanel;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardGlossary;

public class HumanPlayer extends Player implements PlayerActions {
	public final char pType = 'H';
	private Guess humanGuess;

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
		ControlPanel.genHumanReply(cards);
		return null;
	}

	@Override
	public List<Card> getSeenSet() {
		return null;
	}


	public Guess makeSuggestion(Guess humGuess) {
		Guess CPUGuess = new Guess(humGuess);
		return CPUGuess;
	}

	public void updateKnownList(Card newCard) {
		SeenPanel.addCard(newCard);
	}
	
	@Override
	public char getPType() {
		return this.pType;
	}

	@Override
	public BoardCell getCurrentCell() {
		return super.getCellPosition();
	}

	//Check Before getting replies
	public boolean checkForReply (Guess guess) {
		for(Card checkCard: guess.getGuess()) {	 
			for(Card cardInHand: this.getHand()) {
				if(checkCard.compareTo(cardInHand)) {
					return true;
				}
			}
		} 
		return false;
	}
	
	//This is where replys come from
	public Card generateReply(Guess guess) {
		List<Card> replyList = new Vector<Card>();
		
		for(Card gCard: guess.getGuess()) {
			for(Card hCard: this.getHand()) {
				if(hCard.compareTo(gCard)){
					replyList.add((gCard));
					
				}
			}
		}
		Collections.shuffle(replyList);
		return new Card(replyList.get(0));
	}
	
	public void setHumGuess(Guess guess) {
		this.humanGuess = guess;
	}

	@Override
	public Guess makeSuggestion() {
		return this.humanGuess;
	}

	@Override
	public void updateKnownList() {
	}

	public void resolveReplies(ArrayList<Card> replies) {
		replies.stream().forEach(card-> {
			this.updateKnownList(card);
		});
	}

	public void moveMe(BoardCell here) { 
		super.updateCellPosition(here);
	}

}	