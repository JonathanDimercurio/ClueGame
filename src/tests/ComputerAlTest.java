package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import PlayerFiles.ComputerPlayer;
import PlayerFiles.Guess;
import clueGame.*;

public class ComputerAlTest {
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
	
	//
	@Test
	public void testSuggestedRoomMatchesCurrentLocartion() {
		Deck testDeck1 = new Deck(ClueFileIO.getFormattedSetupFile());
		Deck testDeck2 = new Deck(ClueFileIO.getFormattedSetupFile());
		GlossaryActions.createGlossaryFromDeck(testDeck1);
		testDeck1 = DeckActions
				.createSeperateTypeDecks(testDeck1, CardType.PERSON);
		List<ComputerPlayer> testingPlayerList = 
				new Vector<ComputerPlayer>();	
		
		
		ComputerPlayer testPlayer1 = 
				new ComputerPlayer(testDeck1.getDeck().get(0));
		ComputerPlayer testPlayer2 = 
				new ComputerPlayer(testDeck1.getDeck().get(1));
		ComputerPlayer testPlayer3 = 
				new ComputerPlayer(testDeck1.getDeck().get(2));
		
		testingPlayerList.add(testPlayer1);
		testingPlayerList.add(testPlayer2);
		testingPlayerList.add(testPlayer3);
			
		DeckActions.dealDeck(testDeck2, testingPlayerList.stream()
				.collect(Collectors.toList()));
		testPlayer1.moveMeToCell((Room.roomMap.get('N').getCenterCell()));
		
		//Setup a Guess by testPlayer1
		Guess p1Guess1 = new Guess(testPlayer1.makeSuggestion());
		//Ensure the guessed Room is the room testPlayer1 is in
		for(Card checkThisCard: p1Guess1.getGuess()) {
			if(checkThisCard.getCardtype().equals(CardType.ROOM)) {
				assertTrue(checkThisCard.getCardName()
						.contains(testPlayer1.getCurrentCell()
								.getMyRoomType().getRoomName()));
			}
			
		}
		
		//Move testPLayer1 to a different room, test again
		testPlayer1.moveMeToCell((Room.roomMap.get('P').getCenterCell()));
		Guess p1Guess2 = new Guess(testPlayer1.makeSuggestion());
		//Ensure the guessed Room is the room testPlayer1 is in
		for(Card checkThisCard: p1Guess2.getGuess()) {
			if(checkThisCard.getCardtype().equals(CardType.ROOM)) {
				assertTrue(checkThisCard.getCardName()
						.contains(testPlayer1.getCurrentCell()
								.getMyRoomType().getRoomName()));
			}
			
		}
		
		//Move testPlayer1 and ensure the test fails
		testPlayer1.moveMeToCell((Room.roomMap.get('N').getCenterCell()));
		for(Card checkThisCard: p1Guess2.getGuess()) {
			if(checkThisCard.getCardtype().equals(CardType.ROOM)) {
				assertFalse(checkThisCard.getCardName()
						.contains(testPlayer1.getCurrentCell()
								.getMyRoomType().getRoomName()));
			}
			
		}
	}
	
}	
		
