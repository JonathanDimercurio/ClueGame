/* Player Abstract Class
 * Purpose:	This class is a parent is all
 * 			player classes for the board game.
 * Authors:	Jonathan Dimercurio, Senya Stein
 */
package clueGame;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public abstract class Player {
	
	//Data structures and fields
	private List<Card> hand = new Vector<>();
	private String name;
	private String color;
	private String position;

	//abstract updateHane method
	abstract void updateHand(Card newCard);
	//abstract void accusation();
	
	
	//Constructor
	public Player (String playerName, String playerID) {
		this.name = new String(playerName);
		setStartLocationAndColor(playerID);
	}
	
	/* setStartLocation(String)
	 * Purpose:	Using playerID, we use a switch statement to set
	 * the starting location and color
	 */
	private void setStartLocationAndColor(String playerid) {
		switch (playerid) {
			case "NP":
				this.color = new String("BLUE");
				this.position = new String("2,1");
				break;
				
			case "NM":
				this.color = new String("RED");
				this.position = new String("0,18");
				break;
				
			case "AR":
				this.color = new String("GREEN");
				this.position = new String("25,6");
				break;
				
			case "CJ":
				this.color = new String("BLACK");
				this.position = new String("25,2");
				break;
				
			case "SC":
				this.color = new String("ORANGE");
				this.position = new String("21,21");
				break;
				
			case "DJ":
				this.color = new String("PURPLE");
				this.position = new String("7,21");
				break;
				
			case "SB":
				this.color = new String("YELLOW");
				this.position = new String("3,12");
				break;
				
			default:
			new BadConfigFormatException ("Starting location is not valid. Please check.");
			break;
		}
	}

	
	public List<Card> suggestionList(Card suggestedPerson, Card suggestedRoom, Card suggestedWeapon) {
		List<Card> suggestionList = new Vector<Card>();
		suggestionList.add(suggestedPerson);
		suggestionList.add(suggestedRoom);
		suggestionList.add(suggestedWeapon);
		return suggestionList;
	}
	
	/* checkSugggestion() ~ Dependencies: ~ Calls:
	 *  TODO: not very good. revisit when GUI is complete.
	 */
	public String checkSuggestion(List<Card> suggestedCards) {
		List<Card> replyList = new Vector<Card>();
		for (Card findCard: suggestedCards) {
			if (this.hand.contains(findCard)) {
				replyList.add(findCard);
			}
		}
		return chooseReply(replyList);
	}
	
	/* chooseReply() ~ Dependencies: none ~ Calls: none
	 * Purpose:	
	 */
	private String chooseReply(List<Card> cards) {
		Collections.shuffle(cards);
		return cards.get(0).getCardName();
	}
	
	
	//Getters
	public List<Card> getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public String getPosition() {
		return position;
	}

	public void addCardToHand(Card hand) {
		this.hand.add(hand);
	}

	@Override
	public String toString() {
		return "Player: " + name + ""
				+ "\nIn Hand:\n" + handToString() 
				+ "Color: " + color 
				+ "\nPosition: (" + position + ")\n";
	}
	
	private String handToString() {
		String tempString = new String();
		for (Card tempCard: hand) {
			tempString = tempString.concat("\tCard Name[" + tempCard.getCardName() + " - " + tempCard.getCardtype() + " - " +tempCard.getCardSymbol() + "]\n");
		}
		return tempString;
	}
}