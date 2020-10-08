package experiment;

import experiment.*;
import java.util.ArrayList;

public class TestBoardCellV2 {
	public static int[][] gridAssigner = new int[TestBoard.BOARD_HIEGHT][TestBoard.BOARD_WIDTH];
	
	//suggested fields
	
	private int cRow, cColumn;
	
	private boolean isRoom = false,		isBorder = false,		isBorderCell = false,
					isOccupied = false,	hasDoor = false,		min = false,
					edgeRow = false,	isWalkable = false, 	max = false,
					edgeCol = false, 	isBrokenRide = false;
		
	private ArrayList<CellStatus> myStatus = new ArrayList<CellStatus>();
	
	public TestBoardCellV2 (int locRow, int locCol) {
		this.cColumn 	= locCol;
		this.cRow		= locRow;
		this.checkIfBorderCell();
	}
	
	public void addCellStatus(CellStatus status) {
		this.myStatus.add(status);
	}
	
	private void buildAdjList () {
		
	}
	
	private void checkAdjCells () {
		switch (this.findCase(edgeRow, edgeCol)) {
		
		case 1:
			
			
			
		default: 
			break;
		
		}
	}
	
	private int findCase(boolean onRow, boolean onColumn) {
		if (onRow && onColumn) 	{ return 1; }
		if (!onRow && onColumn) { return 2; }
		if (onRow && !onColumn) { return 3; }
		return 0;
	}
	
	private void checkIfBorderCell () {
		if(cRow == 0) 	{ this.edgeRow = true; min = true;}
		if(cColumn == 0){ this.edgeCol = true; min = true;}
		if(cRow == TestBoard.BOARD_WIDTH) 		{ this.edgeRow = true; max = true;}
		if(cColumn == TestBoard.BOARD_HIEGHT) 	{ this.edgeCol = true; max = true;}
	}
	
}
