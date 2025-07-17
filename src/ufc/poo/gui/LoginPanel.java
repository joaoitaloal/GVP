package ufc.poo.gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    
	public LoginPanel(){
		super();
		
		JLabel nome = new JLabel("Insira seu nome:");
		JTextField input = new JTextField();
		input.setColumns(25);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(e ->{
			pcs.firePropertyChange("curPanel", "login", "main");
			pcs.firePropertyChange("setUser", "guest", input.getText());
		});
		
		this.add(nome);
		this.add(input);
		this.add(submit);
	}
}
