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
		this.goalPerson = new Card("Person", thePerson.getCardName(), thePerson.getCardSymbol());
		this.goalRoom	= new Card("Room", theRoom.getCardName(), theRoom.getCardSymbol());
		this.goalWeapon	= new Card("Weapon", theWeapon.getCardName(), theWeapon.getCardSymbol());
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
