package clueGame;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public interface PlayerActions {

	public abstract Set<Card> getSeenList();
	
	/* accusation() ~ 
	 * Purpose: This method will use 3 Card names, and check for the
	 * 			winning accusation. Returns boolean.
	 */
	public static boolean accusation(Card accusedPerson, Card accusedRoom, Card accusedWeapon) {
		if (!(Solution.getGoalPerson().getCardName().contentEquals(accusedPerson.getCardName()))) { return false; }
		if (!(Solution.getGoalRoom().getCardName().contentEquals(accusedRoom.getCardName()))) { return false; }
		if (!(Solution.getGoalWeapon().getCardName().contentEquals(accusedWeapon.getCardName()))) { return false; }
		return true;
	}
	
	public static int rollDice() { 		
		Random Rolled = new Random();
		int diceRoll = Rolled.nextInt(10) + 2;
		return diceRoll;
	}

	/* generateReplies ~ 
	 * 
	 */
	public static List<Card> generateReplies(List<Card> suggestedCardList,Player currentPlayer) {
		List<Player> tempPlayerList = new Vector<Player>();
		tempPlayerList.addAll(Player.players);
		tempPlayerList.remove(currentPlayer);
		
		List<Card> playerReplies = new Vector<Card>();
		for (Player checkThisPlayer: tempPlayerList) {
			playerReplies.add(checkThisPlayer.findReply(suggestedCardList));
		} return playerReplies;
		
	}

	/* chooseReply() ~ Dependencies: none ~ Calls: none
	 * Purpose:	
	 */
 	public abstract Card chooseReply(List<Card> cards);

}
