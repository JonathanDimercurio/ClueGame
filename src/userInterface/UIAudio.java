package userInterface;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class UIAudio {
	private static String introSound = new String("resources/sound/intro.wav");
	private static String startSound = new String("resources/sound/lol.wav");
	
	private static Clip introSE = getSound(introSound);
	private static Clip startSE = getSound(startSound);
	
	
	
	private static Clip getSound(String soundFile) {
		try {
			String soundName = new String(soundFile);
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
	
	public static void playIntroSound() {
		introSE.start();
	}
	
	public static void stopIntroSound() {
		introSE.stop();
		introSE.close();
	}
	
	public static void playStartSound() {
		startSE.start();
	}
	
	public static enum Volume{
		MUTE, LOW, MEDIUM, HIGH;
	}
	
	public static Volume volume = Volume.LOW;
	
}