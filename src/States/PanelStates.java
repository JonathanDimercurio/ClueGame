package States;

import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

@SuppressWarnings("serial")
public class PanelStates extends JPanel 
							implements States{
	
	protected DefaultListModel<is> states =
			new DefaultListModel<is>();
		
	//General Constructor
	public PanelStates() {
		super();
		setOpaque(true);
		setVisible(true);
		states.addListDataListener(stateChange());
		
	}
	
	//Adder
	protected void addState(is newState) {
		int index = (states.getSize());
		states.add(index, newState);
	}
	
	//Setter
	protected void setState(int whichState, is is ) {
		states.set(whichState, is);
	}
	
	//Listeners
	private ListDataListener stateChange( ) {
		return new ListDataListener() {

			@Override
			public void intervalAdded(ListDataEvent e) {
				switch (states.lastElement()) {
					case NOTVISIBLE:
						System.out.println("inter - Hit");
						Arrays.stream(getComponents())
								.forEach(comp->{
									comp.setVisible(false);
								});
						states.removeAllElements();
						
						break;
					default:
						break;
				}
				
			}
			
			@Override
			public void intervalRemoved(ListDataEvent e) {
				
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				System.out.print("contentsChanged - hit" 
											+ e.toString());
			}
		};
	}
 	
}