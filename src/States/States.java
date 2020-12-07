package States;

import java.awt.event.ActionListener;

import javax.swing.event.EventListenerList;

public interface States {

	public static EventListenerList listenerList = 
			new EventListenerList();
	
	public enum is {
		GUESSING, BEHIND, infoPanelHIDDEN, ACTIVE,
		PLAYERNEXTTURN, NOTVISIBLE;
	}
	
	public static void addListener(ActionListener l) {
		listenerList.add(ActionListener.class, l);
	}

	public static void removeListener(ActionListener l) {
		listenerList.remove(ActionListener.class, l);
	}
	 
	public static void fireActionList() {
		Object[] listeners = listenerList.getListenerList();
		 
		for (int i = listeners.length-2; i>=0; i-=2) {
			
			if (listeners[i]==ActionListener.class) {
				
				
//				if (fooEvent == null)		
//					fooEvent = new FooEvent(this);
//				((FooListener)listeners[i+1]).fooXXX(fooEvent);
			}
			
		}
	}
	
	
}