package Main;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;

import Cache.Cache;
import Memory.MainMemory;
import Utils.Number;
import Utils.CacheLogger;
import Utils.FileReader;

public class Main {
	
	public static void main(String[] args) throws JSONException, IOException {
		
		Scanner in = new Scanner(System.in);
		
		String leituras;
		String memoriaPrincipal;
		boolean stepByStep;
		
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
		
		System.out.println("Executar passo a passo? [1]Sim [0]Não");
		stepByStep = in.nextInt() != 0;
		
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
			if (stepByStep && j != 0) {
				System.out.println("Pressione qualquer tecla...");
				System.out.println();
				if (j == 1) {
					in.nextLine();
					in.nextLine();					
				} else {
					in.nextLine();
				}
			}
			System.out.println("Passo " + j + " de " + readings.lines());
			System.out.println("Endereço mapeado:" + cache.getLastAddress());
			if (cache.getCacheResult()) {
				System.out.println("Foi um HIT");
			} else {
				System.out.println("Foi um MISS");
			}
			System.out.println("----------------------");
			cache.printCache();
		}

		log.generateLogFile();
		System.out.println("Hits:" + cache.getHits());
		System.out.println("Misses:" + cache.getMisses());
		System.out.println("Arquivo de log gerado com sucesso!");
		
		in.close();
	}
	
}
