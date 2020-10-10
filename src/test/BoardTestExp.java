/**
 * BoardTestExp Class
 * 
 * Purpose: This is a testing class for board movement with the Clue Game Board project.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import experiment.*;

class BoardTestExp {

	
	private TestBoard tBoard;


	@Test
	void creatTestBoard() {
	this.tBoard = new TestBoard();
	tBoard.calcTargets(tBoard.getCell(1, 1), 2);
	
	}

	
	
	
	


}



//Required test for getAdjacencies().
/*@Test
public void testAdjacency() {
	TestBoardCell cell = tBoard.getCell(0,0);
	ArrayList<TestBoardCell> testList = cell.getAdjacencies();
	Assert.assertTrue(testList.contains(tBoard.getCell(1,0)));
	Assert.assertTrue(testList.contains(tBoard.getCell(0,1)));
	Assert.assertEquals(2, testList.size());
	fail("Not yet implemented");
}

/*
//Target Tests adjacency list is the correct size and has exactly the right elements.
//@Test
public void testTargetsNormal() {
	TestBoardCell cell = tBoard.getCell(0,0);
	tBoard.calcTargets(cell, 3);
	ArrayList<TestBoardCell> targets =  tBoard.getTargets();
	Assert.assertEquals(6, targets.size());
	Assert.assertTrue(targets.contains(tBoard.getCell(3,0)));
	Assert.assertTrue(targets.contains(tBoard.getCell(2,1)));
	Assert.assertTrue(targets.contains(tBoard.getCell(0,1)));
	Assert.assertTrue(targets.contains(tBoard.getCell(1,2)));
	Assert.assertTrue(targets.contains(tBoard.getCell(0,3)));
	Assert.assertTrue(targets.contains(tBoard.getCell(1,0)));
}

//targetsMixed test for a complex situation where there may be a cell that 
//represents a room and another that is occupied by an opponent
//@Test
public void testTargetsMixed() {
	tBoard.getCell(0, 2).setCellStatus(CellStatus.OCCUPIED);
	tBoard.getCell(1,2).setCellStatus(CellStatus.ISROOM);
	TestBoardCell cell= tBoard.getCell(0,3);
	tBoard.calcTargets(cell,3);
	ArrayList<TestBoardCell> targets = tBoard.getTargets();
	Assert.assertEquals(3, targets.size());
	Assert.assertTrue(targets.contains(tBoard.getCell(1,2)));
	Assert.assertTrue(targets.contains(tBoard.getCell(2,2)));
	Assert.assertTrue(targets.contains(tBoard.getCell(3,3)));
}

//targetsWalkable test for a complex situation where there may be a cell that 
//represents a walkway and another that is a room and another is a broken-ride.
//@Test
public void testTargetWalkable() {
	tBoard.getCell(1, 2).setCellStatus(CellStatus.ISROOM);
	tBoard.getCell(3,2).setCellStatus(CellStatus.WALKWAY);
	tBoard.getCell(2,3).setCellStatus(CellStatus.BROKENRIDE);
	TestBoardCell cell= tBoard.getCell(0,3);
	tBoard.calcTargets(cell,3);
	ArrayList<TestBoardCell> targets = tBoard.getTargets();
	Assert.assertEquals(3, targets.size());
	Assert.assertTrue(targets.contains(tBoard.getCell(1,2)));
	Assert.assertTrue(targets.contains(tBoard.getCell(2,2)));
	Assert.assertTrue(targets.contains(tBoard.getCell(2,3)));
	Assert.assertTrue(targets.contains(tBoard.getCell(3,3)));
	Assert.assertTrue(targets.contains(tBoard.getCell(1,3)));
}

//TargetStartSwap Swaps the start location, and checked for targets, then changes it and searches again
//@Test
public void testTargetStartSpotSwap() {
	tBoard.getCell(1, 0).setCellStatus(CellStatus.STARTSPOT);
	TestBoardCell cell= tBoard.getCell(0,3);
	tBoard.calcTargets(cell,4);
	ArrayList<TestBoardCell> targets = tBoard.getTargets();
	Assert.assertEquals(3, targets.size());
	Assert.assertTrue(targets.contains(tBoard.getCell(1,2)));
	Assert.assertTrue(targets.contains(tBoard.getCell(2,2)));
	Assert.assertTrue(targets.contains(tBoard.getCell(2,3)));
	Assert.assertTrue(targets.contains(tBoard.getCell(3,3)));
	Assert.assertTrue(targets.contains(tBoard.getCell(1,3)));
	
	tBoard.getCell(0, 0).setCellStatus(CellStatus.WALKWAY);
	tBoard.getCell(3, 3).setCellStatus(CellStatus.STARTSPOT);
	Assert.assertEquals(3, targets.size());
	Assert.assertTrue(targets.contains(tBoard.getCell(1,2)));
	Assert.assertTrue(targets.contains(tBoard.getCell(2,2)));
	Assert.assertTrue(targets.contains(tBoard.getCell(2,3)));
	Assert.assertTrue(targets.contains(tBoard.getCell(3,3)));
	Assert.assertTrue(targets.contains(tBoard.getCell(1,3)));
	
}
*/