/* Board
 * Purpose: Board will form, generate and maintain the game board.
 * 			Skeleton version with TestBoardCell workable methods and fields.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein 
 */

package clueGame;
import java.util.*;

public class Board implements GameControl{
	
	//Member variables	
	private int numRows;
	private int numColumns;
	private int mapIndex;	
	private BoardCell[][] gameGrid;
	private static Solution theSolution;
	
	private Set<BoardCell> 			targets;
	private Set<BoardCell> 			visited;
		
	//Start	Singleton Pattern
	private static Board theInstance = new Board();
	private ClueFileIO theFiles = ClueFileIO.getTheGameFiles();
	
	private Board() {
	}
	
	public void initialize() {
		resetBoardValues();
		try{ 
		this.theFiles.loadLayoutConfig();
		} catch (BadConfigFormatException e) {
			new BadConfigFormatException(
					"ConfigFiles corrupt, please check for improper data.       ");
		}
		try{ 
		this.theFiles.loadSetupConfig();
		} catch (BadConfigFormatException e) {
			new BadConfigFormatException(
					"ConfigFiles corrupt, please check for improper data.       ");
		}
		
		new Room(ClueFileIO.getFormattedSetupFile());
		setBoardFields();
		cellCreator();
		generateGrid();
		generateAdjacencyList();

	}
	
	private void resetBoardValues() {
		numRows = 0;
		numColumns = 0;
		mapIndex = 0;
		if(targets != null) { targets.clear(); }
		if(visited != null) { visited.clear(); }
		BoardCell.clearCellValues();
		if(Room.roomMap != null) { Room.roomMap.clear(); }
	}

	public static Board getInstance() {
        return theInstance;
	}
	//End	Singleton Pattern
	
	//Required for 360 tests.
	public void setConfigFiles(String layoutInput, String setupInput) {
		theFiles.setConfigFiles(layoutInput, setupInput);
	}
	public void loadSetupConfig() throws BadConfigFormatException {
		theFiles.loadSetupConfig();
	}
	
	public void loadLayoutConfig() throws BadConfigFormatException {
		theFiles.loadLayoutConfig();
	}
	//Required for 360 tests.
	
	
	private void setBoardFields() {
		this.numRows = ClueFileIO.getFormattedLayoutFile().size();
		this.numColumns = ClueFileIO.getFormattedLayoutFile().get(0).length;
		this.gameGrid = new BoardCell[this.numColumns][this.numRows];
	}

	private void cellCreator() {
		ArrayList<String[]> formattedLayoutFile = new ArrayList<String[]>();
		formattedLayoutFile.addAll(ClueFileIO.getFormattedLayoutFile());
		for (String[] tempString: formattedLayoutFile) {
			for(String tinyTempString: tempString) {
				char validateCell = tinyTempString.charAt(0);
				genMapGameBoardData(tinyTempString, validateCell);
			}
		}
	}
		
	//Start	Grid&Adj block
	private void genMapGameBoardData(String gbCell, char validatedCell) {
		BoardCell.mapGameBoardData.put(
				this.mapIndex, new BoardCell(
						gbCell, validatedCell, getRoom(validatedCell)
						, mapIndex++));		
	}
	
	private void generateGrid() {
		int cRow = 0;
		int cColumn = 0;
		int count = 0;
		while (cRow < numRows) {
				while (cColumn < numColumns) {
					gameGrid[cColumn][cRow] = BoardCell.mapGameBoardData.get(count);
					adjustCellCoords(cColumn, cRow, count++);
					cColumn += 1;
				}
			if (cRow != numRows) {
				cColumn = 0;
				}
			cRow += 1;
			}	
	}

	private void adjustCellCoords(int columns, int rows, int key) {
		BoardCell.mapGameBoardData.get(key).setxCol(columns);
		BoardCell.mapGameBoardData.get(key).setyRow(rows);
	}

	private void generateAdjacencyList() {
		int cRow = 0;
		int cColumn = 0;
		while (cRow < numRows) {
				while (cColumn < numColumns) {
					BoardCell tempCell = gameGrid[cColumn][cRow];
					doorChecker(tempCell);
					secretPassageCheck(tempCell);
					if(tempCell.isWalkable()) {
						tempCell.setAdjList(checkAdjList(cColumn, cRow));
					}
					cColumn += 1;
				}
			if (cRow != numRows) {
				cColumn = 0;
				}
			cRow += 1;
			}
	}
	
