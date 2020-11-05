/* gameSetupTests.java
 * Purpose: 
 * 			
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein 
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

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
	@SuppressWarnings("unlikely-arg-type")
	@Test
	void checkPlayers() {
		List<Player> testPlayerListSize = board.getPlayers();
		assertEquals(7, testPlayerListSize.size());
		
		//Now we'll test for duplicate players.
		Set<Player> setTestPlayers = new HashSet<Player>(testPlayerListSize);
		if (testPlayerListSize.size() < setTestPlayers.size()) { assert false; }
	}
	
	
	/*check deck size, and ensure no duplicates
	 * 
	 */
	@Test
	void checkDeck() {
		
		//First we check the deck size to ensure it is correct
		List<Card> testDeck1 = new Vector<Card>(board.getDeck());
		assertEquals(24, testDeck1.size());
		
		//Now we can check the deck for duplicates
		Set<Player> setTestPlayers = new HashSet<Player>(testDeck1);
		
		//Now we check the Solution is dealt
		List<Card> test1Deck
		
	}
	//Create Player class with human and computer child classes.   
	//Use people data to instantiate 6 players (1 human and 5 computer)
	
	
	//Create complete deck of cards (weapons, people and rooms)
	
	
	//Deal cards to the Answer and the players 
	//(all cards dealt, players have roughly same # of cards, no card dealt twice) 

}