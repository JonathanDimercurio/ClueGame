package userInterface;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {
	
	private static void addAllElements(Container mainF) {
		
		
	    mainF.setLayout(new GridBagLayout());
	    GridBagConstraints GBL = new GridBagConstraints();
	    

//	    JButton button;
//	    button = new JButton("Accusation");
//	    GBL.fill = GridBagConstraints.NONE;
//	    GBL.gridx = 0;
//	    GBL.gridy = 0;
//	    mainF.add(button, GBL);
//	   
//	    button = new JButton("Suggestion");
//	    GBL.fill = GridBagConstraints.NONE;
//	    GBL.weightx = 0.5;
//	    GBL.gridx = 1;
//	    GBL.gridy = 0;
//	    mainF.add(button, GBL);
//	 
//	    button = new JButton("");
//	    GBL.fill = GridBagConstraints.NONE;
//	    GBL.weightx = 0.5;
//	    GBL.gridx = 2;
//	    GBL.gridy = 0;
//	    mainF.add(button, GBL);

	    
	    
	    JPanel playerControl = GBPlayerControlPanel.createAndShowGUI();
	    GBL.weighty = 1.0;
	    GBL.gridx = -1;
	    GBL.fill = GridBagConstraints.WEST;
	    GBL.anchor = GridBagConstraints.SOUTHEAST;
	    mainF.add(playerControl, GBL);  
	}
	
	
	
	
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Clue Game");
        
        
        
        frame.setPreferredSize(new Dimension(600,600)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        addAllElements(frame);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
}
