package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import clueGame.Board;
import clueGame.BoardCell;



public class UIGrid {
	private static Image bgImage = 
			new ImagePanelComponent("resources/bgArt.png").getImage();
	
	private final static Dimension SIZE =  new Dimension(702,572);
	final static int boardRows	= Board.getInstance().getNumRows();
	final static int boardColumns = Board.getInstance().getNumColumns();
	private static Map<Integer, JLabel> 	cellData 	= new HashMap<>();
	
	@SuppressWarnings("exports")
	public static JLayeredPane createAndShowUI() {
				
		JLayeredPane bgPane = new JLayeredPane();
		bgPane.setMaximumSize(SIZE);
		bgPane.setMinimumSize(SIZE);
		bgPane.setBackground(Color.DARK_GRAY);
		
		bgPane.add(test1());
		
		
		bgPane.add(setBGImage());
		bgPane.setOpaque(true);
		bgPane.setVisible(true);
		
		return bgPane;
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
	
	private static JPanel test1() {
		JPanel testP = new JPanel(new GridBagLayout());
		testP.setMaximumSize(SIZE);
		testP.setMinimumSize(SIZE);		
		int p = boardColumns * boardRows; 
		
		
		for(int i = 0 ; i < p; i++ ){
			testP.add(createColoredLabel("",new Color(0,0,0,0)));
			
		}
		testP.setOpaque(false);
		testP.setBorder(BorderFactory.createLineBorder(Color.black));
		testP.setVisible(true);
		testP.setBounds(0, 0, 702, 572);
		return testP;
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
		   	label.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,125), 1));
		   	label.setMaximumSize(new Dimension(26,26));
		   	label.setMinimumSize(new Dimension(26,26));
		   	label.setBounds(new Rectangle(26,26));
		   	return label;
	   }
}