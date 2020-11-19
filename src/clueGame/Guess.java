package clueGame;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Guess {
	private Set<Card> guess = new HashSet<Card>();
	
	public Guess(Guess newGuess) {
		this.guess = newGuess.getGuess();
	}
	
	public Guess(Card ... theGuess) {
		this.guess.addAll(Arrays.stream(theGuess).collect(Collectors.toSet()));
	}

	public Guess(List<Card> findCardByList) {
		this.guess.addAll(findCardByList.stream().collect(Collectors.toSet()));
	}

	public Set<Card> getGuess() {
		return this.guess;
	}
}
