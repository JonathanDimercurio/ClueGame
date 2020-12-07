package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UIResources.UITurnCtrl;
import UserInterface.ControlPanel;

@SuppressWarnings("serial")
public class RollButton extends BLib{
	
	public RollButton() {
		super();
		setIcon(imageMap
				.get(iconNames[3]));
		setDisabledIcon(grayMap
				.get(iconNames[3]));
		buttonActions.modButtons(this);
		    addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    	}
		    });
	    setVisible(true);
	}
}
