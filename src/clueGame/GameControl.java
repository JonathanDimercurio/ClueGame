package clueGame;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public interface GameControl {
	
	public static void gameAssetInit() {
		GameControl.assignPlayers();
		GameControl.deal();
	}
	 
	/* findCardByname() ~
	 * this method will take a string and compare it to the completeDeck
	 * until it matches the string name, the it will return a new Card
	 * object.
	 */
	public static Card findCardByName(String findThisCard) {
		for (Card potentialMatch: Deck.getCompleteDeck()) {
			if (potentialMatch.getCardName().contentEquals(findThisCard)) {
				Card foundCard = new Card(potentialMatch);
				return foundCard;
			}
		}
		return null;
	}
	
	static CardType findCardTypeByString(String checkType) {
		switch (checkType) {
			case "Person":
				return CardType.PERSON;
			case "Weapon":
				return CardType.WEAPON;
			case "Room":
				return CardType.ROOM;
		default:
				new BadConfigFormatException ("Card is not valid, please check ClueSetup.txt for errors.");
				return null;
		}
	}
	
	/* deal() ~ takes a new copy of Deck.completeDeck
	 * and deals a card to every player until the deck is empty.
	 * This will result in 3 cards in every players hand.
	 */
	public static void deal() {
		List<Card> theDeck = new Vector<Card>();
		theDeck = Deck.dealersDeck();
		dealDeck(theDeck);
		while(!theDeck.isEmpty()) {
			int i = 0;
			GameControl.dealCard(i, theDeck);
		}
	}
	
	static void dealDeck(List<Card> dealingDeck) {
		while(!dealingDeck.isEmpty()) {
			int i = 0;
			dealCard(i, dealingDeck);
		}
	}
	
	
	/* dealCard() ~ Recursive solution to deal out
	 * cards to every player. Mainly for practice
	 * working with recursion.
	 */
	private static void dealCard(int count, List<Card> Deck) {
		Player.players.get(count).updateHand(Deck.get(count++));
		if (count < 7) { GameControl.dealCard(count, Deck); }
		Deck.remove(0);	
	}

	/* assignPlayers() ~ creates a static List of players based off
	 * the SetupConfig.txt file. We use this through the program to
	 * Reference the current players turn, and many other features.
	 */
	static void assignPlayers() {
		 Vector<Card> undealtPeople = new Vector<Card>();
		 undealtPeople.addAll(Deck.getTotalPeopleDeck());
		 Collections.shuffle(undealtPeople);
		 Card humanPlayer = undealtPeople.get(0);
		 Player.players.add(new HumanPlayer(humanPlayer.getCardName(), humanPlayer.getCardSymbol()));
		 undealtPeople.remove(humanPlayer);
		 for (Card tempCard: undealtPeople) {
			 Player.players.add(new ComputerPlayer(tempCard.getCardName(), tempCard.getCardSymbol()));
		 }
	}
	
	static void initRooms() {
		
		for( String[] temp1: ClueFileIO.getFormattedSetupFile()) {
			if (temp1[0].contentEquals("Room") || temp1[0].contentEquals("Space") ) {
				Room addRoom = new Room(temp1);
				Room.roomMap.put(addRoom.getKey(), addRoom);
			}
		}
	}
}