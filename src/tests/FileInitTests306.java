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

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;
import clueGame.Room;
import experiment.CellStatus;
import experiment.TestBoard;
import experiment.TestBoardCellV2;

public class FileInitTests306 {
	
	
	public static final int LEGEND_SIZE = 8;
	public static final int NUM_ROWS = 30;
	public static final int NUM_COLUMNS = 22;

	private static Board board;

	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		
		board.setConfigFiles("ClueLayout306.csv", "ClueSetup306.txt");
	}

	//These are the rooms our team developed in ClueSetup
	//New Test #1
	@Test
	public void testRoomLabels() {
			
		assertEquals("Casion Arcade", board.getRoom('C').getName() );
		assertEquals("Giant Dipper", board.getRoom('D').getName() );
		assertEquals("Beach", board.getRoom('B').getName() );
		assertEquals("Parking Lot", board.getRoom('P').getName() );
		assertEquals("Neptunre's Kingdom", board.getRoom('F').getName() );
		assertEquals("Logger's Revenge", board.getRoom('L').getName() );
		assertEquals("Ticket Stand", board.getRoom('T').getName() );
		assertEquals("Haunted Castle", board.getRoom('H').getName() );
		assertEquals("Boardwalk", board.getRoom('W').getName() );
		assertEquals("Broken Ride", board.getRoom('K').getName() );
		assertEquals("Glider", board.getRoom('G').getName() );
		assertEquals("Bike Path", board.getRoom('I').getName() );
	}

	//Tests Board Size and Legend size
	//New Test #2
	@Test
	public void testBoardDimensions() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
		assertEquals(LEGEND_SIZE,(int) board.getRoomMap().size() );
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
		//BoardCell cell = board.getCell(0,0);
		//Set<BoardCell> testList = cell.getAdjList();
		
		//**//replace to below with above line.
		Set<BoardCell> testList = new HashSet<BoardCell>();
		//**//remove to pass
		
		Assert.assertTrue(testList.contains(board.getCell(1,0)));
		Assert.assertTrue(testList.contains(board.getCell(0,1)));
		Assert.assertEquals(2, testList.size());
	}

	//Old Experiment Test#2
	//Basic Movement with a dice roll of 4, all cells are walkable and unoccupied.
	@Test
	void creatTestBoard() {
	board.calcTargets(board.getCell(0, 1), 4);
	Set<BoardCell> testTargets = board.getTargets();
	
	//Old Experiment Test#3
	//remove next line to pass test
	/**/ testTargets.removeAll(board.getTargets());
	
	Assert.assertTrue(testTargets.contains(board.getCell(3,0)));
	Assert.assertTrue(testTargets.contains(board.getCell(2,3)));
	Assert.assertTrue(testTargets.contains(board.getCell(1,2)));
	Assert.assertTrue(testTargets.contains(board.getCell(3,2)));
	Assert.assertTrue(testTargets.contains(board.getCell(0,3)));
	Assert.assertTrue(testTargets.contains(board.getCell(1,0)));
	}

	//Old Experiment Test#4
	//Target Tests adjacency list is the correct size and has exactly the right elements.
	@Test
	public void testTargetsNormal() {
		BoardCell cell = board.getCell(0,0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets =  board.getTargets();
				
		//remove next line to pass test
		/**/ targets.removeAll(board.getTargets());
				
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3,0)));
		Assert.assertTrue(targets.contains(board.getCell(2,1)));
		Assert.assertTrue(targets.contains(board.getCell(0,1)));
		Assert.assertTrue(targets.contains(board.getCell(1,2)));
		Assert.assertTrue(targets.contains(board.getCell(0,3)));
		Assert.assertTrue(targets.contains(board.getCell(1,0)));
	}
	
	//Old Experiment Test#5
	//targetsMixed test for a complex situation where there may be a cell that 
	//represents a wall and another that is occupied by an opponent
	@Test
	public void testTargetsMixed() {
		board.getCell(0,2).adjustCellStatus(clueGame.CellStatus.OCCUPIED);
		board.getCell(1,2).adjustCellStatus(clueGame.CellStatus.VOID);
		BoardCell cell= board.getCell(0,3);
		board.calcTargets(cell,3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0,0)));
		Assert.assertTrue(targets.contains(board.getCell(1,1)));
		Assert.assertTrue(targets.contains(board.getCell(2,2)));
		Assert.assertTrue(targets.contains(board.getCell(3,3)));
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
		//remove iterator to pass test
		targets.add(board.getCell(0, 3));
		//remove above line
		Assert.assertEquals(0, targets.size());
	}
	
	
	
	
	
	
	
	
	//Below are examples given from the provided FileInitTests306 file
	
	
	


	@Test
	public void FourDoorDirections() {
		BoardCell cell = board.getCell(8, 7);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.LEFT, cell.getDoorDirection());
		cell = board.getCell(7, 12);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.UP, cell.getDoorDirection());
		cell = board.getCell(4, 8);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.RIGHT, cell.getDoorDirection());
		cell = board.getCell(16, 9);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.DOWN, cell.getDoorDirection());
		// Test that walkways are not doors
		cell = board.getCell(12, 14);
		assertFalse(cell.isDoorway());
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
		Assert.assertEquals(17, numDoors);
	}

	// Test a few room cells to ensure the room initial is correct.
	@Test
	public void testRooms() {
		// just test a standard room location
		BoardCell cell = board.getCell( 23, 23);
		Room room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Kitchen" ) ;
		assertFalse( cell.isLabel() );
		assertFalse( cell.isRoomCenter() ) ;
		assertFalse( cell.isDoorway()) ;

		// this is a label cell to test
		cell = board.getCell(2, 19);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Lounge" ) ;
		assertTrue( cell.isLabel() );
		assertTrue( room.getLabelCell() == cell );
		
		// this is a room center cell to test
		cell = board.getCell(20, 11);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Ballroom" ) ;
		assertTrue( cell.isRoomCenter() );
		assertTrue( room.getCenterCell() == cell );
		
		// this is a secret passage test
		cell = board.getCell(3, 0);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Study" ) ;
		assertTrue( cell.getSecretPassage() == 'K' );
		
		// test a walkway
		cell = board.getCell(5, 0);
		room = board.getRoom( cell ) ;
		// Note for our purposes, walkways and closets are rooms
		assertTrue( room != null );
		assertEquals( room.getName(), "Walkway" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		
		// test a closet
		cell = board.getCell(24, 18);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Unused" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		
	}

}
