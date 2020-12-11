package PlayerFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import clueGame.Card;

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

	@Override
	public String toString() {
		ArrayList<String> cardnames = new ArrayList<String>();
		guess.stream().forEach(card->{
			cardnames.add(card.getCardName());
		});
		return cardnames.toString();
	}
	
	
}
