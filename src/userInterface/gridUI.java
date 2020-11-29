package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;
import clueGame.Player;



public class gridUI {
	private static Image bgImage = 
			new ImagePanelComponent("resources/bgArt.png").getImage();
	
	private final static Dimension SIZE =  new Dimension(702,572);
	final static int boardRows	= Board.getInstance().getNumRows();
	final static int boardColumns = Board.getInstance().getNumColumns();
	private static Map<Integer, JLabel>	cells = new HashMap<Integer, JLabel>();
	private static Map<Integer, DoorDirection> doors = new HashMap<Integer, DoorDirection>();

	@SuppressWarnings("exports")
	public static JLayeredPane createAndShowUI() {
				
		JLayeredPane bgPane = new JLayeredPane();
		bgPane.setMaximumSize(SIZE);
		bgPane.setMinimumSize(SIZE);
		bgPane.setBackground(Color.DARK_GRAY);		
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
				BoardCell tempCell = BoardCell.mapGameBoardData.get(mapIndex);
				
				JLabel test1 = createColoredLabel("",new Color(0,0,0,20));
				
				if (tempCell.isWalkable()) {
					test1.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,20), 2));
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
		UICtrl.playerList.stream().forEach(player->{
			  cells.get(player.getCellPosition().getKey())
			  	.setIcon(new ImageIcon(player.getIcon()));
			  cells.get(player.getCellPosition().getKey())
			  	.setBackground(player.getColor());
			  cells.get(player.getCellPosition().getKey())
			  	.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		});
	}
	

	private static int resolveDoor(DoorDirection doorDircetion, int mapIndex) {
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
	
	   @SuppressWarnings("unused")
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
}