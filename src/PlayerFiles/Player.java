/* Player Abstract Class
 * Purpose:	This class is a parent is all
 * 			player classes for the board game.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package PlayerFiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import UIResources.PlayerIcon;
import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.Deck;

public abstract class Player {
	
	protected List<Card> hand = new Vector<>();
	protected ArrayList<Image> icon = new ArrayList<Image>();
	protected String name;
	protected Color color;
	protected int keyPosition;
	protected Point position;
	protected int roomCenterOffset;
	
	public Player() {
		
	}
	
	public Player(Card addPlayerFromCard) {
		this.name = new String(addPlayerFromCard.getCardName());
		setStartLocationAndColor(addPlayerFromCard.getCardSymbol());
	}
	
	public Player(Deck constructFromDeck) {
		for(Card makeMeAComputerPlayer: constructFromDeck.getDeck()) {
		this.name = new String(makeMeAComputerPlayer
				.getCardName());
		setStartLocationAndColor(makeMeAComputerPlayer
				.getCardSymbol());
		}
	}

	public abstract BoardCell getCurrentCell();
	public abstract boolean checkForReply(Guess guess);
	public abstract Card generateReply(Guess guess);
	public abstract void updateHand(Card newCard);
	public abstract Guess makeSuggestion();
	public abstract void updateKnownList();
	public abstract char getPType();
	
	/* setStartLocation(String)
	 * Purpose:	Using playerID, we use a switch statement to set
	 * the starting location and color
	 */
	private void setStartLocationAndColor(String playerid) {
		switch (playerid) {
			case "NP":
				this.color = new Color(60, 120, 220);
				this.position = new Point(2,1);
				this.keyPosition = 29;
				this.icon.addAll(new 
						PlayerIcon("resources/nepPIcon.png")
						.getImage());
				roomCenterOffset = Board.getInstance().getNumColumns();
				break;
				
			case "NM":
				this.color = Color.ORANGE;
				this.position = new Point(18,0);
				this.keyPosition = 18;
				this.icon.addAll(new 
						PlayerIcon("resources/nemPIcon.png")
						.getImage());
				roomCenterOffset = 1;
				break;
				
			case "AR":
				this.color = new Color(0, 150, 0);
				this.position = new Point(25,6);
				this.keyPosition = 187;
				this.icon.addAll(new 
						PlayerIcon("resources/ariPIcon.png")
						.getImage());
				roomCenterOffset = -1;
				break;
				
			case "CJ":
				this.color = Color.GRAY;
				this.position = new Point(12,1);
				this.keyPosition = 39;
				this.icon.addAll(new 
						PlayerIcon("resources/capPIcon.png")
						.getImage());
				this.roomCenterOffset = 2;
				break;
				
			case "SC":
				this.color = Color.PINK;
				this.position = new Point(21,21);
				this.keyPosition = 588;
				this.icon.addAll(new 
						PlayerIcon("resources/sanPIcon.png")
						.getImage());
				this.roomCenterOffset = 1 - (Board.getInstance().getNumColumns());
				break;
				
			case "DJ":
				this.color = Color.RED;
				this.position = new Point(7,21);
				this.keyPosition = 574;
				this.icon.addAll(new 
						PlayerIcon("resources/davPIcon.png")
						.getImage());
				this.roomCenterOffset = 1 + (Board.getInstance().getNumColumns());
				break;
				
			case "SB":
				this.color = Color.YELLOW;
				this.position = new Point(25,12);
				this.keyPosition = 349;
				this.icon.addAll(new 
						PlayerIcon("resources/bobPIcon.png")
						.getImage());
				this.roomCenterOffset = 0;
				break;
				
			default:
			new BadConfigFormatException
				("Starting location is not valid. Please check.");
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

	public Point getPosition() {
		return this.position;
	}
	
	public Image getSmallIcon() {
		return this.icon.get(0);
	}

	public Image getLargeIcon() {
		return this.icon.get(1);
	}
	public int roomCenterOffset() {
		return this.roomCenterOffset;
	}
}