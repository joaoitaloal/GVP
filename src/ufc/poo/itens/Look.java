package ufc.poo.itens;

import ufc.poo.itens.pecas.Acessorio;
import ufc.poo.itens.pecas.PecaInferior;
import ufc.poo.itens.pecas.PecaSuperior;
import ufc.poo.itens.pecas.RoupaIntima;

public class Look {
	private PecaSuperior p_sup;
	private PecaInferior p_inf;
	private RoupaIntima r_int;
	private Acessorio acess;
	private String local;
	
	public Look() {
		this.p_inf = null;
		this.p_sup = null;
		this.r_int = null;
		this.acess = null;
	}
	public Look(PecaSuperior p_sup, PecaInferior p_inf, RoupaIntima r_int, Acessorio acess) {
		this.p_inf = p_inf;
		this.p_sup = p_sup;
		this.r_int = r_int;
		this.acess = acess;
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
	public void modificarAtual(Acessorio acess) {
		this.acess = acess;
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
	public void modificarAtual(PecaSuperior p_sup, PecaInferior p_inf, RoupaIntima r_int, Acessorio acess) {
		this.p_sup = p_sup;
		this.p_inf = p_inf;
		this.r_int = r_int;
		this.acess = acess;
	}
	
	public void deletarAtual() {
		this.p_inf = null;
		this.p_sup = null;
		this.r_int = null;
		this.acess = null;
	}
	
	public PecaSuperior getSup(){
		return this.p_sup;
	}
	
	public PecaInferior getInf(){
		return this.p_inf;
	}

	public RoupaIntima getInt(){
		return this.r_int;
	}
	
	public Acessorio getAcess(){
		return this.acess;
	}
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	public boolean contemItem(Item item) {
		return item.equals(p_sup) || item.equals(p_inf) || item.equals(r_int) || item.equals(acess);
	}
	
}
