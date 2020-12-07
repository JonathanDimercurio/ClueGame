package UserInterface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import States.PanelStates;

@SuppressWarnings("serial")
public class GamePanel extends PanelStates{
	
	public GamePanel() {
		super();
		setLayout(new GridBagLayout());
		setBackground(Color.DARK_GRAY);
	    GridBagConstraints GBL = 
	    		new GridBagConstraints();
	    
	    GBL.gridx = 0;
	    GBL.gridy = 0;
	    GBL.fill = GridBagConstraints.WEST;
	    GBL.anchor = GridBagConstraints
	    			.NORTHWEST;
	    add(gridUI.createAndShowUI(), GBL);
	    
	    GBL.gridx = 2;
	    GBL.gridy = 0;
	    GBL.fill = GridBagConstraints.WEST;
	    GBL.anchor = GridBagConstraints.WEST;
	    add(SeenPanel.populateSeenList(), GBL);
	
	    GBL.gridx = 0;
	    GBL.gridy = 1;
	    GBL.insets = new Insets(-143,0,0,0);
	    GBL.anchor = GridBagConstraints
	    		.SOUTHWEST;
	    add(new ControlPanel(), GBL);
	}
	
}