/* Computer Player Class
 * Purpose:	This class is a child class to Player.
 * 			This will contain infomation and methods
 * 			relative to the Computer controlled players.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class ComputerPlayer extends Player implements PlayerActions{
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
		Guess CPUGuess = guessLogic.generateGuess(getCurrentCell().getMyRoomType().getName());
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
			return this.getHand().stream().anyMatch(card -> checkCard.compareTo(card));
		} return false;
	}
	
	//This is where replys come from
	public Card generateReply(Guess guess) {
		List<Card> replyCard = new Vector<Card>();
		
		for(Card checkCard: guess.getGuess()) {
			for(Card myCard: this.getHand()) {
				if(myCard.compareTo(checkCard)) {
					replyCard.add(new Card(myCard));
		}}}
		Collections.shuffle(replyCard);
		return replyCard.get(0);
	}

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
		return guessLogic.checkIfSeenByString(resolveThisCell.getMyRoomType().getName());
	}

	public BoardCell getCurrentCell() {
		return super.getCellPosition();
	}
	
	public void moveMeToCell(BoardCell moveHere) {
		super.updateCellPosition(moveHere);
	}
	
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

}	