package UIResources;

import java.util.HashMap;
import java.util.Map;

import javax.swing.border.Border;

import PlayerFiles.Player;
import UserInterface.MainFrame;
import clueGame.*;

public class UICtrl {

	public static Map<UILocNames, Border> borderLib = 
			new HashMap<UILocNames, Border>();
	
	public static Board board = Board.getInstance();
	public static Deck mainDeck;
	public static Deck playerDeck;
	private static UICtrl TheGame = new UICtrl();
	private static MainFrame main;
	
	
    public static void main(String[] args) {

        javax.swing.SwingUtilities
        	.invokeLater(new Runnable() {
            public void run() {
            
            	System.out.println("- - - - - - - - - - - - - - - - ");
            	System.out.println(Solution.getSolution().toString());
            	System.out.println("- - - - - - - - - - - - - - - - ");
            	for(Player player: UIPlayerControl.playerList) {
            		System.out.println(player.getHand().toString());
            	}
            	
        		UICtrl.initUI();
        		main = new MainFrame();
            }
        });
    }

    public static void initUI() {
    	if ( TheGame == null) {
    		TheGame = new UICtrl();
    	}
    }
    
    public static void revalidate() {
    	main.revalidate();
    	main.repaint();
    }
    
    
	private UICtrl() {
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		 
		board.initialize();
		UICtrl.mainDeck = new Deck(ClueFileIO.getFormattedSetupFile());
		UICtrl.playerDeck = new Deck(DeckActions
				.createSeperateTypeDecks(mainDeck, CardType.PERSON)
				.getDeck());
		
		GlossaryActions.createGlossaryFromDeck(UICtrl.mainDeck);
		UIPlayerControl.playerCommand = 
				new UIPlayerControl(playerDeck);
		
		
		
		DeckActions.dealDeck(new Deck(GlossaryActions
				.allKnownCardsSet()), UIPlayerControl.playerList);
	}
}