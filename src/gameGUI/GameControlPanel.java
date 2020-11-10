package gameGUI;

import java.awt.Dimension;
import javax.swing.JFrame;

public class GameControlPanel extends JFrame{
	public GameControlPanel() {
		setSize(new Dimension(400, 250));
		setTitle("Neat Stuff Area");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel whoseTurn = new JLabel("Whose turn?");
		JLabel roll = new JLabel("Roll:");
		
		
	}
	
	public static void drawGCP() {
		GameControlPanel gcp = new GameControlPanel();
		gcp.setVisible(true);
		
	}
	
	private JPanel playerNamePanel() {
		JPanel playerName = new JPanel();
		playerName.setLayout(new GridLayout(1,1));
		JLabel playerNameLabel = new JLabel(currentPlayerTurn());
		panel.add
	}
	
	public void currentPlayerTurn() {
		
	}
}
