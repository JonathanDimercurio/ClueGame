package UIResources;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public interface UITimer {

	public static Timer taskTimer(
			int delay, ActionListener task) {
		
		Timer timer = new Timer(delay, task);
		timer.setRepeats(false);
		return timer;
	}
}
