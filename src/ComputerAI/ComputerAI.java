package ComputerAI;

import java.util.ArrayList;
import PlayerFiles.ComputerPlayer;
import PlayerFiles.Guess;
import PlayerFiles.Player;
import PlayerFiles.PlayerActions;
import UIResources.UIPlayerControl;
import clueGame.Card;

public class ComputerAI {
	
	Guess hasGuess;

	
	
	public ComputerAI(ComputerPlayer compPlayer) {
		

		compGenGuess(compPlayer);
				
		new MoveMeAI(compPlayer);
		
		compGenGuess(compPlayer);
		
	}

	private void compGenGuess(ComputerPlayer currentPlayer) {
		ArrayList<Card> replies = new ArrayList<Card>();
		
		if(!currentPlayer.haveSeenCell(currentPlayer
				.getCellPosition()) && (hasGuess == null)
				&& currentPlayer.getCurrentCell().ifRoomCenter()) 
		{
			
			hasGuess = new Guess(currentPlayer.makeSuggestion());
		
			for(Player eachPlayer: UIPlayerControl.playerList) {
				if(PlayerActions.checkForReply(hasGuess, eachPlayer)) {
					replies.add(PlayerActions
							.generateReply(hasGuess, eachPlayer));
				}
			}	
			currentPlayer.resolveReplies(replies);
		}
	}
}