package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Buttons.ButtonPanel;
import PlayerFiles.PlayerActions;
import States.PanelStates;
import UIResources.UICtrl;
import UIResources.UIPlayerControl;
import clueGame.Board;
import clueGame.Card;

@SuppressWarnings("serial")
public class ControlPanel extends PanelStates {
	
	@SuppressWarnings("exports")
	public static JSlider menuC = new JSlider(1,10);
	public static boolean hasRolled = false;
	public static boolean hasMoved = false;
	public static boolean hasGuessed = false;
	
//	private static Card humanReply;
	
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
				JPanel accPanel = new AccusationUI();
				Arrays.stream(buttons.getComponents())
				.forEach(comp->{
					comp.setEnabled(false);
				});
				add(accPanel);
				setVisible(true);
				break;
				
			case 2:
				//Suggestion panel ***
				JPanel sugPanel = new SuggestionPanel();
				Arrays.stream(buttons.getComponents())
				.forEach(comp->{
					comp.setEnabled(false);
				});
				add(sugPanel);
				setVisible(true);
				break;		
				
			case 3:
				//Roll Button hit
				int roll = PlayerActions.rollDice();
				
				Board.getInstance().calcTargets(
						UIPlayerControl.getHumPlayer()
						.getCellPosition(), roll);
				gridUI.displayHighlight(Board.getInstance().getTargets());
				
				add(new RollPanel(roll));
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
				case 8:
					menuControl(8);
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
	
	public static void genHumanReply(List<Card> cards) {
		menuC.setValue(3);
	}

}
