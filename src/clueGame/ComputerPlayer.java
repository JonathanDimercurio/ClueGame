/* Computer Player Class
 * Purpose:	This class is a child class to Player.
 * 			This will contain infomation and methods
 * 			relative to the Computer controlled players.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.util.List;

public class ComputerPlayer extends Player {
	Guess guessLogic;
	
	public ComputerPlayer(String playerName, String playerPositon) {
		super(playerName, playerPositon);
	}
	
	void updateHand(Card newCard) {
		super.addCardToHand(newCard);
		this.guessLogic.compPlayersHand(newCard);
	}

	
	
	
	//Getters

	public List<Card> getSuggestion() {
		return this.guessLogic.generateGuess();
	}
	
	public void handleSuggestionReply() {
		//TODO
	}
	
	@Override
	public String toString() {
		return "\nComputer Payer\n" + super.toString();
	}
}	