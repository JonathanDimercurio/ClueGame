package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import States.PanelStates;
import UIResources.UITurnCtrl;
import clueGame.Player;

@SuppressWarnings("serial")
public class SmallPlayerPanel extends PanelStates{

	
	public SmallPlayerPanel(Player player) {
		setLayout(new GridLayout(0, 2));
		add(new JLabel(new ImageIcon(player.getSmallIcon())));
		JTextField playerName = new JTextField(" " + player.getName());
		playerName.setFont(new Font("SansSerif", Font.BOLD, 9));
		playerName.setFocusable(false);
		add(playerName);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		if(player.getColor() == UITurnCtrl.getCurPlyrColor()) {
			setBackground(player.getColor());
		}
		
	}
}