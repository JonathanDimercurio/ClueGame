package UIResources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class UIPlayerIcons {
	
	private final int ICON_WIDTH = 40;
	private final int ICON_HIEGHT = 40;
	private String[] iconList = new String[]{"ariPIcon.png","bobPIcon.png"
												,"capPIcon.png","davPIcon.png"
												,"nemPIcon.png","plaIcon"
												,"sanPIcon.png","nepPIcon.png"};
	
	private static Map<String, Image> iconMap = 
			new HashMap<String, Image>();
	private static UIPlayerIcons playerIcons = 
			new UIPlayerIcons();
	
	public UIPlayerIcons getPlayerIcons() {
		return UIPlayerIcons.playerIcons;
	}
	
	private UIPlayerIcons() {
		Arrays.stream(iconList).forEach(name->{
			checkImageInput(name);
		});
	}
	
	private void checkImageInput(String checkString) {
		String path = new String("resouces/" + checkString); 
		BufferedImage image = null;
	       try {                
	          image = ImageIO.read(new File(path));
	       } catch (IOException ex) {
	    	   System.out.println("Check the image file.");
	       }
	       image.getScaledInstance(ICON_WIDTH, ICON_HIEGHT,  
	    		   java.awt.Image.SCALE_SMOOTH);
	       setIcons(checkString, image);
	}

	public void setIcons(String file, 
			@SuppressWarnings("exports") Image image) {
		UIPlayerIcons.iconMap.put(file, image);
	}
	
	@SuppressWarnings("exports")
	public static Image getPIcon(String name) {
		return UIPlayerIcons.iconMap.get(name);
	}
}
