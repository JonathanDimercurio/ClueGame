package tests;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.ClueFileIO;

public class ClueFileIOtest {

	@Test(expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		ClueFileIO test1 = new ClueFileIO("ClueLayoutBadColumns306.csv", "ClueSetup306.txt");
		test1.loadLayoutConfig();
		test1.loadSetupConfig();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		ClueFileIO test2 = new ClueFileIO("ClueLayoutBadRoom306.csv", "ClueSetup306.txt");
		test2.setConfigFiles("ClueLayoutBadRoom306.csv", "ClueSetup306.txt");
		test2.loadSetupConfig();
		test2.loadLayoutConfig();

	}
	
	@Test
	public void testLists() throws BadConfigFormatException {
		ClueFileIO test3 = new ClueFileIO("ClueLayout.csv", "ClueSetup.txt");
		ArrayList<String[]> testList1 = new ArrayList<String[]>();
		ArrayList<String[]> testList2 = new ArrayList<String[]>();
		testList1.addAll(ClueFileIO.getFormattedLayoutFile());
		testList2.addAll(ClueFileIO.getFormattedSetupFile());
	}

}
