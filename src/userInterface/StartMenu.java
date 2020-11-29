package userInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu {
	
	
	@SuppressWarnings("exports")
	public static JLabel introSplash;
	private static JLabel startMenu;
	private static ImageIcon bgSplashArt = new ImageIcon("resources/splashBGArt.gif");
	private static ImageIcon startArt = new ImageIcon("resources/StartMenu.gif");
	private static JPanel mainP;
	
	
	@SuppressWarnings("exports")
	public static JPanel createAndShowGUI() {
			mainP = new JPanel();
			mainP.setBackground(Color.DARK_GRAY);

			
			StartMenu.startMenu = new JLabel(startArt);
			
			StartMenu.introSplash = new JLabel(bgSplashArt);
			StartMenu.introSplash.setVisible(true);
			
			mainP.add(StartMenu.introSplash);
			
			
			int delay = 8000; // milliseconds
			ActionListener task =  new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					StartMenu.introSplash.setVisible(false);
					StartMenu.startMenu.setVisible(true);
					mainP.add(startMenu);
				}
			};
				
			Timer timer = new Timer(delay, task);
			timer.setRepeats(false);
			timer.start();
			
			return mainP;
	    }
}