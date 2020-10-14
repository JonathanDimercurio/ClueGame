/**
 * Board
 * 
 * Purpose: Board will form, generate and maintain the game board.
 * 			Skeleton version with TestBoardCell workable methods and fields.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein
 * 
 */

package clueGame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Board {
	
	//Member variables	
	private int numRows, numColumns, mapIndex = 0;	
	private BoardCell[][] gameGrid;
	
	private String layoutConfigFile, setupConfigFile;
	
	//Data Structures
	private Map<Character, Room> roomMap = new HashMap<Character, Room>();
	ArrayList<String> setupF = new ArrayList<String>();
	ArrayList<String> layoutF = new ArrayList<String>();
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
		
	//Start	Singleton Pattern
	private static Board theInstance = new Board();
	
	private Board() {
	}
	
	public void initialize() {
		try {
			this.loadConfigFiles();
		} catch (BadConfigFormatException e) {
			
		}
	}
	
	public static Board getInstance() {
        return theInstance;
	}
	//End	Singleton Pattern
	
	
	//Start Set&Load ConfigFiles block
	public void setConfigFiles(String layoutInput, String setupInput) {
			this.layoutConfigFile = "data/" + layoutInput;
			this.setupConfigFile =  "data/" + setupInput;
	}
	
	public void loadConfigFiles() throws BadConfigFormatException {
			loadSetupConfig();
			loadLayoutConfig();
			genGrid();
			genAdj();
	}
	//End 	Set&Load ConfigFiles block
	
	
	//Start	SetupFile Init&Check block
	public void loadSetupConfig() throws BadConfigFormatException {
		BufferedReader scanIt;
		try {
			File layoutInput = new File(setupConfigFile);
			scanIt = new BufferedReader(new FileReader(layoutInput));
			while( scanIt.ready()) {
				String line = scanIt.readLine();
				this.setupF.add(line);				
			}
			scanIt.close();
			} catch  (IOException e1) {
				e1.printStackTrace();
			}
		setupF.remove(null);	
		initSetupConfig(setupF);
	}
	
	public void initSetupConfig(ArrayList<String> checkSetup) throws BadConfigFormatException{
		for (String temp1: checkSetup) {
			if (!temp1.startsWith("//")) {
				Room addRoom = new Room(temp1);
				roomMap.put(addRoom.getKey(), addRoom);
			}
		}
	}
	//End 	SetupFile Init&Check block
	
	
	//Start LayoutFile Init&Check block
	public void loadLayoutConfig() throws BadConfigFormatException {
		BufferedReader scanIt;
		try {
			File layoutInput = new File(layoutConfigFile);
			scanIt = new BufferedReader(new FileReader(layoutInput));
			while( scanIt.ready()) {
				String line = scanIt.readLine();
				this.layoutF.add(line);		
			}
			scanIt.close();
			} catch  (IOException e1) {
				e1.printStackTrace();
			}
		layoutF.remove(null);
		checkFormatLayout(layoutF);
		}
	
	private void checkFormatLayout(ArrayList<String> checkLayoutFullTable) throws BadConfigFormatException {
		numRows = checkLayoutFullTable.size();
		this.mapIndex = 0;
		String[] temp1 = checkLayoutFullTable.get(0).split(",");
		this.numColumns = temp1.length;
		this.gameGrid = new BoardCell[this.numColumns][this.numRows];
		for (String layoutRows: checkLayoutFullTable) {
			String[] boardCellArray = layoutRows.split(",");
			if (this.numColumns != boardCellArray.length) { throw new BadConfigFormatException("Columns are not in correct format, please check layoutConfigFile.     "); }
			for (String boardCellArrayIndex : boardCellArray) {
					cellValidator(boardCellArrayIndex);
			}
		}
	}

	private void cellValidator(String gbCell) throws BadConfigFormatException {
		char validateCell = gbCell.charAt(0);
		checkForRoom(validateCell);
		genMapGameBoardData(gbCell, validateCell);
	}
	
	public void checkForRoom(char c) throws BadConfigFormatException {
		if (!roomMap.containsKey(c)){
			throw new BadConfigFormatException ("Room is not valid, please check " + this.getSetupConfigFile() + "      ");		
		} 
	}
	//End 	LayoutFile Init&Check block
	
		
	//Start	Grid&Adj block
	private void genMapGameBoardData(String gbCell, char validatedCell) {
		BoardCell.mapGameBoardData.put(this.mapIndex, new BoardCell(gbCell, validatedCell, getRoom(validatedCell), mapIndex++));		
	}
	
	private void genGrid() {
		int cRow = 0, cColumn = 0, count = 0;
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

	private void adjustCellCoords(int x, int y, int key) {
		BoardCell.mapGameBoardData.get(key).setxCol(x);
		BoardCell.mapGameBoardData.get(key).setyRow(y);
	}

	private void genAdj() {
		int cRow = 0, cColumn = 0;
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
	
	void doorChecker (BoardCell checkDoor) {
		if (checkDoor.isDoorway()) {
			roomFinder(checkDoor);
		}
	}
	
	private void roomFinder(BoardCell cellDoor) {
		int indexer = doorToRoomLinker(cellDoor);
		char roomType = BoardCell.mapGameBoardData.get((cellDoor.getKey() + indexer)).getIntial();
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
				return -(this.numRows - 1);
			case DOWN:
				return this.numRows + 1;
			case LEFT:
				return  -1;
			case RIGHT:
				return 1;
			default:
				return 0;
				
		}
	}
	
	void secretPassageCheck(BoardCell checkCellForSP) {
		if (checkCellForSP.isSecretPassage()) {
			linkSecretPassage(checkCellForSP);			
		}
	}
	
	private void linkSecretPassage(BoardCell cellWithSP) {
			for (BoardCell linkRoomCenter: BoardCell.roomCenters) {
				if (cellWithSP.getSecretPassage() == linkRoomCenter.getIntial()) {
					cellWithSP.secretPassageCell =  linkRoomCenter;
				}
			}
			for (BoardCell linkRoomCenter: BoardCell.roomCenters) {
				if (linkRoomCenter.getIntial() == linkRoomCenter.getIntial()) {
				linkRoomCenter.addToAdjList(linkRoomCenter.secretPassageCell);
				}
			}	
	}

	Set<BoardCell> checkAdjList(int x, int y) {
		Set<BoardCell> tempAdjList = new HashSet<BoardCell>();
		if((x - 1) >= 0) {
			if (getSmartCell(x-1,y).isWalkable()){
			 tempAdjList.add(getSmartCell(x-1,y)); 
			 } 
		}
		if((y - 1) >= 0) {
			if (getSmartCell(x,y-1).isWalkable()) {
			tempAdjList.add(getSmartCell(x,y-1));
			}
		}
		if((x + 1) <= numColumns - 1) {
			if(getSmartCell(x+1,y).isWalkable()) {
				tempAdjList.add(getSmartCell(x+1,y));	
			}
		}
		if((y + 1) <= numRows - 1) {
			if(getSmartCell(x,y+1).isWalkable()){ 
			tempAdjList.add(getSmartCell(x,y+1)); 
			}
		}
		return tempAdjList;
	}
	//End	Grid&Adj block
	
	
	//Start Pathing Algorithm Block
	public void calcTargets(BoardCell startCell, int pathL) {
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		visited.add(startCell);
		dfs(startCell, pathL);
		targets.remove(startCell);
	}
	
	public void dfs(BoardCell start, int path) {
		for (BoardCell node: start.getAdjList()){
			if(!visited.contains(node)) {
				visited.add(node);
				if(node.isRoomCenter() && node.isSecretPassage()) {
					targets.add(node);
					targets.add(node.secretPassageCell);
				} else if(node.isRoomCenter()) {
					targets.add(node);
				} else if(path == 1) {
					if (!node.isOccupied())
					targets.add(node);
				} else {
					dfs(node, path - 1);
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
		BoardCell tempCell = gameGrid[x][y];
		return tempCell;
	}
	
	public BoardCell getSmartCell(int x, int y) {
		BoardCell tempCell = gameGrid[x][y];
		return tempCell;
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
		return roomMap;
	}
	
	public Room getRoom(BoardCell c) {
		return c.getMyRoomType();
	}
		
	public String getLayoutConfigFile() {
		return layoutConfigFile;
	}

	public String getSetupConfigFile() {
		return setupConfigFile;
	}

	public Room getRoom(char c) {
		return roomMap.get(c);
	}

	public Set<BoardCell> getAdjList(int i, int j) {
		return getCell(i,j).getAdjList();
	}

}