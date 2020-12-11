package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Listeners.TarListener;
import PlayerFiles.Player;
import UIResources.ImagePanelComponent;
import UIResources.UIPlayerControl;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;



public class gridUI {
	
	
	private static Image bgImage = 
			new ImagePanelComponent("resources/bgArt.png").getImage();
	
	static ImageIcon targetIcon = new ImageIcon("resources/tar11.gif");
 
	
	private final static Dimension SIZE =  new Dimension(702,572);
	final static int boardRows	= Board.getInstance().getNumRows();
	final static int boardColumns = Board.getInstance().getNumColumns();
	
	public static ArrayList<Integer> highlightList = new ArrayList<Integer>();
	
	private static Map<Integer, JLabel>	cells = 
			new HashMap<Integer, JLabel>();
	
	private static Map<Integer, JLabel>	highlights = 
			new HashMap<Integer, JLabel>();	
	
	private static Map<Integer, DoorDirection> doors = 
			new HashMap<Integer, DoorDirection>();	
	
	@SuppressWarnings("exports")
	public static JLayeredPane createAndShowUI() {
				
		JLayeredPane bgPane = new JLayeredPane();
		bgPane.setMaximumSize(SIZE);
		bgPane.setMinimumSize(SIZE);
		bgPane.setBackground(Color.DARK_GRAY);
		bgPane.add(highLightGrid());
		bgPane.add(walkableGrid());
		bgPane.add(setBGImage());
		bgPane.setOpaque(true);
		bgPane.setVisible(true);
		return bgPane;
	}
	
	private static JPanel walkableGrid() {

		JPanel walkP = new JPanel(new GridLayout(boardRows, boardColumns));
		walkP.setMaximumSize(SIZE);
		walkP.setMinimumSize(SIZE);		
		GridBagConstraints gridC = new GridBagConstraints(); 
		gridC.anchor = GridBagConstraints.NORTHWEST;
		int mapIndex = 0;		
		for(int i = 0 ; i < boardRows; i++ ){
			for(int p = 0 ; p < boardColumns; p++) {
				BoardCell tempCell = BoardCell
						.mapGameBoardData.get(mapIndex);
				
				JLabel test1 = createColoredLabel("",new Color(0,0,0,20));
				
				if (tempCell.isWalkable()) {
					test1.setBorder(BorderFactory
							.createLineBorder(new Color(0,0,0,20), 2));
				}
				if (tempCell.isDoorway()) {
					doors.put(resolveDoor(tempCell.getDoorDirection(), 
							mapIndex), tempCell.getDoorDirection());
				}
				cells.put(mapIndex++, test1);
				walkP.add(test1, gridC);
			}
		}
		constructDoorMarkers();	
		showPlayers();
		walkP.setOpaque(false);
		walkP.setVisible(true);
		walkP.setBounds(0, 0, 702, 572);
		return walkP;
	}
	
	private static JPanel highLightGrid() {

		JPanel walkP = new JPanel(new GridLayout(boardRows, boardColumns));
		walkP.setMaximumSize(SIZE);
		walkP.setMinimumSize(SIZE);		
		GridBagConstraints gridC = new GridBagConstraints(); 
		gridC.anchor = GridBagConstraints.NORTHWEST;
		int mapIndex = 0;		
		for(int i = 0 ; i < boardRows; i++ ){
			for(int p = 0 ; p < boardColumns; p++) {
				
				JLabel test1 = createHighlight();
				test1.setIcon(targetIcon);
				test1.setVisible(false);
				highlights.put(mapIndex++, test1);
				walkP.add(test1, gridC);

			}
		}

		walkP.setOpaque(false);
		walkP.setVisible(true);
		walkP.setBounds(0, 0, 702, 572);
		return walkP;
	}
	
	private static void constructDoorMarkers() {
		doors.keySet().stream().forEach(cell->{
			switch (doors.get(cell)) {
				case UP:
					cells.get(cell).setBorder(BorderFactory
							.createMatteBorder(0,0,4,0,Color.BLUE));
					break;
				case DOWN:
					cells.get(cell).setBorder(BorderFactory
							.createMatteBorder(4,0,0,0,Color.BLUE));
					break;
				case LEFT:
					cells.get(cell).setBorder(BorderFactory
							.createMatteBorder(0,0,0,4,Color.BLUE));
					break;
				case RIGHT:
					cells.get(cell).setBorder(BorderFactory
							.createMatteBorder(0,4,0,0,Color.BLUE));
					break;
				default:
					cells.get(cell).setBorder(BorderFactory
							.createMatteBorder(0,0,0,0,Color.BLUE));
			}	
		});
	}
	
	private static JLabel setBGImage() {
		ImageIcon bgIcon = new ImageIcon(bgImage);
		JLabel bgPanel = new JLabel(bgIcon);
		bgPanel.setMaximumSize(SIZE);
		bgPanel.setMinimumSize(SIZE);		
		bgPanel.setOpaque(true);
		bgPanel.setVisible(true);
		bgPanel.setBounds(0, 0, 702, 572);
		return bgPanel;
	}
	
