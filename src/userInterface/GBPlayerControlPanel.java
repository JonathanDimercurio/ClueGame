
package userInterface;
 
/*
 * GridBagLayoutDemo.java requires no other files.
 */
 
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
 
@SuppressWarnings("serial")
public class GBPlayerControlPanel extends Container{
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    public GBPlayerControlPanel() {
    	createAndShowGUI();
    }
 
    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
 
        JButton button;
    pane.setLayout(new GridBagLayout());
    GridBagConstraints cGCB = new GridBagConstraints();
    if (shouldFill) {
    //natural height, maximum width
    cGCB.fill = GridBagConstraints.HORIZONTAL;
    }
 
    button = new JButton("Button 1");
    if (shouldWeightX) {
    cGCB.weightx = 0.5;
    }
    cGCB.fill = GridBagConstraints.HORIZONTAL;
    cGCB.gridx = 0;
    cGCB.gridy = 0;
    pane.add(button, cGCB);
    button = new JButton("Button 2");
    cGCB.fill = GridBagConstraints.HORIZONTAL;
    cGCB.weightx = 0.5;
    cGCB.gridx = 1;
    cGCB.gridy = 0;
    pane.add(button, cGCB);
 
    button = new JButton("Button 3");
    cGCB.fill = GridBagConstraints.HORIZONTAL;
    cGCB.weightx = 0.5;
    cGCB.gridx = 2;
    cGCB.gridy = 0;
    pane.add(button, cGCB);
 
    button = new JButton("Long-Named Button 4");
    cGCB.fill = GridBagConstraints.HORIZONTAL;
    cGCB.ipady = 30;      //make this component tall
    cGCB.weightx = 0.0;
    cGCB.gridwidth = 3;
    cGCB.gridx = 0;
    cGCB.gridy = 1;
    pane.add(button, cGCB);
 
    button = new JButton("5");
    cGCB.fill = GridBagConstraints.HORIZONTAL;
    cGCB.ipady = 0;       //reset to default
    cGCB.weighty = 1.0;   //request any extra vertical space
    cGCB.anchor = GridBagConstraints.PAGE_END; //bottom of space
    cGCB.insets = new Insets(10,0,0,0);  //top padding
    cGCB.gridx = 1;       //aligned with button 2
    cGCB.gridwidth = 2;   //2 columns wide
    cGCB.gridy = 2;       //third row
    pane.add(button, cGCB);
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
 
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

