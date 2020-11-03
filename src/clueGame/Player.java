package clueGame;

import java.util.List;
import java.util.Vector;

public abstract class Player {
	private List<Card> hand = new Vector<>();
	private String name;
	private String color;
	private int position;
	
	public Player (String playerName, String playerColor, int playerPositon) {
		this.name = new String(playerName);
		this.color = new String(playerColor);
	}

	abstract void updateHand(Card newCard);

}