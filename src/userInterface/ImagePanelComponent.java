package userInterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanelComponent extends JPanel{
	private BufferedImage imageOutPut;
	
	public ImagePanelComponent(String pString) {
		checkImageInput(pString);
	}
	
	private void checkImageInput(String checkString) {
	    BufferedImage image = null;
	       try {                
	          image = ImageIO.read(new File(checkString));
	       } catch (IOException ex) {
	    	   System.out.println("Check the image file.");
	       }
	       this.imageOutPut = image;
	}
	
    @SuppressWarnings("exports")
	public BufferedImage getImage() {
    	return this.imageOutPut;
    }
	
}
