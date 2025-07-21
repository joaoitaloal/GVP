package ufc.poo.itens.pecas;

import java.sql.SQLException;
import java.util.Date;

import ufc.poo.database.Database;
import ufc.poo.itens.Item;
import ufc.poo.itens.interfaces.ILavavel;

public class RoupaIntima extends Item implements ILavavel {

	public RoupaIntima(String cor, String tamanho, String conservacao, String nome) {
		super(cor, tamanho, conservacao, nome);
	}
	
	public String toString() {
		return super.toString() + "; Tipo: " + getTipo();
	}

	@Override
	public void registrarLavagem(Database db) throws SQLException {
		try {
			db.insertLavagem(new Date().toString(), this.getId());
		} catch (SQLException e) {
			throw e;
		}
		
	}

	@Override
	public String getTipo() {
		return "RoupaIntima";
	}

}
