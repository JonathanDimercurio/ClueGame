package userInterface;

import java.awt.Insets;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public interface buttonActions {
	
	public static void bSound1() {
		try {
			String soundName = "resources/39.wav";    
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
   	  		Clip clip = AudioSystem.getClip();
   	  		clip.open(audioInputStream);
   	  		clip.start();
		} catch (Exception e1) {
     		System.err.println(e1.getMessage());
     	}
		
	}
	
	@SuppressWarnings("exports")
	public static JButton modButtons(JButton button) {
		
		button.setMargin(new Insets(0,0,0,0));
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(10,10,10,10));


		return button;
		
	}
	
	
	
}