package ufc.poo.itens.interfaces;

import ufc.poo.database.Database;

public interface IEmprestavel {
	public void registrarEmprestimo(Database db);
	public int quantidadeDeDiasDesdeOEmprestimo(Database db);
	public void registrarDevolucao(Database db);
}
