package gameGUI;

public class FooPlayer {
	private static final int INCREASE_AMOUNT = 10;
	private int health = 0;
	
	public int getHealth() {
		return health;
	}
	
	public void increaseHealth() {
		health += INCREASE_AMOUNT;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}

	public void increaseHP() {
		health += INCREASE_AMOUNT;
		
	}
}
