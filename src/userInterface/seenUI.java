package userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import clueGame.Card;
import clueGame.CardType;
import clueGame.Deck;

public class seenUI {
	
	public static Map<String, CardType> typeMap = new HashMap<String, CardType>();
	public static Map<String, JTextArea> jTextMap = new HashMap<String, JTextArea>();
	public static Map<CardType, JPanel> panelMap = new HashMap<CardType, JPanel>();
	
	private static DefaultListModel<Card> UIWatchList = new  DefaultListModel<Card>();
	
	
	
	public seenUI() {
		
	}
	
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
            	UICtrl.initUI();
            	populateSeenList();
            }
        });	
    }
	

	private static void addElements(Container mFrame) {
		int i = 0;
		mFrame.setLayout(new GridBagLayout());
		GridBagConstraints GBC = new GridBagConstraints();
		GBC.fill = GridBagConstraints.HORIZONTAL;
		GBC.anchor = GridBagConstraints.LINE_START;
		GBC.gridy = i++;
		
		
		
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
		mFrame.add(panelMap.get(CardType.NONE), GBC);

		GBC.gridy = i++;
		Arrays.stream(panelMap.get(CardType.PERSON).getComponents()).forEach(comp->{
			comp.setVisible(false);});
		panelMap.get(CardType.PERSON).setBackground(Color.LIGHT_GRAY);
		panelMap.get(CardType.PERSON).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelMap.get(CardType.PERSON).setVisible(true);
		mFrame.add(panelMap.get(CardType.PERSON), GBC);
		
		GBC.gridy = i++;
		Arrays.stream(panelMap.get(CardType.WEAPON).getComponents()).forEach(comp->{
			comp.setVisible(false);});
		panelMap.get(CardType.WEAPON).setBackground(Color.LIGHT_GRAY);
		panelMap.get(CardType.WEAPON).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelMap.get(CardType.WEAPON).setVisible(true);
		mFrame.add(panelMap.get(CardType.WEAPON), GBC);
		
		GBC.gridy = i++;
		Arrays.stream(panelMap.get(CardType.ROOM).getComponents()).forEach(comp->{
			comp.setVisible(false);});
		panelMap.get(CardType.ROOM).setBackground(Color.LIGHT_GRAY);
		panelMap.get(CardType.ROOM).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelMap.get(CardType.ROOM).setVisible(true);
		mFrame.add(panelMap.get(CardType.ROOM), GBC);
		
		JButton testButton = new JButton("Test");
    	testButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			Deck tempDeck = new Deck(UICtrl.mainDeck.getDeck());
    			Collections.shuffle(tempDeck.getDeck());
    			UIWatchList.addElement(tempDeck.getDeck().get(0));
    			tempDeck.getDeck().remove(0);}});
    	testButton.setVisible(true);
    	mFrame.add(testButton);
		
	}
	
	private static void createAndShowUI() {
		JFrame mainFrame = new JFrame("Seen Cards List");
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setMinimumSize(new Dimension(200,800));
	    addElements(mainFrame);
	    mainFrame.pack();
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
	}

	public static void populateSeenList() {
		int indexer = 1;
		UICtrl.playerList.forEach(player->{
			player.getHand().stream().forEach(card->{
				typeMap.put(card.getCardName(), card.getCardtype());
				jTextMap.put(card.getCardName(), new JTextArea());
				jTextMap.get(card.getCardName()).setName(card.getCardName());
				jTextMap.get(card.getCardName()).insert(card.getCardName(), 0);
				jTextMap.get(card.getCardName()).setBackground(player.getColor());
				jTextMap.get(card.getCardName()).setPreferredSize(new Dimension(100, 40));
			});
		});

		JPanel temp = new JPanel();
		temp.add(new JLabel("Cards in you hand:"));
		panelMap.put(CardType.NONE, temp);
		panelMap.get(CardType.NONE).setName("Hand");
		panelMap.get(CardType.NONE).setLayout(new GridBagLayout());
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
			panelMap.get(type).setLayout(new GridBagLayout());
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
		createAndShowUI();
	}
}
