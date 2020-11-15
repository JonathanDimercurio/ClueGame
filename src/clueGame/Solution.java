/* Solution
 * Purpose: 		This class will contain the solution to the game.
 * Dependencies:	Card class.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 */

package clueGame;

public class Solution {
	private static Card goalPerson;
	private static Card goalRoom;
	private static Card goalWeapon;

	//Begin Singleton
	private static Solution TheSolution;
	
	private Solution(Card thePerson, Card theRoom, Card theWeapon) {
		Solution.goalPerson = thePerson;
		Solution.goalRoom = theRoom;
		Solution.goalWeapon = theWeapon;
	}
	
	public static Solution getSolution() {
		return Solution.TheSolution;
	}
	
	public static void initSolution(Card thePerson, Card theRoom, Card theWeapon) {
			TheSolution = new Solution(thePerson, theRoom, theWeapon);
	}
	//End Singleton
	
	//Generic getters
	public static Card getGoalPerson() {
		return Solution.goalPerson;
	}
	public static Card getGoalRoom() {
		return Solution.goalRoom;
	}
	public static Card getGoalWeapon() {
		return Solution.goalWeapon;
	}
	
}