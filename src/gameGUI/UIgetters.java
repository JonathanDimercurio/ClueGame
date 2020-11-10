package gameGUI;

import java.awt.Color;
import java.util.List;

import clueGame.*;

public interface UIgetters {
	
	
	public static Player getCurrentPlayer(int indexPlayer) {
		int i = indexPlayer % Player.players.size();
		return Player.players.get(i);
	}
	
	public static List<Card> genAIGuess(String currentRoom, Player currentPlayer) {
		ComputerPlayer currentComputerPlayer = null;
		for (ComputerPlayer checkPlayer: ComputerPlayer.computerPlayerList) {
			if (checkPlayer.getName().contains(currentPlayer.getName())) {
				currentComputerPlayer = checkPlayer;
			}
		}
		return currentComputerPlayer.guessLogic.generateGuess(currentRoom);
	}
	
	
}