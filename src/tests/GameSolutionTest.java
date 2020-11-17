package tests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.*;

public class GameSolutionTest {
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

	@Test
	void solutionChecker() {
		
		//First lets get a deck
		Deck testDeck1 = new Deck(ClueFileIO.getFormattedSetupFile());
		
		//Next lets get a few cards
		Card testCard1 = new Card(testDeck1.getDeck().get(0));
		Card testCard2 = new Card(testDeck1.getDeck().get(11));
		Card testCard3 = new Card(testDeck1.getDeck().get(20));
		
		//Test assertTrue a correct accusation
		Solution.initSolution(testCard1, testCard2, testCard3); 
		assertTrue(PlayerActions.accusation(new Guess(testCard1, testCard2, testCard3)));
		
		//Incorrect solution cards
		Card incorrectPerson = testDeck1.getDeck().get(21);
		Card incorrectRoom	= testDeck1.getDeck().get(12);
		Card incorrectWeapon = testDeck1.getDeck().get(19);
		
		//Test an incorrect accusations
		assertFalse(PlayerActions.accusation(new Guess(incorrectPerson, testCard2, testCard3)));
		assertFalse(PlayerActions.accusation(new Guess(testCard1, incorrectRoom, testCard3)));
		assertFalse(PlayerActions.accusation(new Guess(testCard1, testCard2, incorrectWeapon)));
		
	}
	
//	@Test
	void insureDealingIsUpdatingSeenList() {
		
		Deck testDeck1 = new Deck(ClueFileIO.getFormattedSetupFile());
		Deck testDeck2 = new Deck(ClueFileIO.getFormattedSetupFile());
		GlossaryActions.createGlossaryFromDeck(testDeck1);
		testDeck1 = DeckActions.createSeperateTypeDecks(testDeck1, CardType.PERSON);
		List<ComputerPlayer> testingPlayerList = new Vector<>();
		for (Card playerCards: testDeck1.getDeck()) {
			testingPlayerList.add(new ComputerPlayer(playerCards));
		}
		ComputerPlayer testPlayer1 = testingPlayerList.get(0);
		ComputerPlayer testPlayer2 = testingPlayerList.get(3);
		ComputerPlayer testPlayer3 = testingPlayerList.get(5);
		//Generate a ComputerPlayer list for testing

		DeckActions.dealDeck(testDeck2, testingPlayerList.stream().collect(Collectors.toList()));

		
		assertTrue(testPlayer1.guessLogic.checkIfSeen(testPlayer1.getHand().get(1)));
		assertFalse(testPlayer1.guessLogic.checkIfSeen(testPlayer2.getHand().get(1)));
		assertFalse(testPlayer1.guessLogic.checkIfSeen(testPlayer2.getHand().get(2)));
		testPlayer1.updateHand(testPlayer2.getHand().get(1));
		assertTrue(testPlayer1.guessLogic.checkIfSeen(testPlayer2.getHand().get(1)));
		testPlayer1.guessLogic.addListToSeen(testPlayer3.getHand());
		assertTrue(testPlayer1.guessLogic.checkIfSeen(testPlayer3.getHand().get(1)));
		assertTrue(testPlayer1.guessLogic.checkIfSeen(testPlayer3.getHand().get(2)));
		
		}
			
	//Test a computer making a suggestion, and receiving a reply form another computer player
	//testPlayer1 is moved into a room before making a suggestion
	@Test
	void testingReplyBoolean() {
		Deck testDeck1 = new Deck(ClueFileIO.getFormattedSetupFile());
		Deck testDeck2 = new Deck(ClueFileIO.getFormattedSetupFile());
		GlossaryActions.createGlossaryFromDeck(testDeck1);
		testDeck1 = DeckActions.createSeperateTypeDecks(testDeck1, CardType.PERSON);
		List<ComputerPlayer> testingPlayerList = new Vector<>();

		testingPlayerList.add(new ComputerPlayer(testDeck1.getDeck().get(0)));
		testingPlayerList.add(new ComputerPlayer(testDeck1.getDeck().get(2)));
		
		
		DeckActions.dealDeck(testDeck2, testingPlayerList.stream().collect(Collectors.toList()));
		
		ComputerPlayer testPlayer1 = testingPlayerList.get(0);
		testPlayer1.moveMeToCell((Room.roomMap.get('N').getCenterCell()));
		
		Guess p1Guess = new Guess(testPlayer1.makeSuggestion());
		List<Card> replyList = new Vector<Card>();
		for(ComputerPlayer checkReply: testingPlayerList) {
			if(checkReply.checkForReply(p1Guess)) {
			replyList.add(new Card(checkReply.generateReply(p1Guess)));
		}}
		
		testPlayer1.resolveReplies(replyList);
		
		assertEquals(replyList.size(), 1);
		
	}
	
	@Test
	void disproveSuggestionTests() {
		//Generate a ComputerPlayer list for testing
		Deck testDeck1 = new Deck(ClueFileIO.getFormattedSetupFile());
		Deck testDeck2 = new Deck(ClueFileIO.getFormattedSetupFile());
		GlossaryActions.createGlossaryFromDeck(testDeck1);
		testDeck1 = DeckActions.createSeperateTypeDecks(testDeck1, CardType.PERSON);
		List<ComputerPlayer> testingPlayerList = new Vector<ComputerPlayer>();	
		
		
		ComputerPlayer testPlayer1 = new ComputerPlayer(testDeck1.getDeck().get(0));
		ComputerPlayer testPlayer2 = new ComputerPlayer(testDeck1.getDeck().get(1));
		ComputerPlayer testPlayer3 = new ComputerPlayer(testDeck1.getDeck().get(2));
		
		testingPlayerList.add(testPlayer1);
		testingPlayerList.add(testPlayer2);
		testingPlayerList.add(testPlayer3);
			
		DeckActions.dealDeck(testDeck2, testingPlayerList.stream().collect(Collectors.toList()));
		testPlayer1.moveMeToCell((Room.roomMap.get('N').getCenterCell()));
		
		//Call our testSuggestionMethod with these specific cards
		Guess p1Guess = new Guess(testPlayer1.makeSuggestion());
		
		//Each Player is returning 1 card from their hand as an answer
		List<Card> testReplyList = new Vector<Card>();
		for(ComputerPlayer checkReply: testingPlayerList) {
			if(checkReply.checkForReply(p1Guess)) {
				testReplyList.add(new Card(checkReply.generateReply(p1Guess)));
		}}
		
		assertEquals(testReplyList.size(), 1);
		}
	
}
