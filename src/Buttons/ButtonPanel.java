package Buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import States.PanelStates;
import UIResources.UIPlayerControl;
import UIResources.UITurnCtrl;
import UserInterface.ControlPanel;
import UserInterface.SmallPlayerPanel;

@SuppressWarnings("serial")
public class ButtonPanel extends PanelStates{
	
	
	
	private JButton sButton;
	private JButton rButton;
	private JButton aButton;
	private JButton nButton;
	
	public ButtonPanel() {
		initButtonPanel();
		buttonController();
	}
	
	private void checkButtons() {
		sButton = new SuggestionButton();
		rButton = new RollButton();
		aButton = new AccusationButton();
		nButton = new NextButton();
		
	}
	
	private void buttonController() {
		
		checkButtons();
		if (UITurnCtrl.ifHumanPlayersTurn()) {
			
			
			if(UIPlayerControl.getHumPlayer()
					.getCellPosition()
					.isRoomCenter() && !ControlPanel.hasGuessed) {		
				sButton.setEnabled(true);
			} else { 
				sButton.setEnabled(false); 
			}
			
			
			if(!ControlPanel.hasRolled) {
				rButton.setEnabled(true);
			}else {
				rButton.setEnabled(false);
			}
			
			
			if (ControlPanel.hasMoved)  {
				nButton.setEnabled(true);
			} else {
				nButton.setEnabled(false);
			}
			
			
			aButton.setEnabled(true);
			
			
		}
		if (!UITurnCtrl.ifHumanPlayersTurn()) {
			sButton.setEnabled(false);
			rButton.setEnabled(false);
			aButton.setEnabled(false);
			nButton.setEnabled(true);
		}
		
		add(sButton);
		add(rButton);
		add(aButton); 
		add(nButton);
	}
		
	private void initButtonPanel() {
		setMinimumSize(new Dimension(690, 138));
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		GridLayout gridType = new GridLayout(0,2);
		gridType.setVgap(5);
		setLayout(gridType);
		add(new JLabel("          Current Player:"));
		add(new SmallPlayerPanel(UITurnCtrl
						.getCurrentPlayer()));		
	}

}