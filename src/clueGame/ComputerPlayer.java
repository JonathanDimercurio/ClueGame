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
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class ComputerPlayer extends Player {
	public static Set<ComputerPlayer> computerPlayerList = new HashSet<ComputerPlayer>();
	
	Guess guessLogic = new Guess();
	
	public ComputerPlayer(String playerName, String playerPositon) {
		super(playerName, playerPositon);
		computerPlayerList.add(this);
	}
	
	void updateHand(Card newCard) {
		super.addCardToHand(newCard);
		this.guessLogic.compPlayersHand(newCard);
	}
	
	public void makeSuggestion() {
			if (super.getCellPosition().ifRoomCenter()) {
				String roomName = super.getCellPosition().getMyRoomType().getRoomName();
				List<Card> suggestion = guessLogic.generateGuess(roomName);
				if(super.generateSuggestionReply(suggestion, this) != null) {
					this.guessLogic.addPossibleSolution(analyzeSuggestionReply(super.generateSuggestionReply(suggestion, this), suggestion));
				} else {
					this.guessLogic.addPossibleSolution(suggestion);
				}			
			}			
	}
		
	public List<Card> analyzeSuggestionReply(List<Card> suggestionResult, List<Card> playersSuggestion) {
			for (Card removeCard: suggestionResult) {
				for(Card comparingCard: playersSuggestion) {
					if(removeCard.getCardName() == comparingCard.getCardName()) {
						playersSuggestion.remove(comparingCard);
					}
				}
			}
			return playersSuggestion;
		}
	
	public void move() {
		Random Rolled = new Random();
		int diceRoll = Rolled.nextInt(10) + 2;
		Board.getInstance().calcTargets(super.getCellPosition(), diceRoll);
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
		for (Card checkIfVisited: this.guessPossibleSolutionGetter()) {
			if(checkIfVisited.getCardName().contains(resolveThisCell.getMyRoomType().getName())) {
				return true;
			}
		}
		return false;
	}

	public BoardCell getCurrentCell() {
		return super.getCellPosition();
	}
	
	public List<Card> guessPossibleSolutionGetter() {
		return this.guessLogic.possibleSolutionGetter();
	} 

	//Methods specifically used for testing
	//Instead of using guessLogic to generate a suggestion, we want to manly
	//Assign the guess, so we'll use this method for that.
	public List<Card> testSuggestion(List<Card> suggestion) {		
		List<Card> reply = new Vector<Card>();
		if (super.generateSuggestionReply(suggestion, this) != null) {
			reply.addAll(super.generateSuggestionReply(suggestion, this));
			return reply;
		}
		return null;
	}
	
}	