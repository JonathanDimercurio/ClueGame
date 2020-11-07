/* Solution
 * Purpose: 	This class will contain the solution to the game.
 * Dependencies:	Card class.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 */

package clueGame;

import java.util.List;
import java.util.Vector;

public class Solution {
	private Card goalPerson;
	private Card goalRoom;
	private Card goalWeapon;
	private List<Card> solution = new Vector<Card>();
	
	public Solution(Card thePerson, Card theRoom, Card theWeapon) {
		this.goalPerson = thePerson;
		this.goalRoom	= theRoom;
		this.goalWeapon	= theWeapon;
		solution.add(thePerson);
		solution.add(theWeapon);
		solution.add(theRoom);
	}
	
	public Card getGoalPerson() {
		return goalPerson;
	}

	public Card getGoalRoom() {
		return goalRoom;
	}

	public Card getGoalWeapon() {
		return goalWeapon;
	}

	public Solution getSolution() {
		return this;
	}
	
	public List<Card> getTheSolution() {
		return this.solution;
	}
	
	//Begin Actions
	/* accusation() ~ 
	 * Purpose: This method will use 3 Card names, and check for the
	 * 			winning accusation. Returns boolean.
	 */
	@SuppressWarnings("unused")
	public boolean accusation(Card accusedPerson, Card accusedRoom, Card accusedWeapon) {
		if (this.getGoalPerson() == accusedPerson &&
			this.getGoalRoom()== accusedRoom	&&
			this.getGoalWeapon()== accusedWeapon )
		{ return true; } else { return false; }
	}

}