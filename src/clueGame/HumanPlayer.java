/* HumanPlayer, implements parent class, Player.
 * Purpose:	
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

public class HumanPlayer extends Player {

	/* ####Player Choice####
	 * For future builds when the user is prompted for the option to
	 * choose the person they wish to play. Until then we are hard
	 * coding the choice.
	 */
	static final String choice = new String("NP");
	
	public HumanPlayer(String playerName, String playerPositon) {
		super(playerName, playerPositon);
	}
	
	void updateHand(Card newCard) {
		//TODO
	}

	public static String getChoice() {
		return choice;
	}
	
	@Override
	public String toString() {
		return super.toString() + " human";
	}
}	