package ufc.poo.itens.pecas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import ufc.poo.database.Database;
import ufc.poo.itens.Item;
import ufc.poo.itens.interfaces.IEmprestavel;
import ufc.poo.itens.interfaces.ILavavel;

public class PecaInferior extends Item implements ILavavel, IEmprestavel {
	private LocalDate dataEmprestimo;

	public PecaInferior(String cor, String tamanho, String conservacao, String nome) {
		super(cor, tamanho, conservacao, nome);
		dataEmprestimo = null;
	}

	@Override
	public void registrarLavagem(Database db) throws SQLException {
		try {
			db.insertLavagem(new Date().toString(), this.getId());
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public String toString() {
		return super.toString() + "; Tipo: " + getTipo();
	}

	@Override
	public String getTipo() {
		return "PecaInferior";
	}

	@Override
	public void registrarEmprestimo(LocalDate data) {
		this.dataEmprestimo = data;
	}

	@Override
	public int quantidadeDeDiasDesdeOEmprestimo() {
		if(dataEmprestimo == null) return -1;
		return (int) ChronoUnit.DAYS.between(dataEmprestimo, LocalDate.now());
	}

	@Override
	public void registrarDevolucao(LocalDate data) {
		dataEmprestimo = null;
	}
	
	@Override
	public LocalDate getEmprestimo() {
		return dataEmprestimo;
	}

}
