package userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clueGame.CardType;

public class UISeenCards {
	private static int index = 0;
	
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	UICtrl.initUI();
            	createAndShow();
            }
        });
    }

    private static void addAllElements(Container knownF) {
    	
    	JPanel knownCards1 = new JPanel();
    	knownCards1.setPreferredSize(new Dimension(100,600));
    	knownCards1.setVisible(true);
    	knownCards1.setLayout(new BoxLayout(knownCards1, BoxLayout.PAGE_AXIS));
    	knownCards1.add(sList("Cards in Hand", CardType.NONE));
    	knownCards1.add(sList("Seen Person Cards", CardType.PERSON));    	
    	knownCards1.add(sList("Seen Weapon Cards", CardType.WEAPON));
    	knownCards1.add(sList("Seen Room Cards", CardType.ROOM));
    	knownF.add(knownCards1);
    }
    					
	private static JPanel sList(String label, CardType theType) {
		index = 2;
		JPanel knownCards = new JPanel(new GridBagLayout());
    	GridBagConstraints grid = new GridBagConstraints();
    	grid.fill = GridBagConstraints.VERTICAL;
    	grid.gridy = 1;
    	knownCards.add(new JLabel(label), grid);
    	knownCards.setPreferredSize(new Dimension(100,180));
    	knownCards.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    	knownCards.setVisible(true);
    	
    	
    	if(CardType.NONE == theType) {
    		UICtrl.humanPlayer.getHand().forEach(card ->{
    			grid.gridy = index++;
				JTextField tempTextField = new JTextField(card.getCardName());
				knownCards.add(tempTextField, grid);

    		});
    	}
    	
    	UICtrl.humanPlayer.seenCards.values().stream().forEach(card->{
    		if(card.getCardtype() == theType) {
    			grid.gridy = index++;
				JTextField tempTextField = new JTextField(card.getCardName());
				knownCards.add(tempTextField, grid);
    		}
    	});
    	
    	
    	return knownCards;

	}
	 
	public static void createAndShow() {
		JFrame mFrame = new JFrame();
		mFrame.setPreferredSize(new Dimension(200,600));
		mFrame.setMinimumSize(new Dimension(200,600));
		addAllElements(mFrame);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setVisible(true);
		
	}
	
}
