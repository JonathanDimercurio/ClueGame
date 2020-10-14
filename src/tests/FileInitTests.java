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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;
import clueGame.Room;
import experiment.CellStatus;
import experiment.TestBoard;
import experiment.TestBoardCellV2;

public class FileInitTests {
	
	
	public static final int LEGEND_SIZE = 13;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 27;

	private static Board board;

	@BeforeAll
	public static void setUp() throws BadConfigFormatException{
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
	}
	
	//Testing the output for SetupConfigFile
	@Test
	public void testLineTheory() throws BadConfigFormatException, IOException {
		File layoutInput = new File(board.getSetupConfigFile());
		BufferedReader scanIt = new BufferedReader(new FileReader(layoutInput));
		try {
			while( scanIt.readLine() != null) {
				Stream<String> lines = scanIt.lines();  
				lines.forEach(System.out::println);
				
			} 
		} finally {
				scanIt.close();
			}
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
		BoardCell cell = board.getCell(1,6);
		Set<BoardCell> testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(0,6)));
		Assert.assertTrue(testList.contains(board.getCell(1,5)));
		Assert.assertTrue(testList.contains(board.getCell(1,7)));
		Assert.assertTrue(testList.contains(board.getCell(2,6)));
		Assert.assertEquals(4, testList.size());
	}

	//Old Experiment Test#2
	//Basic Movement with a dice roll of 4, all cells are walkable and unoccupied.
	@Test
	void creatTestBoard() {
	board.calcTargets(board.getCell(0, 1), 4);
	Set<BoardCell> testTargets = board.getTargets();
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
		Assert.assertEquals(9, targets.size());
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
