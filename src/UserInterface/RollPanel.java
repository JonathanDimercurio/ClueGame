package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RollPanel extends JPanel{

	
	public RollPanel(int roll) {
		setPreferredSize(new Dimension(100,180));
		setBackground(Color.DARK_GRAY);
		Font font1 = new Font("SansSerif", Font.BOLD, 30);
		
		
		JLabel tempF = new JLabel();
		
		tempF.setFocusable(false);
		tempF.setText("You rolled: " + roll);
		tempF.setFont(font1);
		add(tempF);
		
		tempF = new JLabel();
		
		tempF.setFocusable(false);
		tempF.setFont(font1);
		tempF.setText("Select a space!");
		add(tempF);
		setVisible(true);
	}
}
