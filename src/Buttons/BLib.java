package Buttons;

import java.awt.Image;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BLib extends JButton{
	
	protected static Map<String, ImageIcon> imageMap = 
			new HashMap<String, ImageIcon>();
	
	protected static Map<String, ImageIcon> grayMap = 
			new HashMap<String, ImageIcon>();
		
	protected String[] iconNames = 
			
			new String[] {	"resources/aBut01.png",
							"resources/nBut01.png",
					 		"resources/sBut01.png", 
					 		"resources/rBut01.png" };
	
	protected BLib() {
			Arrays.stream(iconNames).forEach(name->{
				imageMap.put(name, new ImageIcon(name));
				Image greyImage = GrayFilter
						.createDisabledImage(imageMap
								.get(name)
								.getImage());
				grayMap.put(name, new ImageIcon(greyImage));
			});
	}
}
