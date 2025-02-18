package userInterface;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerIcon extends JPanel{
	private Image newimg;
	
	public PlayerIcon(String pString) {
		checkImageInput(pString);
	}
		
	private void checkImageInput(String checkString) {
	    BufferedImage image = null;
	       try {                
	          image = ImageIO.read(new File(checkString));
	       } catch (IOException ex) {
	    	   System.out.println("Check the image file.");
	       }
	       this.newimg = image
	    		   .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	}
	
    @SuppressWarnings("exports")
	public Image getImage() {
    	return this.newimg;
    }
}