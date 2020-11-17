package userInterface;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import clueGame.CardType;

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
	
	public static JButton modButtons(JButton button) {
		
		button.setMargin(new Insets(0,0,0,0));
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0,0,0,0));


		return button;
		
	}
	
	
	
}