package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Arrays;

import States.PanelStates;
import UIResources.UIPlayerControl;
import clueGame.Player;

@SuppressWarnings("serial")
public class PlayerTurnPanel extends PanelStates{
	
	public PlayerTurnPanel() {
		super();
		setPreferredSize(new Dimension(100,180));
		GridLayout gridType = new GridLayout(6, 1, 0, 0);
		gridType.setHgap(5);
		gridType.setVgap(1);
		setBackground(Color.DARK_GRAY);
		setLayout(gridType);
		
		
		UIPlayerControl.playerList
					.stream().forEach(player->{	
				add(new SmallPlayerPanel(player), gridType);
		});
		
		
		
	}
}