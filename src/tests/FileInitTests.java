/**
 * FileInitTests306
 * 
 * Purpose: Our version of the FileInitTest
 * 			works with Skeleton version with TestBoardCell.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */
package tests;
import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;

public class FileInitTests {
	
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 27;

	private static Board board;

	@BeforeAll
	public static void setUp() throws BadConfigFormatException{
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
	}
	

	//These are the rooms our team developed in ClueSetup
	//New Test #1
	@Test
	public void testRoomLabels() {
		assertEquals("Casino Arcade", board.getRoom('C').getName() );
		assertEquals("Giant Dipper", board.getRoom('D').getName() );
		assertEquals("Beach", board.getRoom('B').getName() );
		assertEquals("Parking Lot", board.getRoom('P').getName() );
		assertEquals("Neptune's Kingdom", board.getRoom('N').getName() );
		assertEquals("Logger's Revenge", board.getRoom('L').getName() );
		assertEquals("Ticket Stand", board.getRoom('T').getName() );
		assertEquals("Haunted Castle", board.getRoom('H').getName() );
		assertEquals("Boardwalk", board.getRoom('W').getName() );
		assertEquals("Broken Ride", board.getRoom('Q').getName() );
	}

	//Tests Board Size and Legend size
	//New Test #2
	@Test
	public void testBoardDimensions() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}

	//Testing the total number of cells generated
	//New Test #3
	@Test
	public void testTotalCellCount() {
		assertEquals((NUM_COLUMNS*NUM_ROWS), BoardCell.gameBoardData.size());
	}
	
	//Old Experiment Test#1
	@Test
	void testAdjacency() {
		BoardCell cell = board.getCell(14,8);
		Set<BoardCell> testList1 = cell.getAdjList();
		Assert.assertTrue(testList1.contains(board.getCell(13,8)));
		Assert.assertTrue(testList1.contains(board.getCell(15,8)));
		Assert.assertTrue(testList1.contains(board.getCell(14,7)));
		Assert.assertTrue(testList1.contains(board.getCell(14,9)));
		Assert.assertEquals(4, testList1.size());
		
		cell = board.getCell(18,6);
		Set<BoardCell> testList2 = cell.getAdjList();
		Assert.assertTrue(testList2.contains(board.getCell(16,4)));
		Assert.assertTrue(testList2.contains(board.getCell(18,7)));
		Assert.assertEquals(2, testList2.size());
		
		
	}

	//Old Experiment Test#2
	//Basic Movement with a dice roll of 4, all cells are walkable and unoccupied.
	@Test
	void creatTestBoard() {
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

	//Old Experiment Test#4
	//Target Tests adjacency list is the correct size and has exactly the right elements.
	@Test
	public void testTargetsNormal() {
		board.calcTargets(board.getCell(1,2), 6);
		Set<BoardCell> testTargets = board.getTargets();
		Assert.assertEquals(1, testTargets.size());
		Assert.assertTrue(testTargets.contains(board.getCell(3,6)));
	}
	
	//Old Experiment Test#5
	//targetsMixed test for a complex situation where there may be a cell that 
	//represents a wall and another that is occupied by an opponent
	@Test
	public void testTargetsMixedRoomsAndWalkways() {
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

	//Old Experiment Test #6
	//targetsWalkable test for a complex situation where there may be a cell that 
	//represents a wall, or VOID and another that is occupied and another is a broken-ride.
	@Test
	public void testTargetWalkable() {
		board.getCell(1,1).adjustCellStatus(clueGame.CellStatus.OCCUPIED);
		board.getCell(0,1).adjustCellStatus(clueGame.CellStatus.VOID);
		board.getCell(2,0).adjustCellStatus(clueGame.CellStatus.BROKENRIDE);
		BoardCell cell= board.getCell(0,0);
		board.calcTargets(cell,2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
	}
	
	// Test that we have the correct number of doors
	@Test
	public void testNumberOfDoorways() {
		int numDoors = 0;
		for (int row = 0; row < board.getNumRows(); row++)
			for (int col = 0; col < board.getNumColumns(); col++) {
				BoardCell cell = board.getCell(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(14, numDoors);
	}
}
