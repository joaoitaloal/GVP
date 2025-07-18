package ufc.poo.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

// Isso é pra ser uma classe que gerencia um unico arquivo, a gente deve ter umas cinco dessas instanciadas por vez:
//  uma pros usos de um look da pessoa x, uma pras lavagens da pessoa x, uma pra registrar emprestimos da pessoa, uma pra registrar itens da pessoa
public class Database {
    private File db;
    private File dir;
    
    public Database(String pessoa, String fileName){
    	new File("./Database").mkdir();
        this.dir = new File("./Database/"+pessoa);
        dir.mkdir();

        this.db = new File(dir, fileName);
    }

    public void saveFile(String texto) throws Exception{
        try {
            FileWriter gravador = new FileWriter(db);
            gravador.write(texto);
            gravador.close();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void appendLine(String texto) throws Exception{
        try {
            FileWriter gravador = new FileWriter(db);
            for(char c : texto.toCharArray()) {
            	gravador.append(c);
            }
            gravador.append('\n');
            gravador.close();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void removeSubstring(String remove) throws Exception {
    	try {
    		File temp = new File(dir, "db_tempfile.txt");
    		
    		BufferedReader reader = new BufferedReader(new FileReader(db));
    		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
    		
    		String line = reader.readLine();
    		while(line != null) {
    			writer.write(line.replace(remove, ""));
    			writer.newLine();
    		}
    		
    		reader.close();
    		writer.close();
    		
    		String fileName = db.getName();
    		
    		db.delete();
    		db = temp;
    		db.renameTo(new File(fileName));	
    	}catch(Exception e) {
    		throw e;
    	}
    }
    
    public Vector<String> readFileLines() throws Exception{
        if(!db.isFile() || !db.canRead()) throw new Exception("Arquivo não encontrado"); // ToDo: Criar uma classe pra esse erro bonitinho
        try {
        	Vector<String> leitura = new Vector<String>();
            BufferedReader reader = new BufferedReader(new FileReader(db));
            String line = reader.readLine();
            while(line != null) {
            	leitura.add(line);
            	line = reader.readLine();
            }
            reader.close();
            
            return leitura;
        } catch (Exception e) {
            throw e;
        }
    }
}
