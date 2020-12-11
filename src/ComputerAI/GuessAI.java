/* GuessAI
 * Purpose:
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 */

package ComputerAI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import PlayerFiles.Guess;
import clueGame.Card;
import clueGame.CardType;
import clueGame.GlossaryActions;

public class GuessAI implements GlossaryActions {
	
	private Map<String, Card> unSeenCards = new HashMap<String, Card>();
	
	
	public GuessAI() {
		GlossaryActions.allKnownCardsSet()
				.forEach(card -> unSeenCards.put(card.getCardName(),card));
		}
	
	public Guess generateGuess(String roomName) {
		
		Card roomGuess = 
				new Card(GlossaryActions.findCardByName(roomName));
		
		Card personGuess = new Card(genCardByType(CardType.PERSON));
		Card weaponGuess = new Card(genCardByType(CardType.WEAPON));
		
		
		Guess theGuess = 
				new Guess(roomGuess,personGuess,weaponGuess);
		
		return theGuess;
	}
	
	private Card genCardByType(CardType findType) {
		ArrayList<Card> remainingCards = new ArrayList<Card>();
		unSeenCards.values().stream().forEach(card->{
			if(card.getCardtype() == findType) {
				remainingCards.add(card);
			}
		});
		if(remainingCards.size() > 0) {
			Collections.shuffle(remainingCards);
			return remainingCards.get(0);
		} else {
			for(Card card: GlossaryActions.allKnownGameCardsToList(findType) ) {
				remainingCards.add(card);
			}
			Collections.shuffle(remainingCards);
			return remainingCards.get(0);
		}
	}
	
	public List<Card> createSeenCardList() {
		HashSet<Card> seenList = new HashSet<Card>();
		seenList.addAll(GlossaryActions.allKnownCardsSet());
		seenList.removeAll(unSeenCards.values());
		return seenList.stream().collect(Collectors.toList());
	}
 
 	public List<Card> getUnSeenCards() {
 		return this.unSeenCards.values().stream()
 				.collect(Collectors.toList());
 	}

	public void addListToSeen(List<Card> seenCards) {
		seenCards.forEach(card->{
			if(unSeenCards.containsKey(card.getCardName())) {
				unSeenCards.remove(card.getCardName());
			}
		});
	}
	
	public boolean checkIfSeen(Card checkThisCard) {
		return (!unSeenCards.containsKey(checkThisCard.getCardName()));
	}
	
	public boolean checkIfSeenByString(String checkThisString) {
		return (!unSeenCards.containsKey(checkThisString));
	}
}