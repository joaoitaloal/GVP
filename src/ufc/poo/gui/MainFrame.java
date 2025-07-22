package ufc.poo.gui;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import javax.swing.*;

import ufc.poo.gui.pages.EstatisticasPanel;
import ufc.poo.gui.pages.ListPanel;
import ufc.poo.gui.pages.MainPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    
    public MainFrame(String titulo){
        super(titulo);
        
        JPanel wrapperPanel = new JPanel();
        
        CardLayout cl = new CardLayout();
        wrapperPanel.setLayout(cl);
        
        MainPanel main = new MainPanel();
        wrapperPanel.add(main, "main");
        
        ListPanel list = new ListPanel(this);
        wrapperPanel.add(list, "list");
        
        EstatisticasPanel stats = new EstatisticasPanel(this);
        wrapperPanel.add(stats, "stats");
        
        PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if("itensScreen".equals(evt.getPropertyName())) {
					cl.show(wrapperPanel, "main");
				}else if("outrosScreen".equals(evt.getPropertyName())) {
					try {
						list.reload();
						cl.show(wrapperPanel, "list");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else if("estatisticasScreen".equals(evt.getPropertyName())) {
					try {
						stats.reload();
						cl.show(wrapperPanel, "stats");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		main.addPropertyChangeListener(listener);
		list.addPropertyChangeListener(listener);
		stats.addPropertyChangeListener(listener);
        
        cl.show(wrapperPanel, "main");
        
        setContentPane(wrapperPanel);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
