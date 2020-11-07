/* Solution
 * Purpose: 	This class will contain the solution to the game.
 * Dependencies:	Card class.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 */

package clueGame;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Guess {
	private List<Card> unguessedPeople;
	private List<Card> unguessedRooms;
	private List<Card> unguessedWeapons;
	private List<Card> givenCards;
	private Set<Card> possibleSolution = new HashSet<Card>();
		
	public Guess() {
		unguessedPeople = new Vector<Card>();
		this.unguessedPeople.addAll(Card.getTotalPeople());
		unguessedRooms = new Vector<Card>();
		this.unguessedRooms.addAll(Card.getTotalRooms());
		unguessedWeapons = new Vector<Card>();
		this.unguessedWeapons.addAll(Card.getTotalWeapons());
		givenCards = new Vector<Card>();
		possibleSolution.addAll(unguessedPeople);
		possibleSolution.addAll(unguessedRooms);
		possibleSolution.addAll(unguessedWeapons);
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
		for (Card addingCARD: this.possibleSolution) {
			findDeck(addingCARD).add(addingCARD);
			possibleSolution.remove(addingCARD);
		}
	}
	
	private void adjustUnguessedLists(List<Card> guessing) {
		for (Card removeCARD: guessing) {
			findDeck(removeCARD).remove(removeCARD);
		}
	}

	private List<Card> findDeck(Card decklessCARD) {
		if (decklessCARD.getCardtype() == CardType.PERSON)	{ return this.unguessedPeople; }
		if (decklessCARD.getCardtype() == CardType.ROOM)		{ return this.unguessedRooms; }
		if (decklessCARD.getCardtype() == CardType.WEAPON)	{ return this.unguessedWeapons; }
		return null;
	}

	//Choosing a card from the decks by Type, returning a guess
	private Card findRoomCardByName(String playerRoom) {
		for (Card findRoom: unguessedRooms) {
			if (playerRoom == findRoom.getCardName()) {
				return findRoom;
			}
		}
		return null;
	}

	private Card chooseGuessByType(List<Card> deckType) {
		Collections.shuffle(deckType);
			return deckType.get(0);
	}

	private boolean checkSolutionList(Card checkForCard) {
		if(this.possibleSolution.contains(checkForCard)) {
			return true;
		} else {
			return false;
		}
	}
	
	//TODO might have to change this name
	//Add ComputerPlayer hand to guessed cards as the hand is dealt.
	
	public void compPlayersHand(Card cardDeckToplayer) {
		switch (cardDeckToplayer.getCardtype()) {
			case PERSON:
				this.unguessedPeople.remove(cardDeckToplayer);
				this.givenCards.add(cardDeckToplayer);
				break;
			case ROOM:
				this.unguessedRooms.remove(cardDeckToplayer);
				this.givenCards.add(cardDeckToplayer);
				break;
			case WEAPON:
				this.unguessedWeapons.remove(cardDeckToplayer);
				this.givenCards.add(cardDeckToplayer);
				break;
			default:
				break;	
		}
	}
	
 	public void addPossibleSolution(List<Card> addingSolutions) {
 		this.possibleSolution.addAll((Set) addingSolutions);
 	}
}