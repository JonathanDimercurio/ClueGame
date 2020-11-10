/* Player Abstract Class
 * Purpose:	This class is a parent is all
 * 			player classes for the board game.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public abstract class Player {
	
	//Data structures and fields
	private List<Card> hand = new Vector<>();
	private String name;
	private Color color;
	private int keyPosition;
	private int currentPlayersTurn = 0;
	private int diceRoll;
	public static List<Player>		players = new Vector<>();
	
	//abstract updateHane method
	abstract void updateHand(Card newCard);
	//abstract void accusation();
	//Constructor
	public Player (String playerName, String playerID) {
		this.name = new String(playerName);
		setStartLocationAndColor(playerID);
		Player.players.add(this);
		
	}
	
	/* setStartLocation(String)
	 * Purpose:	Using playerID, we use a switch statement to set
	 * the starting location and color
	 */
	private void setStartLocationAndColor(String playerid) {
		switch (playerid) {
			case "NP":
				this.color = Color.BLUE;
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
				this.color = Color.BLACK;
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

	public List<Card> generateSuggestionReply(List<Card> suggestion, ComputerPlayer accuser) {
		List<Card> handledReply = new Vector<Card>();
		List<ComputerPlayer> playersChecking = new Vector<ComputerPlayer>();
		playersChecking.addAll(ComputerPlayer.computerPlayerList);
		playersChecking.remove(accuser);
		for (ComputerPlayer checkingThisPlayer: playersChecking) {
			if(checkingThisPlayer.checkSuggestion(suggestion) != null) {
				handledReply.add(checkingThisPlayer.checkSuggestion(suggestion));
			}
		}
		return handledReply;
	}
	
	/* checkSugggestion() ~ Dependencies: ~ Calls:
	 *  TODO: not very good. revisit when GUI is complete.
	 */
	@SuppressWarnings("unused")
	public Card checkSuggestion(List<Card> suggestedCards) {
		List<Card> replyList = new Vector<Card>();
		for (Card findCard: suggestedCards) {
			if (this.hand.contains(findCard)) {
				replyList.add(findCard);
			}
		}
		if (replyList != null) {
			return chooseReply(replyList);
		}
		return null;
		
	}
	
	/* chooseReply() ~ Dependencies: none ~ Calls: none
	 * Purpose:	
	 */
	private Card chooseReply(List<Card> cards) {
		cards.remove(null);
		Collections.shuffle(cards);
		if (!cards.isEmpty()) {
			return cards.get(0);
		}
		return null;
	}
	
	public List<Card> getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return this.color;
	}

	protected BoardCell getCellPosition() {
		return BoardCell.mapGameBoardData.get(keyPosition);
	}

	public void addCardToHand(Card hand) {
		this.hand.add(hand);
	}

	protected void updateCellPosition(BoardCell moveMeHere) {
		this.keyPosition = moveMeHere.getKey();
	}

	protected void emptyHand() {
		this.hand.removeAll(hand);
	}

	public static Player getHumanPlayer() {
		List hPlayer = Player.players;
		hPlayer.removeAll(ComputerPlayer.computerPlayerList);
		return (Player) hPlayer.get(0);
	}
}