package userInterface;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class MainFrame {
	
	
	private static void addAllElements(Container mainF) {
		
		
	    mainF.setLayout(new GridBagLayout());
	    GridBagConstraints cGCB = new GridBagConstraints();
	    
	    
	    GBPlayerControlPanel playerCtrlPanel = new GBPlayerControlPanel();
	    cGCB.anchor = GridBagConstraints.LAST_LINE_START;
	    mainF.add(playerCtrlPanel, cGCB);
	    
	}
	
	
	
	
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Clue Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        addAllElements(frame);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
	
}
