/**
 * TestBoard Class
 * 
 * Purpose: This TestBoard class will generate a game board based of the constant fields.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */


package experiment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class TestBoard {
	
	public final static int BOARD_HIEGHT = 4;
	public final static int BOARD_WIDTH = 4;
	
	private TestBoardCell[][] grid = new TestBoardCell[BOARD_HIEGHT][BOARD_WIDTH];
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	
	
	
	private ArrayList<TestBoardCell> row = new ArrayList<TestBoardCell>();
	private ArrayList<ArrayList<TestBoardCell>> gameBoard = new ArrayList<ArrayList<TestBoardCell>>();
	
	public TestBoard() {
		int column = 0;
		while (column < BOARD_WIDTH) {
			this.gameBoard.add(populateRow(column));
			column += 1;
		}
	}
	
	private ArrayList<TestBoardCell> populateRow(int column) {
		ArrayList<TestBoardCell> addingRow = new ArrayList<TestBoardCell>();
		int currentRow = 0;
		while (currentRow < BOARD_HIEGHT) {
			addingRow.add(new TestBoardCell(currentRow, column));
			currentRow += 1;
		}
		return addingRow;
	}
	
	public void calcTargets( TestBoardCell startCell, int pathlength) {
		this.row.clear();
	}
	
	public ArrayList<TestBoardCell> getTargets() {
		this.row.clear();
		return this.row;
	}
	
	public TestBoardCell getCell(int row, int column) {
		return this.gameBoard.get(row).get(column);
	}
}