package clueGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {
	public static Map<Character, Room> roomMap = new HashMap<>();
	
	private String name;
	private String type;
	private char key;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public Room(Room newRoom) {
		this.name = newRoom.getName();
		this.type = newRoom.getType();
		this.key = newRoom.getKey();
		Room.roomMap.put(this.key, this);

	}
	
	public Room(String type, String name, String key) {
		this.name = name;
		this.type = type;
		this.key = key.charAt(0);
		Room.roomMap.put(this.key, this);
	}
	
	//TODO review this thought for a constructor
	public Room (ArrayList<String[]> addRoom) {
		for(int index = 0; index < addRoom.size(); index++) {
		
			if((addRoom.get(index)[0].contains("Room")||(addRoom.get(index)[0].contains("Space")) && !(Room.roomMap.keySet().contains(addRoom.get(index)[2].charAt(0))))) {
				this.type = addRoom.get(index)[0];
				this.name = addRoom.get(index)[1];
				this.key = addRoom.get(index)[2].charAt(0);
				new Room(this);
			}
		}
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
