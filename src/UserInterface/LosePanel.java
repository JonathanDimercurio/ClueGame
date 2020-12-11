package UserInterface;

import java.awt.Color;
import States.PanelStates;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LosePanel 
			extends PanelStates {
	
	private JLabel startArt = 
			new JLabel(new ImageIcon("resources/lose.gif")); 
	
	public LosePanel() {
		super();
		setFocusable(true);
		add(startArt);
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory
				.createEmptyBorder(30,30,30,30));

	}
	
}