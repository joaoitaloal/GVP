package ufc.poo.gvp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import ufc.poo.database.Database;
import ufc.poo.gui.MainFrame;

public class ClienteGVP {
	private static String curUser;

	public static void main(String[] args) {

        MainFrame jal = new MainFrame("titulo");
        jal.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if("setUser".equals(evt.getPropertyName())) {
					curUser = evt.getNewValue().toString();
				}
			}
		});
	}

}


/*Database banco = new Database();

String leitura = null;
try {
    leitura = banco.readFile();     
} catch (Exception e) {
    e.printStackTrace();
}
if(leitura != null){
    System.out.println(leitura);      
}*/