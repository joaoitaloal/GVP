package ufc.poo.itens.interfaces;

import java.sql.SQLException;

import ufc.poo.database.Database;

public interface ILavavel {
	public void registrarLavagem(Database db) throws SQLException;
}
