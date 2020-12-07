package Buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import States.PanelStates;
import UIResources.UICtrl;
import UIResources.UITurnCtrl;
import UserInterface.SmallPlayerPanel;

@SuppressWarnings("serial")
public class ButtonPanel extends PanelStates{
	
	private JButton sButton;
	private JButton rButton;
	private JButton aButton;
	
	public ButtonPanel() {
		initButtonPanel();
		buttonController();
	}
	
	private void checkButtons() {
		sButton = new SuggestionButton();
		rButton = new RollButton();
		aButton = new AccusationButton();
		
	}
	
	private void buttonController() {
		checkButtons();
		if (UITurnCtrl.ifHumanPlayersTurn()) {
			sButton.setEnabled(true);
			rButton.setEnabled(true);
			aButton.setEnabled(true);
		}
		if (!UITurnCtrl.ifHumanPlayersTurn()) {
			sButton.setEnabled(false);
			rButton.setEnabled(false);
			aButton.setEnabled(false);
		}
		add(sButton);
		add(rButton);
		add(aButton);
		add(new NextButton());
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