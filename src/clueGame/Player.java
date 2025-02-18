/* Player Abstract Class
 * Purpose:	This class is a parent is all
 * 			player classes for the board game.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.List;
import java.util.Vector;

import userInterface.PlayerIcon;

public abstract class Player {
	
	private List<Card> hand = new Vector<>();
	private String name;
	private Color color;
	private int keyPosition;
	private Image icon;
	private Point position;
		
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
				this.position = new Point(2,1);
				this.keyPosition = 29;
				this.icon = new PlayerIcon("resources/nepPIcon.png").getImage();
				break;
				
			case "NM":
				this.color = Color.RED;
				this.position = new Point(18,0);
				this.keyPosition = 18;
				this.icon = new PlayerIcon("resources/nemPIcon.png").getImage();
				break;
				
			case "AR":
				this.color = Color.GREEN;
				this.position = new Point(25,6);
				this.keyPosition = 187;
				this.icon = new PlayerIcon("resources/ariPIcon.png").getImage();
				break;
				
			case "CJ":
				this.color = Color.GRAY;
				this.position = new Point(12,1);
				this.keyPosition = 39;
				this.icon = new PlayerIcon("resources/capPIcon.png").getImage();
				break;
				
			case "SC":
				this.color = Color.ORANGE;
				this.position = new Point(21,21);
				this.keyPosition = 588;
				this.icon = new PlayerIcon("resources/sanPIcon.png").getImage();
				break;
				
			case "DJ":
				this.color = Color.PINK;
				this.position = new Point(7,21);
				this.keyPosition = 574;
				this.icon = new PlayerIcon("resources/davPIcon.png").getImage();
				break;
				
			case "SB":
				this.color = Color.YELLOW;
				this.position = new Point(25,12);
				this.keyPosition = 349;
				this.icon = new PlayerIcon("resources/bobPIcon.png").getImage();
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

	public BoardCell getCellPosition() {
		return BoardCell.mapGameBoardData.get(keyPosition);
	}

	protected void addCardToHand(Card hand) {
		this.hand.add(hand);
	}

	protected void updateCellPosition(BoardCell moveMeHere) {
		this.keyPosition = moveMeHere.getKey();
	}

	@SuppressWarnings("exports")
	public Point getPosition() {
		return this.position;
	}
	
	@SuppressWarnings("exports")
	public Image getIcon() {
		return this.icon;
	}
	
}