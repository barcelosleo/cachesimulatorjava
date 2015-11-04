package Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {
	
	/**
	 * Linhas do arquivo
	 */
	protected List<String> lines = new ArrayList<String>();
	
	/**
	 * M�tdo construtor que recebe como par�metro o nome do arquivo
	 * @param file
	 */
	public FileReader(String file) {
		this.openFile(file);
	}
	
	/**
	 * M�todo que l� o arquivo e joga cada linha na lista de linhas do arquivo
	 * @param file
	 */
	protected void openFile(String file) {
		
		FileInputStream fis = null;
        BufferedReader reader = null;
        
		try {
            fis = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(fis));
          
            String line = reader.readLine();
            while(line != null){
                this.addLine(line);
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}
	
	/**
	 * M�tdo que insere o conte�do de uma linha do arquivo na lista
	 * @param line
	 */
	protected void addLine(String line) {
		this.lines.add(line);
	}
	
	/**
	 * M�todo que retorna uma determinada linha do arquivo
	 * @param position
	 * @return
	 */
	public String getLine(int position) {
		if(this.lines.size() > 0)
			return this.lines.get(position);
		else
			return null;
	}
	
	/**
	 * M�todo que retorna o n�mero de linhas do arquivo
	 * @return
	 */
	public int lines() {
		return this.lines.size();
	}

}
