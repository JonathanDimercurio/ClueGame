package userInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.border.Border;

import clueGame.*;

public class UICtrl {

	public static Map<UILocNames, Border> borderLib = new HashMap<UILocNames, Border>();
	public static int pIndexer = 0;
	public static Board board = Board.getInstance();
	public static List<Player> playerList = new Vector<>();
	public static Deck mainDeck;
	public static Deck playerDeck;
	public static ButtonLib BLib = ButtonLib.getButtonLib();
	@SuppressWarnings("unused")
	public final static UICtrl TheGame = new UICtrl();

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame.createAndShowGUI();
            }
        });	
    }
    
    public static UICtrl getUI() {
    	return UICtrl.TheGame;
    }
    
	
	@SuppressWarnings("static-access")
	private UICtrl() {
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		 
		board.initialize();
		
		UICtrl.mainDeck = new Deck(ClueFileIO.getFormattedSetupFile());
		UICtrl.playerDeck = new Deck(DeckActions.createSeperateTypeDecks(mainDeck, CardType.PERSON).getDeck());
		
		
		GlossaryActions.createGlossaryFromDeck(UICtrl.mainDeck);
		
		playerList.add(new HumanPlayer(playerDeck.getDeck().get(0)));
		playerDeck.getDeck().remove(0);
		for (Card playerCards: UICtrl.playerDeck.getDeck()) {
			playerList.add(new ComputerPlayer(playerCards));
		}
	}	


	
}
