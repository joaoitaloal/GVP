package ufc.poo.gui.pages;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ufc.poo.database.Estatisticas;
import ufc.poo.gui.ReloadablePanel;
import ufc.poo.gui.components.NavBar;
import ufc.poo.itens.Item;
import ufc.poo.itens.Look;

@SuppressWarnings("serial")
public class EstatisticasPanel extends ReloadablePanel {
    GridBagConstraints gbc;
    
    private Vector<Item> itens;
    private Vector<String> lavagens;
    private Vector<Look> looks;
    
    JPanel statsWrapper;
    
	public EstatisticasPanel(Component parent) {
		super();
		
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		NavBar nav = new NavBar();
		nav.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String evtName = evt.getPropertyName();
				if("itensScreen".equals(evtName) || "outrosScreen".equals(evtName)) {
					event.firePropertyChange(evtName, null, null);
				}
			}
		});
		
		setGridDimensions(0, 1, 1, 1, gbc);
		this.add(nav);
	}
	
	public void reload() throws SQLException {
		try {
			itens = MainPanel.db.getItens();
		} catch (SQLException e) {
			throw e;
		}
		
		try {
			lavagens = MainPanel.db.getLavagens();
		} catch (SQLException e) {
			throw e;
		}
		
		try {
			looks = MainPanel.db.getUsosLook(itens);
		} catch (Exception e) {
			throw e;
		}
		
		Estatisticas estatisticas = new Estatisticas(itens, lavagens, looks);
		
		if(statsWrapper != null) this.remove(statsWrapper);
		
		statsWrapper = new JPanel();
		statsWrapper.setLayout(new GridLayout(0, 1));
		
		statsWrapper.add(new JLabel("Quantidade total de itens: "+estatisticas.getQntdItens()));
		statsWrapper.add(new JLabel("Quantidade total de PecaSuperior: "+estatisticas.getQntdSup()));
		statsWrapper.add(new JLabel("Quantidade total de PecaInferior: "+estatisticas.getQntdInf()));
		statsWrapper.add(new JLabel("Quantidade total de RoupaIntima: "+estatisticas.getQntdInt()));
		statsWrapper.add(new JLabel("Quantidade total de Acessorio: "+estatisticas.getQntdAces()));
		statsWrapper.add(new JLabel("Quantidade total de lavagens: "+estatisticas.getQntdLavagens()));
		
		String maisLavado = estatisticas.getMaisLavado() == null?"NENHUM":estatisticas.getMaisLavado().toString();
		statsWrapper.add(new JLabel("Item mais lavado: "+maisLavado));
		
		statsWrapper.add(new JLabel("Quantidade total de itens emprestados: "+estatisticas.getQntdEmprestados()));

		String maisTempoEmprestado = estatisticas.getMaisTempoEmprestado() == null?"NENHUM":estatisticas.getMaisTempoEmprestado().toString();
		statsWrapper.add(new JLabel("Item emprestado a mais tempo: "+maisTempoEmprestado));
		
		statsWrapper.add(new JLabel("Maior tempo emprestimo(dias): "+estatisticas.getMaiorNumeroTempoEmprestado()));
		statsWrapper.add(new JLabel("Quantidade total looks: "+estatisticas.getQntdLooks()));

		String maisUsado = estatisticas.getMaisUsado() == null?"NENHUM":estatisticas.getMaisUsado().toString();
		statsWrapper.add(new JLabel("Item que mais é usado entre todos os looks: "+maisUsado));
		
		this.setGridDimensions(0, 2, 1, 1, gbc);
		this.add(statsWrapper, gbc);
	}
	
	private void setGridDimensions(int x, int y, int width, int height, GridBagConstraints gbc) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	}
}
