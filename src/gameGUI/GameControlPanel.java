//package gameGUI;
//
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//import java.util.Random;
//import java.awt.*;
//
//import javax.swing.*;
//import javax.swing.border.*;
//
//import clueGame.*;
//
//public class GameControlPanel extends JPanel implements GetUI {{
//	private JTextField name;	
//	private String curRoom = "Beach";
//	private static int i = 0;
//	private static Player currentPlayer;
//	private static GameControlPanel gcp;
//	
//	public GameControlPanel() {
//		Board board = Board.getInstance();
//		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
//		board.initialize();
//		
//		GameControlPanel.currentPlayer = GetUI.getCurrentPlayer(i++);
//		
//		
//		setLayout(new GridLayout(2, 0));
//		JPanel gcPanel = createNamePanel();
//		gcPanel.setBackground(currentPlayer.getColor());
//		add(gcPanel);
//		
//		gcPanel = createAccusationButton();
//		add(gcPanel);
//		
//		gcPanel = createGuessField();
//		add(gcPanel);
//		
//	}
//
//	private JPanel createGuessField() {
//		JPanel totalGuessPanel = new JPanel();
//		JPanel guessPanel = new JPanel();
//		JPanel replyPanel = new JPanel();
//		//totalGuessPanel.setPreferredSize(new Dimension(600, 10)); //dimension of guess panel
//		guessPanel.setLayout(new GridLayout(3, 0));
//		//guessPanel.setPreferredSize(new Dimension(200, 70));
//		replyPanel.setLayout(new GridLayout(3, 0));
//		//replyPanel.setPreferredSize(new Dimension(200, 70));
//		if (ComputerPlayer.computerPlayerList.contains(currentPlayer)) {
//			List<Card> AIGuesses = GetUI.genAIGuess("Beach", currentPlayer);
//			JLabel firstGuess = new JLabel(AIGuesses.get(0).getCardName());
//			guessPanel.add(firstGuess);
//			JLabel secondGuess = new JLabel(AIGuesses.get(1).getCardName());
//			guessPanel.add(secondGuess);
//			JLabel thirdGuess = new JLabel(AIGuesses.get(2).getCardName());
//			guessPanel.add(thirdGuess);
//			guessPanel.setBorder(new TitledBorder ("Guesses"));
//			List<Card> guessReplies =currentPlayer.generateSuggestionReply(AIGuesses,(ComputerPlayer) currentPlayer);
//			if (guessReplies != null ) {
//				for (Card addMe: guessReplies) {
//					JLabel addR = new JLabel(addMe.getCardName());
//					replyPanel.add(addR);					
//				}
//			}
//			replyPanel.setBorder(new TitledBorder ("Replies"));
//			totalGuessPanel.add(guessPanel, BorderLayout.WEST);
//			totalGuessPanel.add(replyPanel, BorderLayout.EAST);
//			//replyPanel.setPreferredSize(new Dimension(150, 40));
//			return totalGuessPanel;
//		} else {
//			JComboBox<String> People, Rooms, Weapons;
//			People = this.createPlayerGuessOptions();
//			Rooms = this.createRoomGuessOptions();
//			Weapons = this.createWeaponsGuessOptions();
//			guessPanel.add(People);
//			guessPanel.add(Rooms);
//			guessPanel.add(Weapons);
//			return guessPanel;
//		}
//	}
//
//	private JPanel createAccusationButton() {
//		JPanel beePanel = new JPanel();
//		beePanel.setLayout(new GridLayout(2, 2));
//		JButton accuButton = new JButton("Make Accusation");
//		add(accuButton, BorderLayout.EAST);
//		//TODO need to actually make accusation
//		JButton nextButton = new JButton("End Turn, Next Player");
//		nextButton.setMaximumSize(new Dimension(10, 10));;
//		nextButton.addActionListener(new BListener());
//		add(nextButton, BorderLayout.WEST);
//		//TODO need to end turn, call next player
//		ButtonGroup buttonPanel = new ButtonGroup();
//		buttonPanel.add(accuButton);
//		buttonPanel.add(nextButton);
//		return beePanel;
//		
//	}
//	
//	private JPanel createNamePanel() {
//		JPanel namePanel = new JPanel();
//		namePanel.setLayout(new GridLayout(2, 2));
//		String playerNameString = "Player Name:  " + currentPlayer.getName();
//		JLabel nameLabel = new JLabel(playerNameString);
//		namePanel.add(nameLabel);
//		//namePanel.setPreferredSize(new Dimension(200, 50)); //size of name panel
//		JLabel currentPlayerName = new JLabel(currentPlayer.getName());
//		//currentPlayerName.setPreferredSize(new Dimension(170, 7)); //size of player name area
//		Random Rolled = new Random();
//		int diceRoll = Rolled.nextInt(10) + 2; //TODO This is junk and the color shit
//		String sRoll = "Roll:  " + Integer.toString(diceRoll);
//		JLabel roll = new JLabel(sRoll);
//		roll.setBackground(currentPlayer.getColor());
//		namePanel.add(roll);
//		namePanel.setBorder(new TitledBorder ("Whose turn is it?"));
//		return namePanel;
//	}
//	
//	private static void initGCP() {
//		gcp = new GameControlPanel();
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(750, 180);
//		frame.add(gcp, BorderLayout.CENTER);
//		frame.setVisible(true);
//	}
//
//
//	private JComboBox<String> createPlayerGuessOptions() {
//		JComboBox<String> guessChoicesPeople = new JComboBox<String>();
//		for (Card getPeople: Card.getTotalPeople()) {
//			guessChoicesPeople.addItem(getPeople.getCardName());
//		}
//		return guessChoicesPeople;
//	}
//		
//	private JComboBox<String> createRoomGuessOptions() {
//		JComboBox<String> guessChoicesRooms = new JComboBox<String>();
//		for (Card getRooms: Card.getTotalRooms()) {
//			guessChoicesRooms.addItem(getRooms.getCardName());
//		}
//		return guessChoicesRooms;
//	}
//	
//	private JComboBox<String> createWeaponsGuessOptions() {
//		JComboBox<String> guessChoicesWeapons = new JComboBox<String>();
//		for (Card getWeapons: Card.getTotalRooms()) {
//			guessChoicesWeapons.addItem(getWeapons.getCardName());
//		}
//		return guessChoicesWeapons;
//	}
//
//	private class BListener implements ActionListener 
//	{
//
//		public void actionPerformed(ActionEvent e) 
//		{
//			gcp.setVisible(false);
//		}
//	}
//	
//	public static void main(String[] args) {
//
//		initGCP();
//
//	}
//}
//
