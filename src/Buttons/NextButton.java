package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UIResources.UITurnCtrl;
import UserInterface.ControlPanel;

@SuppressWarnings("serial")
public class NextButton extends BLib {
	public NextButton() {
		setIcon(imageMap.get(iconNames[1]));
		setDisabledIcon(grayMap.get(iconNames[1]));
		buttonActions.modButtons(this);
		addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ControlPanel.hasRolled = false;
		    	ControlPanel.hasGuessed = false;
		    	ControlPanel.hasMoved = false;
		    	
		    	UITurnCtrl.endTurn();
		    	ControlPanel.menuC.setValue(9);
		    }
		});
	    setVisible(true);
	}
}
