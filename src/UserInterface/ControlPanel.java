package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Buttons.ButtonPanel;
import States.PanelStates;
import UIResources.UICtrl;

@SuppressWarnings("serial")
public class ControlPanel extends PanelStates {
	
	@SuppressWarnings("exports")
	public static JSlider menuC = new JSlider(1,10);
	
	public ControlPanel() {
		initPanel();
		menuControl(0);
	}
	
	protected void menuControl(int menu) {
		removeAll();
		JPanel buttons = new ButtonPanel();
		buttons.setVisible(true);
		add(buttons);
		switch (menu) {
			case 1:
				//Accusation panel ***
				JPanel accPanel = AccusationUI.apUI();
				Arrays.stream(buttons.getComponents())
				.forEach(comp->{
					comp.setEnabled(false);
				});
				add(accPanel);
				setVisible(true);
				break;
			case 2:
				//Suggestion panel ***
				JPanel sugPanel = AccusationUI.apUI();
				Arrays.stream(buttons.getComponents())
				.forEach(comp->{
					comp.setEnabled(false);
				});
				add(sugPanel);
				setVisible(true);
				break;
			default:
				
				JPanel playerPanel = new PlayerTurnPanel();
				add(playerPanel);
				setVisible(true);
				break;
		}
		setVisible(true);
		UICtrl.revalidate();
	}
		
	private void initPanel() {
		setBackground(Color.DARK_GRAY);
		setMinimumSize(new Dimension(702, 180));
		setBorder(BorderFactory
					.createEmptyBorder(10, 5, 5, 5));
		GridLayout grid = new GridLayout(0,2);
		grid.setHgap(15);
		setLayout(grid);
		menuC.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				switch (menuC.getValue()) {
				case 1:
					menuControl(1);
					break;
				case 2:
					menuControl(2);
					break;
				case 3:
					menuControl(3);
					break;
				case 9:
					ControlPanel.menuC.setValue(10);
					break;
				default:
					menuControl(0);
					break;
				
				}
			}
			
		});
	}

}
