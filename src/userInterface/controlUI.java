package userInterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class controlUI {
	
	public static JPanel createAndShowUI() {
		JPanel mainF = new JPanel();
		mainF.setBackground(Color.DARK_GRAY);
		mainF.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		mainF.setMinimumSize(new Dimension(700, 142));
		
		
		
		
		
		return mainF;
	}

}
