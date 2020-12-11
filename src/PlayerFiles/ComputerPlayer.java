/* Computer Player Class
 * Purpose:	This class is a child class to Player.
 * 			This will contain infomation and methods
 * 			relative to the Computer controlled players.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package PlayerFiles;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import ComputerAI.GuessAI;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.Deck;

public class ComputerPlayer extends Player implements PlayerActions{
	public final char pType = 'C';
	public GuessAI guessLogic = new GuessAI();
	
	public ComputerPlayer(Card makeCompPlayerByCard) {
		super(makeCompPlayerByCard);
	}
	
	public ComputerPlayer(Deck deckOfComputerPlayers) {
		super(deckOfComputerPlayers);
	}
	
	/* makeSuggestion() ~ Returns a List<Cards> as a guess.
	 * 
	 */
	@Override
	public Guess makeSuggestion() {
		Guess CPUGuess = guessLogic.generateGuess(getCurrentCell()
				.getMyRoomType().getName());
		return CPUGuess;
	}
	
	/* resolveReplies() ~ Takes an arbitrary long list of Cards
	 * and adds them to the seen list for the specific player.
	 */
	public void resolveReplies(List<Card> seenCards) {
		guessLogic.addListToSeen(seenCards);
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

	//Begin AI move block
	//Automatically generates a dice roll
	public void move() {
		int diceRolled = PlayerActions.rollDice();
		Board.getInstance().calcTargets(super.getCellPosition(), diceRolled);
		super.updateCellPosition(moveMe(Board.getInstance().getTargets()));
	}
	
	private BoardCell moveMe(Set<BoardCell> availTargets) {
		List<BoardCell> availSpaces = new Vector<BoardCell>();
		availSpaces.addAll(availTargets);
		
		for(BoardCell move: availTargets) {
				if (!haveSeenCell(move) && move.isRoomCenter()) {
					return move;
				} else {
					availSpaces.add(move);
				}
			}
		Collections.shuffle(availSpaces);
		return availSpaces.get(0);
	}
	
	public boolean haveSeenCell(BoardCell resolveThisCell) {
		for(Card card: getSeenSet()) {
			if(card.getCardName().contains(resolveThisCell
					.getMyRoomType().getName())) {
				return true;
			}
		}
		return false;
	}

	public BoardCell getCurrentCell() {
		return super.getCellPosition();
	}
	
	public void moveMeToCell(BoardCell moveHere) {
		super.updateCellPosition(moveHere);
	}
	//End AI move block
	
	
	public void updateHand(Card newCard) {
		super.addCardToHand(newCard);
	}

	@Override
	public List<Card> getSeenSet() {		
		return guessLogic.createSeenCardList();
	}

	@Override
	public Card chooseReply(List<Card> cards) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void updateKnownList() {
		this.guessLogic.addListToSeen(this.getHand());
	}

	@Override
	public char getPType() {
		return this.pType;
	}

}	