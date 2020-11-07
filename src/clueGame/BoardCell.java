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

public class BoardCell {
	private int yRow;
	private int xCol;
	private char intial;
	private Room myRoomType;
	private int key;

	private DoorDirection doorDirection = DoorDirection.NONE;
	
	private boolean roomLabel 	= 	false;
	private boolean roomCenter 	= 	false;
	private boolean	isDoorway 	= 	false;
	private boolean isOccupied 	= 	false;
	private boolean isWalkable 	= 	false;
	private boolean isSecretPassage = false;
	
	BoardCell secretPassageCell;
	char secretPassage;
	
	//Reference Data structures
	//make these package not public
	//probably remove some of these entirely
	public static Set<BoardCell> 			gameBoardData 		= new HashSet<>();
	public static Map<Integer, BoardCell> 	mapGameBoardData 	= new HashMap<>();
	public static Set<BoardCell>			roomCenters 		= new HashSet<>();
	public static Set<BoardCell>			roomDoors			= new HashSet<>();
	
	//Instanced Structures
	private Set<BoardCell>					adjList 			= new HashSet<>();
	private Set<CellStatus> 				myStatus 			= new HashSet<>();	

	//Two Parameter constructor 	//Obsolete
	public BoardCell (int locXCol, int locYRow) {
		this.xCol 		= locXCol;
		this.yRow		= locYRow;
		gameBoardData.add(this);
	}
	
	//Three parameter constructor 	//Primary
	public BoardCell (String cellDisc, char cellType, Room setRoomType, int myKey) {
		gameBoardData.add(this);
		this.myRoomType = setRoomType;
		this.intial = cellType;
		this.key = myKey;
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
				BoardCell.roomCenters.add(this);
				break;
				
			case '<':
				this.doorDirection = DoorDirection.LEFT;
				this.isDoorway = true;
				BoardCell.roomDoors.add(this);
				break;
				
			case '^':
				this.doorDirection = DoorDirection.UP;
				this.isDoorway = true;
				BoardCell.roomDoors.add(this);
				break;
								
			case '>':
				this.doorDirection = DoorDirection.RIGHT;
				this.isDoorway = true;
				BoardCell.roomDoors.add(this);
				break;
				
			case'v':
				this.doorDirection = DoorDirection.DOWN;
				this.isDoorway = true;
				BoardCell.roomDoors.add(this);
				break;
				
			default:
				this.secretPassage = feature;
				this.isSecretPassage = true;
				break;
		}
	}

	public void setAdjList(Set<BoardCell> adjList) {
		this.adjList.addAll(adjList);
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

	public void addToAdjList(BoardCell addCell) {
		this.adjList.add(addCell);
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

	public void setOccupied(boolean isOccupied) {
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
	
	public int getKey() {
		return key;
	}

	public char getIntial() {
		return intial;
	}

	public boolean ifRoomCenter() {
		return roomCenter;
	}
	
	public BoardCell getThis() {
		return this;
	}
	
	public boolean isSecretPassage() {
		return isSecretPassage;
	}
}