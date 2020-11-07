//package clueGame;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Vector;
//
//public class Deck {
//	
//	//Local Data Structures
//	private List<Card> deck = new Vector<>();
//	
//	private void constructDecksByCardType(String addCardType) {
//		String[] spliter = new String[4];
//		spliter = addCardType.split(", ");
//		if (!spliter[0].equals("Space")) {
//			Card tempCard = new Card(spliter[0],spliter[1],spliter[2]);
//			this.deck.add(tempCard);
//		}
//	}
//	
//	private void dealDeck(List<Card> dealingDeck) {
//		while(!dealingDeck.isEmpty()) {
//			int i = 0;
//			dealCard(i, dealingDeck);
//		}
//	}
//
//	private void dealCard(int i, List<Card> Deck) {
//		players.get(i).updateHand(Deck.get(i++));
//		if (i < 7) { dealCard(i, Deck); }
//		Deck.remove(0);	
//	}
//
//	/* shuffleindividualDecks() 	~ Returns: HashSet<Card>
//	 * Purpose: 
//	 */
//	private List<Card> combineAllDecks() {
//		ArrayList<Vector<Card>> individualDecks = new ArrayList<Vector<Card>>();
//		individualDecks.add(new Vector<Card>(Card.getTotalRooms()));
//		individualDecks.add(new Vector<Card>(Card.getTotalPeople()));
//		individualDecks.add(new Vector<Card>(Card.getTotalWeapons()));
//	
//		for (Vector<Card> eachDeck: individualDecks) {
//			Collections.shuffle(eachDeck);
//		}
//		return generateSolution(individualDecks);
//		//TODO
//	}
//	
//	/* generateSolution() 			~ Returns: ArrayList<Vector<Card>> 
//	 * Purpose: This method will establish the winning combination of cards.
//	 * 			After doing so, it removes them from the List, and returns
//	 * 			a modified List of cards.
//	 */
//	@SuppressWarnings("unlikely-arg-type")
//	private List<Card> generateSolution(ArrayList<Vector<Card>> allDecks) {
//		this.theSolution = new Solution(allDecks.get(0).get(0), allDecks.get(1).get(0), allDecks.get(2).get(0));
//		for (int i = 0; i<3; i++) { allDecks.get(i).remove(0); }
//		return shuffleDecksTogether(allDecks);
//	}
//	
//	/* shuffleDeck()
//	 * Purpose:	Here we shuffle all of the elements of individualDecks together
//	 * 			after having removed the member variables that make up solution.
//	 * 			We return a HashSet of cards to be deal to players.
//	 */
//	private List<Card> shuffleDecksTogether(ArrayList<Vector<Card>> decksByType) {
//		List<Card> combinedDeck = new Vector<Card>();
//		for (Vector<Card> tempDeck: decksByType) {
//				combinedDeck.addAll(tempDeck);
//			}
//		Collections.shuffle(combinedDeck);
//		return combinedDeck;
//	}
//	//End Deck methods
//
//}
