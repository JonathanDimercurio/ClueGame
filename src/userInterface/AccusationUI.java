package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import clueGame.CardType;
import clueGame.GlossaryActions;
import clueGame.Guess;

public class AccusationUI {	
	private static HashMap<String, JComboBox<String>> comboBoxs = new HashMap<String, JComboBox<String>>();
	
	private AccusationUI() {
		apUI();
	}
	
	@SuppressWarnings("exports")
	public static JPanel apUI() {
	
		 
		JPanel AP = new JPanel();
		GridLayout gridType = new GridLayout(6, 1, 0, 0);
		gridType.setHgap(0);
		
		AP.setLayout(gridType);
		AP.setPreferredSize(new Dimension(100,180));
		AP.setBackground(Color.red);
		
		
		AP.add(addCBox("pBox", CardType.PERSON), gridType);
		AP.add(addCBox("wBox", CardType.WEAPON), gridType);
		AP.add(addCBox("rBox", CardType.ROOM), gridType);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				  JComponent comp = (JComponent) e.getSource();
				  Window win = SwingUtilities.getWindowAncestor(comp);
				  win.dispose();
			}
		});
		
		AP.add(cancelButton, gridType);
		
		JButton makeAcuButton = new JButton("Commit");
		makeAcuButton.addActionListener (new ActionListener () {
			@Override
		    public void actionPerformed(ActionEvent e) {
				generateGuess();
			}
		});
		AP.add(makeAcuButton, gridType);
		
		
		
//**************************************** implement through UIActions
		return AP;
	}
	
	
	
	
	
	private static Guess generateGuess() {
		return 	new Guess(
					GlossaryActions.findCardByList(
						comboBoxs.get("pBox").getSelectedItem().toString(),
						comboBoxs.get("wBox").getSelectedItem().toString(),
						comboBoxs.get("rBox").getSelectedItem().toString()
				));

	}
	
	private static JComboBox<String> addCBox(String boxName, CardType listType) {
		comboBoxs.put(boxName, new JComboBox<String>(type2List(listType)));
		return comboBoxs.get(boxName);
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
