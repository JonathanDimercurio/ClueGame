/**
 * TestBoard Class
 * 
 * Purpose: This TestBoard class will generate a game board based of the constant fields.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */


package experiment;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class TestBoard {
	
	public final static int BOARD_HIEGHT = 4;
	public final static int BOARD_WIDTH = 4;
	
	//static TestBoardCellV2[][] grid = new TestBoardCellV2[BOARD_HIEGHT][BOARD_WIDTH];
	private static TestBoardCellV2[][] grid = new TestBoardCellV2[BOARD_HIEGHT][BOARD_WIDTH];
	private Set<TestBoardCellV2> gameBoard;
	
	private Set<TestBoardCellV2> targets;
	private Set<TestBoardCellV2> visited;
	
	
	public TestBoard() {
		grid = null;
		gameBoard = new HashSet<TestBoardCellV2>();
		
		//int index1 = 0, index2 = 0;
		
		for (TestBoardCellV2 iCell: gameBoard) {
			iCell = new TestBoardCellV2();
			gameBoard.add(iCell);
			}
		
	}
	
	

	public void calcTargets( TestBoardCell startCell, int pathlength) {
	}
	
	public Set<TestBoardCellV2> getTargets() {
		return this.gameBoard;
	}
		
	public static void addCellToGrid(TestBoardCellV2 cellToAdd) {
		grid[cellToAdd.getcRow()][cellToAdd.getcColumn()] = cellToAdd;
	}

}

























/********************************
*/////Obsolete Code 


/*
 * 	//private ArrayList<TestBoardCell> row = new ArrayList<TestBoardCell>();
 *	//private ArrayList<ArrayList<TestBoardCell>> gameBoard = new ArrayList<ArrayList<TestBoardCell>>();
 * 
 * 
public TestBoard() {
	int column = 0;
	while (column < BOARD_WIDTH) {
		this.gameBoard.add(populateRow(column));
		column += 1;
	}
}

private ArrayList<TestBoardCell> populateRow(int column) {
	ArrayList<TestBoardCell> addingRow = new ArrayList<TestBoardCell>();
	int currentRow = 0;
	while (currentRow < BOARD_HIEGHT) {
		addingRow.add(new TestBoardCell(currentRow, column));
		currentRow += 1;
	}
	return addingRow;
}
*/

/*
public TestBoardCell getCell(int row, int column) {
	return this.gameBoard.get(row).get(column);
}
*/