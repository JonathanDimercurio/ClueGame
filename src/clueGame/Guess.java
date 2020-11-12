package clueGame;

import java.util.List;
import java.util.Vector;

public class Guess {
	private List<Card> guess = new Vector<Card>();
	
	public Guess(Card theGuessedPerson, Card theGuessedRoom, Card theGuessWeapon) {
		guess.add(theGuessedPerson);
		guess.add(theGuessedRoom);
		guess.add(theGuessWeapon);
	}

	public List<Card> getGuess() {
		return this.guess;
	}
	
 	public Card getGuessPerson() {
		return guess.get(0);
	}

	public Card getGuessRoom() {
		return guess.get(1);
	}

	public Card getGuessWeapon() {
		return guess.get(2);
	}
}
