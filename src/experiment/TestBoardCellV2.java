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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TestBoardCellV2 {
	
	private Set<TestBoardCellV2> adjList = new HashSet<TestBoardCellV2>();
	private ArrayList<CellStatus> myStatus = new ArrayList<CellStatus>();
	private int cRow, cColumn;
	
	private boolean isRoom = false,			isBrokenRide = false,
					isOccupied = false,		hasDoor = false,		isWalkable = false;
	
	public TestBoardCellV2 (int locCol, int locRow) {
		this.cColumn 	= locCol;
		this.cRow		= locRow;
	}

	public void buildAdjList(Set<TestBoardCellV2> inputList) {
		this.adjList.addAll(inputList);
	}
		
	public Set<TestBoardCellV2> getAdjList() {
		//Set<TestBoardCellV2> tempList = this.adjList;
		//return tempList;
		return this.adjList;
	}

	public int getcRow() {
		return cRow;
	}

	public int getcColumn() {
		return cColumn;
	}
	
	public void addCellStatus(CellStatus status) {
		this.myStatus.add(status);
	}

	public boolean isRoom() {
		return isRoom;
	}

	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public boolean isWalkable() {
		return isWalkable;
	}

	public void setWalkable(boolean isWalkable) {
		this.isWalkable = isWalkable;
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

		
	private int findCase(int onRow, int onColumn) {
		if ((onRow == 0 && onColumn != 0) || ((onRow == TestBoard.BOARD_HIEGHT && onColumn != 0))) 	{ return 1; }
		if ((onRow != 0 && onColumn == 0) || ((onColumn == TestBoard.BOARD_WIDTH && onRow != 0))) 	{ return 2; }
		if (onRow == 0 && onColumn == 0) 															{ return 3; }
		if (onRow == 0 && onColumn == TestBoard.BOARD_WIDTH) 										{ return 4; }
		if (onRow == TestBoard.BOARD_HIEGHT && onColumn == 0) 										{ return 5; }
		if (onRow == TestBoard.BOARD_HIEGHT && onColumn == TestBoard.BOARD_WIDTH) 					{ return 6; }
		return 0;
	}

		private void checkAdjCells () {
	switch (this.findCase(cRow, cColumn)) {
		case 1: 
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		default:
			//Our default case is the cell is within normal checkAdj function space.
			break;
		}
	}

		private boolean checkIfBorderCell() {
		if (this.cRow != 0 && this.cColumn != 0 && this.cColumn != TestBoard.BOARD_HIEGHT && this.cRow != 0) {
			return true;
		}
		return false;
	}
	
	
}
*/