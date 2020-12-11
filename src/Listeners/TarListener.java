package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import UIResources.UIPlayerControl;
import UserInterface.ControlPanel;
import UserInterface.gridUI;
import clueGame.BoardCell;

public class TarListener extends MouseAdapter {
		int theKey;
		int index = 0;
	boolean entered = false;
	
	public TarListener(int key) {
		this.theKey = key;
	}
	
		@Override
	public void mouseClicked(MouseEvent e) {
		if(entered) {
			gridUI.removePlayerIcon(UIPlayerControl.getHumPlayer());
			
			UIPlayerControl.getHumPlayer().moveMe(
					BoardCell.mapGameBoardData.get(theKey));
			
			gridUI.addPlayerIcon(UIPlayerControl.getHumPlayer());
			gridUI.removeHighlights();
			
			ControlPanel.hasRolled = true;
			ControlPanel.hasMoved = true;
			
			ControlPanel.menuC.setValue(9);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.entered = true;
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.entered = false;
		
	}
	}