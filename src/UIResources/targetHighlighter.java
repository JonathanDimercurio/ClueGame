package UIResources;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class targetHighlighter extends JPanel{
	ImageIcon newimg;
	
	public targetHighlighter() {
		checkImageInput("resources/tar11.gif");
	}
		
	private void checkImageInput(String checkString) {
	    ImageIcon image = null;
	    image = new  ImageIcon(checkString);
	    newimg = image;
	}
	
    public ImageIcon getImage() {
    	return this.newimg;
    }
}