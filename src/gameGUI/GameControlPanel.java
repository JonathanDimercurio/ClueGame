package gameGUI;

import java.awt.Dimension;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class GameControlPanel extends JPanel{
	private JTextField name;
	public GameControlPanel() {
		setLayout(new GridLayout(2, 0));
		JPanel gcPanel = createNamePanel();
		add(gcPanel);
//		gcPanel = createAccusationButton();
//		add(gcPanel);
//		gcPanel = createGuessField();
//		add(gcPanel);
//		gcPanel = createGuessResultField();
//		add(gcPanel);
//	}
//	
//	private JPanel createGuessResultField() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private JPanel createGuessField() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private JPanel createAccusationButton() {
//		// TODO Auto-generated method stub
//		return null;
	}

	private JPanel createNamePanel() {
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(1, 2));
		JLabel nameLabel = new JLabel("Player Name");
		name = new JTextField(20);
		namePanel.add(nameLabel);
		namePanel.add(name);
		namePanel.setBorder(new TitledBorder (new EtchedBorder(), "Whose turn is it?"));
		return namePanel;
	}


	//GCP = game control panel
//	public static void drawGCP() {
//		GameControlPanel gcp = new GameControlPanel();
//		gcp.setVisible(true);
//		
//	}
//	
//	private void drawCurrentPlayer() {
//		JPanel playerNamePanel = new JPanel();
////		playerNamePanel.setLayout(new );
//		JLabel whoseTurn = new JLabel("Whose turn?");
//		setSize(new Dimension(100, 25));
//		playerNamePanel.add(whoseTurn);
//	}

	public static void main(String[] args) {
	GameControlPanel gcp = new GameControlPanel();
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(750, 180);
	
	
	frame.add(gcp, BorderLayout.CENTER);
	frame.setVisible(true);
	}
}

