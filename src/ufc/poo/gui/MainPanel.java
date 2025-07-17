package ufc.poo.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ufc.poo.database.DatabaseSet;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	DatabaseSet db; 
	
	public MainPanel() {
		super();
		
		JLabel teste = new JLabel("teste");
		this.add(teste);
	}
}
