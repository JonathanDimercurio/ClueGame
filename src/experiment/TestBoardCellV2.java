/**
 * TestBoardCellV2
 * 
 * Purpose: This class is for testing out individual cells within the TestBoard Class
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */

package experiment;

import experiment.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TestBoardCellV2 {
	
	private Set<TestBoardCellV2> adjList;
	private ArrayList<CellStatus> myStatus = new ArrayList<CellStatus>();
	private int cRow, cColumn;
	
	private boolean isRoom = false,		isBrokenRide = false,
					isOccupied = false,	hasDoor = false,		isWalkable = false;
	

	public TestBoardCellV2() {
		//genCoord();
	}
	
	public TestBoardCellV2 (int locRow, int locCol) {
		this.cColumn 	= locCol;
		this.cRow		= locRow;
	}
	
	public void addCellStatus(CellStatus status) {
		this.myStatus.add(status);
	}
	
	private void buildAdjList() {
		
	}
	
	
	public int getcRow() {
		return cRow;
	}

	public int getcColumn() {
		return cColumn;
	}

	
	
}
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cColumn;
		result = prime * result + cRow;
		return result;
	}
	*/
	
	/*
	@Override
	public boolean equals(TestBoardCellV2 obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestBoardCellV2 other = (TestBoardCellV2) obj;
		if (cColumn != other.cColumn)
			return false;
		if (cRow != other.cRow)
			return false;
		return true;
	}
	
		private void checkAdjCells () {
		switch (this.findCase(edgeRow, edgeCol)) {
			
				//These cases provide logic to check whether the cell is on the edge of the border
				//And will handle out-of-bounds situations
			case 1: 
				break;
			case 2:
				break;
			case 3:
				break;
			
			default:
				//Our default case is the cell is within normal checkAdj function space.
				break;
		
		}
	}
	

	private int findCase(boolean onRow, boolean onColumn) {
		if (!onRow && onColumn) { return 1; }
		if (onRow && !onColumn) { return 2; }
		if (onRow && onColumn) 	{ return 3; }
		
		//if(edgeCellCaseDefiner())
		
		return 0;
	}
	
	private void checkIfBorderCell () {
		if(cRow == 0) 	{ this.edgeRow = true; min = true;}
		if(cColumn == 0){ this.edgeCol = true; min = true;}
		if(cRow == TestBoard.BOARD_WIDTH) 		{ this.edgeRow = true; max = true;}
		if(cColumn == TestBoard.BOARD_HIEGHT) 	{ this.edgeCol = true; max = true;}
	}
	
		public TestBoardCellV2 (int locRow, int locCol, ArrayList<CellStatus> cellStatus) {
		this.cColumn 	= locCol;
		this.cRow		= locRow;
		this.checkIfBorderCell();
		for (CellStatus toAdd: cellStatus) {
			this.addCellStatus(toAdd);
		}
		TestBoard.addCellToGrid(this);
 	}


}
*/