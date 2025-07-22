package ufc.poo.gui.components;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ufc.poo.gui.EventPanel;
import ufc.poo.itens.pecas.Acessorio;
import ufc.poo.itens.pecas.PecaInferior;
import ufc.poo.itens.pecas.PecaSuperior;
import ufc.poo.itens.pecas.RoupaIntima;

@SuppressWarnings("serial")
public class InputNewItem extends EventPanel {
    
	private JTextField nomeI;
	private JTextField corI;
	private JTextField conservacaoI;
	private JTextField tamanhoI;
	
	public InputNewItem(String tipo) {
		super();
		
		JLabel addLabel = new JLabel("Adicionar "+tipo+": ");
		this.add(addLabel);
		
		JLabel nomeL = new JLabel("nome: ");
		nomeI = new JTextField();
		nomeI.setColumns(10);
		
		JLabel corL = new JLabel("cor: ");
		corI = new JTextField();
		corI.setColumns(10);
		
		JLabel conservacaoL = new JLabel("conservação: ");
		conservacaoI = new JTextField();
		conservacaoI.setColumns(10);
		
		JLabel tamanhoL = new JLabel("tamanho: ");
		tamanhoI = new JTextField();
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
			
			String corInput = corI.getText();
			String tamanhoInput = tamanhoI.getText();
			String conservacaoInput = conservacaoI.getText();
			String nomeInput = nomeI.getText();
			
			if("".equals(corInput) || "".equals(tamanhoInput) || "".equals(conservacaoInput)|| "".equals(nomeInput)) {
				JOptionPane.showMessageDialog(null, "Não são permitidas entradas vazias!", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			} 
			
			switch (tipo){
				case "PecaSuperior":
					PecaSuperior sup = new PecaSuperior(corInput, tamanhoInput, conservacaoInput, nomeInput);
					event.firePropertyChange("addSup", null, sup);
					break;
				case "PecaInferior":
					PecaInferior inf = new PecaInferior(corInput, tamanhoInput, conservacaoInput, nomeInput);
					event.firePropertyChange("addInf", null, inf);
					break;
				case "RoupaIntima":
					RoupaIntima intim = new RoupaIntima(corInput, tamanhoInput, conservacaoInput, nomeInput);
					event.firePropertyChange("addInt", null, intim);
					break;
				case "Acessorio":
					Acessorio aces = new Acessorio(corInput, tamanhoInput, conservacaoInput, nomeInput);
					event.firePropertyChange("addAces", null, aces);
					break;
			}
			
		});
		this.add(add);
		this.setAlignmentX(CENTER_ALIGNMENT);
	}
}
