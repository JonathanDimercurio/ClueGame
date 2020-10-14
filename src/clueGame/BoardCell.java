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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import clueGame.CellStatus;

public class BoardCell {
	private int yRow, xCol;
	private char intial;
	private Room myRoomType;
	
	private DoorDirection doorDirection = DoorDirection.NONE;
	
	private boolean roomLabel 	= 	false,
					roomCenter 	= 	false, 
					isDoorway 	= 	false,
					isOccupied 	= 	false,	
					isWalkable 	= 	false;
	
	char secretPassage;
	
	//Data structures
	public static Set<BoardCell> gameBoardData = new HashSet<BoardCell>();
	public static Map<Integer, BoardCell> mapGameBoardData = new HashMap<Integer, BoardCell>();
	private Set<BoardCell> adjList = new HashSet<BoardCell>();
	private Set<CellStatus> myStatus = new HashSet<CellStatus>();	

	//Two Parameter constructor 	//Obsolete
	public BoardCell (int locXCol, int locYRow) {
		this.xCol 		= locXCol;
		this.yRow		= locYRow;
		gameBoardData.add(this);
	}
	
	//Three parameter constructor 	//Primary
	public BoardCell (String cellDisc, char cellType, Room setRoomType) {
		gameBoardData.add(this);
		this.myRoomType = setRoomType;
		this.intial = cellType;
		if(this.intial == 'W') {
			this.isWalkable = true;
		}
		if (cellDisc.length() > 1) {
			adjustCellAttributes(cellDisc.charAt(1));	
		}
	}

	private void adjustCellAttributes(char feature) {
		switch(feature) {
			case '#':
				this.roomLabel = true;
				Board.getInstance().getRoom(this.intial).setLabelCell(this);
				break;
			
			case '*':
				this.roomCenter = true;
				Board.getInstance().getRoom(this.intial).setCenterCell(this);
				break;
				
			case '<':
				this.doorDirection = DoorDirection.LEFT;
				this.isDoorway = true;
				break;
				
			case '^':
				this.doorDirection = DoorDirection.UP;
				this.isDoorway = true;
				break;
								
			case '>':
				this.doorDirection = DoorDirection.RIGHT;
				this.isDoorway = true;
				break;
				
			case'v':
				this.doorDirection = DoorDirection.DOWN;
				this.isDoorway = true;
				break;
				
			default:
				this.secretPassage = feature;
				break;
		}
	}

	public void setAdjList(Set<BoardCell> adjList) {
		this.adjList = adjList;
	}

	//Might be obsolete
	public void adjustCellStatus(CellStatus tempStat) {
		this.myStatus.add(tempStat);
		if(tempStat == CellStatus.VOID || tempStat == CellStatus.BROKENRIDE || tempStat == CellStatus.WALL ) {
			for(BoardCell tempCell: gameBoardData) {
				tempCell.adjList.remove(this);
			}
		}
		if(tempStat == CellStatus.OCCUPIED)
			this.setOccupied(true);
	}

	public boolean isDoorway() {
		return isDoorway;
	}

	public Set<BoardCell> getAdjList() {
		return this.adjList;
	}

	public int getxCol() {
		return xCol;
	}

	public int getyRow() {
		return yRow;
	}

	public void setyRow(int yRow) {
		this.yRow = yRow;
	}

	public char getSecretPassage() {
		return secretPassage;
	}

	public void setxCol(int xCol) {
		this.xCol = xCol;
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
	
	public boolean isLabel() {
		return this.roomLabel;
	}

	public boolean isRoomCenter() {
		return this.roomCenter;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public Room getMyRoomType() {
		return myRoomType;
	}
}