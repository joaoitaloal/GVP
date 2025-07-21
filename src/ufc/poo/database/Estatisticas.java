package ufc.poo.database;

import java.sql.SQLException;
import java.util.Vector;

import ufc.poo.gui.MainPanel;
import ufc.poo.itens.Item;
import ufc.poo.itens.Look;
import ufc.poo.itens.interfaces.IEmprestavel;
import ufc.poo.itens.interfaces.ILavavel;

/*Estatisticas:
	Quantidade de itens
	Quantidade de cada tipo de peca
	Quantidade de lavagens
	Item mais lavado
	Quantidade de itens emprestados
	Item emprestado a mais tempo
	Quantidade de looks
	Item que mais aparece entre todos os looks
*/

public class Estatisticas {
	private int qntdItens = 0;
	private int qntdSup = 0;
	private int qntdInf = 0;
	private int qntdInt = 0;
	private int qntdAces = 0;
	
	private int qntdLavagens = 0;
	private int maiorNumeroLavagens = 0;
	private Item maisLavado = null;
	
	private int qntdEmprestados = 0;
	private int maiorNumeroTempoEmprestado = 0;
	private Item maisTempoEmprestado = null;
	
	private int qntdLooks = 0;
	private int maiorNumeroUsos = 0;
	private Item maisUsado = null;
	
	public Estatisticas(Vector<Item> itens, Vector<String> lavagens, Vector<Look> looks) {		
		qntdItens = itens.size();
		qntdLavagens = lavagens.size();
		qntdLooks = looks.size();
		
		for(Item item : itens) {
			if("PecaSuperior".equals(item.getTipo())) qntdSup = getQntdSup() + 1;
			else if("PecaInferior".equals(item.getTipo())) qntdInf = getQntdInf() + 1;
			else if("RoupaIntima".equals(item.getTipo())) qntdInt = getQntdInt() + 1;
			else if("Acessorio".equals(item.getTipo())) qntdAces = getQntdAces() + 1;
			
			if(item instanceof IEmprestavel) {
				IEmprestavel emprestado = (IEmprestavel) item;
				if(emprestado.getEmprestimo() != null) {
					qntdEmprestados = getQntdEmprestados() + 1;
					
					int dias = emprestado.quantidadeDeDiasDesdeOEmprestimo();
					if(dias >= maiorNumeroTempoEmprestado) {
						maiorNumeroTempoEmprestado = dias;
						maisTempoEmprestado = item;
					}
				}
			}
			
			if(item instanceof ILavavel) {
				try {
					int numeroLavagens = MainPanel.db.getQntdLavagens(item.getId());
					if(numeroLavagens > maiorNumeroLavagens) {
						maiorNumeroLavagens = numeroLavagens;
						maisLavado = item;
					}
				} catch (SQLException e) {
					System.out.println("Um erro ocorreu e portanto um item foi ignorado: ");
					e.printStackTrace();
				}
			}
			
			try {
				int numeroUsos = MainPanel.db.getQntdUsoItem(item.getId());
				if(numeroUsos > maiorNumeroUsos) {
					maiorNumeroUsos = numeroUsos;
					maisUsado = item;
				}
			} catch (SQLException e) {
				System.out.println("Um erro ocorreu e portanto um item foi ignorado: ");
				e.printStackTrace();
			}
			
		}
		
		
	}

	public int getQntdItens() {
		return qntdItens;
	}

	public int getQntdSup() {
		return qntdSup;
	}

	public int getQntdInf() {
		return qntdInf;
	}

	public int getQntdInt() {
		return qntdInt;
	}

	public int getQntdAces() {
		return qntdAces;
	}

	public int getQntdLavagens() {
		return qntdLavagens;
	}

	public Item getMaisLavado() {
		return maisLavado;
	}

	public int getQntdEmprestados() {
		return qntdEmprestados;
	}

	public Item getMaisTempoEmprestado() {
		return maisTempoEmprestado;
	}

	public int getQntdLooks() {
		return qntdLooks;
	}

	public Item getMaisUsado() {
		return maisUsado;
	}
	
	public int getMaiorNumeroTempoEmprestado() {
		return maiorNumeroTempoEmprestado;
	}
}
