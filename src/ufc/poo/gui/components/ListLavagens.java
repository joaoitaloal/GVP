package ufc.poo.gui.components;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ListLavagens extends JPanel {
	public ListLavagens(Vector<String> lavagens) {
		super();
		this.setLayout(new GridLayout(0, 1));
		
		if(lavagens.size() == 0) this.add(new JLabel("Nenhuma lavagem encontrada", SwingConstants.CENTER));
		
		lavagens.forEach(lavagem ->{
			JPanel entryPanel = new JPanel();
			
			JLabel label = new JLabel("Lavagem: "+lavagem);
			
			entryPanel.add(label);
			
			this.add(entryPanel);
		});
	}
}
