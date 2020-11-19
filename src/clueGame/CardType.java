package clueGame;

public enum CardType {
	WEAPON, PERSON, ROOM, NONE;
	
	public static CardType findCardTypeByString(String checkType) {
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
	
}
