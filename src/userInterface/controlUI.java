package userInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class controlUI {
	
	private static Map<String, ImageIcon> imageMap = new HashMap<String, ImageIcon>();
	
	@SuppressWarnings("exports")
	public static JPanel createAndShowUI() {
		JPanel mainP = new JPanel();
		mainP.setBackground(Color.DARK_GRAY);
		mainP.setMinimumSize(new Dimension(700, 142));
		mainP.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JPanel subP = createSubPanel();
		GridLayout gridType = new GridLayout(2,2);
		gridType.setVgap(5);
		gridType.setHgap(10);
		subP.setLayout(gridType);
		
		subP.add(createSuggestionButton());
		subP.add(createRollButton());
		subP.add(createAccusationButton());
		subP.add(createNextButton());
		
		mainP.add(subP);
		mainP.setVisible(true);
		
		
		
		return mainP;
	}
	

	private static JPanel	 createSubPanel() {
		JPanel subPanel = new JPanel();
		subPanel.setMinimumSize(new Dimension(690, 138));
		subPanel.setBorder(UICommands
				.getPBorder(UICommands.currentPlayer()));
		subPanel.setVisible(true);
		return subPanel;
	}
	
	private static Component createAccusationButton() {
		imageMap.put("aIcon", new ImageIcon("resources/aBut01.png"));
		JButton button = new JButton(imageMap.get("aIcon"));
		buttonActions.modButtons(button);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		genGuessUI.handleGuess(true);
	    		
	    		}
	    	});
		return button;
	}

	private static Component createSuggestionButton() {
		imageMap.put("sIcon", new ImageIcon("resources/sBut01.png"));
		JButton button = new JButton(imageMap.get("sIcon"));
		buttonActions.modButtons(button);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		genGuessUI.handleGuess(false);
	    			
	    		}
	    	});
		return button;
		
	}
	
	private static Component createRollButton() {
		imageMap.put("rIcon", new ImageIcon("resources/rBut01.png"));
		JButton button = new JButton(imageMap.get("rIcon"));
		buttonActions.modButtons(button);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	 buttonActions.bSound1();
	        	 button.setVisible(false);
	        	 }
	    	});
		return button;
	}
	
	private static Component createNextButton() {
		imageMap.put("nIcon", new ImageIcon("resources/nBut01.png"));
		JButton button = new JButton(imageMap.get("nIcon"));
		buttonActions.modButtons(button);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	 buttonActions.bSound1();
	        	 button.setVisible(false);
	        	}
	    	});
		return button;

	}

}