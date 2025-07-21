package ufc.poo.itens.pecas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ufc.poo.itens.Item;
import ufc.poo.itens.interfaces.IEmprestavel;

public class Acessorio extends Item implements IEmprestavel {
	private LocalDate dataEmprestimo;

	public Acessorio(String cor, String tamanho, String conservacao, String nome) {
		super(cor, tamanho, conservacao, nome);
		dataEmprestimo = null;
	}

	public String toString() {
		return super.toString() + "; Tipo: " + getTipo();
	}

	@Override
	public String getTipo() {
		return "Acessorio";
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
