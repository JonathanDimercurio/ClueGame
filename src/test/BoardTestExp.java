package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import experiment.*;

class BoardTestExp {

	
	private TestBoard tBoard;


	@BeforeEach
	void creatTestBoard() {
	this.tBoard = new TestBoard();
	}

	
	//Required test for getAdjacencies().
	@Test
	public void testAdjacency() {
		TestBoardCell cell = tBoard.getCell(0,0);
		ArrayList<TestBoardCell> testList = cell.getAdjacencies();
		Assert.assertTrue(testList.contains(tBoard.getCell(1,0)));
		Assert.assertTrue(testList.contains(tBoard.getCell(0,1)));
		Assert.assertEquals(2, testList.size());
		fail("Not yet implemented");
	}
	

		
	@Test
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
		
	@Test
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
	


	

}
