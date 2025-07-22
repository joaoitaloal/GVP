package ufc.poo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Vector;

import ufc.poo.itens.Item;
import ufc.poo.itens.Look;
import ufc.poo.itens.pecas.Acessorio;
import ufc.poo.itens.pecas.PecaInferior;
import ufc.poo.itens.pecas.PecaSuperior;
import ufc.poo.itens.pecas.RoupaIntima;

public class Database {
	private Connection conn;
	
    public Database() throws SQLException {
    	String url = "jdbc:sqlite:mydatabase.db";
    	try {
			this.conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw e;
		}

    	Statement st = conn.createStatement();
    	
    	String sql = "CREATE TABLE IF NOT EXISTS itens (\n"
                + " id integer PRIMARY KEY,\n"
                + " nome text NOT NULL,\n"
                + " cor text NOT NULL,\n"
                + " tamanho text NOT NULL,\n"
                + " conservacao text NOT NULL,\n"
                + " tipo text NOT NULL,\n"
                + " emprestado boolean NOT NULL\n"
                + ");";
    	
    	st.execute(sql);
    	
    	sql = "CREATE TABLE IF NOT EXISTS usosLook (\n"
                + " id integer PRIMARY KEY,\n"
                + " idSup integer NULL,\n"
                + " idInf integer NULL,\n"
                + " idInt integer NULL,\n"
                + " idAces integer NULL,\n"
                + " local text NOT NULL\n"
                + ");";
    	
    	st.execute(sql);
    	
    	sql = "CREATE TABLE IF NOT EXISTS lavagens (\n"
                + " id integer PRIMARY KEY,\n"
    			+ " idItem integer NOT NULL,\n"
                + " data text NOT NULL\n"
                + ");";
    	
    	st.execute(sql);
    	
    	st.close();
    	
    }
    
    public int insertItem(Item item) throws SQLException {
    	String sql = "INSERT INTO itens(nome, cor, tamanho, conservacao, tipo, emprestado) VALUES(?, ?, ?, ?, ?, ?)";
    	int id = -1;
    	
    	try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	pst.setString(1, item.getNome());
        	pst.setString(2, item.getCor());
        	pst.setString(3, item.getTamanho());
        	pst.setString(4, item.getConservacao());
        	pst.setString(5, item.getTipo());
        	pst.setString(6, "NÃO");
        	
        	int changed = pst.executeUpdate();
        	
        	if(changed > 0) {
        		ResultSet rs = pst.getGeneratedKeys();
        		rs.next();
        		
        		id = rs.getInt(1);
        	}
    	}catch(SQLException e) {
    		throw e;
    	}
    	
