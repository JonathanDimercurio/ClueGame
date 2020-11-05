/* Solution
 * Purpose: 	This class will contain the solution to the game.
 * Dependencies:	Card class.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 */

package clueGame;

public class Solution {
	private Card goalPerson;
	private Card goalRoom;
	private Card goalWeapon;
	
	public Solution(Card thePerson, Card theRoom, Card theWeapon) {
		this.goalPerson = thePerson;
		this.goalRoom	= theRoom;
		this.goalWeapon	= theWeapon;
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

}
