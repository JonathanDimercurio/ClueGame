/* Computer Player Class
 * Purpose:	This class is a child class to Player.
 * 			This will contain infomation and methods
 * 			relative to the Computer controlled players.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class ComputerPlayer extends Player implements PlayerActions{
	private GuessAI guessLogic = new GuessAI();
	
	public ComputerPlayer(String playerName, String playerID) {
		super(playerName, playerID);
	}
	
	/* makeSuggestion() ~ Returns a List<Cards> as a guess.
	 * 
	 */
	@Override
	public void makeSuggestion() {
		Guess CPUGuess = guessLogic.generateGuess(getCurrentCell().getMyRoomType().getName());
		resolveReplies(PlayerActions.generateReplies(CPUGuess.getGuess(), this));	
	}
	
	/* resolveReplies() ~ Takes an arbitrary long list of Cards
	 * and adds them to the seen list for the specific player.
	 */
	private void resolveReplies(List<Card> seenCards) {
		guessLogic.addListToSeen(seenCards);
	}
	
	/* finReplyIfInHand() ~ Can find more then one reply card within
	 * a ComputerPlayer's Hand. It will arbitrarily return a random Card
	 * if more then one can be a reply.
	 */
	public Card findReply(List<Card> suggestedCardList) {
		List<Card> possibleReplies = new Vector<Card>();
		for (Card checkThisCard: suggestedCardList) {
			if (this.getHand().contains(checkThisCard)) { possibleReplies.add(checkThisCard); }
		} return chooseReply(possibleReplies);
	}
	
	/* chooseReply() ~ takes a List and randomly chooses
	 * an element of the list to return.
	 */
	@Override
	public Card chooseReply(List<Card> cards)  {
		cards.remove(null);
		Collections.shuffle(cards);
		if (!cards.isEmpty()) {
			return cards.get(0);
		}
		return null;
	}
	
	public void move() {
		int diceRolled = PlayerActions.rollDice();
		Board.getInstance().calcTargets(super.getCellPosition(), diceRolled);
		super.updateCellPosition(moveMe(Board.getInstance().getTargets()));
	}
	
	private BoardCell moveMe(Set<BoardCell> availTargets) {
		List<BoardCell> availSpaces = new Vector<BoardCell>();
		availSpaces.addAll(availTargets);
		for(BoardCell move: availTargets) {
				if (simplePathFinder(move)) {
					return move;
				} else {
					availSpaces.add(move);
				}
			}
		Collections.shuffle(availSpaces);
		return availSpaces.get(0);
	}
	
	private boolean simplePathFinder(BoardCell resolveThisCell) {
		return guessLogic.checkUnguessedRoomsByName(resolveThisCell.getMyRoomType().getName());
	}

	public BoardCell getCurrentCell() {
		return super.getCellPosition();
	}
	
	
	public void updateHand(Card newCard) {
		super.addCardToHand(newCard);
	}

	
	@Override
	public Set<Card> getSeenList() {
		Set<Card> seenSet = new HashSet<Card>();
		seenSet.addAll(guessLogic.getSeenCards());
		return seenSet;
	}


}	