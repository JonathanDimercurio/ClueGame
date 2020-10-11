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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
public class Board {
	
	//public static Board theInstance = new Board(); 
	
	private BoardCell[][] gameGrid;
	private String[][] boardStats;
	private int numRows, numColumns;
	private Map<Character, Room> roomMap;
	
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	
	private String layoutConfigFile, setupConfigFile;
	
	//FileReader reader;
	private Scanner inputFile;
	private String csvFileInput = "/ClueInit1/ClueInitFiles/data/ClueSetup.txt";
		
	//Singleton Pattern
	private static Board theInstance = new Board();
	
	private Board() {
		super();
	}
	
	public void initialize() {
		
	}
		
	//*****************
	
	public void loadConfigFiles(String newInput) throws FileNotFoundException {
	}
		
	private void loadSetupConfig(String newInput) throws FileNotFoundException { 
	}
	
	private void loadLayoutConfig(String newInput) throws FileNotFoundException {  
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
	
	

}



//	private void loadConfigFiles() {
//		try(Scanner txtScanner = new Scanner (new File("ClueSetup.txt"))) {
//			//while(txtScanner.hasNextLine()) {
//				//layoutConfigFile+=(txtScanner.nextLine());
//			//}
//		
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try(Scanner csvScanner = new Scanner (new File("ClueLayout.csv"))) {
//			//while(csvScanner.hasNextLine()) {
//				//layoutConfigFile+=(csvScanner.nextLine());
//			//}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
/*

*/


