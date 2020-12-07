package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;

import States.PanelStates;
import UIResources.UICtrl;
import clueGame.CardType;
import clueGame.GlossaryActions;
import clueGame.Guess;

@SuppressWarnings("serial")
public class AccuPanel extends PanelStates{
	private static HashMap<String, JComboBox<String>> comboBoxs = 
			new HashMap<String, JComboBox<String>>();

	public AccuPanel() {
		setPreferredSize(new Dimension(100,180));
		setBackground(Color.DARK_GRAY);
		GridLayout grid = new GridLayout(6,1,0,0);
		grid.setHgap(1);
		setLayout(grid);
		
		add(addCBox("pBox", CardType.PERSON), grid);
		add(addCBox("wBox", CardType.WEAPON), grid);
		add(addCBox("rBox", CardType.ROOM), grid);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		add(cancelButton, grid);
		JButton makeAcuButton = new JButton("Commit");
		makeAcuButton.addActionListener (new ActionListener () {
			@Override
		    public void actionPerformed(ActionEvent e) {
				generateGuess();
				//TODO figure this crap out
			}
		});
		
	}
	
	private static Guess generateGuess() {
		return 	new Guess(
					GlossaryActions.findCardByList(
						comboBoxs.get("pBox").getSelectedItem().toString(),
						comboBoxs.get("wBox").getSelectedItem().toString(),
						comboBoxs.get("rBox").getSelectedItem().toString()
				));

	}

	private static JComboBox<String> addCBox(String boxName,
											CardType listType) {
		comboBoxs.put(boxName, 
				new JComboBox<String>(type2List(listType)));
		return comboBoxs.get(boxName);
		
	}
	
	public static Vector<String> type2List(CardType findType) {
		Vector<String> aListTest = new Vector<String>();
		UICtrl.mainDeck.getDeck().stream()
			.filter(card1 -> card1.getCardtype() == 
						findType && card1.getCardName()!=null)
			.collect(Collectors.toList()).forEach(card2 -> 
						aListTest.add(card2.getCardName()));
		return aListTest;
	}

		
}