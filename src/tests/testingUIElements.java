package tests;

import java.util.Vector;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import clueGame.CardType;
import userInterface.AccusationUI;
import userInterface.UICtrl;

public class testingUIElements {
	private static UICtrl files;
	
	
	//We'll need these for all the following tests.
	@BeforeAll
	public static void setUp() {
//		files = UICtrl.getUI();	
	}

	
	
	@Test
	public void checkMakeAccusation() {
		Vector<String> arrayTest = AccusationUI.type2List(CardType.PERSON);
		System.out.println(arrayTest.size());
		for(String tempS: arrayTest) { 
				System.out.println(tempS);
			}
		
		

//		
	}
}
