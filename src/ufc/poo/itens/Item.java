package ufc.poo.itens;

public abstract class Item {
	private String cor;
	private String tamanho;
	private String conservacao;
	private String nome;
	private int id;
	
	public Item(String cor, String tamanho, String conservacao, String nome) {
		this.cor = cor;
		this.tamanho = tamanho;
		this.conservacao = conservacao;
		this.nome = nome;
	}
	public Item(Item copia) {
		this.cor = copia.getCor();
		this.tamanho = copia.getTamanho();
		this.conservacao = copia.getConservacao();
		this.nome = copia.getNome();
		setId(copia.getId());
	}
	
	public String toString() {
		return "Nome: "+ nome + "; Cor: " + cor + "; Tamanho: " + tamanho + "; Conservação: " + conservacao;
	}
	
	public String getTamanho() {
		return tamanho;
	}
	
	public String getConservacao() {
		return conservacao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCor() {
		return cor;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	abstract public String getTipo();
}
