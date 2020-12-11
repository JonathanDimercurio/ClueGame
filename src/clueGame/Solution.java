/* Solution
 * Purpose: 		This class will contain the solution to the game.
 * Dependencies:	Card class.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 */

package clueGame;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

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
	
	
	public static Deck genSolution(Deck fullDeck) {
		HashMap<CardType, Vector<Card>> tempMap = 
				new HashMap<CardType, Vector<Card>>();
		
		tempMap.put(CardType.PERSON, new Vector<Card>());
		tempMap.put(CardType.WEAPON, new Vector<Card>());
		tempMap.put(CardType.ROOM, new Vector<Card>());
	
		fullDeck.getDeck().stream().forEach(card->{
			tempMap.get(card.getCardtype()).add(card);
		});
		
		Card person = shuffleAndExtract(tempMap.get(CardType.PERSON));
		Card room = shuffleAndExtract(tempMap.get(CardType.ROOM));
		Card weapon = shuffleAndExtract(tempMap.get(CardType.WEAPON));
		
		Solution.initSolution(person, room, weapon);
		
		Deck returnDeck = new Deck();
		for(CardType type: tempMap.keySet()) {
			tempMap.get(type).stream().forEach(card->{
				returnDeck.addCard(card);
			});
		}
		
		return returnDeck;
	}
	
	private static Card shuffleAndExtract(Vector<Card> inputDeck) {
		Collections.shuffle(inputDeck);
		Card tempCard = new Card(inputDeck.get(0));
		inputDeck.remove(0);
		Collections.shuffle(inputDeck);
		return tempCard;
	}
	
	
	
	@Override
	public String toString() {
		return "Solution = " + goalPerson.toString() + " " 
								+ goalRoom.toString() +  " " 
									+ goalWeapon.toString() + " ";
	}

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