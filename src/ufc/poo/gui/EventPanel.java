package ufc.poo.gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class EventPanel extends JPanel {
    protected final PropertyChangeSupport event = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        event.addPropertyChangeListener(listener);
    }
    
	public EventPanel() {
		super();
	}
}
