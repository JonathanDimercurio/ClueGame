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
	public static List<ComputerPlayer> computerPlayerList = new Vector<ComputerPlayer>();
	
	public Guess guessLogic = new Guess();
	
	List<Card> suggestion 	= new Vector<Card>();
	List<Card> replys		= new Vector<Card>();
	
	public ComputerPlayer(String playerName, String playerID) {
		super(playerName, playerID);
		computerPlayerList.add(this);
	}
	
	void updateHand(Card newCard) {
		super.addCardToHand(newCard);
		this.guessLogic.compPlayersHand(newCard);
	}
	
	public void makeSuggestion() {
			if (super.getCellPosition().ifRoomCenter()) {
				String roomName = super.getCellPosition().getMyRoomType().getRoomName();
				this.suggestion = guessLogic.generateGuess(roomName);
				this.replys	  = super.generateSuggestionReply(suggestion, this);
				this.replys	  = analyzeSuggestionReply(replys, suggestion);
				this.guessLogic.addPossibleSolution(replys);			
			}			
	}
		
	public List<Card> analyzeSuggestionReply(List<Card> suggestionResult, List<Card> playersSuggestion) {
			List<Card> removeMe = new Vector<Card>();
			for (Card removeCard: suggestionResult) {
				for(Card comparingCard: playersSuggestion) {
					if(removeCard.getCardName() == comparingCard.getCardName()) {
						removeMe.add(removeCard);
					}
				}
			}
			playersSuggestion.removeAll(removeMe);
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
	
	//TODO also junk
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
	
	public void emptyHand() {
		super.emptyHand();
	}

	public List<Card> getSuggestion() {
		return suggestion;
	}

	public List<Card> getReplys() {
		return replys;
	}
	
}	