    	return id;
    }
    
	public void updateItem(int id, Item itemNovo) throws SQLException {
		String sql = "UPDATE itens SET nome = ?, cor = ?, tamanho = ?, conservacao = ?, tipo = ? "
				+ "WHERE id = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, itemNovo.getNome());
			pst.setString(2, itemNovo.getCor());
			pst.setString(3, itemNovo.getTamanho());
			pst.setString(4, itemNovo.getConservacao());
			pst.setString(5, itemNovo.getTipo());
			pst.setInt(6, id);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}
	public void deleteItem(int id) throws SQLException {
		String sql = "DELETE FROM itens WHERE id = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, id);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		
	}
	
	public int getQntdLavagens(int idItem) throws SQLException {
		String sql = "SELECT COUNT(*) FROM lavagens WHERE idItem = ?";
		int qntd = 0;
		
    	try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	
        	pst.setInt(1, idItem);
        	
        	ResultSet rs = pst.executeQuery();

        	if (rs.next()) {
        	    qntd = rs.getInt(1);
        	}
    	}catch(SQLException e) {
    		throw e;
    	}
    	
    	return qntd;
	}
	
	public void insertLavagem(String data, int idItem) throws SQLException{
		String sql = "INSERT INTO lavagens(idItem, data) VALUES(?, ?)";
    	
    	try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	
        	pst.setInt(1, idItem);
        	pst.setString(2, data);
        	
        	pst.executeUpdate();
    	}catch(SQLException e) {
    		throw e;
    	}
	}
	
	// deletar lavagens que tão relacionadas com o id idItem
	public void deleteLavagem(int idItem) throws SQLException {
		String sql = "DELETE FROM lavagens WHERE idItem = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, idItem);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void setEmprestimo(int id, LocalDate emprestado) throws SQLException {
		String sql = "UPDATE itens SET emprestado = ? WHERE id = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			if(emprestado != null)
				pst.setString(1, emprestado.toString());
			else
				pst.setString(1, "NÃO");
			pst.setInt(2, id);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public int getQntdUsoItem(int idItem) throws SQLException {
		String sql = "SELECT COUNT(*) FROM usosLook WHERE idSup = ? OR idInf = ? OR idInt = ? OR idAces = ?";
		int qntd = 0;
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, idItem);
			pst.setInt(2, idItem);
			pst.setInt(3, idItem);
			pst.setInt(4, idItem);
			
			ResultSet rs = pst.executeQuery();

        	if (rs.next()) {
        	    qntd = rs.getInt(1);
        	}
		} catch (SQLException e) {
			throw e;
		}
		
		return qntd;
	}
	
	public void insertUsoLook(Look look) throws Exception {
		String sql = "INSERT INTO usosLook(idSup, idInf, idInt, idAces, local) VALUES(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			PecaSuperior supId = look.getSup();
			PecaInferior infId = look.getInf();
			RoupaIntima intId = look.getInt();
			Acessorio acesId = look.getAcess();
			
			if(supId != null)
				pst.setInt(1, supId.getId());
			else
				pst.setNull(1, Types.INTEGER);
			
			if(infId != null)
				pst.setInt(2,infId.getId());
			else
				pst.setNull(2, Types.INTEGER);
			
			if(intId != null)
				pst.setInt(3, intId.getId());
			else
				pst.setNull(3, Types.INTEGER);
			
			if(acesId != null)
				pst.setInt(4, acesId.getId());
			else
				pst.setNull(4, Types.INTEGER);
			
			pst.setString(5, look.getLocal());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void deleteUsoLook(int idItem) throws SQLException {
		String sql = "DELETE FROM usosLook WHERE idSup = ? OR idInf = ? OR idInt = ? OR idAces = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, idItem);
			pst.setInt(2, idItem);
			pst.setInt(3, idItem);
			pst.setInt(4, idItem);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}
    
    public Vector<Item> getItens() throws SQLException{
    	String sql = "SELECT id, nome, cor, tamanho, conservacao, tipo, emprestado FROM itens";
    	Vector<Item> itens = new Vector<Item>();
    	
    	try {
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cor = rs.getString("cor");
				String tamanho = rs.getString("tamanho");
				String conservacao = rs.getString("conservacao");
				String tipo = rs.getString("tipo");
				
				String data = rs.getString("emprestado");
				LocalDate emprestado = "NÃO".equals(data)?null:LocalDate.parse(data);
				
				switch(tipo) {
					case "PecaSuperior":
						PecaSuperior sup = new PecaSuperior(cor, tamanho, conservacao, nome);
						sup.setId(id);
						sup.registrarEmprestimo(emprestado);
						itens.add(sup);
						
						break;
					case "PecaInferior":
						PecaInferior inf = new PecaInferior(cor, tamanho, conservacao, nome);
						inf.setId(id);
						inf.registrarEmprestimo(emprestado);
						itens.add(inf);
						
						break;
					case "RoupaIntima":
						RoupaIntima intim = new RoupaIntima(cor, tamanho, conservacao, nome);
						intim.setId(id);
						itens.add(intim);
						
						break;
					case "Acessorio":
						Acessorio acess = new Acessorio(cor, tamanho, conservacao, nome);
						acess.setId(id);
						acess.registrarEmprestimo(emprestado);
						itens.add(acess);
						
						break;
				}
				
			}
		}catch(SQLException e) {
			throw e;
		}
    	
    	return itens;
    }
    
    public Vector<String> getLavagens() throws SQLException{
    	String sql = "SELECT data, idItem FROM lavagens";
    	Vector<String> datas = new Vector<String>();
    	
		Statement st;
		try {
			st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String data = rs.getString("data");
				String idItem = rs.getString("idItem");
				
				datas.add(data+" Item: "+idItem);
			}
		} catch (SQLException e) {
			throw e;
		}
    		
		return datas;
    }
    
    public Vector<Look> getUsosLook(Vector<Item> itens) throws SQLException{
    	String sql = "SELECT idSup, idInf, idInt, IdAces, local FROM usosLook";
    	Vector<Look> looks = new Vector<Look>();
    	
    	try {
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				int idSup = rs.getInt("idSup");
				int idInf = rs.getInt("idInf");
				int idInt = rs.getInt("idInt");
				int idAces = rs.getInt("idAces");
				String local = rs.getString("local");
				
				PecaSuperior sup = null; PecaInferior inf = null; RoupaIntima intim = null; Acessorio aces = null;
				for(Item item : itens) {
					int id = item.getId();
					
					if(id == idSup) sup = (PecaSuperior) item;
					else if(id == idInf) inf = (PecaInferior) item;
					else if(id == idInt) intim = (RoupaIntima) item;
					else if(id == idAces) aces = (Acessorio) item;
				}
				
				Look look = new Look(sup, inf, intim, aces);
				look.setLocal(local);
				
				looks.add(look);
			}
    	}catch(SQLException e) {
    		throw e;
    	}
    	
    	return looks;
    }
}
