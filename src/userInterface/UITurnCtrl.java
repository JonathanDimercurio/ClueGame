package userInterface;

import clueGame.Player;

public class UITurnCtrl {
	private static int currentTurnNumber = 0;
	private static int indexTurnRotator = 0;
	
	public static void endTurn() {
		currentTurnNumber += 1;
		indexTurnRotator = currentTurnNumber % UIPlayerControl
												.playerList.size();
	}
	
	public static Player getCurrentPlayer() {
		return UIPlayerControl
				.getPlayerByInt(indexTurnRotator);
	}
	
	public static boolean ifHumanPlayersTurn() {
		if (getCurrentPlayer().getPType() == 'H') {
			return true;
		} 
		return false;
	}
}