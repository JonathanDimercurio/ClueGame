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
	private TestBoardCellV2[][] gameGrid = new TestBoardCellV2[BOARD_HIEGHT][BOARD_WIDTH];
	
	
	//private Set<TestBoardCellV2> gameBoard;
	
	private Set<TestBoardCellV2> targets;
	private Set<TestBoardCellV2> visited;
	
	
	public TestBoard() {
		//gameGrid = null;
		this.genGrid();
	}
		

	
	

	public void calcTargets( TestBoardCellV2 startCell, int pathlength) {
	
	}
	
	public Set<TestBoardCellV2> getTargets() {
		//need logic to check visited list
		Set<TestBoardCellV2> targetList = new HashSet<TestBoardCellV2>();
		//need logic here to assign actual targts
		return targetList;
	}
	
	public TestBoardCellV2 getCellFromGrid(int x, int y) {
		return gameGrid[x][y];		
	}
	
	private void genGrid() {
		int cRow = 0, cColumn = 0;
		while (cColumn != TestBoard.BOARD_WIDTH) {
			if (cColumn < TestBoard.BOARD_WIDTH) {
				while (cRow != TestBoard.BOARD_HIEGHT) {
					gameGrid[cColumn][cRow] = new TestBoardCellV2(cRow, cColumn);
					cRow += 1;
				}
			if (cColumn != TestBoard.BOARD_WIDTH) {
				cRow = 0;
				}
			}
			cColumn += 1;
		}
	}
		
	public TestBoardCellV2 getCell(int row, int column) {
		return this.gameBoard[row][column];
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
	
			//int index1 = 0, index2 = 0;
		//gameBoard = new HashSet<TestBoardCellV2>();
		//for (TestBoardCellV2 iCell: gameBoard) {
			
			//iCell = new TestBoardCellV2();
			//gameBoard.add(iCell);
			//}
		
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