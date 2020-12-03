package userInterface;

import java.util.HashMap;
import java.util.Map;
import javax.swing.border.Border;
import clueGame.*;

public class UICtrl {

	@SuppressWarnings("exports")
	public static Map<UILocNames, Border> borderLib = 
			new HashMap<UILocNames, Border>();
	
	public static Board board = Board.getInstance();
	public static Deck mainDeck;
	public static Deck playerDeck;
	public static ButtonLib BLib = ButtonLib.getButtonLib();
	private static UICtrl TheGame = new UICtrl();
	
	
    public static void main(String[] args) {

        javax.swing.SwingUtilities
        	.invokeLater(new Runnable() {
            public void run() {

        		UICtrl.initUI();
        		MainFrame.createAndShowGUI();

            }
        });	
    }
	

	
    public static void initUI() {
    	if ( TheGame == null) {
    		TheGame = new UICtrl();
    	}
    }
    
	
	private UICtrl() {
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		 
		board.initialize();
		UICtrl.mainDeck = new Deck(ClueFileIO.getFormattedSetupFile());
		UICtrl.playerDeck = new Deck(DeckActions
				.createSeperateTypeDecks(mainDeck, CardType.PERSON).getDeck());
		
		GlossaryActions.createGlossaryFromDeck(UICtrl.mainDeck);
		UIPlayerControl.playerCommand = new UIPlayerControl(playerDeck);
		DeckActions.dealDeck(new Deck(GlossaryActions
				.allKnownCardsSet()), UIPlayerControl.playerList);
	}
}
