package UIResources;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import PlayerFiles.ComputerPlayer;
import PlayerFiles.HumanPlayer;
import PlayerFiles.Player;
import clueGame.Deck;

public class UIPlayerControl {
	public static UIPlayerControl playerCommand;
	private static int iPlayer = 0;
	
	public static Vector <Player> playerList = 
			new Vector<Player>();
	
	public static Map<String, Player> playerMap = 
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
		
	}
	
	public static void setHumanPlayer(String playerName) {
		humPlayer = new HumanPlayer(playerMap.get(playerName));
		playerMap.remove(playerName);
		playerMap.put(humPlayer.getName(), humPlayer);
		playerList.removeAll(playerList);
		playerList.addAll(playerMap.values());
		Collections.shuffle(playerList);
		playerList.forEach(player->{
			playerTurns.put(indexer(), player);
		});
		
	}
		
	public static HumanPlayer getHumPlayer() {
		return humPlayer;
	}
	
	public static Player getPlayerByInt(int index) {
		return playerTurns.get(index);
	}
	
	private static int indexer() {
		return iPlayer++;
	}
}