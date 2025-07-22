package ufc.poo.gui.pages;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;

import ufc.poo.gui.ReloadablePanel;
import ufc.poo.gui.components.ListLavagens;
import ufc.poo.gui.components.ListLooks;
import ufc.poo.gui.components.NavBar;
import ufc.poo.itens.Item;
import ufc.poo.itens.Look;

@SuppressWarnings("serial")
public class ListPanel extends ReloadablePanel {
    private Vector<String> lavagens;
    private Vector<Look> looks;
    
    private JScrollPane scrollLavagens;
    private JScrollPane scrollLooks;
    
	public ListPanel(Component parent) {
		super();
		this.scrollLavagens = null;
		this.scrollLooks = null;
		
		this.setLayout(new GridLayout(0, 1));
		
		NavBar nav = new NavBar();
		nav.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String evtName = evt.getPropertyName();
				if("itensScreen".equals(evtName) || "estatisticasScreen".equals(evtName)) {
					event.firePropertyChange(evtName, null, null);
				}
			}
		});
		
		this.add(nav);
	}
	
	public void reload() throws SQLException {
		try {
			lavagens = MainPanel.db.getLavagens();
		} catch (SQLException e) {
			throw e;
		}
		
		Vector<Item> itens;
		try {
			itens = MainPanel.db.getItens();
			looks = MainPanel.db.getUsosLook(itens);
		} catch (Exception e) {
			throw e;
		}
		
		if(scrollLavagens != null) this.remove(scrollLavagens);
		ListLavagens lavagensPanel = new ListLavagens(lavagens);

		scrollLavagens = new JScrollPane(lavagensPanel);
		scrollLavagens.setPreferredSize(new Dimension(1500, 200));
		
		this.add(scrollLavagens);
		
		if(scrollLooks != null) this.remove(scrollLooks);
		ListLooks looksPanel = new ListLooks(looks);

		scrollLooks = new JScrollPane(looksPanel);
		scrollLooks.setPreferredSize(new Dimension(1500, 200));
		
		this.add(scrollLooks);
		
		this.validate();
	}
}

	