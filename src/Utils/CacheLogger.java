package Utils;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CacheLogger {
	
	/**
	 * JSON a ser gerado
	 */
	JSONArray json = new JSONArray();
	
	/**
	 * Método que adiciona um log da cache a lista do JSON a ser gerado
	 * @param stage
	 * @param log
	 * @throws JSONException
	 */
	public void addCacheLog(int stage, JSONObject log) throws JSONException {
		JSONObject cacheLog = new JSONObject();
		cacheLog.put("stage", stage);
		cacheLog.put("cache", log);
		this.json.put(cacheLog);
		
	}
	
	/**
	 * Retorna o JSON
	 * @return
	 */
	public JSONArray getJson() {
		return this.json;
	}
	
	/**
	 * Cria o arquivo de log contendo um JSON com todas as etapas da cache
	 */
	public void generateLogFile() {
		try {

			FileWriter file = new FileWriter("CacheSimulation/log.json");
			file.write(this.json.toString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