	private static void showPlayers() {	
		UIPlayerControl.playerList.stream().forEach(player->{
				player.getCellPosition().setOccupied(true);
			  cells.get(player.getCellPosition().getKey())
			  	.setIcon(new ImageIcon(player.getSmallIcon()));
			  cells.get(player.getCellPosition().getKey())
			  	.setBackground(player.getColor());
			  cells.get(player.getCellPosition().getKey())
			  	.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			  	
		});
	}
	
	public static void removePlayerIcon(@SuppressWarnings("exports") 
										Player currentPlayer) {
		
		if(currentPlayer.getCellPosition().isRoomCenter() ) {
			int oS = currentPlayer.roomCenterOffset();
		cells.get(currentPlayer.getCellPosition().getKey() + oS)
			.setIcon(null);
		cells.get(currentPlayer.getCellPosition().getKey() + oS)
			.setBackground(new Color(0,0,0,20));
		cells.get(currentPlayer.getCellPosition().getKey() + oS)
			.setOpaque(false);
		cells.get(currentPlayer.getCellPosition().getKey() + oS)
		  	.setBorder(BorderFactory.createLineBorder(
		  			new Color(0,0,0,20), 2));

			
		} else {

		
		cells.get(currentPlayer.getCellPosition().getKey())
			.setIcon(null);
		cells.get(currentPlayer.getCellPosition().getKey())
		  	.setBackground(new Color(0,0,0,20));
		cells.get(currentPlayer.getCellPosition().getKey())
		 	.setOpaque(false);
		cells.get(currentPlayer.getCellPosition().getKey())
		  	.setBorder(BorderFactory.createLineBorder(
		  			new Color(0,0,0,20), 2));
		}
	}
	
	@SuppressWarnings("exports")
	public static void addPlayerIcon(Player currentPlayer) {
		if(currentPlayer.getCellPosition().isRoomCenter() ) {
			int oS = currentPlayer.roomCenterOffset();
		cells.get(currentPlayer.getCellPosition().getKey() + oS)
	 		.setIcon(new ImageIcon(currentPlayer.getSmallIcon()));
		cells.get(currentPlayer.getCellPosition().getKey() + oS)
		  	.setBackground(currentPlayer.getColor());
		cells.get(currentPlayer.getCellPosition().getKey() + oS)
		 	.setOpaque(true);
		cells.get(currentPlayer.getCellPosition().getKey() + oS)
		  	.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			
		} else {
		
		
		cells.get(currentPlayer.getCellPosition().getKey())
	 		.setIcon(new ImageIcon(currentPlayer.getSmallIcon()));
		cells.get(currentPlayer.getCellPosition().getKey())
		  	.setBackground(currentPlayer.getColor());
		cells.get(currentPlayer.getCellPosition().getKey())
		 	.setOpaque(true);
		cells.get(currentPlayer.getCellPosition().getKey())
		  	.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}
	}

	private static int resolveDoor(DoorDirection doorDircetion, 
											int mapIndex) {
		switch(doorDircetion) {
			case UP:
				return mapIndex - boardColumns;
			case DOWN:
				return mapIndex + boardColumns;
			case LEFT:
				return  mapIndex - 1;
			case RIGHT:
				return mapIndex + 1;
			default:
				return 0;
		}
	}
	
	public static void displayHighlight(Set<BoardCell> targets) {

		targets.stream().forEach(target->{
			highlights.get(target.getKey()).setVisible(true);;
			highlightList.add(target.getKey());
			cells.get(target.getKey())
				.addMouseListener(new TarListener(target.getKey()));;
		});
		
	}

	public static void removeHighlights() {
		highlightList.stream().forEach(location->{
			for( MouseListener al : cells.get(location).getMouseListeners() ) {
				highlights.get(location).removeMouseListener( al );
			}
			
			highlights.get(location).setVisible(false);
		});
		
	}
		
	private static JLabel createColoredLabel(String text,
			   Color color) {
		   
		   	JLabel label = new JLabel(text);
		   	label.setVerticalAlignment(JLabel.TOP);
		   	label.setHorizontalAlignment(JLabel.CENTER);
		   	label.setOpaque(true);
		   	label.setBackground(color);
		   	label.setForeground(Color.black);
		   	label.setMaximumSize(new Dimension(26,26));
		   	label.setMinimumSize(new Dimension(26,26));
		   	label.setBounds(new Rectangle(26,26));
		   	return label;
	   }
	
	private static JLabel createHighlight() {
		   
		   	JLabel label = new JLabel(targetIcon);
		   	label.setVerticalAlignment(JLabel.CENTER);
		   	label.setHorizontalAlignment(JLabel.CENTER);
		   	label.setForeground(Color.black);
		   	label.setMaximumSize(new Dimension(26,26));
		   	label.setMinimumSize(new Dimension(26,26));
		   	label.setBounds(new Rectangle(26,26));
		   	return label;
	   }
}