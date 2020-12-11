package UIResources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerIcon extends JPanel{
	private ArrayList<Image> newimg = new ArrayList<Image>();
	
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
	       newimg.add(image
	    		   .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
	       newimg.add(image
	    		   .getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
	}
	
    public ArrayList<Image> getImage() {
    	return this.newimg;
    }
}