package ufc.poo.itens;

public abstract class Item {
	private String cor;
	private String tamanho;
	private String conservacao;
	private String nome;
	
	public Item(String cor, String tamanho, String conservacao, String nome) {
		this.cor = cor;
		this.tamanho = tamanho;
		this.conservacao = conservacao;
		this.nome = nome;
	}
	
	//Registrar item no banco? ou coloco isso no banco?
	public String toString() {
		return nome + ":" + tamanho + ":" + conservacao + ":";
	}
}
