package userInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import clueGame.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import clueGame.Card;
import clueGame.CardType;

public class UISeenCards {
	private static int index = 0;
	private static int indexTestList = 0;
	public static Map<Player, Vector<Card>> humanSeenCards = new HashMap<Player, Vector<Card>>();
	private static DefaultListModel<Card> UIWatchList = new  DefaultListModel<Card>();
	private static Map<CardType, JPanel> panelMap = new HashMap<CardType, JPanel>();
	
	
	private static UISeenCards thisPanel;
	
	
	private UISeenCards() {
		populateSeenList();

	}
	
	
	static public void initUISeenCards() {
		thisPanel = new UISeenCards();
	}
	
	static public UISeenCards getUIPanel() {
		return UISeenCards.thisPanel;
	}
   
	public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	UICtrl.initUI();
            	createAndShow();
            	
            }
        });
    }

    private static void addAllElements(Container knownF) {
		panelMap.put(CardType.PERSON, new JPanel());
		panelMap.put(CardType.WEAPON, new JPanel());
		panelMap.put(CardType.ROOM, new JPanel());
    	
    	
    	JPanel knownCardsPanel = new JPanel();
    	knownCardsPanel.setPreferredSize(new Dimension(100,600));
    	knownCardsPanel.setVisible(true);
    	knownCardsPanel.setLayout(new BoxLayout(knownCardsPanel, BoxLayout.PAGE_AXIS));
    	
    	JPanel tPanel = new JPanel();
    	JTextArea text1 = new JTextArea("Testing1");
    	text1.setVisible(true);
    	tPanel.add(text1);
    	knownCardsPanel.add(tPanel);
    	
    	
    	UIWatchList.addListDataListener(new ListDataListener() {
    		@Override
    		public void contentsChanged(ListDataEvent e) {
    	    	Card newSeenCard = new Card(UIWatchList.lastElement());
    			for (Component tempPanel: tPanel) {
    				tPanel.add(cTemp);}
    			
    			panelMap.put(newSeenCard.getCardtype(), tPanel);
    			
    			JTextArea tText = new JTextArea(newSeenCard.getCardName());
    			tText.setVisible(true);
    			System.out.println(tText.getText());
    			
    		}
    		
    		@Override
    		public void intervalAdded(ListDataEvent e) {
    			contentsChanged(e);}
    		
    		@Override
    		public void intervalRemoved(ListDataEvent e) {
    			contentsChanged(e);}
    	});
   
    	panelMap.values().stream().forEach(panel->{
    		panel.setVisible(true);
    		knownCardsPanel.add(panel);
    	});
    	
    	
    	
    	//Testing Block
    	ArrayList<Card> testList1 = new ArrayList<Card>();
    	UICtrl.mainDeck.getDeck().forEach(card->{
    		testList1.add(new Card(card));
    	});
    	JButton bButton = new JButton("Hi");
    	bButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			UIWatchList.addElement(testList1.get(indexTestList));
    			humanSeenCards.get(UICtrl.playerList.get(3)).add(UIWatchList.get(indexTestList++));
    		}
    	});
    	bButton.setVisible(true);
    	knownCardsPanel.add(bButton);
    	//Testing Block end
    	
    	
    	
    	
    	knownF.add(knownCardsPanel);
    	
    	
    }
    
    private static JPanel typeList(CardType type, Card card) {
    	
    	return null;
    }
    
    
    public static void addCardToHumanSeenList(Player player, Card card) {
    	humanSeenCards.get(player).add(card);
    	UIWatchList.addElement(card);
    }
    		
    //Populate the Lists block
	private static JPanel sList(String label, CardType theType) {
		index = 1;
		JPanel knownCards = new JPanel(new GridBagLayout());
    	GridBagConstraints grid = new GridBagConstraints();
    	grid.fill = GridBagConstraints.HORIZONTAL;
    	knownCards.add(new JLabel(label), grid);
    	knownCards.setMinimumSize(new Dimension(100,190));
    	knownCards.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    	knownCards.setVisible(true);
    	
    	
    	if(CardType.NONE == theType) {
    		UICtrl.humanPlayer.getHand().forEach(card ->{
    			GridBagConstraints grid1 = new GridBagConstraints();
    			grid1.anchor = GridBagConstraints.LINE_START;
    			grid1.gridwidth = 10;
    			grid1.gridy = index++;
				JTextField tempTextField = new JTextField(card.getCardName());
				tempTextField.setBackground(UICtrl.humanPlayer.getColor());
				knownCards.add(tempTextField, grid1);

    		});
    	}
    	
    	humanSeenCards.values().stream().forEach(cardList->{
    		cardList.forEach(card->{
    			if(card.getCardtype() == theType) {
    				GridBagConstraints grid2 = new GridBagConstraints();
    				grid2.anchor = GridBagConstraints.LINE_START;
    				grid2.fill = GridBagConstraints.HORIZONTAL;
    				grid2.gridwidth = 10;
    				grid2.gridy = index++;
    				JTextArea tempTextField = new JTextArea(card.getCardName());
    				tempTextField.setBackground(UICtrl.humanPlayer.getColor());
    				tempTextField.setMinimumSize(new Dimension(100,10));
    				knownCards.add(tempTextField, grid2);
    			}

    		});
    	});
    	
		UIWatchList.addListDataListener(new ListDataListener() {
		@Override
		public void contentsChanged(ListDataEvent e) {
			
		}
		
		@Override
		public void intervalAdded(ListDataEvent e) {
			contentsChanged(e);}
		
		@Override
		public void intervalRemoved(ListDataEvent e) {
			contentsChanged(e);}
	});
		
//		knownCards1.add(sList("Cards in Hand", CardType.NONE));    	
//    	knownCards1.add(sList("Seen Person Cards", CardType.PERSON));    	
//    	knownCards1.add(sList("Seen Weapon Cards", CardType.WEAPON));
//    	knownCards1.add(sList("Seen Room Cards", CardType.ROOM));

    	return knownCards;

    	
	}	 
	//End Populate the lists
	
	public static void createAndShow() {
		JFrame mFrame = new JFrame();
		mFrame.setPreferredSize(new Dimension(200,600));
		mFrame.setMinimumSize(new Dimension(200,600));
		addAllElements(mFrame);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setVisible(true);
		
	}
	
	private static void populateSeenList() {
	   	ArrayList<Card> checkList = new ArrayList<Card>();
    	humanSeenCards.values().forEach(cardList->{
    		cardList.forEach(card->{
    			checkList.add(card);
    		});
    	});
    	UIWatchList.addAll(checkList);
	}
	
	
	
}
