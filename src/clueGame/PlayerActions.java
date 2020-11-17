package clueGame;

import java.util.List;
import java.util.Random;

public interface PlayerActions {

	public abstract List<Card> getSeenSet();


	/* chooseReply() ~ Dependencies: none ~ Calls: none
	 * Purpose:	
	 */
 	public abstract Card chooseReply(List<Card> cards);

	/* accusation() ~ 
	 * Purpose: This method will use 3 Card names, and check for the
	 * 			winning accusation. Returns boolean.
	 */
	public static boolean accusation(Guess theGuess) {
		if (!theGuess.getGuess().stream().anyMatch(Card -> Solution.getGoalPerson().equals(Card))) { return false; }
		if (!theGuess.getGuess().stream().anyMatch(Card -> Solution.getGoalWeapon().equals(Card))) { return false; }
		if (!theGuess.getGuess().stream().anyMatch(Card -> Solution.getGoalRoom().equals(Card))) { return false; }
		return true;
	}
	
	public static int rollDice() { 		
		Random Rolled = new Random();
		int diceRoll = Rolled.nextInt(10) + 2;
		return diceRoll;
	}

}