package userInterface;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {
	
	private static void addAllElements(Container mainF) {
		
		
	    mainF.setLayout(new GridBagLayout());
	    GridBagConstraints GBL = new GridBagConstraints();
    
	    JPanel playerControl = GBPlayerControlPanel.createAndShowGUI();
	    GBL.weighty = 1.0;
	    GBL.gridx = -1;
	    GBL.fill = GridBagConstraints.WEST;
	    GBL.anchor = GridBagConstraints.SOUTHEAST;
	    mainF.add(playerControl, GBL);  
	
	    
	    
	    JPanel apControl = AccusationUI.apUI();
	    GBL.weighty = 1.0;
	    GBL.gridx = -1;
	    GBL.fill = GridBagConstraints.WEST;
	    GBL.anchor = GridBagConstraints.SOUTHEAST;
	    mainF.add(AccusationUI.apUI(), GBL);
	
	}
	
	
	
	
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Clue Game");
        frame.setPreferredSize(new Dimension(800,600)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        addAllElements(frame);
        frame.pack();
        frame.setVisible(true);
    }
	
}
