/* gameSetupTests.java
 * Purpose: 
 * 			
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein 
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.*;

public class gameSetupTests {
	static Board board;
	
	//We'll need these for all the following tests.
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}
	
	/*load people and weapons from cluesetup.txt
	* During board.initialize(), all players, weapons and rooms are loaded.
	* Here we will test that. We also test to ensure that no duplicate players
	* Are added to the list of players in the game.
	*/
	@Test
	void checkPlayers() {
		Deck testDeck1 = new Deck(ClueFileIO.getFormattedSetupFile());
		Deck testDeck2 = new Deck(ClueFileIO.getFormattedSetupFile());
		GlossaryActions.createGlossaryFromDeck(testDeck1);
		testDeck1 = DeckActions.createSeperateTypeDecks(testDeck1, CardType.PERSON);
		List<ComputerPlayer> testingPlayerList = new Vector<>();
		for (Card playerCards: testDeck1.getDeck()) {
			testingPlayerList.add(new ComputerPlayer(playerCards));
		}
		
		
		assertEquals(7, testingPlayerList.size());
		
		//Now we'll test for duplicate players.
		Set<Player> setTest2Players = new HashSet<Player>(testingPlayerList);
		if (testingPlayerList.size() < setTest2Players.size()) { assert false; }
	}
	
	/*check deck size, and ensure no duplicates
	 *	Testing:
	 *		deck size
	 *		ensure no duplicates
	 *		check numbers of each type of card, weapons, rooms, people
	 */
	@Test
	void checkDeck() {
		Deck testDeck1 = new Deck(ClueFileIO.getFormattedSetupFile());
		//First we check the deck size to ensure it is correct
		assertEquals(24, testDeck1.getDeck().size());
		
		//Now we can check the deck for duplicates
		Set<Card> testDeck2 = new HashSet<Card>(testDeck1.getDeck());
		if (testDeck2.size() < testDeck1.getDeck().size()) { assert false; }
		
		Deck testDeck3 = DeckActions.createSeperateTypeDecks(testDeck1, CardType.WEAPON);
		Deck testDeck4 = DeckActions.createSeperateTypeDecks(testDeck1, CardType.ROOM);
		Deck testDeck5 = DeckActions.createSeperateTypeDecks(testDeck1, CardType.PERSON);
		
		//Checking the number of weapon cards
		assertEquals(7, testDeck3.getDeck().size());
		
		//Checking the number of room cards
		assertEquals(10, testDeck4.getDeck().size());
		
		//Checking the number of people cards
		assertEquals(7, testDeck5.getDeck().size());

	}
		
	//Ensure each player is dealt a hand
	@Test
	void checkPlayerHands() {
		Deck testDeck1 = new Deck(ClueFileIO.getFormattedSetupFile());
		Deck testDeck2 = new Deck(ClueFileIO.getFormattedSetupFile());
		GlossaryActions.createGlossaryFromDeck(testDeck1);
		testDeck1 = DeckActions.createSeperateTypeDecks(testDeck1, CardType.PERSON);
		List<ComputerPlayer> testingPlayerList = new Vector<>();
		for (Card playerCards: testDeck1.getDeck()) {
			testingPlayerList.add(new ComputerPlayer(playerCards));
		}
		
		DeckActions.dealDeck(testDeck2, testingPlayerList.stream().collect(Collectors.toList()));

		//Check if each player has a hand dealt 
		//and asserting the size is the same
		for(Player eachPlayer: testingPlayerList) {
			if (eachPlayer.getHand() != null) { assert true; } 
			else { assert false; }
		}
	}
	
	//Ensure that no duplicates were dealt
	@Test
	void checkDuplicatesCardsInHand() {
		Deck testDeck1 = new Deck(ClueFileIO.getFormattedSetupFile());
		Deck testDeck2 = new Deck(ClueFileIO.getFormattedSetupFile());
		GlossaryActions.createGlossaryFromDeck(testDeck1);
		testDeck1 = DeckActions.createSeperateTypeDecks(testDeck1, CardType.PERSON);
		List<ComputerPlayer> testingPlayerList = new Vector<>();
		for (Card playerCards: testDeck1.getDeck()) {
			testingPlayerList.add(new ComputerPlayer(playerCards));
		}
		
		DeckActions.dealDeck(testDeck2, testingPlayerList.stream().collect(Collectors.toList()));

		Set<Card> testingList1 = new HashSet<Card>();
		List<Card> testingList2 = new ArrayList<Card>();
		for (Player playerHand: testingPlayerList) {
			testingList1.addAll(playerHand.getHand());
			testingList2.addAll(playerHand.getHand());
		}
		assertEquals(testingList1.size(), testingList2.size());
	}

}