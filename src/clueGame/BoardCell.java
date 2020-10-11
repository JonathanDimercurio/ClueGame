/**
 * BoardCell
 * 
 * Purpose: BoardCell defines a individual cell on the game board. Most methods were
 * created using TestBoardCellV2 and TestBoardCell.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */
package clueGame;

import java.util.HashSet;
import java.util.Set;

import clueGame.CellStatus;

public class BoardCell {
	private int yRow, xCol;
	private char intial;
	private DoorDirection doorDirection;
	private boolean roomLabel, roomCenter, isDoorway;
	private boolean isOccupied = false,	isWalkable = true;
	private char secretPassage;
	
	//Data structures
	public static Set<BoardCell> gameBoardData = new HashSet<BoardCell>();
	private Set<BoardCell> adjList = new HashSet<BoardCell>();
	private Set<CellStatus> myStatus = new HashSet<CellStatus>();	

	
	public BoardCell (int locXCol, int locYRow) {
		this.xCol 	= locXCol;
		this.yRow		= locYRow;
		gameBoardData.add(this);
	}

	public void setAdjList(Set<BoardCell> adjList) {
		this.adjList = adjList;
	}

	public void adjustCellStatus(CellStatus addStat) {
		this.myStatus.add(addStat);
		if(addStat == CellStatus.VOID || addStat == CellStatus.BROKENRIDE || addStat == CellStatus.WALL ) {
			for(BoardCell tempCell: gameBoardData) {
				tempCell.adjList.remove(this);
			}
		}
		if(addStat == CellStatus.OCCUPIED)
			this.setOccupied(true);
	}

	public boolean isDoorway() {
		return isDoorway;
	}

	public void setDoorway(boolean isDoorwar) {
		this.isDoorway = isDoorway;
	}

	public Set<BoardCell> getAdjList() {
		return this.adjList;
	}

	public int getYRow() {
		return yRow;
	}

	public int getxCol() {
		return xCol;
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
		return "TestBoardCellV2 [xCol=" + xCol + ", yRow=" + yRow + "]";
	}
	
	
	
}