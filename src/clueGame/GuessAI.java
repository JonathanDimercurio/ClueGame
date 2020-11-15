/* GuessAI
 * Purpose:
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 */

package clueGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GuessAI implements GlossaryActions {
	
	private Map<String, Card> unSeenCards = new HashMap<String, Card>();
	
	public GuessAI() {
		GlossaryActions.allKnownCardsSet()
				.forEach(card -> unSeenCards.put(card.getCardName(),card));
		}
	
	public Guess generateGuess(String roomName) {
		Card roomGuess = new Card(GlossaryActions.findCardByName(roomName));
		Card personGuess = new Card(genCardByType(CardType.PERSON).get());
		Card weaponGuess = new Card(genCardByType(CardType.WEAPON).get());	
		Guess theGuess = new Guess(roomGuess,personGuess,weaponGuess);
		return theGuess;
	}
	
	private Optional<Card> genCardByType(CardType findType) {
		return unSeenCards.values().stream()
				.filter(card -> card.getCardtype().equals(findType))
						.findAny();
	}
	
	public List<Card> createSeenCardList() {
		 return GlossaryActions.allKnownCardsSet().stream()
				 .filter(card -> unSeenCards.values().contains(card))
				 .collect(Collectors.toList());
	}
 
 	public List<Card> getUnSeenCards() {
 		return this.unSeenCards.values().stream().collect(Collectors.toList());
 	}

	public void addListToSeen(List<Card> seenCards) {
			for(Card removeThisCard: seenCards) {
				this.unSeenCards.remove(removeThisCard.getCardName());
			}
	}
	
	public boolean checkIfSeen(Card checkThisCard) {
		return (!unSeenCards.containsKey(checkThisCard.getCardName()));
	}
	
	public boolean checkIfSeenByString(String checkThisString) {
		return (!unSeenCards.containsKey(checkThisString));
	}
}