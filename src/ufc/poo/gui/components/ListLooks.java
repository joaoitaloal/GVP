package ufc.poo.gui.components;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ufc.poo.itens.Look;
import ufc.poo.itens.pecas.Acessorio;
import ufc.poo.itens.pecas.PecaInferior;
import ufc.poo.itens.pecas.PecaSuperior;
import ufc.poo.itens.pecas.RoupaIntima;

@SuppressWarnings("serial")
public class ListLooks extends JPanel {

	public ListLooks(Vector<Look> looks) {
		super();
		this.setLayout(new GridLayout(0, 1));
		
		if(looks.size() == 0) this.add(new JLabel("Nenhum look encontrado", SwingConstants.CENTER));
		
		looks.forEach(look ->{
			JPanel lookPanel = new JPanel();
			lookPanel.setLayout(new GridLayout(1, 0, 50, 0));
			
			PecaSuperior psup = look.getSup();
			PecaInferior pinf = look.getInf();
			RoupaIntima rint = look.getInt();
			Acessorio acess = look.getAcess();
			
			if(psup != null) {
				JLabel psupL = new JLabel(psup.toString(), SwingConstants.CENTER);
				lookPanel.add(psupL);
			}
			if(pinf != null) {
				JLabel pinfL = new JLabel(pinf.toString(), SwingConstants.CENTER);
				lookPanel.add(pinfL);
			}
			if(rint != null) {
				JLabel rintL = new JLabel(rint.toString(), SwingConstants.CENTER);
				lookPanel.add(rintL);
			}
			if(acess != null) {
				JLabel acessL = new JLabel(acess.toString(), SwingConstants.CENTER);
				lookPanel.add(acessL);
			}
			
			JLabel local = new JLabel("Local: "+look.getLocal(), SwingConstants.CENTER);
			lookPanel.add(local);
			
			this.add(lookPanel);
		});
	}
}
