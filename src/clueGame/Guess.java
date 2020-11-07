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
		unguessedPeople = new Vector<Card>((List) Card.getTotalPeople());
		unguessedRooms = new Vector<Card>((List) Card.getTotalRooms());
		unguessedWeapons = new Vector<Card>((List) Card.getTotalWeapons());
		possibleSolution.addAll(unguessedPeople);
		possibleSolution.addAll(unguessedRooms);
		possibleSolution.addAll(unguessedWeapons);
	}
	
	public List<Card> generateGuess() {
		List<Card> guessing = new Vector<Card>();
		guessing.add(chooseGuessByType(this.unguessedPeople));
		guessing.add(chooseGuessByType(this.unguessedRooms));
		guessing.add(chooseGuessByType(this.unguessedWeapons));
		return guessing;
	}
		
	//Choosing a card from the decks by Type, returning a guess
	private Card chooseGuessByType(List<Card> deckType) {
		Collections.shuffle(deckType);
			return deckType.get(0);
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
}