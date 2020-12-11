package PlayerFiles;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import clueGame.Card;
import clueGame.Solution;

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
		if (!theGuess.getGuess().stream()
				.anyMatch(Card -> Solution.getGoalPerson()
						.equals(Card))) { return false; }
		if (!theGuess.getGuess().stream()
				.anyMatch(Card -> Solution.getGoalWeapon()
						.equals(Card))) { return false; }
		if (!theGuess.getGuess().stream()
				.anyMatch(Card -> Solution
						.getGoalRoom().equals(Card))) 
						{ return false; }
		return true;
	}
	
	public static boolean checkForReply (Guess guess, Player player) {
		for(Card checkCard: guess.getGuess()) {	 
			for(Card cardInHand: player.getHand()) {
				if(checkCard.compareTo(cardInHand)) {
					return true;
				}
			}
		} 
		return false;
	}
	
	public static Card generateReply(Guess guess, Player player) {
		List<Card> replyList = new Vector<Card>();
		
		guess.getGuess().stream().forEach(gCard->{
			for(Card hCard: player.getHand()) {
				if(hCard.compareTo(gCard)){
					replyList.add((gCard));
				}
			}
		});
		Collections.shuffle(replyList);
		if (replyList.get(0) != null) {

			return new Card(replyList.get(0));
		}
		return null;
	}

	
	public static int rollDice() { 		
		Random Rolled = new Random();
		int diceRoll = Rolled.nextInt(10) + 2;
		return diceRoll;
	}

}