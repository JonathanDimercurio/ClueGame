//package tests;
//
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.Vector;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import clueGame.*;
//
//public class ComputerAlTest {
//	static Board board;
//	
//	//We'll need these for all the following tests.
//	@BeforeAll
//	public static void setUp() {
//		// Board is singleton, get the only instance
//		board = Board.getInstance();
//		// set the file names to use my config files
//		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
//		// Initialize will load config files 
//		board.initialize();
//	}
//	
//	//
//	@Test
//	public void testSuggestedRoomMatchesCurrentLocartion() {
//		List<Player> testingPlayerList = new Vector<Player>();
//		List<Card> testSuggestionList	= new Vector<Card>();
//
//		
//		
//		
//		//Move all computer players into rooms, and test
//		for (ComputerPlayer movePlayer: testingPlayerList) {
//			while(!movePlayer.getCurrentCell().ifRoomCenter()) {
//				movePlayer.move();
//			}
//			assertTrue(movePlayer.getCurrentCell().isRoomCenter());
//		}
//		
//		//Check if multiple weaons, rooms and people are in the unseen,
//		//Then they are selected randomly
//		List<Card> testReplyList11 		= new Vector<Card>();
//		List<Card> testReplyList12 		= new Vector<Card>();
//		List<Card> testReplyList13 		= new Vector<Card>();
//		Set<Card>	checkSet			= new HashSet<Card>();
//		ComputerPlayer testPlayer1 = testingPlayerList.get(5);
//		testPlayer1.
//		testReplyList11.addAll(testPlayer1.guessPossibleSolutionGetter());
//		testPlayer1.makeSuggestion();
//		testReplyList12.addAll(testPlayer1.guessPossibleSolutionGetter());
//		testPlayer1.makeSuggestion();
//		testReplyList13.addAll(testPlayer1.guessPossibleSolutionGetter());
//		checkSet.addAll(testReplyList11);
//		checkSet.addAll(testReplyList12);
//		checkSet.addAll(testReplyList13);
//		
//		//Remove all cards from all  players hands to ensure that
//		//The room the accusing player is in will not be refuted
//		
//		for(ComputerPlayer removeMyCards: testingPlayerList) {
//			removeMyCards.emptyHand();
//			assertTrue(removeMyCards.getHand().size() == 0);
//		}
//		
//		//Knowing the hand all hand sizes are 0, therefore no given cards
//		//Soo no players can refute any suggestions. Now we check if the Room
//		//Of the computer player matches the suggested room.
//		List<Card> testReplyList2 		= new Vector<Card>();
//		ComputerPlayer testPlayer2 = testingPlayerList.get(0);
//		testPlayer2.makeSuggestion();
//		testReplyList2.addAll(testPlayer2.guessPossibleSolutionGetter());
//		assertTrue(testPlayer2.getCurrentCell().getMyRoomType().getRoomName().contains(testPlayer2.guessPossibleSolutionGetter().get(1).getCardName()));
//		
//		//Adding 1 weapon card, and 1 person card to unseen list, removing all others.
////		testPlayer1.
//	}
//	
//	@Test
//	public void testOnlyOneCardUnseen() {
//	
//	}
//}	
//		
