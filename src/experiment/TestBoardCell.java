package experiment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class TestBoardCell {
	
	//potential solution for BoardCell indexing
	public static boolean[][] locArray = new boolean[TestBoard.BOARD_HIEGHT][TestBoard.BOARD_WIDTH];
	
	//Set of adjacent target cells
	private ArrayList<TestBoardCell> adjacencies = new ArrayList<TestBoardCell>();
	
	//potential fields to track the location of each cell
	private int cellRow, cellColumn;
	
	public TestBoardCell (int locRow, int locColumn) {
		this.cellRow = locRow;
		this.cellColumn = locColumn;
		locArray[locRow][locColumn] = true;
	}

	//sets whether the cell is has a status. i.e. occupied, walkable.
	public void setCellStatus(CellStatus status) {
	}
	
	//sets whether a cell is a room
	public void setRoomStatus() {
		
	}
	
	public ArrayList<TestBoardCell> getAdjacencies() {
		//Just returning an empty set for testing
		this.adjacencies.clear();
		return this.adjacencies;
	}
	
	public String getCoordinates () {
		String coords = new String(String.valueOf(cellRow) + " " + String.valueOf(cellColumn));
		return coords;
	}

}