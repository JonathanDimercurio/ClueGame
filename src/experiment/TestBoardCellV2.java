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

import java.util.HashSet;
import java.util.Set;

public class TestBoardCellV2 {
	
	public static Set<TestBoardCellV2> gameBoardData = new HashSet<TestBoardCellV2>();
	private Set<TestBoardCellV2> adjList = new HashSet<TestBoardCellV2>();
	private Set<CellStatus> myStatus = new HashSet<CellStatus>();
	private int cRow, cColumn;
	
	private boolean isOccupied = false,	isWalkable = true;
	
	public TestBoardCellV2 (int locCol, int locRow) {
		this.cColumn 	= locCol;
		this.cRow		= locRow;
		gameBoardData.add(this);
	}

	public void adjustCellStatus(CellStatus addStat) {
		this.myStatus.add(addStat);
		if(addStat == CellStatus.VOID || addStat == CellStatus.BROKENRIDE || addStat == CellStatus.WALL ) {
			for(TestBoardCellV2 tempCell: gameBoardData) {
				tempCell.adjList.remove(this);
			}
		}
		if(addStat == CellStatus.OCCUPIED)
			this.setOccupied(true);
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
	
	public boolean isOccupied() {
		return isOccupied;
	}

	private void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public boolean isWalkable() {
		return isWalkable;
	}

	public void setWalkable(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}
	
	@Override
	public String toString() {
		return "TestBoardCellV2 [cColumn=" + cColumn + ", cRow=" + cRow + "]";
	}
}
