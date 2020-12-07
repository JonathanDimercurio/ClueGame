package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UserInterface.ControlPanel;

@SuppressWarnings("serial")
public class SuggestionButton extends BLib{
	
	public SuggestionButton() {
		setIcon(imageMap
				.get(iconNames[2]));
		setDisabledIcon(grayMap
				.get(iconNames[2]));
		buttonActions.modButtons(this);
		    addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		ControlPanel.menuC.setValue(2);
		    	}
		    }); 
	    setVisible(true);
	}
	
	
}