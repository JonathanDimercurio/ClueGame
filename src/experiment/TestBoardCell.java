package experiment;

/**
 * TestBoardCell
 * 
 * Purpose: This class is for testing out individual cells within the TestBoard Class
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class TestBoardCell {
	
	/*
	//potential solution for BoardCell indexing
	//Set of adjacent target cells
	//potential fields to track the location of each cell
	*/
	
	
	/*****
	//public static boolean[][] locArray = new boolean[TestBoard.BOARD_HIEGHT][TestBoard.BOARD_WIDTH];
	*/
	
	//This field is for storing the adjacent cells
	private ArrayList<TestBoardCell> adjCells = new ArrayList<TestBoardCell>();
	
	private int cellRow, cellColumn;
	private ArrayList<CellStatus> myStatus = new ArrayList<CellStatus>();
	
	
	
	public TestBoardCell (int locRow, int locColumn) {
		this.cellRow = locRow;
		this.cellColumn = locColumn;
		//locArray[locRow][locColumn] = true;
	}

	//sets whether the cell is has a status. i.e. occupied, walkable.
	public void setCellStatus(CellStatus status) {
		this.myStatus.add(status);
	}
	

	public ArrayList<TestBoardCell> getAdjacencies() {
		//Just returning an empty set for testing
		this.adjCells.clear();
		return this.adjCells;
	}
	
	public String getCoordinates () {
		String coords = new String(String.valueOf(cellRow) + " " + String.valueOf(cellColumn));
		return coords;
	}
}	
