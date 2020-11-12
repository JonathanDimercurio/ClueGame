package clueGame;

import java.util.HashMap;
import java.util.Map;

public class Room {
	public static Map<Character, Room> roomMap = new HashMap<>();
	
	private String name;
	private String type;
	private char key;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	
	
	public Room (String[] addRoom) {
		if(addRoom[0].contains("Room"))
		this.type = addRoom[0];
		this.name = addRoom[1];
		this.key = addRoom[2].charAt(0);
		Room.roomMap.put(this.key, this);
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
	
	public String getRoomName() {
		return name;
	}

	public String getType() {
		return this.type;
	}

	public BoardCell getCenterCell() {
		return centerCell;
	}

}
