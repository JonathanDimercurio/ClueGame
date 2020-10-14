package clueGame;

public class Room {
	private String name;
	private String type;
	private char key;
	private BoardCell centerCell;
	private BoardCell labelCell;
		
	public Room (String addRoom) throws BadConfigFormatException{
		String[] inputStrArray = addRoom.split(", ");
		if (!inputStrArray[0].contains("Room"))  {
			if (!inputStrArray[0].contains("Space")) {
				throw new BadConfigFormatException("Setup File contains improper formated data, please check.       ");
			}
		}
		this.type = new String(inputStrArray[0]);
		this.name = new String(inputStrArray[1]);
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
	}

	public BoardCell getCenterCell() {
		return centerCell;
	}

}
