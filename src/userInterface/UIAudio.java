package userInterface;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class UIAudio {
	private static String introSound = new String("resources/sound/intro.wav");
	private static String startSound = new String("resources/sound/start.wav");
	
	
	
	@SuppressWarnings("exports")
	public static Clip getIntroSound() {
		Clip introClip = getSound(introSound);
		FloatControl volume = (FloatControl) introClip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(-10);
		return introClip;
	}
	
	@SuppressWarnings("exports")
	public static Clip getStartSound() {
		Clip startClip = getSound(startSound);
		return startClip;
	}

	private static Clip getSound(String soundFile) {
		try {
			String soundName = soundFile;    
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(soundName).getAbsoluteFile());
   	  		Clip clip = AudioSystem.getClip();
   	  		clip.open(audioInputStream);
   	  		return clip;
		} catch (Exception e1) {
     		System.err.println(e1.getMessage());
     		return null;
     	}
		
	}
}