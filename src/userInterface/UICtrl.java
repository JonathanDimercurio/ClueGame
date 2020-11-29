package userInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.border.Border;
import clueGame.*;

public class UICtrl {

	@SuppressWarnings("exports")
	public static Map<UILocNames, Border> borderLib = new HashMap<UILocNames, Border>();
	public static int pIndexer = 0;
	public static Board board = Board.getInstance();
	public static List<Player> playerList = new Vector<>();
	public static Deck mainDeck;
	public static Deck playerDeck;
	public static ButtonLib BLib = ButtonLib.getButtonLib();
	public static HumanPlayer humanPlayer;
	private static UICtrl TheGame = new UICtrl();

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
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
		UICtrl.playerDeck = new Deck(DeckActions.createSeperateTypeDecks(mainDeck, CardType.PERSON).getDeck());
		GlossaryActions.createGlossaryFromDeck(UICtrl.mainDeck);
		constructPlayerList();
		DeckActions.dealDeck(new Deck(GlossaryActions.allKnownCardsSet()), playerList);
	}
	
	private void findHumanPlayer () {
    	UICtrl.playerList.stream().forEach(player ->{
			if(player.getPType() == 'H') {
				UICtrl.humanPlayer  =(HumanPlayer) player;
			}
		});
	}

	//Can implement player choice here
	private void constructPlayerList() {
		playerList.add(new HumanPlayer(playerDeck.getDeck().get(0)));
		findHumanPlayer();
		playerDeck.getDeck().remove(0);
		for (Card playerCards: UICtrl.playerDeck.getDeck()) {
			playerList.add(new ComputerPlayer(playerCards));
		}
		
	}


}

//TODO implement suggestion/accusation function
//TODO implement roll function
//TODO current player icon legend





