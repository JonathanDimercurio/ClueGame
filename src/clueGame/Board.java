/* Board
 * Purpose: Board will form, generate and maintain the game board.
 * 			Skeleton version with TestBoardCell workable methods and fields.
 * 
 * @author Jonathan Dimercurio
 * @author Senya Stein 
 */

package clueGame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Board {
	
	//Member variables	
	private int numRows = 0;
	private int numColumns = 0;
	private int mapIndex = 0;	
	private BoardCell[][] gameGrid;
	private Solution theSolution;
	
	private String layoutConfigFile;
	private String setupConfigFile;
	
	//Data Structures
	private Map<Character, Room> 	roomMap = new HashMap<>();
	private ArrayList<String> 		setupF 	= new ArrayList<>();
	private ArrayList<String> 		layoutF = new ArrayList<>();
	private List<Card> 				deck 	= new Vector<>();
	private List<Player>			players = new Vector<>();
	private Set<BoardCell> 			targets;
	private Set<BoardCell> 			visited;
		
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
	
	//TODO
	//Start Set&Load ConfigFiles block
	public void setConfigFiles(String layoutInput, String setupInput) {
			this.layoutConfigFile = "data/" + layoutInput;
			this.setupConfigFile =  "data/" + setupInput;
	}
	
	public void loadConfigFiles() throws BadConfigFormatException {
			loadSetupConfig();
			loadLayoutConfig();
			generateGrid();
			generateAdjacencyList();
			assignPlayers();
			deal();
			//TODO
	}
	//End 	Set&Load ConfigFiles block
	
	
	//Start	SetupFile Init&Check block
	public void loadSetupConfig() throws BadConfigFormatException {
		File layoutInput = new File(setupConfigFile);
		try(BufferedReader scanIt = new BufferedReader(new FileReader(layoutInput))){
			while( scanIt.ready()) {
				String line = scanIt.readLine();
				this.setupF.add(line);		
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		setupF.remove(null);
		initGameAssets(setupF);
	}
	
	//perhaps check formatting here and direct strings to correct places0o
	
	/* initGameAssets(ArrayList<String>)
	 * Purpose:	initSetupConfig takes an ArrayList of Strings, a line from the
	 * 			SetupConfig file, line by line, then checks for commenting inside the file.
	 * 			Finally it passes the string to constructDeck, then adds rooms objects to 
	 * 			roomMap. 
	 */
	public void initGameAssets(ArrayList<String> checkSetup) throws BadConfigFormatException{
		for (String temp1: checkSetup) {
			if (!temp1.startsWith("//")) {
				constructDecksByCardType(temp1);
				if (temp1.startsWith("Room") || temp1.startsWith("Space")) {
					Room addRoom = new Room(temp1);
					roomMap.put(addRoom.getKey(), addRoom);
				}				
			}
		}
	}
	//End 	SetupFile Init&Check block
	
	
	//Start LayoutFile Init&Check block
	public void loadLayoutConfig() throws BadConfigFormatException {
		File layoutInput = new File(layoutConfigFile);
		try(BufferedReader scanIt = new BufferedReader(new FileReader(layoutInput))){	
			while( scanIt.ready()) {
				String line = scanIt.readLine();
				this.layoutF.add(line);		
			}
		} catch (IOException e1) {
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
			roomMap.get(cellWithSP.getIntial())
			.getCenterCell()
			.addToAdjList(roomMap.get(cellWithSP.getSecretPassage())
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
	public void calcTargets(BoardCell startCell, int pathL) { //path length, how many space to move
		targets = new HashSet<>();
		visited = new HashSet<>();
		visited.add(startCell);
		depthFirstSearch(startCell, pathL);
		targets.remove(startCell);
	}
	
	//Recursive Function
	public void depthFirstSearch(BoardCell start, int path) {
		for (BoardCell node: start.getAdjList()){
			if((!visited.contains(node) && !node.isOccupied()) || node.isRoomCenter()) {
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
	
	
	//Start Deck methods
	private void constructDecksByCardType(String addCardType) {
		String[] spliter = new String[4];
		spliter = addCardType.split(", ");
		if (!spliter[0].equals("Space")) {
			Card tempCard = new Card(spliter[0],spliter[1],spliter[2]);
			this.deck.add(tempCard);
		}
	}
		
	private void deal() {
		List<Card> theDeck = new Vector<Card>();
		theDeck.addAll(combineAllDecks());
		dealDeck(theDeck);
	}
	
	private void dealDeck(List<Card> dealingDeck) {
		while(!dealingDeck.isEmpty()) {
			int i = 0;
			dealCard(i, dealingDeck);
		}
	}

	/* 
	 * 
	 */
	private void dealCard(int count, List<Card> Deck) {
		players.get(count).updateHand(Deck.get(count++));
		if (count < 7) { dealCard(count, Deck); }
		Deck.remove(0);	
	}

	/* comineAllDecks() ~ Dependencies:	Calls:
	 * Purpose: 
	 *	TODO
	 */
	private List<Card> combineAllDecks() {
		ArrayList<Vector<Card>> individualDecks = new ArrayList<Vector<Card>>();
		individualDecks.add(new Vector<Card>(Card.getTotalRooms()));
		individualDecks.add(new Vector<Card>(Card.getTotalPeople()));
		individualDecks.add(new Vector<Card>(Card.getTotalWeapons()));
	
		for (Vector<Card> eachDeck: individualDecks) {
			Collections.shuffle(eachDeck);
		}
		return generateSolution(individualDecks);
	}
	
	/* generateSolution() ~ Dependencies: Calls: 
	 * Purpose: This method will establish the winning combination of cards.
	 * 			After doing so, it removes them from the List, and returns
	 * 			a modified List of cards.
	 */
	@SuppressWarnings("unlikely-arg-type")
	private List<Card> generateSolution(ArrayList<Vector<Card>> allDecks) {
		this.theSolution = new Solution(allDecks.get(0).get(0), allDecks.get(1).get(0), allDecks.get(2).get(0));
		for (int i = 0; i<3; i++) { allDecks.get(i).remove(0); }
		return shuffleDecksTogether(allDecks);
	}
	
	/* shuffleDeck()
	 * Purpose:	Here we shuffle all of the elements of individualDecks together
	 * 			after having removed the member variables that make up solution.
	 * 			We return a HashSet of cards to be deal to players.
	 */
	private List<Card> shuffleDecksTogether(ArrayList<Vector<Card>> decksByType) {
		List<Card> combinedDeck = new Vector<Card>();
		for (Vector<Card> tempDeck: decksByType) {
				combinedDeck.addAll(tempDeck);
			}
		Collections.shuffle(combinedDeck);
		return combinedDeck;
	}
	//End Deck methods
	
	
	//Begin	Player Block
	/* assignPlayers()
	 * Purpose:	This method takes the total of people
	 */
	private void assignPlayers() {
		 ArrayList<Card> undealtPeople = new ArrayList<Card>(Card.getTotalPeople());
		 for (Card tempCard: undealtPeople) {
			 if (checkForHumanPlayer(tempCard)) {
				 players.add(new HumanPlayer(tempCard.getCardName(), tempCard.getCardSymbol()));
			 } else {
				 players.add(new ComputerPlayer(tempCard.getCardName(), tempCard.getCardSymbol()));
			 }
		 }
	}

	/* checkForHumanPlayer()
	 * Purpose:	Checks for HumanPlayer.choice so we can determine
	 * 			which person the human player wants to be.
	 */
	private boolean checkForHumanPlayer(Card checkCard) {
		if (checkCard.getCardSymbol().contains(HumanPlayer.getChoice())) {
			return true;
		 } else {
			 return false;
		 }
	}
	//End	Player Block	
	
	//Begin Actions
	/* accusation() ~ Dependencies: <Solution> theSolution ~ Calls: theSolution.getters;
	 * Purpose: This method will use 3 Card names, and check for the
	 * 			winning accusation. Returns boolean.
	 */
	@SuppressWarnings("unused")
	public boolean accusation(String accusedPerson, String accusedRoom, String accusedWeapon) {
		if (theSolution.getGoalPerson().getCardName() == accusedPerson &&
			theSolution.getGoalRoom().getCardName()	== accusedRoom	&&
			theSolution.getGoalWeapon().getCardName() == accusedWeapon )
		{ return true; } else { return false; }
	}
	

	/* createSuggestionList() ~ Dependencies: ~ Calls:
	 * 
	 */
	public void makeSuggestion(List<Card> suggestionList) {
		
	}
	
	/* handleSuggestion() ~ Dependencies: <Player> players ~ Calls: players.getters; 
	 * Purpose:
	 */
	public void handleSuggestion(List<Card> suggestList) {
//		
	}
	//End	Actions
	
	
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
	
	public List<Player> getPlayers() {
		return players;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public boolean checkForSolution() {
		if (this.theSolution == null) {
			return false;
		} else {
			return true;
		}
	}

	
	public void setLayoutConfigFile(String layoutConfigFile) {
		this.layoutConfigFile = layoutConfigFile;
	}

	
	public void setSetupConfigFile(String setupConfigFile) {
		this.setupConfigFile = setupConfigFile;
	}

	
	public List<Card> getTheSolution() {
		return theSolution.getSolution();
	}
	
}