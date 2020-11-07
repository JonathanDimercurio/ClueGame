package tests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Vector;

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
		List<Card> testDeck1 = board.getDeck();
		
		//Next lets get a few cards
		Card testCard1 = new Card(testDeck1.get(0));
		Card testCard2 = new Card(testDeck1.get(11));
		Card testCard3 = new Card(testDeck1.get(20));
		
		//Test assertTrue a correct accusation
		Solution testGoal = new Solution(testCard1, testCard2, testCard3);
		assertTrue(testGoal.accusation(testCard1, testCard2, testCard3));
		
		//Incorrect solution cards
		Card incorrectPerson = testDeck1.get(21);
		Card incorrectRoom	= testDeck1.get(12);
		Card incorrectWeapon = testDeck1.get(19);
		
		//Test an incorrect accusations
		assertFalse(testGoal.accusation(incorrectPerson, testCard2, testCard3));
		assertFalse(testGoal.accusation(testCard1, incorrectRoom, testCard3));
		assertFalse(testGoal.accusation(testCard1, testCard2, incorrectWeapon));
		
	}
	
	
	@Test
	void handleSuggestionTests() {
		//Generate a ComputerPlayer list for testing
		List<ComputerPlayer> testingPlayerList = new Vector<ComputerPlayer>();
		testingPlayerList.addAll(ComputerPlayer.computerPlayerList);
		
		//Move all computer players into rooms, and test
		for (ComputerPlayer movePlayer: testingPlayerList) {
			while(!movePlayer.getCurrentCell().ifRoomCenter()) {
				movePlayer.move();
			}
			assertTrue(movePlayer.getCurrentCell().isRoomCenter());
		}
		
		//
		//Testing if the room the computerPlayer has moved to is
		//a room they have visited before.
		int i = 0;
		for (ComputerPlayer testIfMyRoomHasBeenVisited: testingPlayerList) {
			for(Card compareToThisCard:testIfMyRoomHasBeenVisited.guessPossibleSolutionGetter()) {
				if(testIfMyRoomHasBeenVisited.getCurrentCell().getMyRoomType().getRoomName().contains(compareToThisCard.getCardName())) {
					i += 1;
				}
			}
		}
		assertEquals(testingPlayerList.size(), i);
		
		//
		//Now that all the ComputerPlayers are in valid rooms,
		//We will ensure the suggestion methods are function properly
		
		//First we'll pass a Suggestion no one can disprove
		List<Card> testReplyList 		= new Vector<Card>();
		List<Card> testSuggestionList	= new Vector<Card>();
		
		//First tested Player
		testSuggestionList.addAll(board.getTheSolution());
		if (testingPlayerList.get(0).testSuggestion(testSuggestionList) != null) {
			testReplyList.addAll(testingPlayerList.get(0).testSuggestion(testSuggestionList));
		}
		assertTrue(testReplyList.size() == 0);
		
		//Testing another player
		testReplyList = testingPlayerList.get(3).testSuggestion(testSuggestionList);
		assertTrue(testReplyList.size() == 0);
		
		
		//
		//Testing a suggestion only the accuser can refute
		testSuggestionList.removeAll(testSuggestionList);
		testSuggestionList = testingPlayerList.get(2).getHand();
		if(testingPlayerList.get(2).testSuggestion(testSuggestionList) != null) {
			testReplyList.addAll(testingPlayerList.get(2).testSuggestion(testSuggestionList));
		}
		assertTrue(testReplyList.size() == 0);
		//All other requirements are tested within the bounds of the above tests.
	}
	
	@Test
	void disproveSuggestionTests() {
		//Generate a ComputerPlayer list for testing
		List<ComputerPlayer> testingPlayerList = new Vector<ComputerPlayer>();
		testingPlayerList.addAll(ComputerPlayer.computerPlayerList);
	
		//Acquire some Lists for experimental purpose
		List<Card> testReplyList 		= new Vector<Card>();
		List<Card> testSuggestionList	= new Vector<Card>();
	
		//Get cards we know are in player2, player3 and player4's hand
		testSuggestionList.add(testingPlayerList.get(1).getHand().get(0));
		testSuggestionList.add(testingPlayerList.get(2).getHand().get(1));
		testSuggestionList.add(testingPlayerList.get(3).getHand().get(2));
		
		//Call our testSuggestionMethod with these specific cards
		if((testingPlayerList.get(0).testSuggestion(testSuggestionList)) != null) {
			testReplyList.addAll(testingPlayerList.get(0).testSuggestion(testSuggestionList));
		}
		//Each Player is returning 1 card from their hand as an answer
		assertEquals(testReplyList.size(), 3);
		assertTrue(testReplyList.contains(testingPlayerList.get(1).getHand().get(0)));
		assertTrue(testReplyList.contains(testingPlayerList.get(2).getHand().get(1)));
		assertTrue(testReplyList.contains(testingPlayerList.get(3).getHand().get(2)));
		
		//Ensure, without a doubt that a player will only return 1 card as a reply
		List<Card> testReplyList2 = new Vector<Card>();
		List<Card> testSolList2 = new Vector<Card>();
		testSolList2.addAll(testingPlayerList.get(3).getHand());
		testReplyList2.addAll(testingPlayerList.get(0).testSuggestion(testSolList2));
		assertEquals(testReplyList2.size(), 1);
		}
	
	
}