	private void doorChecker (BoardCell checkDoor) {
		if (checkDoor.isDoorway()) { roomFinder(checkDoor); }
	}
	
	private void roomFinder(BoardCell cellDoor) {
		int indexer = doorToRoomLinker(cellDoor);
		char roomType = BoardCell.mapGameBoardData.get((cellDoor
				.getKey() + indexer)).getIntial();
		roomCenterFinder(cellDoor, roomType);
	}
	
	private void roomCenterFinder(BoardCell cellDorr, char roomType) {
		for (BoardCell findRoomCenter: BoardCell.roomCenters) {
			if (findRoomCenter.getIntial() == roomType) {
				cellDorr.addToAdjList(findRoomCenter.getThis());
				findRoomCenter.addToAdjList(cellDorr.getThis());
			}
		}
	}
	
	private int doorToRoomLinker(BoardCell cellDoor) {
		switch(cellDoor.getDoorDirection()) {
			case UP:
				return -(this.numColumns);
			case DOWN:
				return this.numColumns;
			case LEFT:
				return  -1;
			case RIGHT:
				return 1;
			default:
				return 0;
				
		}
	}
	
	private void secretPassageCheck(BoardCell checkCellForSP) {
		if (checkCellForSP.isSecretPassage()) {
			linkSecretPassage(checkCellForSP);			
		}
	}
	
	private void linkSecretPassage(BoardCell cellWithSP) {
			Room.roomMap.get(cellWithSP.getIntial())
			.getCenterCell()
			.addToAdjList(Room.roomMap.get(cellWithSP.getSecretPassage())
			.getCenterCell());
	}
	
	private Set<BoardCell> checkAdjList(int x, int y) {
		Set<BoardCell> tempAdjList = new HashSet<>();
		if((x - 1) >= 0 && getSmartCell(x-1,y).isWalkable()){
			 tempAdjList.add(getSmartCell(x-1,y)); 
			 } 
		if((y - 1) >= 0 && getSmartCell(x,y-1).isWalkable()) {
			tempAdjList.add(getSmartCell(x,y-1));
			}
		if((x + 1) <= numColumns - 1 && getSmartCell(x+1,y).isWalkable()) {
				tempAdjList.add(getSmartCell(x+1,y));	
			}
		if((y + 1) <= numRows - 1 && getSmartCell(x,y+1).isWalkable()){ 
			tempAdjList.add(getSmartCell(x,y+1)); 
			}
		return tempAdjList;
	}
	//End	Grid&Adj block
	
	
	//Start Pathing Algorithm Block
	public void calcTargets(BoardCell startCell, int pathL) { 
		//path length, how many space to move
		targets = new HashSet<>();
		visited = new HashSet<>();
		visited.add(startCell);
		depthFirstSearch(startCell, pathL);
		targets.remove(startCell);
	}
	
	//Recursive pathing Function
	public void depthFirstSearch(BoardCell start, int path) {
		for (BoardCell node: start.getAdjList()){
			if((!visited.contains(node) && 
					!node.isOccupied()) || node.isRoomCenter()) {
				
				visited.add(node);
				if(node.isRoomCenter() && node.isSecretPassage()) {
					targets.add(node);
					targets.add(node.secretPassageCell);
				} else if(node.isRoomCenter()) {
					targets.add(node);
				} else if(path == 1 && (!node.isOccupied())){
					targets.add(node);
				} else {
					depthFirstSearch(node, path - 1);
				}
			visited.remove(node);
			}
		}
	}
	
	public Set<BoardCell> getTargets() {
		return this.targets;
	}
	//End	Pathing Algorithm Block
	
	
	//Start getCell & smartGetCell
	public BoardCell getCell (int y, int x) {
		return gameGrid[x][y];
	}
	
	public BoardCell getSmartCell(int x, int y) {
		return gameGrid[x][y];
	}
	//End 	getCell & smartGetCell	
	
	
	//Generic Getters
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public Map<Character, Room> getRoomMap() {
		return Room.roomMap;
	}
	
	public Room getRoom(BoardCell c) {
		return c.getMyRoomType();
	}
		
	public Room getRoom(char c) {
		return Room.roomMap.get(c);
	}

	public Set<BoardCell> getAdjList(int i, int j) {
		return getCell(i,j).getAdjList();
	}

	public boolean checkForSolution() {
		if (Board.theSolution == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void setSolution(Solution inputSolution) {
		Board.theSolution = inputSolution;
	}
}