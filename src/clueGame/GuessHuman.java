///* GuessAI
// * Purpose:
// * 
// * @author Jonathan Dimercurio
// * @author Senya Stein
// */
//
//package clueGame;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Vector;
//import java.util.stream.Collectors;
//
//public class GuessHuman implements GlossaryActions {
//	
//	public Map<String, Card> unSeenCards = new HashMap<String, Card>();
//	
//	public GuessHuman() {
//		GlossaryActions.allKnownCardsSet().stream().forEach(card->{
//			unSeenCards.put(card.getCardName(), card);
//		});
//		}
//	
//	public void generateGuess(Guess choosenGuess) {
//	}
//
// 	public List<Card> getUnSeenCards() {
// 		return this.unSeenCards.values().stream().collect(Collectors.toList());
// 	}
//
//	public void addListToSeen(List<Card> seenCards) {
//			for(Card removeThisCard: seenCards) {
//				this.unSeenCards.remove(removeThisCard.getCardName());
//			}
//	}
//	
//	public boolean checkIfSeen(Card checkThisCard) {
//		return (!unSeenCards.containsKey(checkThisCard.getCardName()));
//	}
//	
//	public boolean checkIfSeenByString(String checkThisString) {
//		return (!unSeenCards.containsKey(checkThisString));
//	}
//}