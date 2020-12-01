package userInterface;

import java.awt.Color;
import java.lang.reflect.Array;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class playerSelectUI {
	
	
	
	@SuppressWarnings("exports")
	public static JPanel createAndShowGUI() {
		retrivePlayerInfo();
		
		JPanel mainP = new JPanel();
		mainP.setBackground(Color.DARK_GRAY);
		
		
		
		
		JRadioButton playerSelecter = new JRadioButton();
		
		
		return mainP;
	}

	private static Array[] retrivePlayerInfo() {
//		UICtrl.playerList
		
		return null;
	}
	
}
