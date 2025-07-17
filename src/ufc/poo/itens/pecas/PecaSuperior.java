package ufc.poo.itens.pecas;

import ufc.poo.database.Database;
import ufc.poo.itens.Item;
import ufc.poo.itens.interfaces.IEmprestavel;
import ufc.poo.itens.interfaces.ILavavel;

public class PecaSuperior extends Item implements ILavavel, IEmprestavel {
	String nome;

	public PecaSuperior(String cor, String tamanho, String conservacao, String nome) {
		super(cor, tamanho, conservacao, nome);
	}

	@Override
	public void registrarEmprestimo(Database db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int quantidadeDeDiasDesdeOEmprestimo(Database db) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void registrarDevolucao(Database db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarLavagem(Database db) {
		// TODO Auto-generated method stub
		
	}
	
}
