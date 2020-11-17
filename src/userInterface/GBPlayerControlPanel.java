
package userInterface;
 
/*
 * GridBagLayoutDemo.java requires no other files.
 */
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import clueGame.Player;
 

public class GBPlayerControlPanel {
	
    final static boolean shouldFill = false;
    final static boolean shouldWeightX = true;
    private static JButton button;
    private static ImageIcon bIcon;
    private static JLabel label;
    
    public static JPanel gbPlayerControlPanel = new JPanel();
    
    public GBPlayerControlPanel() {
    	
    }
 
    public static void addComponentsToPane(Container pane) {
    }
 
    public static JPanel createAndShowGUI() {
    	gbPlayerControlPanel.setPreferredSize(new Dimension(300, 100));
    	gbPlayerControlPanel.setBorder(UICommands.getPBorder(UICommands.currentPlayer()));
    	gbPlayerControlPanel.setLayout(new GridBagLayout());
		GridBagConstraints cGCB = new GridBagConstraints();
		
		bIcon = new ImageIcon("resources/sBut01.png");
		button = new JButton(bIcon);
		buttonActions.modButtons(button);
		cGCB.fill = GridBagConstraints.NONE;
//		cGCB.weightx = 1;
		cGCB.gridx = 3;
		cGCB.gridwidth = 2;   //2 columns wide
		cGCB.gridy = 0;
			
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	 buttonActions.bSound1();
	        	 button.setVisible(false);}});
	    gbPlayerControlPanel.add(button, cGCB);
		   
		    
		bIcon = new ImageIcon("resources/sBut01.png");
		button = new JButton(bIcon);
		buttonActions.modButtons(button);
		cGCB.fill = GridBagConstraints.NONE;
//		    cGCB.weightx = 1;
		cGCB.gridx = 3;
		cGCB.gridwidth = 2;   //2 columns wide
		cGCB.gridy = 0;
		button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			buttonActions.bSound1();
			button.setVisible(false);}});
		gbPlayerControlPanel.add(button, cGCB);
		 
		bIcon = new ImageIcon("resources/rBut01.png");
		button = new JButton(bIcon);
		buttonActions.modButtons(button);
		cGCB.fill = GridBagConstraints.NONE;
//		    cGCB.weightx = 1;
		cGCB.gridx = 6;
		cGCB.gridwidth = 2;   //2 columns wide
		cGCB.gridy = 0;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonActions.bSound1();
				button.setVisible(false);}});
		gbPlayerControlPanel.add(button, cGCB);
//		 
		    
		label = new JLabel(UICommands.currentPlayer().getName(), JLabel.LEFT);
		cGCB.anchor = GridBagConstraints.CENTER;
		cGCB.fill = GridBagConstraints.WEST;
//		    cGCB.weighty = -2.0; 
//		    cGCB.weightx = -1.0;
		cGCB.gridwidth = 20;
		cGCB.gridx = 6;
		cGCB.gridy = 1;
		gbPlayerControlPanel.add(label, cGCB);
		 
		    
		bIcon = new ImageIcon("resources/nBut01.png");
		button = new JButton(bIcon);
		buttonActions.modButtons(button);
		cGCB.fill = GridBagConstraints.NONE;
		cGCB.ipady = 0;       //reset to default
//		    cGCB.weighty = 1.0;   //request any extra vertical space
		cGCB.anchor = GridBagConstraints.SOUTHWEST; //bottom of space
		cGCB.insets = new Insets(10,0,0,0);  //top padding
		cGCB.gridx = 0;       //aligned with button 2
		cGCB.gridwidth = 10;   //2 columns wide
		cGCB.gridy = 1;       //third row
			button.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		        	 buttonActions.bSound1();
		        	 button.setVisible(false);}});
		gbPlayerControlPanel.add(button, cGCB);
		
		

 
        //Set up the content pane.
        addComponentsToPane(gbPlayerControlPanel.getRootPane());
 
        //Display the window.
        gbPlayerControlPanel.setVisible(true);
		return gbPlayerControlPanel;
    }
    

}
