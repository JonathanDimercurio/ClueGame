package userInterface;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gridUI {
	@SuppressWarnings("exports")
	private static BufferedImage bgImage = new ImagePanelComponent("resources/bgArt.png").getImage();
	
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
            	createAndShowUI();

            }
        });	
    }
	
	public static void createAndShowUI() {
		JFrame mainF = new JFrame();
		JPanel bgPanel = new JPanel();
		mainF.add(bgPanel.add(new JLabel(new ImageIcon(bgImage))));
		mainF.pack();
		mainF.setVisible(true);
		
		
//		createButtonArray();
//		createTargetArray();
		
	}
	

}
