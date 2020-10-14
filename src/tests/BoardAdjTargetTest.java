package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {

	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}

	@Test //location with only adjacent walkways
	public void adjWalkwayTest() {
		Set<BoardCell> testList = board.getAdjList(13, 7);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(13, 6)));
		assertTrue(testList.contains(board.getCell(14, 7)));
		assertTrue(testList.contains(board.getCell(12, 7)));
		assertTrue(testList.contains(board.getCell(13, 8)));
	}
	
	@Test //Testing non-center room cells for empty adjacency
	public void roomNoAdjTest() {
		Set<BoardCell> testList = board.getAdjList(3, 1);
		assertEquals(0, testList.size());
		testList = board.getAdjList(8, 0);
		assertEquals(0, testList.size());
		testList = board.getAdjList(0, 8);
		assertEquals(0, testList.size());
		testList = board.getAdjList(4, 14);
		assertEquals(0, testList.size());
	}
	
	@Test //Testing unused cells at edge of board
	public void edgeCellsNoAdjTest() {
		Set<BoardCell> testList = board.getAdjList(3, 1);
		assertEquals(0, testList.size());
		testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		testList = board.getAdjList(0, 20);
		assertEquals(0, testList.size());
		testList = board.getAdjList(0, 14);
		assertEquals(0, testList.size());
	}
	
	@Test //Locations beside a room, not a doorway
	public void cellsNextToRoomsNoDoorsAdjTest() {
		Set<BoardCell> testList = board.getAdjList(7, 4);
		assertEquals(2, testList.size());
		testList = board.getAdjList(1, 12);
		assertEquals(1, testList.size());
	}
	
	@Test //Locations beside a room AND are doorways
	public void doorwayTester() {
		//doorway to top left room
		Set<BoardCell> testList = board.getAdjList(5, 4);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(6, 4)));
		assertTrue(testList.contains(board.getCell(5, 5)));
		
		//doorway to parking lot
		testList = board.getAdjList(4, 8);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(1, 9)));
		assertTrue(testList.contains(board.getCell(5, 8)));
		assertTrue(testList.contains(board.getCell(4, 7)));
		assertTrue(testList.contains(board.getCell(4, 9)));	
	}
	
	@Test //Testing Secret passages
	public void secretPassageTester() {
		Set<BoardCell> testList = board.getAdjList(3, 2);
		assertEquals(2, testList.size());
	}
	
	@Test //Leaving a room with a secret passage
	public void exitScretPassageRoom() {
		board.calcTargets(board.getCell(16,4), 4);
		Set<BoardCell> testTargets = board.getTargets();
		assertEquals(3, testTargets.size());
		assertTrue(testTargets.contains(board.getCell(20, 7)));
		assertTrue(testTargets.contains(board.getCell(16, 7)));
		assertTrue(testTargets.contains(board.getCell(16, 24)));	
	}
	
	//Basic Movement with a dice roll of 4, all cells are walkable and unoccupied.
	//Targets include rooms
	@Test
	void creatWalkwayWithRooms() {
	board.calcTargets(board.getCell(13,12), 4);
	Set<BoardCell> testTargets = board.getTargets();
	Assert.assertEquals(20, testTargets.size());
	Assert.assertTrue(testTargets.contains(board.getCell(13,14)));
	Assert.assertTrue(testTargets.contains(board.getCell(11,12)));
	Assert.assertTrue(testTargets.contains(board.getCell(11,10)));
	Assert.assertTrue(testTargets.contains(board.getCell(10,13)));
	Assert.assertTrue(testTargets.contains(board.getCell(16,13)));
	Assert.assertTrue(testTargets.contains(board.getCell(15,14)));
	Assert.assertTrue(testTargets.contains(board.getCell(14,11)));
	Assert.assertTrue(testTargets.contains(board.getCell(15,12)));
	Assert.assertTrue(testTargets.contains(board.getCell(14,9)));
	Assert.assertTrue(testTargets.contains(board.getCell(10,13)));
	}
	
	//targetsMixed test for a complex situation where there may be a cell that 
	//represents a wall and another that is occupied by an opponent
	@Test
	public void testBlockingMixed() {
		board.getCell(4, 6).setOccupied(true);
		board.getCell(6, 6).setOccupied(true);
		board.getCell(6, 5).setOccupied(true);
		board.calcTargets(board.getCell(5, 6),3);
		Set<BoardCell> targets = board.getTargets();
		board.getCell(4, 6).setOccupied(false);
		board.getCell(6, 6).setOccupied(false);
		board.getCell(6, 5).setOccupied(false);
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(4,8)));
		Assert.assertTrue(targets.contains(board.getCell(3,2)));
		Assert.assertTrue(targets.contains(board.getCell(6,4)));
		Assert.assertTrue(targets.contains(board.getCell(5,9)));
	}

	
	@Test //Occupied space and a brokenride obstecle
	public void testTarget() {
		board.getCell(1,1).adjustCellStatus(clueGame.CellStatus.OCCUPIED);
		board.getCell(0,1).adjustCellStatus(clueGame.CellStatus.VOID);
		board.getCell(2,0).adjustCellStatus(clueGame.CellStatus.BROKENRIDE);
		BoardCell cell= board.getCell(0,0);
		board.calcTargets(cell,2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
	}
}
