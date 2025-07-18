package ufc.poo.gui;

import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ufc.poo.database.DatabaseSet;
import ufc.poo.itens.pecas.PecaSuperior;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	public static DatabaseSet db; 
	private Container parent;
	
    @Override
    public void addNotify() {
        super.addNotify();  // Ensure the default behavior happens
        this.parent = getParent().getParent().getParent().getParent();
        System.out.println(parent);
        parent.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if("setUser".equals(evt.getPropertyName())) {
					System.out.println(evt.getNewValue().toString());
					db = new DatabaseSet(evt.getNewValue().toString());
				}
			}
		});
    }
	
	public MainPanel() {
		super();
		
		/*Container parent = getParent().getParent(); // Tem um wrapper entre ele e o parent
		parent.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if("setUser".equals(evt.getPropertyName())) {
					db = new DatabaseSet(evt.getNewValue().toString());
				}
			}
		});*/
		
		// Teria um desse pra cada tipo de peça, ai cria a respectiva peça dependendo da area que o usuario tiver usando, botar no toString de cada peça uma adição que diga que tipo de peça é
		
		JLabel addLabel = new JLabel("Adicionar item: ");
		JLabel nomeL = new JLabel("nome: ");
		JTextField nomeI = new JTextField();
		nomeI.setColumns(10);
		
		JLabel corL = new JLabel("cor: ");
		JTextField corI = new JTextField();
		corI.setColumns(10);
		
		JLabel conservacaoL = new JLabel("conservação: ");
		JTextField conservacaoI = new JTextField();
		conservacaoI.setColumns(10);
		
		JLabel tamanhoL = new JLabel("tamanho: ");
		JTextField tamanhoI = new JTextField();
		tamanhoI.setColumns(10);
		
		this.add(nomeL);
		this.add(nomeI);
		
		this.add(corL);
		this.add(corI);
		
		this.add(conservacaoL);
		this.add(conservacaoI);
		
		this.add(tamanhoL);
		this.add(tamanhoI);
		
		JButton add = new JButton("Adicionar");
		add.addActionListener(e ->{
			try {
				db.adicionarItem(new PecaSuperior(corI.getText(), tamanhoI.getText(), conservacaoI.getText(), nomeI.getText()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		this.add(add);
		
	}
}
