/*












	private void loadConfigFiles() {
	try(Scanner txtScanner = new Scanner (new File("ClueSetup.txt"))) {
		//while(txtScanner.hasNextLine()) {
			//layoutConfigFile+=(txtScanner.nextLine());
		//}
	
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	try(Scanner csvScanner = new Scanner (new File("ClueLayout.csv"))) {
		//while(csvScanner.hasNextLine()) {
			//layoutConfigFile+=(csvScanner.nextLine());
		//}
	} catch (FileNotFoundException e) {
		// 
		e.printStackTrace();
	}
	
	
}

/********************************
*/////Obsolete Code 
/*
private Set<TestBoardCellV2> checkAdjList(int x, int y) {
	Set<TestBoardCellV2> tempAdjList = new HashSet<TestBoardCellV2>();
	if((x - 1) >= 0	&& getCell(x-1,y).isWalkable())		{ tempAdjList.add(getCell(x-1,y)); } 
	if((y - 1) >= 0 && getCell(x,y-1).isWalkable())		{ tempAdjList.add(getCell(x,y-1)); }
	if((x + 1) <= TestBoard.BOARD_WIDTH - 1 && getCell(x+1,y).isWalkable()) {
		tempAdjList.add(getCell(x+1,y)); 
	}
	if((y + 1) <= TestBoard.BOARD_HIEGHT - 1 && getCell(x,y+1).isWalkable()) { 
		tempAdjList.add(getCell(x,y+1)); 
	}
	return tempAdjList;
}


/*
 * targets.removeAll(visited);
for (TestBoardCellV2 statusCheck: visited) {
		
	}
	
	
while(0 < pathlength) {
	visited.add(startCell);
	visited.addAll(startCell.getAdjList());
	for (TestBoardCellV2 newTargets: visited) {
		visited.addAll(newTargets.getAdjList());
	}
	pathlength -= 1;
}
	
for (TestBoardCellV2 newTargets: targets) {
	calcTargets(newTargets, pathlength);
}

	visited.removeAll(visited);
}

*/



/*
 * 	//private ArrayList<TestBoardCell> row = new ArrayList<TestBoardCell>();
 *	//private ArrayList<ArrayList<TestBoardCell>> gameBoard = new ArrayList<ArrayList<TestBoardCell>>();
 * 
 * 
  		if(cRow - 1 >= 0) 							{ adjList.add(TestBoard.getCell(cRow-1,cColumn)); } 
		if(cColumn - 1 >= 0) 						{ adjList.add(TestBoard.getCell(cRow,cColumn-1)); }
		if(cRow + 1 <= TestBoard.BOARD_HIEGHT) 		{ adjList.add(TestBoard.getCell(cRow+1,cColumn)); }
		if(cColumn + 1 >= TestBoard.BOARD_WIDTH) 	{ adjList.add(TestBoard.getCell(cRow,cColumn+1)); }
 * 
 * 
 * 
 * 
public TestBoard() {
	int column = 0;
	while (column < BOARD_WIDTH) {
		this.gameBoard.add(populateRow(column));
		column += 1;
	}
	
			//int index1 = 0, index2 = 0;
		//gameBoard = new HashSet<TestBoardCellV2>();
		//for (TestBoardCellV2 iCell: gameBoard) {
			
			//iCell = new TestBoardCellV2();
			//gameBoard.add(iCell);
			//}
		
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
*/

/*
public TestBoardCell getCell(int row, int column) {
	return this.gameBoard.get(row).get(column);
}
*/