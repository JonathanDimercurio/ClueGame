package UIResources;

import java.awt.Color;

import javax.swing.JComponent;

import ComputerAI.ComputerAI;
import clueGame.ComputerPlayer;
import clueGame.Player;

@SuppressWarnings("serial")
public class UITurnCtrl extends JComponent {
	private static int currentTurnNumber = 0;
	
	public static void endTurn() {
		currentTurnNumber += 1;
		if(!UITurnCtrl.ifHumanPlayersTurn()) {
			new ComputerAI((ComputerPlayer) UITurnCtrl
					.getCurrentPlayer());
		}
		UICtrl.revalidate();
	}
	
	public static Player getCurrentPlayer() {
		return UIPlayerControl
				.getPlayerByInt(getIndex());
	}
	
	public static boolean ifHumanPlayersTurn() {
		if (getCurrentPlayer().getPType() == 'H') {
			return true;
		} 
		return false;
	}
	
	public static Color getCurPlyrColor() {
		return getCurrentPlayer().getColor();
	}
	
	private static int getIndex() {
		return currentTurnNumber % UIPlayerControl
		.playerList.size();

	}
}