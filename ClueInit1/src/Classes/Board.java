package Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Board {
	
	//public static Board theInstance = new Board(); 
	
	BoardCell[][] grid;
	String[][] boardStats;
	int numRows;
	int numColumns;
	String layoutConfigFile;
	String setupConfigFile;
	Map<Character, Room> roomMap;
	
	//FileReader reader;
	Scanner inputFile;
	String csvFileInput = "/ClueInit1/ClueInitFiles/data/ClueSetup.txt";
	
	private void intitialize() {
		
	}
	
	public void loadConfigFiles(String fileInput) throws FileNotFoundException {
		inputFile = new Scanner (new File(csvFileInput));
	}
		
	private void loadSetupConfig() { 
		
		
	}
	private void loadLayoutConfig() {  
		
	}
	
	
	
	public Board() throws FileNotFoundException{
		super();
		this.loadConfigFiles(csvFileInput);
	}
	
	/*
	public static Board getInstance() {
		return theInstance;
	}
	*/
	
	public void initialize() {
		
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
	

