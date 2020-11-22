package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clueGame.Board;



public class gridUI {
	private static Image bgImage = new ImagePanelComponent("resources/bgArt.png").getImage();
	final static int boardRows	= Board.getInstance().getNumRows();
	final static int boardColumns = Board.getInstance().getNumColumns();
	private static Map<Integer, JButton> mapGrid = new HashMap<Integer, JButton>();
	private static int index = 0;
	private static JButton button;
	
	
//    public static void main(String[] args) {    	
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//            	UICtrl.initUI();
//            	createAndShowUI();
//
//            }
//        });
//    }
	
	@SuppressWarnings("exports")
	public static JPanel createAndShowUI() {
		JPanel bgPanel = new JPanel();
		bgPanel.setMaximumSize(new Dimension(1200,600));
		bgPanel.setMinimumSize(new Dimension(700, 568));
//		createButtonArray();
//		bgPanel.setLayout(new GridLayout(boardColumns, boardRows));
//		for (int i = 0; i < index; i++) {
//			bgPanel.add(mapGrid.get(i));
//		}		
		
		bgPanel.add(new JLabel(new ImageIcon(bgImage)));
		bgPanel.setBackground(Color.DARK_GRAY);
		bgPanel.setVisible(true);
		return bgPanel;
	}
	
//	private static void createButtonArray() {
//		for (int i = 0; i < gridUI.boardRows; i++) {
//			for (int p = 0; p < gridUI.boardColumns; p++) {
//				button = new JButton();
//				
//				button.setVisible(false);
//				gridUI.mapGrid.put(index++, button);
//	} } }
	
}