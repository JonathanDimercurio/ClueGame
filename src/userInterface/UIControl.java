package userInterface;

import java.util.List;
import java.util.Vector;

import clueGame.*;

public class UIControl {

	public static Board board = Board.getInstance();
	public Deck mainDeck;
	public Deck playerDeck;
	public List<Player> playerList = new Vector<>();
	
	public UIControl() {
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		 
		board.initialize();
		
		this.mainDeck = new Deck(ClueFileIO.getFormattedSetupFile());
		this.playerDeck = new Deck(DeckActions.createSeperateTypeDecks(mainDeck, CardType.PERSON).getDeck());
		
		
		GlossaryActions.createGlossaryFromDeck(this.mainDeck);
		
		playerList.add(new HumanPlayer(playerDeck.getDeck().get(0)));
		playerDeck.getDeck().remove(0);
		for (Card playerCards: this.playerDeck.getDeck()) {
			playerList.add(new ComputerPlayer(playerCards));
		}
	}
	
	public Player currentPlayer(int indexer) {
		return this.playerList.get(indexer % this.playerList.size());
	}
}
