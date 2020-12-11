package clueGame;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import PlayerFiles.Player;

public interface DeckActions {
	
	/* placeInTypeDeck ~
	 * This simple method places the newly crafted card into
	 * the correct deck, by CardType.
	 */
	public static Deck createSeperateTypeDecks(Deck multipleTypeDeck, 
													CardType findType) {
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
		List<Card> newDeck = new Vector<Card>();
		newDeck.addAll(inputDeck.getDeck());
		return newDeck;
	}

	/* dealDeck() ~ takes a new copy of Deck.completeDeck
	 * and deals a card to every player until the deck is empty.
	 * This will result in 3 cards in every players hand.
	 */
	@SuppressWarnings("exports")
	public static void dealDeck(Deck fullDeck, 
									List<Player> dealToPlayers) {
		
		List<Card> dealingDeck = deckCloner(Solution
										.genSolution(fullDeck)); 
						
		int remainingCards = 0;		
		//Deal remainder cards if any first. Update dealingDeck.
		Collections.shuffle(dealingDeck);
		if(dealingDeck.size() % dealToPlayers.size() != 0) {
			remainingCards = dealingDeck.size() % dealToPlayers.size();
			for ( int index = remainingCards ; index > 0; index--) {
				dealToPlayers.get(index)
									.updateHand(dealingDeck
											.get(index));
				dealingDeck.remove(index);
			}
		}
		while(!dealingDeck.isEmpty()) {
			for (Player player: dealToPlayers) {
				player.updateHand(dealingDeck.get(0));
				dealingDeck.remove(0);
			}
		}
		
		for (Player CPUplayer: dealToPlayers) {
			CPUplayer.updateKnownList();
		}
		
	}
}
