package clueGame;

public abstract class Player {
	String name;
	String color;
	int position;
	
	public Player (String playerName, String playerColor, int playerPositon) {
		
	}

	abstract void updateHand(Card newCard);
	

}