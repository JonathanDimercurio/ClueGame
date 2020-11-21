package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import clueGame.Card;
import clueGame.CardType;

public class seenUI {
	
	public static Map<String, CardType> typeMap = new HashMap<String, CardType>();
	@SuppressWarnings("exports")
	public static Map<String, JTextArea> jTextMap = new HashMap<String, JTextArea>();
	@SuppressWarnings("exports")
	public static Map<CardType, JPanel> panelMap = new HashMap<CardType, JPanel>();
	
	private static DefaultListModel<Card> UIWatchList = new  DefaultListModel<Card>();

	public seenUI() {	
	}
	
	@SuppressWarnings("exports")
	public static JPanel populateSeenList() {
		int indexer = 1;
		UICtrl.playerList.forEach(player->{
			player.getHand().stream().forEach(card->{
				typeMap.put(card.getCardName(), card.getCardtype());
				jTextMap.put(card.getCardName(), new JTextArea());
				jTextMap.get(card.getCardName()).setName(card.getCardName());
				jTextMap.get(card.getCardName()).insert(card.getCardName(), 0);
				jTextMap.get(card.getCardName()).setBackground(player.getColor());
				jTextMap.get(card.getCardName()).setPreferredSize(new Dimension(100, 40));
				jTextMap.get(card.getCardName()).setVisible(false);
			});
		});

		JPanel temp = new JPanel();
		panelMap.put(CardType.NONE, temp);
		panelMap.get(CardType.NONE).setLayout(new GridLayout(0,1));
		panelMap.get(CardType.NONE).setPreferredSize(new Dimension(200,250));
		
		for(Card card: UICtrl.humanPlayer.getHand()){
			
			GridBagConstraints GBC = new GridBagConstraints();
			GBC.fill = GridBagConstraints.HORIZONTAL;
			GBC.anchor = GridBagConstraints.LINE_START;
			GBC.gridy = indexer++;
			panelMap.get(CardType.NONE).add(jTextMap.get(card.getCardName()),GBC);
			indexer++;
		}
		
		CardType[] typeList = {CardType.PERSON, CardType.WEAPON, CardType.ROOM};
		indexer = 1;
		for (CardType type: typeList) {
			JPanel tempP = new JPanel();
			tempP.add(new JLabel("Seen " + type.toString().toLowerCase() + "s:"));
			panelMap.put(type, tempP);
			panelMap.get(type).setName(type.toString());
			panelMap.get(type).setLayout(new  GridLayout(0,1));
			panelMap.get(type).setPreferredSize(new Dimension(200,250));
			
			for(String tempS: jTextMap.keySet()) {
				if (typeMap.get(tempS) == type) {
					
					GridBagConstraints GBC = new GridBagConstraints();
					GBC.fill = GridBagConstraints.HORIZONTAL;
					GBC.anchor = GridBagConstraints.LINE_START;
					GBC.gridy = indexer++;
					panelMap.get(type).add(jTextMap.get(tempS),GBC);
					indexer++;
				}
			}
			
			
		}
		
		JPanel mainFrame = new JPanel();
	    mainFrame.setMinimumSize(new Dimension(200,800));
	    mainFrame.setLayout(new GridLayout(4,0));
	    mainFrame.setBackground(Color.DARK_GRAY);
	    GridBagConstraints GBC = new GridBagConstraints();
	    mainFrame.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	    mainFrame.setVisible(true);
	    
	    
	    UIWatchList.addListDataListener(new ListDataListener() {
    		@Override
    		public void contentsChanged(ListDataEvent e) {
    			Card tempCard = UIWatchList.lastElement();
    			jTextMap.get(tempCard.getCardName()).setVisible(true);
    			mainFrame.revalidate();
    		}
    		
    		@Override
    		public void intervalAdded(ListDataEvent e) {contentsChanged(e);}
    		@Override
    		public void intervalRemoved(ListDataEvent e) {contentsChanged(e);}});

		int i = 1;
		GBC.fill = GridBagConstraints.HORIZONTAL;
		GBC.anchor = GridBagConstraints.LINE_START;
		GBC.gridy = i++;		
		panelMap.get(CardType.NONE).add(new JTextArea("Cards in Hand:"));
		JTextArea tempArea = new JTextArea();
		for (Card card: UICtrl.humanPlayer.getHand()) {
			GBC.gridy = i++;
			tempArea = new JTextArea();
			tempArea.setName(card.getCardName());
			tempArea.insert(card.getCardName(), 0);
			tempArea.setBackground(UICtrl.humanPlayer.getColor());
			tempArea.setPreferredSize(new Dimension(100, 40));
			tempArea.setVisible(true);
			panelMap.get(CardType.NONE).add(tempArea, GBC);
		}
		panelMap.get(CardType.NONE).setBackground(Color.LIGHT_GRAY);
		panelMap.get(CardType.NONE).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelMap.get(CardType.NONE).setVisible(true);
		mainFrame.add(panelMap.get(CardType.NONE), GBC);

		GBC.gridy = i++;
		panelMap.get(CardType.PERSON).setBackground(Color.LIGHT_GRAY);
		panelMap.get(CardType.PERSON).setVisible(true);
		panelMap.get(CardType.PERSON).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainFrame.add(panelMap.get(CardType.PERSON), GBC);
		
		GBC.gridy = i++;
		panelMap.get(CardType.WEAPON).setBackground(Color.LIGHT_GRAY);
		panelMap.get(CardType.WEAPON).setVisible(true);
		panelMap.get(CardType.WEAPON).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainFrame.add(panelMap.get(CardType.WEAPON), GBC);
		
		GBC.gridy = i++;
		panelMap.get(CardType.ROOM).setBackground(Color.LIGHT_GRAY);
		panelMap.get(CardType.ROOM).setVisible(true);
		panelMap.get(CardType.ROOM).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainFrame.add(panelMap.get(CardType.ROOM), GBC);		
		
		
    	return mainFrame;
	}

}