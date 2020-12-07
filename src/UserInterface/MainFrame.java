package UserInterface;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import ComputerAI.ComputerAI;
import States.States.is;
import UIResources.UITurnCtrl;
import clueGame.ComputerPlayer;

@SuppressWarnings("serial")
public class MainFrame extends SmartFrame{
	protected int menuIndex = 2;
		
	public MainFrame() {

		menuControl(menuIndex);
		setVisible(true);
		pack();
	}

	protected void menuControl(int menu) {
		switch (menu) {
			case 1:
				JPanel startMenu = new StartMenu();
				startMenu.addComponentListener(seeMe());
				add(startMenu);
				menuIndex += 1;
				revalidate();
				break;
			case 2:
				JPanel playerChooser = new playerSelectUI();
				playerChooser.addComponentListener(seeMe());
				add(playerChooser);
				menuIndex += 1;
				revalidate();
				break;
			default:
				JPanel gameP = new GamePanel();
				gameP.addComponentListener(seeMe());
				add(gameP);
				menuIndex += 1;
				revalidate();
				break;
		}
	}

	protected ComponentListener seeMe() {
		return new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				menuControl(menuIndex);
			}	
		};
	}
}