/* Solution
 * Purpose: This class will contain the solution to the game.
 * Dependencies:	CardType Enum;
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 */

package clueGame;

public class Solution {
	private Card goalPerson;
	private Card goalRoom;
	private Card goalWeapon;
	
	public Card getGoalPerson() {
		return goalPerson;
	}
	public void setGoalPerson(Card goalPerson) {
		this.goalPerson = goalPerson;
	}
	public Card getGoalRoom() {
		return goalRoom;
	}
	public void setGoalRoom(Card goalRoom) {
		this.goalRoom = goalRoom;
	}
	public Card getGoalWeapon() {
		return goalWeapon;
	}
	public void setGoalWeapon(Card goalWeapon) {
		this.goalWeapon = goalWeapon;
	}
}
