package ufc.poo.gui.components;

import java.awt.GridLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ufc.poo.itens.Item;
import ufc.poo.itens.interfaces.IEmprestavel;
import ufc.poo.itens.interfaces.ILavavel;
import ufc.poo.itens.pecas.Acessorio;
import ufc.poo.itens.pecas.PecaInferior;
import ufc.poo.itens.pecas.PecaSuperior;
import ufc.poo.itens.pecas.RoupaIntima;

@SuppressWarnings("serial")
public class ListItens extends JPanel {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final int inputSize = 10;
    private Vector<Item> checked;
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    
	public ListItens(Vector<Item> itens) {
		super();
		this.setLayout(new GridLayout(0, 1));
		this.checked = new Vector<Item>();
		
		if(itens.size() == 0) this.add(new JLabel("Nenhum item encontrado", SwingConstants.CENTER));
		
		itens.forEach(item ->{
			JPanel itemPanel = new JPanel();
			
			JCheckBox check = new JCheckBox();
			check.addActionListener(e ->{
				if(check.isSelected()) {
					if(checked.size() > 3) {
						JOptionPane.showMessageDialog(null, "No máximo quatro itens!", "Aviso", JOptionPane.WARNING_MESSAGE);
						check.setSelected(false);
					}else {
						boolean unico = true;
						for(Item checkedItem : checked) {
							if(checkedItem.getTipo().equals(item.getTipo())) {
								unico = false;
							}
						}
						if(unico)
							checked.add(item);
						else {
							JOptionPane.showMessageDialog(null, "Um item do tipo " + item.getTipo() + " já foi adicionado!", "Aviso", JOptionPane.WARNING_MESSAGE);
							check.setSelected(false);
						}
					}
				}else {
					checked.remove(item);
				}
				pcs.firePropertyChange("checkChange", null, checked.clone());
			});
			itemPanel.add(check);

			JLabel itemId = new JLabel(Integer.toString(item.getId()));
			JLabel itemTipo = new JLabel(item.getTipo());
			JTextField itemNome = new JTextField(item.getNome(), inputSize);
			JTextField itemCor = new JTextField(item.getCor(), inputSize);
			JTextField itemConservacao = new JTextField(item.getConservacao(), inputSize);
			JTextField itemTamanho = new JTextField(item.getTamanho(), inputSize);

			itemPanel.add(itemId);
			itemPanel.add(itemTipo);
			itemPanel.add(itemNome);
			itemPanel.add(itemCor);
			itemPanel.add(itemConservacao);
			itemPanel.add(itemTamanho);
			
			if(item instanceof IEmprestavel) {
				LocalDate emprestimo = ((IEmprestavel) item).getEmprestimo();
				String text = emprestimo == null?"NÃO":emprestimo.toString();
				
				JLabel emprestimoLabel = new JLabel("Emprestado: "+text);
				itemPanel.add(emprestimoLabel);
			}
			
			JButton salvar = new JButton("Salvar");
			salvar.addActionListener(e ->{
				
				String corInput = itemCor.getText();
				String tamanhoInput = itemTamanho.getText();
				String conservacaoInput = itemConservacao.getText();
				String nomeInput = itemNome.getText();
				
				if("".equals(corInput) || "".equals(tamanhoInput) || "".equals(conservacaoInput)|| "".equals(nomeInput)) {
					JOptionPane.showMessageDialog(null, "Não são permitidas entradas vazias!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				switch (item.getTipo()){
					case "PecaSuperior":
						PecaSuperior sup = new PecaSuperior(corInput, tamanhoInput, conservacaoInput, nomeInput);
						sup.setId(item.getId());
						pcs.firePropertyChange("modify", null, sup);
						break;
					case "PecaInferior":
						PecaInferior inf = new PecaInferior(corInput, tamanhoInput, conservacaoInput, nomeInput);
						inf.setId(item.getId());
						pcs.firePropertyChange("modify", null, inf);
						break;
					case "RoupaIntima":
						RoupaIntima intim = new RoupaIntima(corInput, tamanhoInput, conservacaoInput, nomeInput);
						intim.setId(item.getId());
						pcs.firePropertyChange("modify", null, intim);
						break;
					case "Acessorio":
						Acessorio aces = new Acessorio(corInput, tamanhoInput, conservacaoInput, nomeInput);
						aces.setId(item.getId());
						pcs.firePropertyChange("modify", null, aces);
						break;
				}
			});
			
			JButton deletar = new JButton("Deletar");
			deletar.addActionListener(e ->{
				pcs.firePropertyChange("delete", null, item.getId());
			});
			
			itemPanel.add(salvar);
			itemPanel.add(deletar);
			
			if(item instanceof IEmprestavel) {
				JButton emprestar = new JButton("Emprestimo");
				emprestar.addActionListener(e ->{
					pcs.firePropertyChange("emprestimo", null, item);
				});
				itemPanel.add(emprestar);
				
				JButton devolver = new JButton("Devolução");
				devolver.addActionListener(e ->{
					pcs.firePropertyChange("devolucao", null, item);
				});
				itemPanel.add(devolver);
			}
			if(item instanceof ILavavel) {
				JButton lavar = new JButton("Lavar");
				lavar.addActionListener(e ->{
					pcs.firePropertyChange("lavagem", null, item);
				});
				itemPanel.add(lavar);
			}
			
			this.add(itemPanel);
		});
	}
}
