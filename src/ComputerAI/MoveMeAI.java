package ComputerAI;

import UIResources.UICtrl;
import UserInterface.gridUI;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;

public class MoveMeAI {
	
	public MoveMeAI(ComputerPlayer currentCompPlayer) {
		BoardCell.mapGameBoardData.get(currentCompPlayer
											.getCurrentCell()
											.getKey())
											.setOccupied(false);
		
		gridUI.removePlayerIcon(currentCompPlayer);
		currentCompPlayer.move();
		gridUI.addPlayerIcon(currentCompPlayer);
		
		BoardCell.mapGameBoardData.get(currentCompPlayer
											.getCurrentCell()
											.getKey())
											.setOccupied(true);	
		UICtrl.revalidate();
		
	}
	
}