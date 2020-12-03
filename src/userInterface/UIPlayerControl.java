package userInterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import clueGame.ComputerPlayer;
import clueGame.Deck;
import clueGame.HumanPlayer;
import clueGame.Player;

public class UIPlayerControl {
	public static UIPlayerControl playerCommand;
	private static int index = 0;
	
	public static Vector <Player> playerList = 
			new Vector<Player>();
	
	
	
	private static Map<String, Player> playerMap = 
			new HashMap<String, Player>();
	
	private static Map<Integer, Player> playerTurns = 
			new HashMap<Integer, Player>();
	
	private static HumanPlayer humPlayer;
	
	
	
	public UIPlayerControl(Deck pDeck){
		UICtrl.playerDeck.getDeck()
				.forEach(card->{
			playerMap.put(new String(card.getCardName()), 
					new ComputerPlayer(card));
		});
		
		playerMap.values().stream().forEach(player->{
			playerList.add(player);
		});
		
		Collections.shuffle(playerList);
		
		playerList.forEach(player->{
			playerTurns.put(indexer(), player);
		});
		
	}
	
	public static void setHumanPlayer(String playerName) {
		humPlayer = new HumanPlayer(playerMap.get(playerName));
	}
	
	public static HumanPlayer getHumPlayer() {
		return humPlayer;
	}
	
	public static Player getPlayerByInt(int index) {
		return playerTurns.get(index);
	}
	
	private int indexer() {
		return index++;
	}
}
