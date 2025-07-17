package ufc.poo.gui;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	//Se eu usar esse pcs em todos os frames vale criar uma classe separada pra padronizar legal
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    
    public MainFrame(String titulo){
        super(titulo);
        
        JPanel wrapperPanel = new JPanel();
        
        CardLayout cl = new CardLayout();
        wrapperPanel.setLayout(cl);
        
        LoginPanel login = new LoginPanel();
        login.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if("curPanel".equals(evt.getPropertyName())) {
					cl.show(wrapperPanel, evt.getNewValue().toString());
				}else if("setUser".equals(evt.getPropertyName())) {
					pcs.firePropertyChange("setUser", evt.getOldValue(), evt.getNewValue());
				}
			}
		});
        
        MainPanel main = new MainPanel();
        
        wrapperPanel.add(main, "main");
        wrapperPanel.add(login, "login");
        
        cl.show(wrapperPanel, "login");
        
        setContentPane(wrapperPanel);
        
        pack();
        setVisible(true);
    }
}
