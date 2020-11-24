package userInterface;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {
	
	private static void addAllElements(Container mainF) {
		
		
	    mainF.setLayout(new GridBagLayout());
	    mainF.setBackground(Color.DARK_GRAY);
	    GridBagConstraints GBL = new GridBagConstraints();
	    
	    
	    GBL.gridx = 0;
	    GBL.gridy = 0;
	    GBL.fill = GridBagConstraints.WEST;
	    GBL.anchor = GridBagConstraints.NORTHWEST;
	    mainF.add(gridUI.createAndShowUI(), GBL);
	    
	    GBL.gridx = 2;
	    GBL.gridy = 0;
	    GBL.fill = GridBagConstraints.WEST;
	    GBL.anchor = GridBagConstraints.WEST;
	    mainF.add(seenUI.populateSeenList(), GBL);
	
	    
	    
	    GBL.gridx = 0;
	    GBL.gridy = 1;
	    GBL.insets = new Insets(-143,0,0,0);
	    GBL.anchor = GridBagConstraints.SOUTHWEST;
	    mainF.add(controlUI.createAndShowUI(), GBL);
	}
	
	
	
	
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Clue Game");
        frame.setMinimumSize(new Dimension(860,500)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        addAllElements(frame);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    //TODO Suggestion function not implemented
    //TODO 
	
}
