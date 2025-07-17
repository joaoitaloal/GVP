package ufc.poo.itens.pecas;

import ufc.poo.database.Database;
import ufc.poo.itens.Item;
import ufc.poo.itens.interfaces.ILavavel;

public class RoupaIntima extends Item implements ILavavel {

	public RoupaIntima(String cor, String tamanho, String conservacao, String nome) {
		super(cor, tamanho, conservacao, nome);
	}

	@Override
	public void registrarLavagem(Database db) {
		// TODO Auto-generated method stub
		
	}

}
