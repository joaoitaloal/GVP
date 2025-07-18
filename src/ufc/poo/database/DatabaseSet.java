package ufc.poo.database;

import ufc.poo.itens.Item;
import ufc.poo.itens.Look;

// Classe com todos os databases que vão estar abertos de uma vez no programa
public class DatabaseSet {
	private Database usosLook;
	private Database lavagens;
	private Database emprestimos;
	private Database itens;

	//Se der pra fazer os metodos de registrar aqui, massa
	public DatabaseSet(String pessoa) {
		this.usosLook = new Database(pessoa, "usosLook.txt");
		this.lavagens = new Database(pessoa, "lavagens.txt");
		this.emprestimos = new Database(pessoa, "emprestimos.txt");
		this.itens = new Database(pessoa, "itens.txt");
	}
	
	public void adicionarItem(Item item) throws Exception {
		try {
			this.itens.appendLine(item.toString());
		}catch(Exception e) {
			throw e;
		}
	}
	public void atualizarItem(Item itemAntigo, Item itemNovo) {
		
	}
	public void apagarItem(Item item) {
		
	}
	
	public void registrarLavagem(Item item){
		
	}
	
	public void registrarEmprestimo(Item item) {
		
	}
	public void registrarDevolucao(Item item) {
		
	}
	
	public void registrarUsoLook(Look look) {
		
	}
	
}
