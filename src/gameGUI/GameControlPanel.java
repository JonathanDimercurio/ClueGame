package gameGUI;

import java.awt.Dimension;
import javax.swing.JFrame;

public class GameControlPanel extends JFrame{
	public GameControlPanel() {
		setSize(new Dimension(400, 250));
		setTitle("Neat Stuff Area");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void drawGCP() {
		GameControlPanel gcp = new GameControlPanel();
		gcp.setVisible(true);
	}
}
