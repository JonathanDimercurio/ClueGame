package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import States.PanelStates;
import UIResources.UIPlayerControl;

@SuppressWarnings("serial")
public class playerSelectUI extends PanelStates
								implements ActionListener{
	
	private static JLabel icon;
	private static String selection;
	
    public playerSelectUI() {
    	super();
    	setBackground(Color.LIGHT_GRAY);
    	setBorder(BorderFactory
				.createMatteBorder(30,30,30,30,Color.DARK_GRAY));
    	setLayout(new BorderLayout());
		icon = new JLabel();

		JPanel discriptionPanel = new JPanel(new GridLayout(0,1));
		
		
		//	Text field ~~~~~~~~
		JTextArea discription = new JTextArea();
		discription.setBorder(BorderFactory
				.createMatteBorder(5,5,5,0,Color.DARK_GRAY));
		discription.setText(
					"\n"
				+ "                     Beach Boardwalk Clue Game!               \n"
				+ "   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n"
				+ "\n"
				+"  Your task is to find out who commited the murder!  "
				+ "\n"
				+ "  Select the charecter you'd like to play!  \n"
				+ "\n"
				+ "\n"
				+ "  Remember you can only make suggestions in a room.  \n"
				+ "  You can only make one Accusation, if wrong you lose the game!   \n" 
				+ "\n"
				);
		// End Text field ~~~~~~
		
		
		//	Start	commit button ~~~~~~~ 
		JButton commit = new 
				JButton("Choose, Then click here to begin!");
		commit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UIPlayerControl.setHumanPlayer(selection);
				setVisible(false);
			}
		});
		//	end commit button 	~~~~~~~
		
		
		discriptionPanel.add(discription);
		discriptionPanel.add(commit);
		
		
		//	Radio buttons ~~~~~~~~~~~~~~~~~~
		ButtonGroup group = new ButtonGroup();
		JPanel radioPanel = new JPanel(new GridLayout(0,1));
		radioPanel.setBorder(BorderFactory
				.createMatteBorder(5,5,5,5,Color.DARK_GRAY));
		
		UIPlayerControl.playerList.forEach(player->{
			JRadioButton tempP = new 
					JRadioButton(player.getName());
			
			tempP.setActionCommand(player.getName());
			group.add(tempP);
			tempP.addActionListener(this);
			radioPanel.add(tempP);
			
		});
		//	End radio buttons	~~~~~~~~~~~~
		
		
		add(discriptionPanel, BorderLayout.WEST);
		add(radioPanel, BorderLayout.CENTER);
		add(icon, BorderLayout.EAST);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		setOpaque(true);
		
    }

	@SuppressWarnings("exports")
	@Override
	public void actionPerformed(ActionEvent e) {
		UIPlayerControl.playerList.stream().forEach(player->{
			if(player.getName().contentEquals(e.getActionCommand())) {
				playerSelectUI.icon.setIcon(new 
							ImageIcon(player.getLargeIcon()));
				
				selection = new String(player.getName());
				playerSelectUI.icon
					.setBorder(BorderFactory
							.createLineBorder(player.getColor(), 55));
				
				playerSelectUI.icon.setBackground(player.getColor());
			}
		});
		
	}
	
}