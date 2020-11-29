package userInterface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.awt.Color;

import javax.sound.sampled.Clip;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame {
	@SuppressWarnings("exports")
	public static JCheckBox isSTART = new JCheckBox();
	private static Clip introClip = UIAudio.getIntroSound();
	private static Clip startClip = UIAudio.getStartSound();
	private static JFrame frame = new JFrame("Clue Game");
	
	private static void addAllElements() {
    	if (!isSTART.isSelected()) {
    		introClip.start();
    		JPanel startMenu = StartMenu.createAndShowGUI(); 
    		frame.add(startMenu);
			int delay = 8300; // milliseconds
			
			ActionListener task =  new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.addKeyListener(new KeyListener() {

						@Override
						public void keyTyped(KeyEvent e) {
							introClip.stop();
							startClip.start();
							startMenu.setVisible(false);
							isSTART.doClick();
							frame.revalidate();
							createAndShowGUI();
						}

						@Override
						public void keyPressed(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
						
					});
				}
			};
				
			Timer timer = new Timer(delay, task);
			timer.setRepeats(false);
			timer.start();
			
    	} else {
			
    		frame.setLayout(new GridBagLayout());
    		frame.setBackground(Color.DARK_GRAY);
		    GridBagConstraints GBL = new GridBagConstraints();
		    
		    GBL.gridx = 0;
		    GBL.gridy = 0;
		    GBL.fill = GridBagConstraints.WEST;
		    GBL.anchor = GridBagConstraints.NORTHWEST;
		    frame.add(gridUI.createAndShowUI(), GBL);
		    
		    GBL.gridx = 2;
		    GBL.gridy = 0;
		    GBL.fill = GridBagConstraints.WEST;
		    GBL.anchor = GridBagConstraints.WEST;
		    frame.add(seenUI.populateSeenList(), GBL);
		
		    GBL.gridx = 0;
		    GBL.gridy = 1;
		    GBL.insets = new Insets(-143,0,0,0);
		    GBL.anchor = GridBagConstraints.SOUTHWEST;
		    frame.add(controlUI.createAndShowUI(), GBL);
		    
		    isSTART.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					frame.revalidate();
					}
				});
		}

	}
	
    public static void createAndShowGUI() {
        
        frame.setMinimumSize(new Dimension(860,500)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setFocusable(true);
        addAllElements();
        frame.pack();
        frame.setVisible(true);
    }
}
