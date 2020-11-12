package gameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class UITest extends JPanel{
	private FooPlayer fooPlayer = new FooPlayer();
	private JLabel fooHP = new JLabel();
	private JButton increaseHP = new JButton("End Turn");
	
	public UITest() {
		increaseHP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fooPlayer.increaseHP();
				
				String text = String.format("Healt: %03d", fooPlayer.getHealth());
				fooHP.setText(text);
			}
		});
		
		String text = String.format("Health %03d", fooPlayer.getHealth());
		fooHP.setText(text);
		add(fooHP);
		add(increaseHP);
	}
	
	private static void createAndShowGUI() {
		UITest paintEg = new UITest();
		
		JFrame frame = new JFrame("UITest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(paintEg);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
}
