/**
z * TestBoard Class
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
	
	private Set<TestBoardCellV2> targets;
	private Set<TestBoardCellV2> visited;
	
	public TestBoard() {
		genGrid();
		genAdj();
	}
	
	public void calcTargets(TestBoardCellV2 startCell, int pathL) {
		targets = new HashSet<TestBoardCellV2>();
		visited = new HashSet<TestBoardCellV2>();
		visited.add(startCell);
		dfs(startCell, pathL);
		targets.remove(startCell);
	}
	
	public void dfs(TestBoardCellV2 start, int path) {
		for (TestBoardCellV2 node: start.getAdjList()){
			if(!visited.contains(node)) {
				visited.add(node);
				if(path == 1) {
					if (!node.isOccupied())
					targets.add(node);
				} else {
					dfs(node, path - 1);
				}
				visited.remove(node);
			}
		}
	}
	

	public Set<TestBoardCellV2> getTargets() {
		return this.targets;
	}
	

	//Solid. Working well
	private void genGrid() {
		int cRow = 0, cColumn = 0;
		while (cColumn != TestBoard.BOARD_WIDTH) {
			if (cColumn < TestBoard.BOARD_WIDTH) {
				while (cRow != TestBoard.BOARD_HIEGHT) {
					gameGrid[cColumn][cRow] = new TestBoardCellV2(cColumn, cRow);
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


