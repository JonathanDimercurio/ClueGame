package userInterface;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import clueGame.Card;
import clueGame.Player;

public interface UICommands {
	
//	public static Player currentPlayer() {
//		return UIPlayerControl.playerList.get(UICtrl.pIndexer);
//	}
	
//	public static void endTurn() {
//		UICtrl.pIndexer += 1;
//	}
	
	public static JButton modBoardCellBlocks(JLabel cellBackground) {
		return null;
	}

	public static Border getPBorder(Player currentPlayer) {
		Border pBorder = BorderFactory.createLineBorder(currentPlayer.getColor(), 3); 
		UICommands.setBorder(UILocNames.GBPlayerControlPanel, pBorder);
		return UICommands.getBorder(UILocNames.GBPlayerControlPanel);
	}
	
	public static void setBorder(UILocNames loc, Border newBorder) {
		UICtrl.borderLib.put(loc, newBorder);
	}
	
	public static Border getBorder(UILocNames loc) {
		return UICtrl.borderLib.get(loc);
	}
	
	public static void updatePlayerSeenList(Player player, Card ... cards) {
	}
	
}
