package experiment;

import java.util.Collections;
import java.util.Set;

public class TestBoardCell {
	
	//potential solution for BoardCell indexing
	public static boolean[][] locArray = new boolean[TestBoard.BOARD_HIEGHT][TestBoard.BOARD_WIDTH];
	
	//Set of adjacent target cells
	private Set<TestBoardCell> adjacencies = Collections.emptySet();
	
	//potential fields to track the location of each cell
	private int cellRow, cellColumn;
	
	public TestBoardCell (int locRow, int locColumn) {
		this.cellRow = locRow;
		this.cellColumn = locColumn;
		
		locArray[locRow][locColumn] = true;
	}

	//need to pass it a cell somehow, below.
	public Set<TestBoardCell> getAdjList(int i) {
		return null; //Probably can't return this
	}
	
	//sets whether the cell is has a status. i.e. occupied, walkable.
	public void setCellStatus() {
		
	}
	
	//sets whether a cell is a room
	public void setRoomStatus() {
		
	}
	
	public Set<TestBoardCell> getAdjacencies() {
		//Just returning an empty set for testing
		this.adjacencies.clear();
		return this.adjacencies;
	}

}