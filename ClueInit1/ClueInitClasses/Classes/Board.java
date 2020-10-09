package Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Board {
	
	BoardCell[][] grid;
	int numRows;
	int numColumns;
	String layoutConfigFile;
	String setupConfigFile;
	Map<Character, Room> roomMap;
	
	private void intitialize() {
		
	}
	private void loadConfigFiles() {
		
		
	}
	private void loadSetupConfig() { // scans in clue setup text file
		try(Scanner txtScanner = new Scanner (new File("ClueSetup.txt"))) {
			while(txtScanner.hasNextLine()) {
				layoutConfigFile+=(txtScanner.nextLine());
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void loadLayoutConfig() {  // scans in clue map layout csv file
		try(Scanner csvScanner = new Scanner (new File("ClueLayout.csv"))) {
			while(csvScanner.hasNextLine()) {
				layoutConfigFile+=(csvScanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Board theInstance = new Board(); 
	
	private Board(){
		super();
	}
	
	public static Board getInstance() {
		return theInstance;
	}
	
	public void initialize() {
		
	}
	
}
