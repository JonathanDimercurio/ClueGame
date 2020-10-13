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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;
public class Board {
	
	//Member variables
	private int numRows = 25, numColumns = 25;
	private String layoutConfigFile, setupConfigFile;
	
	private String test1layout;
	
	//Data Structures
	private Map<Character, Room> roomMap = new HashMap<Character, Room>();
	private BoardCell[][] gameGrid = new BoardCell[25][25];
	
	ArrayList<String> setupF = new ArrayList<String>();
	ArrayList<String> layoutF = new ArrayList<String>();
	
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
		
	//FileReader reader;
	private Scanner inputFile;
	private String fileInput;
		
	//Singleton Pattern
	private static Board theInstance = new Board();
	
	private Board() {
		genGrid();
		genAdj();
	}
	
	public void initialize() {
	}
	public static Board getInstance() {
        return theInstance;
	}
	
	//*****************
	
	public void loadConfigFiles() throws BadConfigFormatException {
			//loadSetupConfig();
			//loadLayoutConfig();
	}
		

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
		checkFormatLayout(setupF);
		
		}
	
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
	
	private void checkFormatLayout(ArrayList<String> checkFile) throws BadConfigFormatException {
		for (String tempF: checkFile) {
				String[] arrOfStr = tempF.split(",", 50);
				for (String tempS : arrOfStr) {
					if (tempS.startsWith(" ")) {tempS = tempS.substring(1); }
					if (tempS == "") { throw  new BadConfigFormatException(); }
					if (tempS == "Y" ) { throw  new BadConfigFormatException(); }
				}
			}
		}

		
	
	public void setConfigFiles(String layoutInput, String setupInput)  throws BadConfigFormatException  {
		this.layoutConfigFile = "data/" + layoutInput;
		this.setupConfigFile =  "data/" + setupInput;
		this.loadConfigFiles();
	}

	public String getLayoutConfigFile() {
		return layoutConfigFile;
	}
	public String getSetupConfigFile() {
		return setupConfigFile;
	}
	public void setSetupConfigFile(String setupConfigFile) {
		this.setupConfigFile = setupConfigFile;
	}
	public Scanner getInputFile() {
		return inputFile;
	}
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
				if(path == 1) {
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
	//Solid. Working well
	private void genGrid() {
		int cRow = 0, cColumn = 0;
		while (cColumn != numColumns) {
			if (cColumn < numColumns) {
				while (cRow != numRows) {
					gameGrid[cColumn][cRow] = new BoardCell(cColumn, cRow);
					cRow += 1;
				}
			if (cColumn != numColumns) {
				cRow = 0;
				}
			}
			cColumn += 1;
		}
	}
	//Generates the adjLists inside gameGrid
	private void genAdj() {
		int cRow = 0, cColumn = 0;
		while (cColumn != numColumns) {
			if (cColumn < numColumns) {
				while (cRow != numRows) {
					gameGrid[cColumn][cRow].setAdjList(checkAdjList(cColumn, cRow));
					cRow += 1;
				}
			if (cColumn != numColumns) {
				cRow = 0;
				}
			}
			cColumn += 1;
		}
	}
	//Solid. Works well.
	private Set<BoardCell> checkAdjList(int x, int y) {
		Set<BoardCell> tempAdjList = new HashSet<BoardCell>();
		if((x - 1) >= 0) 								{ tempAdjList.add(getCell(x-1,y)); } 
		if((y - 1) >= 0) 								{ tempAdjList.add(getCell(x,y-1)); }
		if((x + 1) <= numColumns - 1) 		{ tempAdjList.add(getCell(x+1,y)); }
		if((y + 1) <= numRows - 1) 		{ tempAdjList.add(getCell(x,y+1)); }
		return tempAdjList;
	}
	
	public BoardCell getCell(int x, int y) {
		BoardCell tempCell = gameGrid[x][y];
		return tempCell;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}
	
	public Map<Character, Room> getRoomMap() {
		return roomMap;
	}
	public Room getRoom(BoardCell c) {
		// TODO Auto-generated method stub
		return null;
	}

	public Room getRoom(char c) {
		// TODO Auto-generated method stub
		Room a = new Room();
		return a;
	}

}