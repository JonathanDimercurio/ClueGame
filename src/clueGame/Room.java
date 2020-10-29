package clueGame;

public class Room {
	private String name;
	private char key;
	private BoardCell centerCell;
	private BoardCell labelCell;
		
	public Room (String addRoom) throws BadConfigFormatException{
		String[] inputStrArray = addRoom.split(", ");
		if (!inputStrArray[0].contains("Room") && !inputStrArray[0].contains("Space")) {
				throw new BadConfigFormatException("Setup File contains improper formated data, please check.       ");
		}
		new String(inputStrArray[0]);
		this.name = inputStrArray[1];
		this.key = inputStrArray[2].charAt(0);
	}
	
	public char getKey() {
		return key;
	}

	public BoardCell getLabelCell() {
		return labelCell;
	}

	public void setLabelCell(BoardCell labelCell) {
		this.labelCell = labelCell;
	}

	public void setCenterCell(BoardCell centerCell) {
		this.centerCell = centerCell;
	}

	public String getName() {
		return name;
	}R

	public BoardCell getCenterCell() {
		return centerCell;
	}

}
