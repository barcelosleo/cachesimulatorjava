package Cache;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Utils.Number;

public class Cache {
	
	/**
	 * Conjuntos de vias da Cache
	 */
	protected List<Set> sets = new ArrayList<Set>();
	
	/**
	 * Atributo que guarda o último resultado do mapeamento. Se for um HIT é true, se for um MISS é false
	 */
	protected boolean cacheResult = false;
	
	/**
	 * Atributo que guarda o último endereço acessado
	 */
	protected Number address = null;
	
	/**
	 * Atributo que armazena o número de hits da cache
	 */
	protected int hits = 0;
	
	/**
	 * Atributo que armazena o número de hits da cache
	 */
	protected int misses = 0;
	
	/**
	 * Método construtor sem parâmetro
	 */
	public Cache(){
		
	}
	
	/**
	 * Método construtor em que é passado como parâmetro o número de Conjuntos e o
	 * número de Vias de cada conjunto
	 * @param sets
	 * @param ways
	 */
	public Cache(int sets, int ways) {
		for(int i = 0; i < sets; i++)
			this.addSet(ways);
	}
	
	/**
	 * Métono que adiciona um Conjunto e recebe como parâmetro o número de vias do mesmo
	 * @param ways
	 */
	protected void addSet(int ways) {
		this.sets.add(new Set(ways));
	}
	
	/**
	 * Método que mostra a representação gráfica da cache
	 */
	public void printCache() {
		for(int i = 0; i < this.sets.size(); i++)
			this.sets.get(i).printSet(i);
	}
	
	/**
	 * Retorna um determinado conjunto da cache
	 * @param index
	 * @return Conjunto
	 */
	public Set getSet(int index) {
		return this.sets.get(index);
	}
	
	/**
	 * Método que aplica o mapeamento associativo por conjuntos apenas para 2 vias
	 * @param value
	 * @throws JSONException 
	 */
	public void map(Number address, Number data) throws JSONException {
		
		this.address = address;
		
		String addressString = address.getBaseTwo(5);
		
		Number indexOfSet = new Number("" + addressString.charAt(3) + addressString.charAt(4));
		
		Number tag = new Number("" + addressString.charAt(0) + addressString.charAt(1) + addressString.charAt(2));
		
		Set setToSearch = this.getSet(indexOfSet.getBaseTen());
		
		if (setToSearch.isInThisSet(addressString, indexOfSet.getBaseTwo(2))) {
			
			// Is a hit
			this.cacheResult = true;
			
			setToSearch.hit(addressString, indexOfSet.getBaseTwo(2));
			for (int i = 0; i < this.sets.size(); i++) {
				if (i != indexOfSet.getBaseTen())
					this.getSet(i).increasesCounters();
			}
		} else {			
			// Is a miss
			this.cacheResult = false;
			
			for (int i = 0; i < this.sets.size(); i++) {
				this.getSet(i).increasesCounters();
			}
			Way LRU = this.LRU(indexOfSet.getBaseTen()); 
			LRU.setTag(tag);
			LRU.setValidity(true);
			LRU.setData(data);
		}
		
		System.out.println("Endereço mapeado:" + this.address.getBaseTwo(5));
		
		if (this.cacheResult) {
			System.out.println("Foi um HIT");
			this.hits++;
		} else {
			System.out.println("Foi um MISS");
			this.misses++;
		}
		System.out.println("----------------------");
	}
	
	/**
	 * Método que retorna a Via LRU
	 * @param indexOfSet
	 * @return
	 */
	public Way LRU(int indexOfSet) {
		return this.getSet(indexOfSet).LRU();
	}
	
	/**
	 * Métdo que monta um json do estado atual da cache
	 * @return
	 * @throws JSONException
	 */
	public JSONObject log() throws JSONException {
		JSONObject cache = new JSONObject();
		JSONArray setList = new JSONArray();
		
		if (this.cacheResult) {
			cache.put("cacheResult", "hit");
		} else {
			cache.put("cacheResult", "miss");
		}
		
		cache.put("mapedAddress", this.address.getBaseTwo(5));
		
		for(int i = 0; i < this.sets.size(); i++)
			setList.put(this.sets.get(i).log(i));
		
		cache.put("sets", setList);
		
		return cache;
	}
	
	/**
	 * Método que retorna o número de hits da cache
	 * @return
	 */
	public int getHits() {
		return this.hits;
	}
	
	/**
	 * Método que retorna o número de misses da cache
	 * @return
	 */
	public int getMisses() {
		return this.misses;
	}
}
