package experiment;

import java.util.Collections;
import java.util.Set;


public class TestBoard {
	
	//The data structure gameBoard will be 4x4 for testing
	public final static int BOARD_HIEGHT = 4;
	public final static int BOARD_WIDTH = 4;
	
	//Rows to be added to the board
	private Set<TestBoardCell> row = Collections.emptySet();
	
	
	//Field for the gameBoard
	private Set<Set<TestBoardCell>> gameBoard;
	
	//Constructs the gameBoard based off the parameters passed.
	//The dimension can be altered via the static fields within this class.
	public TestBoard() {
		int column = 0;
		while (column++ < BOARD_WIDTH) {
			this.populateRow(column);
			this.gameBoard.add(row);
			this.gameBoard.clear();
		}
	}
	
	//This method populates the row set to be added to the game board
	//This is called inside TestBoard() and written here for clarity.
	private void populateRow(int column) {
		int currentRow = 0;
		while (currentRow < BOARD_HIEGHT) {
			this.row.add(new TestBoardCell(currentRow, column));
		}
	}
	
	private void calcTargets( TestBoardCell startCell, int pathlength) {
		//Just clearing row for no reason for testing
		this.row.clear();
	}
	
	public Set<TestBoardCell> getTargets() {
		//Just returning an empty set for testing
		this.row.clear();
		return this.row;
	}
	

}