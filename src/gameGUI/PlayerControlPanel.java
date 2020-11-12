package gameGUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import clueGame.Player;

public class PlayerControlPanel extends JPanel{
	
		private CurrentPlayer currentPlayer = new CurrentPlayer();
		private JLabel currentPlayerName = new JLabel();
		private JButton endTurnNextPlayer = new JButton("Next Player");
		
		public PlayerControlPanel() {
			endTurnNextPlayer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					currentPlayer.getCurrentPlayer();
					
					String text = String.format("It is %s's turn", currentPlayer.getName());
					currentPlayerName.setText(text);
				}
			});
		
		String text = String.format("It is %s's turn", currentPlayer.getName());
		currentPlayerName.setText(text);
		add(currentPlayerName);
		}
		
		private static void createAndShowPlayerControlPanel() {
			PlayerControlPanel topCharPanel = new PlayerControlPanel();
			
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(topCharPanel);
			frame.pack();
			frame.setLocation(null);
			frame.setVisible(true);
			
			
		}
		
		private static void main(String[] args ) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowPlayerControlPanel();
				}
			});
		}
		
}
