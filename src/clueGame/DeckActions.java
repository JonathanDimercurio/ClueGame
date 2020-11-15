package clueGame;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public interface DeckActions {
	
	/* placeInTypeDeck ~
	 * This simple method places the newly crafted card into
	 * the correct deck, by CardType.
	 */
	public static Deck createSeperateTypeDecks(Deck multipleTypeDeck, CardType findType) {
		Deck deckByType = new Deck();
		for (Card tempCard: multipleTypeDeck.getDeck()) {
			if (tempCard.getCardtype() == findType) {
				deckByType.getDeck().add(tempCard);
			}
		}
		return deckByType;
	}
	
	/* dealersDeck() 	~ Returns: List<Card>
	 * Purpose: 
	 */
	public static List<Card> deckCloner (Deck inputDeck) {
		List<Card> dealersDeck = new Vector<Card>();
		dealersDeck.addAll(inputDeck.getDeck());
		return dealersDeck;
	}

	/* dealDeck() ~ takes a new copy of Deck.completeDeck
	 * and deals a card to every player until the deck is empty.
	 * This will result in 3 cards in every players hand.
	 */
	public static void dealDeck(Deck dealingDeck, List<Player> dealToPlayers) {
		Collections.shuffle((List<?>) dealingDeck.getDeck());
		while(!dealingDeck.getDeck().isEmpty()) {
			int i = 0;
			dealCard(i, dealingDeck, dealToPlayers);
		}
	}
	
	/* dealCard() ~ Recursive solution to deal out
	 * cards to every player. Mainly for practice
	 * working with recursion.
	 */
	private static void dealCard(int indexer, Deck dealingDeck, List<Player> dealToPlayers) {
		dealToPlayers.get(indexer).updateHand(dealingDeck.getDeck().get(indexer++));
		if (indexer < 7) { dealCard(indexer, dealingDeck, dealToPlayers); }
		dealingDeck.getDeck().remove(0);
	}

}
