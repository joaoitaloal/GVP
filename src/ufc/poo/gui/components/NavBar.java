package ufc.poo.gui.components;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NavBar extends JPanel {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    
    public NavBar() {
    	super();
    	
    	JButton itens = new JButton("itens");
    	itens.addActionListener(e ->{
    		pcs.firePropertyChange("itensScreen", null, null);
    	});
    	
    	JButton outros = new JButton("outros");
    	outros.addActionListener(e ->{
    		pcs.firePropertyChange("outrosScreen", null, null);
    	});
    	
    	JButton estatisticas = new JButton("estatisticas");
    	estatisticas.addActionListener(e ->{
    		pcs.firePropertyChange("estatisticasScreen", null, null);
    	});
    	
    	this.add(itens);
    	this.add(outros);
    	this.add(estatisticas);
    }

}
