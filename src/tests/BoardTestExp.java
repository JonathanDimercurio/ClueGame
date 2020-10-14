/**
 * BoardTestExp Class
 * 
 * Purpose: This is a testing class for board movement with the Clue Game Board project.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */

package tests;
 
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import experiment.*;

class BoardTestExp {

	
	private TestBoard tBoard;

	//Required test for getAdjacencies().
	@Test
	void testAdjacency() {
		this.tBoard = new TestBoard();
		TestBoardCellV2 cell = tBoard.getCell(0,0);
		Set<TestBoardCellV2> testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(tBoard.getCell(1,0)));
		Assert.assertTrue(testList.contains(tBoard.getCell(0,1)));
		Assert.assertEquals(2, testList.size());
	}
	
	//Basic Movement with a dice roll of 4, all cells are walkable and unoccupied.
	@Test
	void creatTestBoard() {
	this.tBoard = new TestBoard();
	tBoard.calcTargets(tBoard.getCell(0, 1), 4);
	for (TestBoardCellV2 temp : tBoard.getTargets()) {
		System.out.println(temp.toString());
		}
	System.out.println("----------dice roll of 4-----------");
	System.out.println("-----------------------------------");
	
	Set<TestBoardCellV2> testTargets = tBoard.getTargets();
	
	Assert.assertTrue(testTargets.contains(tBoard.getCell(3,0)));
	Assert.assertTrue(testTargets.contains(tBoard.getCell(2,3)));
	Assert.assertTrue(testTargets.contains(tBoard.getCell(1,2)));
	Assert.assertTrue(testTargets.contains(tBoard.getCell(3,2)));
	Assert.assertTrue(testTargets.contains(tBoard.getCell(0,3)));
	Assert.assertTrue(testTargets.contains(tBoard.getCell(1,0)));
	}
	
	//Target Tests adjacency list is the correct size and has exactly the right elements.
	@Test
	public void testTargetsNormal() {
		this.tBoard = new TestBoard();
		TestBoardCellV2 cell = tBoard.getCell(0,0);
		tBoard.calcTargets(cell, 3);
		Set<TestBoardCellV2> targets =  tBoard.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(tBoard.getCell(3,0)));
		Assert.assertTrue(targets.contains(tBoard.getCell(2,1)));
		Assert.assertTrue(targets.contains(tBoard.getCell(0,1)));
		Assert.assertTrue(targets.contains(tBoard.getCell(1,2)));
		Assert.assertTrue(targets.contains(tBoard.getCell(0,3)));
		Assert.assertTrue(targets.contains(tBoard.getCell(1,0)));
		
		for (TestBoardCellV2 temp : tBoard.getTargets()) {
			System.out.println(temp.toString());
			}
		System.out.println("----------dice roll of 3-----------");
		System.out.println("-----------------------------------");
		
	}
	
	//targetsMixed test for a complex situation where there may be a cell that 
	//represents a wall and another that is occupied by an opponent
	@Test
	public void testTargetsMixed() {
		this.tBoard = new TestBoard();
		tBoard.getCell(0,2).adjustCellStatus(CellStatus.OCCUPIED);
		tBoard.getCell(1,2).adjustCellStatus(CellStatus.VOID);
		TestBoardCellV2 cell= tBoard.getCell(0,3);
		tBoard.calcTargets(cell,3);
		Set<TestBoardCellV2> targets = tBoard.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(tBoard.getCell(0,0)));
		Assert.assertTrue(targets.contains(tBoard.getCell(1,1)));
		Assert.assertTrue(targets.contains(tBoard.getCell(2,2)));
		Assert.assertTrue(targets.contains(tBoard.getCell(3,3)));
		
		for (TestBoardCellV2 temp : targets) {
			System.out.println(temp.toString());
			}
		System.out.println("----------dice roll of 3-----------");
		System.out.println("-----------------------------------");
	}

	//targetsWalkable test for a complex situation where there may be a cell that 
	//represents a wall, or VOID and another that is occupied and another is a broken-ride.
	@Test
	public void testTargetWalkable() {
		this.tBoard = new TestBoard();
		tBoard.getCell(1,1).adjustCellStatus(CellStatus.OCCUPIED);
		tBoard.getCell(0,1).adjustCellStatus(CellStatus.VOID);
		tBoard.getCell(2,0).adjustCellStatus(CellStatus.BROKENRIDE);
		TestBoardCellV2 cell= tBoard.getCell(0,0);
		tBoard.calcTargets(cell,2);
		Set<TestBoardCellV2> targets = tBoard.getTargets();
		Assert.assertEquals(0, targets.size());

		for (TestBoardCellV2 temp : targets) {
			System.out.println(temp.toString());
			}
		System.out.println("----------dice roll of 3-----------");
		System.out.println("-----------------------------------");
	}

	
	
	
	


}
