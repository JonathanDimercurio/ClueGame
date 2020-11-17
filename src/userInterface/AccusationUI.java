package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import clueGame.CardType;

public class AccusationUI {
	
	
	
	
	@SuppressWarnings("exports")
	public static JPanel apUI() {
	
		JPanel AP = new JPanel();
		AP.setLayout(new GridBagLayout());
		GridBagConstraints cGBC = new GridBagConstraints();
		AP.setPreferredSize(new Dimension(300, 100));
		AP.setBackground(Color.red);
		
		
		JComboBox<String> personBox = new JComboBox<String>(AccusationUI.type2List(CardType.PERSON));
	cGBC.fill = GridBagConstraints.HORIZONTAL;
//		cGBC.ipady = 0;       //reset to default
		cGBC.weighty = 1.0;   //request any extra vertical space
		cGBC.weightx = 1.0;
//		cGBC.anchor = GridBagConstraints.EAST; //bottom of space
		cGBC.insets = new Insets(10,0,0,0);  //top padding
		cGBC.gridx = 5;       //aligned with button 2
		cGBC.gridwidth = 10;   //2 columns wide
		cGBC.gridy = 0;       //third row
		AP.add(personBox, cGBC);
		
		JComboBox<String> weaponBox = new JComboBox<String>(AccusationUI.type2List(CardType.WEAPON));
		cGBC.fill = GridBagConstraints.HORIZONTAL;
//		cGBC.ipady = 0;       //reset to default
		cGBC.weighty = 1.0;   //request any extra vertical space
		cGBC.weightx = 1.0;
//		cGBC.anchor = GridBagConstraints.EAST; //bottom of space
		cGBC.insets = new Insets(10,0,0,0);  //top padding
		cGBC.gridx = 5;       //aligned with button 2
		cGBC.gridwidth = 10;   //2 columns wide
		cGBC.gridy = 50;       //third row
		AP.add(weaponBox, cGBC);
		
		JComboBox<String> roomBox = new JComboBox<String>(AccusationUI.type2List(CardType.ROOM));
		cGBC.fill = GridBagConstraints.HORIZONTAL;
//		cGBC.ipady = 0;       //reset to default
		cGBC.weighty = 1.0;   //request any extra vertical space
		cGBC.weightx = 1.0;
//		cGBC.anchor = GridBagConstraints.EAST; //bottom of space
		cGBC.insets = new Insets(10,0,0,0);  //top padding
		cGBC.gridx = 5;       //aligned with button 2
		cGBC.gridwidth = 10;   //2 columns wide
		cGBC.gridy = 100;       //third row

		AP.add(roomBox, cGBC);
		
		
		AP.setVisible(true);
		
		
//**************************************** implement through UIActions
		return AP;
	}
	
	//This method dictates what will population the ComboBoxes,
	//based of what CardType. A more advanced version could remove
	//arbitrary elements from each list, perhaps based off the seen list
	public static Vector<String> type2List(CardType findType) {
		Vector<String> aListTest = new Vector<String>();
		UICtrl.mainDeck.getDeck().stream()
			.filter(card1 -> card1.getCardtype() == findType && card1.getCardName()!=null)
			.collect(Collectors.toList()).forEach(card2 -> aListTest.add(card2.getCardName()));
		return aListTest;
	}
	
}
