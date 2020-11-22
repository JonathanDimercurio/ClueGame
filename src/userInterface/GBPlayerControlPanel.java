
package userInterface;
 
/*
 * GridBagLayoutDemo.java requires no other files.
 */
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
 

public class GBPlayerControlPanel {
	
    final static boolean shouldFill = false;
    final static boolean shouldWeightX = true;
    
    private static JButton button;
    private static ImageIcon bIcon;
    private static JLabel label;
    
    @SuppressWarnings("exports")
	public static JPanel gbPlayerControlPanel = new JPanel();
    
    public GBPlayerControlPanel() {
    	
    }
 
    @SuppressWarnings("exports")
	public static JPanel createAndShowGUI() {
    	gbPlayerControlPanel.setPreferredSize(new Dimension(300, 100));
    	gbPlayerControlPanel.setBorder(UICommands.getPBorder(UICommands.currentPlayer()));
    	gbPlayerControlPanel.setLayout(new GridBagLayout());
		GridBagConstraints cGBC = new GridBagConstraints();
		
		bIcon = new ImageIcon("resources/sBut01.png");
		button = new JButton(bIcon);
		buttonActions.modButtons(button);
		cGBC.fill = GridBagConstraints.NONE;
		cGBC.gridx = 3;
		cGBC.gridwidth = 2;   //2 columns wide
		cGBC.gridy = 0;
			
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	 buttonActions.bSound1();
	        	 button.setVisible(false);}});
	    gbPlayerControlPanel.add(button, cGBC);
		   
		    
		bIcon = new ImageIcon("resources/sBut01.png");
		button = new JButton(bIcon);
		buttonActions.modButtons(button);
		cGBC.fill = GridBagConstraints.NONE;
		cGBC.gridx = 3;
		cGBC.gridwidth = 2;
		cGBC.gridy = 0;
		button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			buttonActions.bSound1();
			button.setVisible(false);}});
		gbPlayerControlPanel.add(button, cGBC);
		 
		bIcon = new ImageIcon("resources/rBut01.png");
		button = new JButton(bIcon);
		buttonActions.modButtons(button);
		cGBC.fill = GridBagConstraints.NONE;
		cGBC.gridx = 6;
		cGBC.gridwidth = 2;   //2 columns wide
		cGBC.gridy = 0;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonActions.bSound1();
				button.setVisible(false);}});
		gbPlayerControlPanel.add(button, cGBC);
//		 
		    
		label = new JLabel(UICommands.currentPlayer().getName(), JLabel.LEFT);
		cGBC.anchor = GridBagConstraints.CENTER;
		cGBC.fill = GridBagConstraints.WEST;
//		    cGCB.weighty = -2.0; 
//		    cGCB.weightx = -1.0;
		cGBC.gridwidth = 20;
		cGBC.gridx = 6;
		cGBC.gridy = 1;
		gbPlayerControlPanel.add(label, cGBC);
		 
		    
		bIcon = new ImageIcon("resources/nBut01.png");
		button = new JButton(bIcon);
		buttonActions.modButtons(button);
		cGBC.fill = GridBagConstraints.NONE;
		cGBC.ipady = 0;       //reset to default
//		    cGCB.weighty = 1.0;   //request any extra vertical space
		cGBC.anchor = GridBagConstraints.SOUTHWEST; //bottom of space
		cGBC.insets = new Insets(10,0,0,0);  //top padding
		cGBC.gridx = 0;       //aligned with button 2
		cGBC.gridwidth = 10;   //2 columns wide
		cGBC.gridy = 1;       //third row
			button.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		        	 buttonActions.bSound1();
		        	 button.setVisible(false);}});
		gbPlayerControlPanel.add(button, cGBC);
		
		

 
 
        //Display the window.
        gbPlayerControlPanel.setVisible(true);
		return gbPlayerControlPanel;
    }
    

}
