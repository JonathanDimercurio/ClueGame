package UserInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

import States.PanelStates;
import UIResources.UITimer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StartMenu 
			extends PanelStates 
			implements UITimer{
	
	private Timer timer;
	private JLabel startArt = 
			new JLabel(new ImageIcon("resources/splashBGArt.gif")); 
	
	public StartMenu() {
		super();
		setFocusable(true);
		add(startArt);
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory
				.createEmptyBorder(30,30,30,30));
		
		timer = UITimer.taskTimer(8000, taskOne());
		timer.start();
	}
	
	private ActionListener taskOne() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startArt.setIcon(new 
						ImageIcon("resources/StartMenu.gif"));
				startArt.setVisible(true);
				addMouseListener(new taskTwo());
				revalidate();
			}
		};
	}
	
	private class taskTwo extends MouseAdapter {

			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
			
		}
}