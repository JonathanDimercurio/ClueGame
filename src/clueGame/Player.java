/* Player Abstract Class
 * Purpose:	This class is a parent is all
 * 			player classes for the board game.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

public abstract class Player {
	
	private List<Card> hand = new Vector<>();
	private String name;
	private Color color;
	private int keyPosition;
		
	public Player(Card addPlayerFromCard) {
		this.name = new String(addPlayerFromCard.getCardName());
		setStartLocationAndColor(addPlayerFromCard.getCardSymbol());
	}
	
	public Player(Deck constructFromDeck) {
		for(Card makeMeAComputerPlayer: constructFromDeck.getDeck()) {
		this.name = new String(makeMeAComputerPlayer.getCardName());
		setStartLocationAndColor(makeMeAComputerPlayer.getCardSymbol());
		}
	}

	public abstract void updateHand(Card newCard);
	public abstract Guess makeSuggestion();
	public abstract void updateKnownList();
	public abstract char getPType();
	
	/* setStartLocation(String)
	 * Purpose:	Using playerID, we use a switch statement to set
	 * the starting location and color
	 */
	//TODO Might want to make this a choice at the start of the game for the human.
	private void setStartLocationAndColor(String playerid) {
		switch (playerid) {
			case "NP":
				this.color = Color.getHSBColor(220, 40, 70);
//				this.position = new String("2,1");
				this.keyPosition = 29;
				break;
				
			case "NM":
				this.color = Color.RED;
//				this.position = new String("18,0");
				this.keyPosition = 18;
				break;
				
			case "AR":
				this.color = Color.GREEN;
//				this.position = new String("25,6");
				this.keyPosition = 187;
				break;
				
			case "CJ":
				this.color = Color.GRAY;
//				this.position = new String("12,1");
				this.keyPosition = 39;
				break;
				
			case "SC":
				this.color = Color.ORANGE;
//				this.position = new String("21,21");
				this.keyPosition = 588;
				break;
				
			case "DJ":
				this.color = Color.PINK;
//				this.position = new String("7,21");
				this.keyPosition = 574;
				break;
				
			case "SB":
				this.color = Color.YELLOW;
//				this.position = new String("25,12");
				this.keyPosition = 349;
				break;
				
			default:
			new BadConfigFormatException ("Starting location is not valid. Please check.");
			break;
		}
	}

	/* getHand() ~ required **********
	 * 
	 */
	public List<Card> getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	@SuppressWarnings("exports")
	public Color getColor() {
		return this.color;
	}

	protected BoardCell getCellPosition() {
		return BoardCell.mapGameBoardData.get(keyPosition);
	}

	protected void addCardToHand(Card hand) {
		this.hand.add(hand);
	}

	protected void updateCellPosition(BoardCell moveMeHere) {
		this.keyPosition = moveMeHere.getKey();
	}

}