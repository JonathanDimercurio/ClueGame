package UserInterface;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WinPanel extends JPanel{

	private JLabel startArt = 
			new JLabel(new ImageIcon("resources/win.gif")); 
	
	public WinPanel() {
		super();
		setFocusable(true);
		add(startArt);
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory
				.createEmptyBorder(30,30,30,30));

	}
	
}