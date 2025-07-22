package ufc.poo.gui.components;

import javax.swing.JButton;

import ufc.poo.gui.EventPanel;

@SuppressWarnings("serial")
public class NavBar extends EventPanel {
	
    public NavBar() {
    	super();
    	
    	JButton itens = new JButton("itens");
    	itens.addActionListener(e ->{
    		event.firePropertyChange("itensScreen", null, null);
    	});
    	
    	JButton outros = new JButton("outros");
    	outros.addActionListener(e ->{
    		event.firePropertyChange("outrosScreen", null, null);
    	});
    	
    	JButton estatisticas = new JButton("estatisticas");
    	estatisticas.addActionListener(e ->{
    		event.firePropertyChange("estatisticasScreen", null, null);
    	});
    	
    	this.add(itens);
    	this.add(outros);
    	this.add(estatisticas);
    }

}
