package userInterface;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ButtonLib {
    public static Map<String, ImageIcon> 			bIcon = new HashMap<String, ImageIcon>();
    public static Map<String, JLabel> 				bLabel = new HashMap<String, JLabel>();
    public static Map<String, GridBagConstraints>	GBC = new 	HashMap<String, GridBagConstraints>();
    public static Map<String, JButton>				buttons = new HashMap<String, JButton>();
    
    private static ButtonLib buttonLibrary = new ButtonLib();
    
    public static ButtonLib getButtonLib() {
    	return ButtonLib.buttonLibrary;
    }
 
    private ButtonLib() {
    	constructNextButton();
    	contructSuggestionButton();
    }
	
	private void constructNextButton() {
		bIcon.put(new String("nIcon"), new ImageIcon("resources/sBut01.png"));
		bLabel.put(new String("nLabel"), new JLabel());
		buttons.put(new String("nButton"), new JButton());
		GBC.put(new String("nGCB"), new GridBagConstraints());
	}
	
	
	public Container addNextbutton(Container pane) {

		GridBagConstraints 	nGCB = GBC.get("nGCB");
		JLabel				nLabel = bLabel.get("nLabel");
		JButton				nButton =  buttons.get("nButton");
		buttonActions.modButtons(nButton);
		
		
		nGCB.fill = GridBagConstraints.NONE;
//		cGCB.weightx = 1;
		nGCB.gridx = 3;
		nGCB.gridwidth = 2;   //2 columns wide
		nGCB.gridy = 0;
			
		nButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	 buttonActions.bSound1();
	        	 nButton.setVisible(false);}});
		pane.add(nButton, nGCB);
		return pane;
		
	}
	
	private void contructSuggestionButton() {
		
	}

	
	
}
