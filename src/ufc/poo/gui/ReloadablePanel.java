package ufc.poo.gui;

import java.sql.SQLException;

@SuppressWarnings("serial")
public abstract class ReloadablePanel extends EventPanel {
	public ReloadablePanel() {
		
	}
	
	public abstract void reload() throws SQLException;
}
