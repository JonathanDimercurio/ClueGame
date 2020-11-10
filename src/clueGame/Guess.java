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

public class Guess {
	private List<Card> unguessedPeople 	= new Vector<Card>();
	private List<Card> unguessedRooms 	= new Vector<Card>();
	private List<Card> unguessedWeapons = new Vector<Card>();
	private List<Card> givenCards 		= new Vector<Card>();
	private List<Card> possibleSolution = new Vector<Card>();
		
	public Guess() {
		this.unguessedPeople.addAll(Card.getTotalPeople());
		this.unguessedRooms.addAll(Card.getTotalRooms());
		this.unguessedWeapons.addAll(Card.getTotalWeapons());
//		possibleSolution.addAll(unguessedPeople);
//		possibleSolution.addAll(unguessedRooms);
//		possibleSolution.addAll(unguessedWeapons);
	}
	
	//Heart of the guessing logic
	public List<Card> generateGuess(String roomName) {		
		possibleSolutionRecycler();
		List<Card> guessing = new Vector<Card>();		
		guessing.add(chooseGuessByType(this.unguessedPeople));
		guessing.add(findRoomCardByName(roomName));
		guessing.add(chooseGuessByType(this.unguessedWeapons));
		adjustUnguessedLists(guessing);
		return guessing;
	}
	
	private void possibleSolutionRecycler() {
		if(this.possibleSolution != null) {
			for (Card addingCARD: this.possibleSolution) {
				List<Card> checkingDeck = findDeck(addingCARD);
				checkingDeck.add(addingCARD);
			}
		}
		this.possibleSolution.removeAll(this.unguessedPeople);
		this.possibleSolution.removeAll(this.unguessedRooms);
		this.possibleSolution.removeAll(this.unguessedWeapons);
	}
	
	private void adjustUnguessedLists(List<Card> guessing) {
		for (Card removeCARD: guessing) {
			if(removeCARD != null) {
				findDeck(removeCARD).remove(removeCARD);
			}
		}
	}

	private List<Card> findDeck(Card decklessCARD) {
		if (decklessCARD.getCardtype() == CardType.PERSON)	{ return this.unguessedPeople; }
		if (decklessCARD.getCardtype() == CardType.ROOM)	{ return this.unguessedRooms; }
		if (decklessCARD.getCardtype() == CardType.WEAPON)	{ return this.unguessedWeapons; }
		return null;
	}

	//Choosing a card from the decks by Type, returning a guess
	private Card findRoomCardByName(String playerRoom) {
		
		for (Card findRoom: unguessedRooms) {
			if (findRoom.getCardName().contentEquals(playerRoom)) {
				return findRoom;
			}
		}
		return null;
	}

	private Card chooseGuessByType(List<Card> deckType) {
		Collections.shuffle(deckType);
			return deckType.get(0);
	}
	
	public void compPlayersHand(Card givenCard) {
		switch (givenCard.getCardtype()) {
			case PERSON:
				this.unguessedPeople.remove(givenCard);
				this.possibleSolution.remove(givenCard);
				this.givenCards.add(givenCard);
				break;
			case ROOM:
				this.unguessedRooms.remove(givenCard);
				this.possibleSolution.remove(givenCard);
				this.givenCards.add(givenCard);
				break;
			case WEAPON:
				this.unguessedWeapons.remove(givenCard);
				this.possibleSolution.remove(givenCard);
				this.givenCards.add(givenCard);
				break;
			default:
				break;	
		}
	}
	
 	public void addPossibleSolution(List<Card> addingSolutions) {
 		this.possibleSolution.addAll(addingSolutions);
 	}

 	public List<Card> possibleSolutionGetter() {
 		return this.possibleSolution;
 	}

 	public boolean checkUnguessedByNameRooms(String checkRoom) {
 		if (unguessedRooms.contains(findRoomCardByName(checkRoom))) {
 			return true;
 		} else {
 			return false;
 		}
 	}
}