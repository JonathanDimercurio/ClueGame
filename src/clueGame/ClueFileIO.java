		package clueGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ClueFileIO {

	boolean choice;
	private static File layoutInputFile;
	private static File setupInputFile;
	
	private static ArrayList<String[]> formattedSetupFile = new ArrayList<String[]>();
	private static ArrayList<String[]> formattedLayoutFile = new ArrayList<String[]>();
	
	
	private static ClueFileIO theGameFiles = new ClueFileIO();
	
	private ClueFileIO() {
	}
	
	public static ClueFileIO getTheGameFiles() {
		return ClueFileIO.theGameFiles;
	}
	
	public ClueFileIO(String layoutInputFile, String setupInputFile) throws BadConfigFormatException {
		setConfigFiles(layoutInputFile, setupInputFile);
		loadSetupConfig();
		loadLayoutConfig();
		
	}
	
	/* setConfigFiles() ~ required **********
	 * needs to accept this format
	 * board.setConfigFiles("ClueLayout306.csv", "ClueSetup306.txt");
	 */ 
	public void setConfigFiles(String layoutInput, String setupInput) {
		 ClueFileIO.layoutInputFile = new File("data/", layoutInput); 
		  ClueFileIO.setupInputFile = new File("data/", setupInput); 
	}
	
	/* loadSetupConfig() ~ required **********
	 * Our version will reserve space for an temporary ArrayList,
	 * then call custom methods to convert each line of the SetupFile
	 * into individual Strings, using ", " string as the separator.
	 */
	public void loadSetupConfig() throws BadConfigFormatException {
		this.choice = true;
		ClueFileIO.formattedSetupFile.addAll(configFileMule(ClueFileIO.setupInputFile));
	}
		
	/* loadLayoutConfig() ~ required ***********
	 * 
	 */
	public void loadLayoutConfig() throws BadConfigFormatException { 
		this.choice = false;
		ClueFileIO.formattedLayoutFile.addAll(configFileMule(ClueFileIO.layoutInputFile));
		badRoomChecker();
	}
	
	/*
	 * 
	 */
	private ArrayList<String[]> configFileMule(File fileInput) throws BadConfigFormatException {
		ArrayList<String> unformattedFile = new ArrayList<>();
		unformattedFile.addAll(scanConfigFile(fileInput));
		ArrayList<String[]> fileOutput = new ArrayList<String[]>();
		fileOutput.addAll(formatConfigFile(unformattedFile));
		return fileOutput;
	}
	
	/* checkSetupFormat(..) ~ This method is called inside loadSetupConfig()
	 * Prior versions executed this check within the constructor for Room.
	 * We felt that was bad programming practice. Here we'll check for known
	 * asset prefixes against the provided ClueSetup.txt
	 */
	private ArrayList<String[]> formatConfigFile(ArrayList<String> unformatedStringsList) {
		ArrayList<String[]> formatedArrayList = new ArrayList<String[]>();
		int index1 = 0;
		for(String unformatedString: unformatedStringsList) {
			if(!unformatedString.startsWith("//")) {
				String[] tempArray = unformatedString.split(",");
				removeLeadingSpaces(tempArray);
				formatedArrayList.add(index1++, tempArray);
			}
		}
		return formatedArrayList;
	}

	private String[] removeLeadingSpaces(String[] tempArray) {
		for(int i = 0; i < tempArray.length; i++) {
			tempArray[i] = tempArray[i].trim();
		}
		return tempArray;
	}

	/* scanConfigFile(...) ~ generalized config file scanner 
	 * Originally this code was repeated. Now we are generalizing it
	 * so it only appears once in our code. This method will convert all
	 * the config files into a List format.
	 */
	public ArrayList<String> scanConfigFile(File scanThisFile) throws BadConfigFormatException {
		ArrayList<String> outputList = new ArrayList<String>();
		try(BufferedReader scanIt = new BufferedReader(new FileReader(scanThisFile))){
			while( scanIt.ready()) {
				String line = scanIt.readLine();
				outputList.add(line);
				if(choice) { checkSetupString(line); }
				else	{ checkLayoutString(line); }
			}
		scanIt.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		outputList.remove(null);
		return outputList;
	}
	
	private void checkLayoutString(String uncheckString) throws BadConfigFormatException {
		if (uncheckString.contains(",,")) {
			throw new BadConfigFormatException("ClueLayout.csv contains improper formated data, please check.       "); }
	}
	
	private void checkSetupString(String checkSetupFileString) throws BadConfigFormatException {
		if(checkSetupFileString.startsWith("Rom")) { throw new BadConfigFormatException("ClueSetup.txt contains improper formated data, please check.       "); }
		}
	
	/* badroomChecker() ~ 
	 * Using two HashSets, we can determine if the layoutFile
	 * contains any corrupt data by insuring that the CharKeys
	 * are defined from the SetupConfigFile.
	 */
	private void badRoomChecker() throws BadConfigFormatException {
		Set<Character> knownCharKeys = new HashSet<Character>();
		Set<Character> unknownCharKeys = new HashSet<Character>();
		for(int i = 0; i < ClueFileIO.formattedSetupFile.size(); i++ ) {
			if (ClueFileIO.formattedSetupFile.get(i)[0].startsWith("Room") || ClueFileIO.formattedSetupFile.get(i)[0].startsWith("Space"))
			knownCharKeys.add(ClueFileIO.formattedSetupFile.get(i)[2].charAt(0));
		}
		for(String[] tempLists: ClueFileIO.formattedLayoutFile) {
			for(String checkMe: tempLists) {
				unknownCharKeys.add(checkMe.charAt(0));
			}
		}
		for (char checkChar : unknownCharKeys ) {
			if (!knownCharKeys.contains(checkChar)) {
			throw new BadConfigFormatException("ClueLayout.csv contains improper room data, please check.       ");
		} }
	}
	
	//Generic Getters
	public static ArrayList<String[]> getFormattedSetupFile() {
		return formattedSetupFile;
	}

	public static ArrayList<String[]> getFormattedLayoutFile() {
		return formattedLayoutFile;
	}
}
