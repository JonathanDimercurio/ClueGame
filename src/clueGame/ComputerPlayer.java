/* Computer Player Class
 * Purpose:	This class is a child class to Player.
 * 			This will contain infomation and methods
 * 			relative to the Computer controlled players.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class ComputerPlayer extends Player {
	Guess guessLogic = new Guess();
	
	public ComputerPlayer(String playerName, String playerPositon) {
		super(playerName, playerPositon);
	}
	
	void updateHand(Card newCard) {
		super.addCardToHand(newCard);
		this.guessLogic.compPlayersHand(newCard);
	}
	
	public void makeSuggestion() {
			String roomName = super.getCellPosition().getMyRoomType().getRoomName();
			List<Card> suggestion = guessLogic.generateGuess(roomName);
			if(super.generateSuggestionReply(suggestion) != null) {
				this.guessLogic.addPossibleSolution(analyzeSuggestionReply(super.generateSuggestionReply(suggestion), suggestion));
			} else {
				this.guessLogic.addPossibleSolution(suggestion);
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
	
	@Override
	public String toString() {
		return "\nComputer Payer\n" + super.toString();
	}

	public void move() {
		Random Rolled = new Random();
		int diceRoll = Rolled.nextInt(10) + 2;
		super.updateCellPosition(moveMe(diceRoll));
	}
	
	private BoardCell moveMe(int DiceRoll) {
		Board.getInstance().calcTargets(super.getCellPosition(), DiceRoll);
		List<BoardCell> availSpaces = new Vector<BoardCell>();
		for(BoardCell move:Board.getInstance().getTargets()) {
			if (move.isRoomCenter()) {
				return move;
			} else {
				availSpaces.add(move);
			}
		}
		Collections.shuffle(availSpaces);
		return availSpaces.get(0);
	}
}	