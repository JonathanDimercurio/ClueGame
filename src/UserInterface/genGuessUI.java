package UserInterface;

import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import PlayerFiles.Guess;
import UIResources.UICtrl;
import clueGame.CardType;
import clueGame.GlossaryActions;

public class genGuessUI {
	private static HashMap<String, JComboBox<String>> comboBoxs = new HashMap<String, JComboBox<String>>();
	private static boolean state = false;
	
	
	public static JPanel createAndShowUI() {
		JPanel mainP = new JPanel();
		GridLayout gridType = new GridLayout(6, 1, 0, 0);
		gridType.setHgap(0);
		mainP.setLayout(gridType);
		
		mainP.add(addCBox("pBox", CardType.PERSON), gridType);
		mainP.add(addCBox("wBox", CardType.WEAPON), gridType);
		mainP.add(addCBox("rBox", CardType.ROOM), gridType);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				  JComponent comp = (JComponent) e.getSource();
				  Window win = SwingUtilities.getWindowAncestor(comp);
				  win.dispose();
			}
		});
		
		mainP.add(cancelButton, gridType);
		
		JButton makeAcuButton = new JButton("Commit");
		makeAcuButton.addActionListener (new ActionListener () {
			@Override
		    public void actionPerformed(ActionEvent e) {
				//TODO 			handle this
				//				generateGuess();
			}
		});
		mainP.add(makeAcuButton, gridType);

		
		return mainP;		
	}
	
	public static void handleGuess(boolean ifAccusation) {
		
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

	public static Vector<String> type2List(CardType findType) {
		Vector<String> aListTest = new Vector<String>();
		UICtrl.mainDeck.getDeck().stream()
			.filter(card1 -> card1.getCardtype() == findType && card1.getCardName()!=null)
			.collect(Collectors.toList()).forEach(card2 -> aListTest.add(card2.getCardName()));
		return aListTest;
	}
	
	
}
