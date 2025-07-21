package ufc.poo.itens.interfaces;

import java.time.LocalDate;

public interface IEmprestavel {
	public void registrarEmprestimo(LocalDate data);
	public int quantidadeDeDiasDesdeOEmprestimo();
	public void registrarDevolucao(LocalDate data);
	public LocalDate getEmprestimo();
}
