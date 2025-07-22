package ufc.poo.gui.pages;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ufc.poo.database.Database;
import ufc.poo.gui.EventPanel;
import ufc.poo.gui.components.InputNewItem;
import ufc.poo.gui.components.ListItens;
import ufc.poo.gui.components.NavBar;
import ufc.poo.itens.Item;
import ufc.poo.itens.Look;
import ufc.poo.itens.interfaces.IEmprestavel;
import ufc.poo.itens.pecas.*;

@SuppressWarnings("serial")
public class MainPanel extends EventPanel {
    
	public static Database db;
	private Vector<Item> itens;
	private Vector<Item> checked;
	
	private static ListItens listItens;
	private static JScrollPane scroll;
	
	public MainPanel() {
		super();
		scroll = null;
		
		try {
			MainPanel.db = new Database();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			this.itens = db.getItens();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.checked = new Vector<Item>();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		NavBar nav = new NavBar();
		nav.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String evtName = evt.getPropertyName();
				if("outrosScreen".equals(evtName) || "estatisticasScreen".equals(evtName)) {
					event.firePropertyChange(evtName, null, null);
				}
			}
		});
		
		setGridDimensions(0, 1, 1, 1, gbc);
		this.add(nav, gbc);
		
		JPanel inputNewLooks = new JPanel();
		
		JLabel local = new JLabel("Local: ");
		JTextField localI = new JTextField(10);
		JButton novoLook = new JButton("Criar look");
		novoLook.addActionListener(e ->{
			PecaSuperior psup = null; 
			PecaInferior pinf = null; 
			RoupaIntima rint = null; 
			Acessorio aces = null;
			
			if("".equals(localI.getText())) {
				JOptionPane.showMessageDialog(null, "Não são permitidas entradas vazias!", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			} 
			
			for(Item item : checked) {
				switch(item.getTipo()) {
					case "PecaSuperior":
						psup = (PecaSuperior) item;
						break;
					case "PecaInferior":
						pinf = (PecaInferior) item;
						break;
					case "RoupaIntima":
						rint = (RoupaIntima) item;
						break;
					case "Acessorio":
						aces = (Acessorio) item;
						break;
				}
			}
			
			Look look = new Look(psup, pinf, rint, aces);
			look.setLocal(localI.getText());
			
			try {
				db.insertUsoLook(look);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		inputNewLooks.add(local);
		inputNewLooks.add(localI);
		inputNewLooks.add(novoLook);
		
		setGridDimensions(0, 2, 1, 1, gbc);
		this.add(inputNewLooks, gbc);
		
		InputNewItem addSup = new InputNewItem("PecaSuperior");
		addSup.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if("addSup".equals(evt.getPropertyName())) {
					PecaSuperior toAddSup = (PecaSuperior) evt.getNewValue();
					try {
						toAddSup.setId(MainPanel.db.insertItem(toAddSup));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					itens.add(toAddSup);
					updateItens(gbc);
				}
			}
		});
		
		InputNewItem addInf = new InputNewItem("PecaInferior");
		addInf.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if("addInf".equals(evt.getPropertyName())) {
					PecaInferior toAddInf = (PecaInferior) evt.getNewValue();
					try {
						toAddInf.setId(MainPanel.db.insertItem(toAddInf));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					itens.add(toAddInf);
					updateItens(gbc);
				}
			}
		});
		
		InputNewItem addInt = new InputNewItem("RoupaIntima");
		addInt.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if("addInt".equals(evt.getPropertyName())) {
					RoupaIntima toAddInt = (RoupaIntima) evt.getNewValue();
					try {
						toAddInt.setId(MainPanel.db.insertItem(toAddInt));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					itens.add(toAddInt);
					updateItens(gbc);
				}
			}
		});
		
		InputNewItem addAces = new InputNewItem("Acessorio");
		addAces.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if("addAces".equals(evt.getPropertyName())) {
					Acessorio toAddAces = (Acessorio) evt.getNewValue();
					try {
						toAddAces.setId(MainPanel.db.insertItem(toAddAces));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					itens.add(toAddAces);
					updateItens(gbc);
				}
			}
		});
		
		this.setGridDimensions(0, 3, 1, 1, gbc);
		this.add(addSup, gbc);
		
		this.setGridDimensions(0, 4, 1, 1, gbc);
		this.add(addInf, gbc);
		
		this.setGridDimensions(0, 5, 1, 1, gbc);
		this.add(addInt, gbc);
		
		this.setGridDimensions(0, 6, 1, 1, gbc);
		this.add(addAces, gbc);
		
		updateItens(gbc);
		
	}
	
	private void updateItens(GridBagConstraints gbc) {
		if(scroll != null)
			this.remove(scroll);
		
		itens.sort(new Comparator<Item>() {
			public int compare(Item item1, Item item2) {
				return Integer.compare(item1.getId(), item2.getId());
			}
		});	
		
		listItens = new ListItens(itens);
		listItens.addPropertyChangeListener(new PropertyChangeListener() {
			@SuppressWarnings("unchecked")
			public void propertyChange(PropertyChangeEvent evt) {
				if("delete".equals(evt.getPropertyName())) {
					int id = Integer.parseInt(evt.getNewValue().toString());
					try {
						MainPanel.db.deleteItem(id);
						MainPanel.db.deleteLavagem(id);
						MainPanel.db.deleteUsoLook(id);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					Item rmv = null;
					for(Item item : itens) {
						if(item.getId() == id) {
							rmv = item;
							break;
						}
					}
					itens.remove(rmv);
					
					updateItens(gbc);
				}else if("modify".equals(evt.getPropertyName())) {
					Item itemNovo = (Item) evt.getNewValue();
					try {
						db.updateItem(itemNovo.getId(), itemNovo);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					Item change = null;
					for(Item item : itens) {
						if(itemNovo.getId() == item.getId()) {
							change = item;
							break;
						}
					}
					itens.remove(change);
					itens.add(itemNovo);
					
					updateItens(gbc);
				}else if("emprestimo".equals(evt.getPropertyName())) {
					Item itemEmprestado = (Item) evt.getNewValue();
					LocalDate data = LocalDate.now();
					
					try {
						db.setEmprestimo(itemEmprestado.getId(), data);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					for(Item item : itens) {
						if(itemEmprestado.getId() == item.getId()) {
							((IEmprestavel) item).registrarEmprestimo(data);
						}
					}
					
					updateItens(gbc);
				}else if("devolucao".equals(evt.getPropertyName())) {
					Item itemEmprestado = (Item) evt.getNewValue();
					LocalDate data = null;
					
					try {
						db.setEmprestimo(itemEmprestado.getId(), data);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					for(Item item : itens) {
						if(itemEmprestado.getId() == item.getId()) {
							((IEmprestavel) item).registrarEmprestimo(data);
						}
					}
					
					updateItens(gbc);
				}else if("lavagem".equals(evt.getPropertyName())) {
					Item itemLavado = (Item) evt.getNewValue();
					
					try {
						db.insertLavagem(LocalDate.now().toString(), itemLavado.getId());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else if("checkChange".equals(evt.getPropertyName())) {
					Object val = evt.getNewValue();
					if(val instanceof Vector<?>)
						checked = (Vector<Item>) val;
				}
			}
		});
		
		scroll = new JScrollPane(listItens);
		scroll.setPreferredSize(new Dimension(1500, 200));
		this.setGridDimensions(0, 7, 1, 1, gbc);
		this.add(scroll, gbc);
		
		this.validate();
	}
	
	private void setGridDimensions(int x, int y, int width, int height, GridBagConstraints gbc) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	}
}
