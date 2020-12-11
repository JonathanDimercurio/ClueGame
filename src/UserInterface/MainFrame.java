package UserInterface;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends SmartFrame{
	//TODO Testing
	public static int menuIndex = 1;
		
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
			case 3:
				JPanel gameP = GamePanel.me;
				gameP.addComponentListener(seeMe());
				add(gameP);
				menuIndex += 1;
				revalidate();
				break;
			case 4:
				JPanel lose = new LosePanel();
				lose.addComponentListener(seeMe());
				add(lose);
				revalidate();
				break;
			case 5:	
				add(new WinPanel());
				revalidate();
				break;
				
			default:
				gameP = GamePanel.me;
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