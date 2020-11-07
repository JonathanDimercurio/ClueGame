package tests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	void disprovingSuggestionTests() {
		
		
		
		//Generate a ComputerPlayer list for testing
		List<ComputerPlayer> testingPlayerList = new Vector<ComputerPlayer>();
		testingPlayerList.addAll(ComputerPlayer.computerPlayerList);
		
		//Move all computer players into rooms
		for (ComputerPlayer movePlayer: testingPlayerList) {
			while(!movePlayer.getCurrentCell().ifRoomCenter()) {
				movePlayer.move();
			}
		}
		
		//Testing if all computer players have moved to a room
		for (ComputerPlayer testMyCell: testingPlayerList) {
			assertTrue(testMyCell.getCurrentCell().isRoomCenter());
		}
		
		//Testing if the room the computerPlayer has moved to is
		//a room they have visited before
		int i = 0;
		for (ComputerPlayer testIfMyRoomHasBeenVisited: testingPlayerList) {
			for(Card compareToThisCard:testIfMyRoomHasBeenVisited.guessPossibleSolutionGetter()) {
				if(testIfMyRoomHasBeenVisited.getCurrentCell().getMyRoomType().getRoomName().contains(compareToThisCard.getCardName())) {
					i += 1;
				}
			}
		}
		assertEquals(testingPlayerList.size(), i);
		
		testingPlayerList.get(0).makeSuggestion();
		
		assert true;
	}
	
	
}
