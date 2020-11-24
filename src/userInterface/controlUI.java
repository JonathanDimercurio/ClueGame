package userInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class controlUI {
	
	private static Map<String, ImageIcon> imageMap = 
			new HashMap<String, ImageIcon>();
	
	private static Map<String, ImageIcon> grayMap = 
			new HashMap<String, ImageIcon>();
		
	private static String[] iconNames = 
			new String[] {"resources/aBut01.png", "resources/nBut01.png",
					 		"resources/sBut01.png", "resources/rBut01.png" };
	
	private static JPanel subPanel;
	private static JPanel aPanel;
	
	
	@SuppressWarnings("exports")
	public static JPanel mainP;

	@SuppressWarnings("exports")
	public static JCheckBox isENABLED = new JCheckBox();
	
	@SuppressWarnings("exports")
	public static JPanel createAndShowUI() {
		
		Arrays.stream(iconNames).forEach(string->{
			setImageIcons(string);
		});
	
		mainP = new JPanel();
		mainP.setBackground(Color.DARK_GRAY);
		mainP.setMinimumSize(new Dimension(702, 142));
		mainP.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		GridLayout grid = new GridLayout(0,2);
		grid.setHgap(15);
		mainP.setLayout(grid);
		
		subPanel = createSubPanel();
		mainP.add(controlUI.subPanel);
		
		aPanel = AccusationUI.apUI();
		aPanel.setVisible(false);
		mainP.add(aPanel);
		
		mainP.setVisible(true);
		
		isENABLED.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(isENABLED.isSelected()) {
					Arrays.stream(subPanel.getComponents()).forEach(component->{
						component.setEnabled(false);
					});
				}
				else {
					Arrays.stream(subPanel.getComponents()).forEach(component->{
						component.setEnabled(true);
					});
				}
			}
		});
		
		return mainP;
	}
	
	private static JPanel createSubPanel() {
		JPanel subPanel = new JPanel();
		subPanel.setMinimumSize(new Dimension(690, 138));
		subPanel.setBorder(UICommands
				.getPBorder(UICommands.currentPlayer()));
		
		GridLayout gridType = new GridLayout(2,2);
		gridType.setVgap(5);
		subPanel.setLayout(gridType);
		subPanel.add(createSuggestionButton());
		subPanel.add(createRollButton());
		subPanel.add(createAccusationButton());
		subPanel.add(createNextButton());
		subPanel.setVisible(true);
		return subPanel;
	}
	
	private static Component createAccusationButton() {
		JButton button = new JButton(imageMap.get(iconNames[0]));
		button.setDisabledIcon(grayMap.get(iconNames[0]));
		buttonActions.modButtons(button);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		isENABLED.doClick();
	    		aPanel.setVisible(true);
	    		aPanel.revalidate();
	    	}
	    });
	    
	    button.setVisible(true);
		return button;
	}

	private static Component createSuggestionButton() {
		JButton button = new JButton(imageMap.get(iconNames[2]));
		button.setDisabledIcon(grayMap.get(iconNames[2]));
		buttonActions.modButtons(button);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		isENABLED.doClick();
	    		
	    		aPanel.setVisible(true);
	    		aPanel.revalidate();
	    	}
	    });
	    
	    button.setVisible(true);
		return button;
	}
	
	private static Component createRollButton() {
		JButton button = new JButton(imageMap.get(iconNames[3]));
		button.setDisabledIcon(grayMap.get(iconNames[3]));
		buttonActions.modButtons(button);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	 buttonActions.bSound1();	 
	       	 }
	    });
	    
	    button.setVisible(true);
		return button;
	}
	
	private static Component createNextButton() {
		JButton button = new JButton(imageMap.get(iconNames[1]));
		button.setDisabledIcon(grayMap.get(iconNames[1]));
		buttonActions.modButtons(button);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	 buttonActions.bSound1();
	       	}
	   	});
	    
	    button.setVisible(true);
		return button;
	}
	
	private static void setImageIcons(String name) {
		imageMap.put(name, new ImageIcon(name));
		Image greyImage = GrayFilter
				.createDisabledImage(imageMap.get(name)
						.getImage());
		grayMap.put(name, new ImageIcon(greyImage));
	}

}