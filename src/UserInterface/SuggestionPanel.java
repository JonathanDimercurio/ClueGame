package UserInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import PlayerFiles.Guess;
import PlayerFiles.HumanPlayer;
import PlayerFiles.Player;
import PlayerFiles.PlayerActions;
import UIResources.UICtrl;
import UIResources.UIPlayerControl;
import clueGame.Card;
import clueGame.CardType;
import clueGame.GlossaryActions;

@SuppressWarnings("serial")
public class SuggestionPanel extends JPanel{	
	private static HashMap<String, JComboBox<String>> comboBoxs = 
					new HashMap<String, JComboBox<String>>();
	
	public SuggestionPanel() {		
		GridLayout gridType = new GridLayout(6, 1, 0, 0);
		gridType.setHgap(0);
		
		setLayout(gridType);
		setPreferredSize(new Dimension(100,180));
		setBackground(Color.DARK_GRAY);

		add(addCBox("pBox", CardType.PERSON), gridType);
		add(addCBox("wBox", CardType.WEAPON), gridType);
		add(addCBox("rBox", CardType.ROOM), gridType);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlPanel.menuC.setValue(9);
			}
		});
		
		add(cancelButton, gridType);
		JButton makeAcuButton = new JButton("Commit");
		makeAcuButton.addActionListener (new ActionListener () {
			@Override
		    public void actionPerformed(ActionEvent e) {
				
				HumanPlayer humP = UIPlayerControl.getHumPlayer();
				ArrayList<Card> replies = new ArrayList<Card>();
				Guess guess = generateGuess();
				humP.setHumGuess(guess);
				
				for(Player eachPlayer: UIPlayerControl.playerList) {
					if(PlayerActions.checkForReply(guess, eachPlayer)) {
						replies.add(PlayerActions
								.generateReply(guess, eachPlayer));
					}
				}	
				humP.resolveReplies(replies);
				ControlPanel.hasGuessed = true;
				
				ControlPanel.menuC.setValue(9);
				
			}});
		add(makeAcuButton);
	}

	private static Guess generateGuess() {
		
		Card pCard = new Card(GlossaryActions
				.findCardByName((String) comboBoxs
						.get("pBox").getSelectedItem()));
		
		Card rCard = new Card(GlossaryActions
				.findCardByName((String) comboBoxs
						.get("rBox").getSelectedItem()));

		Card wCard = new Card(GlossaryActions
				.findCardByName((String) comboBoxs
						.get("wBox").getSelectedItem()));

		
		return new Guess(pCard, rCard, wCard);
		
	}
	
	private static JComboBox<String> addCBox(String boxName, CardType listType) {
		comboBoxs.put(boxName, new JComboBox<String>(type2List(listType)));
		return comboBoxs.get(boxName);
	}	
	
	public static Vector<String> type2List(CardType findType) {
		Vector<String> aListTest = new Vector<String>();
		if(findType != CardType.ROOM) {
			UICtrl.mainDeck.getDeck().stream()
			.filter(card1 -> card1.getCardtype() == 
						findType && card1.getCardName()!=null)
			.collect(Collectors.toList()).forEach(card2 -> 
			aListTest.add(card2.getCardName()));
		} else {
			aListTest.add(UIPlayerControl.getHumPlayer()
					.getCurrentCell().getMyRoomType().getRoomName());
		}

		return aListTest;
	}
	
}
