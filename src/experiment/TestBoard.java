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

import java.util.HashSet;
import java.util.Set;


public class TestBoard {
	
	public final static int BOARD_HIEGHT = 4;
	public final static int BOARD_WIDTH = 4;
	private TestBoardCellV2[][] gameGrid = new TestBoardCellV2[BOARD_HIEGHT][BOARD_WIDTH];
	
	
	//private Set<TestBoardCellV2> gameBoard;
	
	private Set<TestBoardCellV2> targets = new HashSet<TestBoardCellV2>();
	private Set<TestBoardCellV2> visited = new HashSet<TestBoardCellV2>();
	
	
	public TestBoard() {
		//gameGrid = null;
		genGrid();
		genAdj();
	}
		

	
	

	public void calcTargets( TestBoardCellV2 startCell, int pathlength) {
		while(0 < pathlength) {
			visited.add(startCell);
			targets.addAll(startCell.getAdjList());
			for (TestBoardCellV2 newTargets: targets) {
				calcTargets(newTargets, pathlength - 1);
			}
			visited.addAll(targets);	
			}
		
		/*
		 * targets.removeAll(visited);
		for (TestBoardCellV2 statusCheck: visited) {
				
			}
			
		for (TestBoardCellV2 newTargets: targets) {
			calcTargets(newTargets, pathlength);
		}
		*/
		visited.removeAll(visited);
	}
	
	public Set<TestBoardCellV2> getTargets() {
		//need logic to check visited list
		Set<TestBoardCellV2> targetList = new HashSet<TestBoardCellV2>();
		//need logic here to assign actual targts
		return targetList;
	}
	

	//Solid. Working well
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
		
	//Solid works as intended
	private void genAdj() {
		int cRow = 0, cColumn = 0;
		while (cColumn != TestBoard.BOARD_WIDTH) {
			if (cColumn < TestBoard.BOARD_WIDTH) {
				while (cRow != TestBoard.BOARD_HIEGHT) {
					gameGrid[cColumn][cRow].buildAdjList(checkAdjList(cColumn, cRow));
					cRow += 1;
				}
			if (cColumn != TestBoard.BOARD_WIDTH) {
				cRow = 0;
				}
			}
			cColumn += 1;
		}
	}
	
	//Solid. Works well.
	private Set<TestBoardCellV2> checkAdjList(int x, int y) {
		Set<TestBoardCellV2> tempAdjList = new HashSet<TestBoardCellV2>();
		
		if((x - 1) >= 0) 								{ tempAdjList.add(getCell(x-1,y)); } 
		if((y - 1) >= 0) 								{ tempAdjList.add(getCell(x,y-1)); }
		if((x + 1) <= TestBoard.BOARD_WIDTH - 1) 		{ tempAdjList.add(getCell(x+1,y)); }
		if((y + 1) <= TestBoard.BOARD_HIEGHT - 1) 		{ tempAdjList.add(getCell(x,y+1)); }
		return tempAdjList;
	}

	public TestBoardCellV2 getCell(int x, int y) {
		TestBoardCellV2 tempCell = gameGrid[x][y];
		return tempCell;
	}

}	



/********************************
*/////Obsolete Code 





/*
 * 	//private ArrayList<TestBoardCell> row = new ArrayList<TestBoardCell>();
 *	//private ArrayList<ArrayList<TestBoardCell>> gameBoard = new ArrayList<ArrayList<TestBoardCell>>();
 * 
 * 
  		if(cRow - 1 >= 0) 							{ adjList.add(TestBoard.getCell(cRow-1,cColumn)); } 
		if(cColumn - 1 >= 0) 						{ adjList.add(TestBoard.getCell(cRow,cColumn-1)); }
		if(cRow + 1 <= TestBoard.BOARD_HIEGHT) 		{ adjList.add(TestBoard.getCell(cRow+1,cColumn)); }
		if(cColumn + 1 >= TestBoard.BOARD_WIDTH) 	{ adjList.add(TestBoard.getCell(cRow,cColumn+1)); }
 * 
 * 
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