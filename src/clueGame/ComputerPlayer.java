/* Computer Player Class
 * Purpose:	This class is a child class to Player.
 * 			This will contain infomation and methods
 * 			relative to the Computer controlled players.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String playerName, String playerPositon) {
		super(playerName, playerPositon);
	}
	
	void updateHand(Card newCard) {
		super.addCardToHand(newCard);
	}

	@Override
	public String toString() {
		return "\nComputer Payer\n" + super.toString();
	}
	
	
}	