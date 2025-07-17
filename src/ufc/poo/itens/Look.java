package ufc.poo.itens;

import java.util.Vector;

import ufc.poo.database.Database;
import ufc.poo.itens.pecas.Acessorio;
import ufc.poo.itens.pecas.PecaInferior;
import ufc.poo.itens.pecas.PecaSuperior;
import ufc.poo.itens.pecas.RoupaIntima;

public class Look {
	PecaSuperior p_sup;
	PecaInferior p_inf;
	RoupaIntima r_int;
	Vector<Acessorio> acess;
	
	public Look() {
		this.p_inf = null;
		this.p_sup = null;
		this.r_int = null;
		this.acess = new Vector<Acessorio>();
	}
	public Look(PecaSuperior p_sup, PecaInferior p_inf, RoupaIntima r_int) {
		this.p_inf = p_inf;
		this.p_sup = p_sup;
		this.r_int = r_int;
		this.acess = new Vector<Acessorio>();
	}
	
	public void modificarAtual(PecaSuperior p_sup) {
		this.p_sup = p_sup;
	}
	public void modificarAtual(PecaInferior p_inf) {
		this.p_inf = p_inf;
	}
	public void modificarAtual(RoupaIntima r_int) {
		this.r_int = r_int;
	}
	public void modificarAtual(PecaSuperior p_sup, PecaInferior p_inf) {
		this.p_sup = p_sup;
		this.p_inf = p_inf;
	}
	public void modificarAtual(PecaSuperior p_sup, RoupaIntima r_int) {
		this.p_sup = p_sup;
		this.r_int = r_int;
	}
	public void modificarAtual(PecaSuperior p_sup, PecaInferior p_inf, RoupaIntima r_int) {
		this.p_sup = p_sup;
		this.p_inf = p_inf;
		this.r_int = r_int;
	}
	
	public void deletarAtual() {
		this.p_inf = null;
		this.p_sup = null;
		this.r_int = null;
		this.acess.clear();
	}
	
	public void inserirAcessorio(Acessorio acessorio) {
		this.acess.add(acessorio);
	}
	public Acessorio removerAcessorio(int i) {
		return this.acess.remove(i);
	}
	
	public String exibirLook() {
		//ToDo: exibir look numa string
		return "";
	}
	
	// talvez mudar essas pro db
	public void registrarLook(Database db) {
		
	}
	public void deletarLook(Database db) {
		
	}
}
