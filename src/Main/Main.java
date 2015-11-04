package Main;

import java.util.Scanner;

import org.json.JSONException;

import Cache.Cache;
import Memory.MainMemory;
import Utils.Number;
import Utils.CacheLogger;
import Utils.FileReader;

public class Main {
	
	public static void main(String[] args) throws JSONException {
		
		Scanner in = new Scanner(System.in);
		
		String leituras;
		String memoriaPrincipal;
		
		System.out.println("Nome do arquivo que contém as leituras:[Leituras.txt]");
		leituras = in.nextLine();
		if(leituras.equals("")){
			leituras = "Leituras.txt";
		}
		
		System.out.println("Nome do arquivo que contém os dados da memória principal:[MemóriaPrincipal.txt]");
		memoriaPrincipal = in.nextLine();
		if(memoriaPrincipal.equals("")){
			memoriaPrincipal = "MemóriaPrincipal.txt";
		}
		
		// Lê o arquivo de Memória Principal
		FileReader mPrincipal = new FileReader(memoriaPrincipal);
		
		// Instancia uma Memória Principal
		MainMemory mainMemory = new MainMemory(32);
		
		// Será populada a Memória Principal
		for(int i = 0; i < mPrincipal.lines(); i++) {
			// Insere na Memória Principal o dado lido do arquivo 
			mainMemory.insert(i, new Number(mPrincipal.getLine(i)));
		}
		
		// Lê o arquivo de leituras
		FileReader readings = new FileReader(leituras);
		
		// Instancia a Cache com 4 conjuntos de 2 vias 
		Cache cache = new Cache(4, 2);
		
		// Inicializa o CacheLogger para geração do JSON de resultado
		CacheLogger log = new CacheLogger();
		
		// Faz as leituras na Cache
		for(int j = 0; j < readings.lines(); j++) {
			// Endereço a ser mapeado na cache
			Number address = new Number(readings.getLine(j));
			Number data = mainMemory.read(address.getBaseTen());
			cache.map(address, data);
			log.addCacheLog(j, cache.log());
		}

		log.generateLogFile();
		System.out.println("Hits:" + cache.getHits());
		System.out.println("Misses:" + cache.getMisses());
		System.out.println("Arquivo de log gerado com sucesso!");
		
		in.close();
	}
	
}
