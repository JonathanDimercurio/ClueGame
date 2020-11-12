/* Solution
 * Purpose: 	This class will contain the solution to the game.
 * Dependencies:	Card class.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 */

package clueGame;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class GuessAI implements GameControl{
	private List<Card> unseenPeople = new Vector<Card>();
	private List<Card> unseenRooms 	= new Vector<Card>();
	private List<Card> unseenWeapons = new Vector<Card>();
	private List<Card> seenCards = new Vector<Card>();
	
	public GuessAI() {
		this.unseenPeople.addAll(Deck.getTotalPeopleDeck());
		this.unseenRooms.addAll(Deck.getTotalRoomDeck());
		this.unseenWeapons.addAll(Deck.getTotalWeaponDeck());
	}
	
	//Heart of the guessing logic
	public Guess generateGuess(String roomName) {
		Card gPerson = chooseGuessByType(this.unseenPeople);
		Card gRoom = GameControl.findCardByName(roomName);
		Card gWeapon = chooseGuessByType(this.unseenWeapons);
		Guess compGuess = new Guess(gPerson, gRoom, gWeapon);
		return compGuess;
	}
	
	public void addListToSeen(List<Card> newSeenCards) {
		this.seenCards.addAll(newSeenCards);
		this.unseenPeople.removeAll(newSeenCards);
		this.unseenRooms.removeAll(newSeenCards);
		this.unseenWeapons.removeAll(newSeenCards);
	}
	
	private Card chooseGuessByType(List<Card> deckType) {
		Collections.shuffle(deckType);
			return deckType.get(0);
	}

 	public boolean checkUnguessedRoomsByName(String checkRoom) {
 		if(this.unseenRooms != null) {
 			for (Card eachUnseenRoomCard: this.unseenRooms) {
 				if(eachUnseenRoomCard.getCardName().contentEquals(checkRoom)) {
 					return true;
 				}
 			}
 			return false;
 		}
 		return true;
 	}
 	
 	public List<Card> getSeenCards() {
 		return this.seenCards;
 	}
}