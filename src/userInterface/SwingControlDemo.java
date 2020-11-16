package userInterface;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import clueGame.PlayerActions;
 
public class SwingControlDemo {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel rollLabel;
   private JPanel controlPanel;
   private UIControl gameData = new UIControl();
   private int pIndexer = 0;
   public static boolean startButton = true;
   public static ImageIcon nIcon = new ImageIcon("resources/rBut01.png");
   
   public SwingControlDemo(){
      prepareGUI();
   }
   public static void main(String[] args){
      SwingControlDemo  swingControlDemo = new SwingControlDemo();      
      swingControlDemo.showButtonDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });
      mainFrame.setMinimumSize(new Dimension(300, 150));
      headerLabel = new JLabel("", JLabel.CENTER);        
      
      rollLabel = new JLabel("",JLabel.CENTER);    
      rollLabel.setSize(350,100);
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(rollLabel);
      mainFrame.setVisible(true);  
   }
  
   private void showButtonDemo(){
	   headerLabel.setText(""); 
      //resources folder should be inside SWING folder.
      ImageIcon nIcon = new ImageIcon("resources/nBut01.png");
      ImageIcon rIcon = new ImageIcon("resources/rBut01.png");
      ImageIcon sIcon = new ImageIcon("resources/sbut01.png");
      
      JButton nextButton = new JButton(nIcon);
      nextButton.setBackground(Color.BLACK);
      JButton rollButton = new JButton(rIcon);
      rollButton.setBackground(Color.BLACK);
      JButton sugButton = new JButton(sIcon);
      sugButton.setBackground(Color.black);
      
      

      
      rollButton.setHorizontalTextPosition(SwingConstants.LEFT);   
      nextButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
             try {
           	  String soundName = "resources/39.wav";    
           	  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
           	  Clip clip = AudioSystem.getClip();
           	  clip.open(audioInputStream);
           	  clip.start();
             } catch (Exception e1) {
                 System.err.println(e1.getMessage());
               }
        	 
        	 if(gameData.currentPlayer(pIndexer).getPType() == 'H') {
        		 headerLabel.setText("Your turn, " + gameData.currentPlayer(pIndexer).getName() + "!");
        		 rollButton.setEnabled(true);        		 
        		 rollButton.setVisible(true);
        		 sugButton.setVisible(true);
        		 rollLabel.setVisible(false);
        	 } else {
        		 headerLabel.setText(gameData.currentPlayer(pIndexer).getName());
        		 rollButton.setVisible(false);
        		 rollLabel.setVisible(false);
        		 sugButton.setVisible(false);
        	 }
        	pIndexer += 1;
         }
      });
      
      
      rollButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        		rollButton.setEnabled(false);
        		String rollString = "Roll Button clicked, Your big roll is: " + String.valueOf(PlayerActions.rollDice());
        		rollLabel.setText(rollString);
        		rollLabel.setVisible(true);
         }
      });
      
      
      
      controlPanel.add(sugButton);
      controlPanel.add(nextButton);
      controlPanel.add(rollButton);
      sugButton.setVisible(false);
      rollButton.setVisible(false);
      
      mainFrame.setVisible(true);  
   }
}