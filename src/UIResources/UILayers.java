package UIResources;

import java.awt.Dimension;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class UILayers extends JLayeredPane {
	
	public UILayers() {
		super();
		setMinimumSize(new Dimension(1100, 800));
		setVisible(true);
		this.addContainerListener(new ContainerListener() {

			@Override
			public void componentAdded(ContainerEvent e) {
				System.out.println("comp hit");
			}

			@Override
			public void componentRemoved(ContainerEvent e) {
				System.out.println("comp hurt");
			}
			
		});
	
	}
}