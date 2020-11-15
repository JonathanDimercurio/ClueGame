package clueGame;

public interface GameControl {
	
	public static void gameAssetInit() {
	}
	 
	/* findCardByname() 
	 * this method will take a string and compare it to the completeDeck
	 * until it matches the string name, the it will return a new Card
	 * object.
	 */
	public static Card findCardByNameInDeck(String findThisCard, Deck inThisDeck) {
		for (Card potentialMatch: inThisDeck.getDeck()) {
			if (potentialMatch.getCardName().contentEquals(findThisCard)) {
				Card foundCard = new Card(potentialMatch);
				return foundCard;
			}
		}
		return null;
	}
	
	static CardType matchStringToCardType(String checkType) {
		switch (checkType) {
			case "Person":
				return CardType.PERSON;
			case "Weapon":
				return CardType.WEAPON;
			case "Room":
				return CardType.ROOM;
		default:
				return CardType.NONE;
		}
	}
		
//	static void initRooms() {
//		
//		for( String[] temp1: ClueFileIO.getFormattedSetupFile()) {
//			if (temp1[0].contentEquals("Room") || temp1[0].contentEquals("Space") ) {
//				Room addRoom = new Room(temp1);
//				Room.roomMap.put(addRoom.getKey(), addRoom);
//			}
//		}
//	}
}