package clueGame;

import java.util.HashSet;
import java.util.Set;

public class Guess {
	private Set<Card> guess = new HashSet<Card>();
	
	public Guess(Card ... theGuess) {
		for (Card tempC: theGuess) {
			guess.add(tempC);
		}
			
	}

	public Set<Card> getGuess() {
		return this.guess;
	}
}
