package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UserInterface.ControlPanel;

@SuppressWarnings("serial")
public class AccusationButton extends BLib{

	public AccusationButton() {
		setIcon(imageMap.get(iconNames[0]));
		setDisabledIcon(grayMap.get(iconNames[0]));
		buttonActions.modButtons(this);
	    addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ControlPanel.menuC.setValue(1);
	    	}
	    });
	    setVisible(true);
	}
}
