package Cache;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Utils.Number;

public class Set {
	
	/**
	 * Lista das vias que est�o neste conjunto
	 */
	protected List<Way> ways = new ArrayList<Way>();
	
	/**
	 * M�todo construtor sem par�metro
	 */
	public Set() {
		
	}
	
	/**
	 * M�todo construtor que recebe como par�metro o n�mero de vias
	 * @param ways
	 */
	public Set(int ways) {
		for (int i = 0; i < ways; i++)
			this.addWay();
	}
	
	/**
	 * M�todo que retorna as vias deste conjunto
	 * @return
	 */
	public List<Way> getWays() {
		return this.ways;
	}
	
	/**
	 * M�tdo que adiciona uma via ao conjunto
	 */
	protected void addWay() {
		this.ways.add(new Way());
	}
	
	/**
	 * M�todo que printa no console o Conjunto
	 */
	public void printSet(int set) {
		int i = 0;
		System.out.print(new Number(set).getBaseTwo(2));
		while (i < this.ways.size()) {
			System.out.print("|Val\t|Tag\t|Data\t\t\t|Cont\t|");
			i++;
		}
		System.out.println("");
		
		System.out.print(new Number(set).getBaseTwo(2));
		for (i = 0; i < this.ways.size(); i++) {
			this.ways.get(i).printWay();
		}
		System.out.println("\n");
	}

	/**
	 * Retorna uma determinada via do conjunto
	 * @param index
	 * @return Via
	 */
	private Way getWay(int index) {
		return this.ways.get(index);
	}
	
	/**
	 * Verifica se um endere�o passado como par�metro est� na via passada como par�metro dentro doconjunto
	 * @param wayIndex
	 * @param address
	 * @param indexOfSet
	 * @return
	 */
	private boolean testWayTag(int wayIndex, String address, String indexOfSet){
		if (this.getWay(wayIndex).getTag() != null) {
			return address.equals(this.getWay(wayIndex).getTag().getBaseTwo(3) + indexOfSet);
		} else {
			return false;
		}
	}
	
	/**
	 * Verifica se um endere�o j� est� no conjunto
	 * @param address
	 * @param indexOfSet
	 * @return
	 */
	public boolean isInThisSet(String address, String indexOfSet) {
		if ( (this.getWay(0).getValidity()) || (this.getWay(1).getValidity()) ){
			if ( this.testWayTag(0, address, indexOfSet) || this.testWayTag(1, address, indexOfSet) ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Quando h� um hit no conjunto os contadores s�o incrementados e o contador na posi��o em que o hit aconteceu � zerado
	 * @param address
	 * @param indexOfSet
	 */
	public void hit(String address, String indexOfSet) {
		if (address.equals(this.getWay(0).getTag().getBaseTwo(3) + indexOfSet)){
			this.ways.get(0).hit();
			this.ways.get(1).increasesCounter();
		} else {
			this.ways.get(1).hit();
			this.ways.get(0).increasesCounter();
		}
	}
	
	/**
	 * Incrementa todos os contadores do conjunto
	 */
	public void increasesCounters() {
		for (int i = 0; i < this.ways.size(); i++) {
			this.ways.get(i).increasesCounter();
		}
	}
	
	/**
	 * Retorna o contador da via cujo contador � menor
	 * @return
	 */
	public int getLesserCounter() {
		if (this.ways.get(0).counter < this.ways.get(1).counter)
			return this.ways.get(0).counter;
		else
			return this.ways.get(1).counter;
	}
	
	/**
	 * Retorna a via Last Recent Used
	 * @return
	 */
	public Way LRU() {
		if (!this.getWay(0).getValidity()){
			return this.getWay(0);
		} else {
			if (!this.getWay(1).getValidity()){
				if (this.getWay(0).counter < this.getWay(1).counter) {
					return this.getWay(0); 
				} else {
					return this.getWay(1);
				}
			}
			return this.getWay(1);
		}
	}
	
	/**
	 * M�todo que monta um JSON do estado atual do conjunto
	 * @param indexSet
	 * @return
	 * @throws JSONException
	 */
	public JSONObject log(int indexSet) throws JSONException {
		JSONObject set = new JSONObject();
		JSONArray wayList = new JSONArray();
		
		set.put("index", (new Number(indexSet).getBaseTwo(2)));		
		for (int i = 0; i < this.ways.size(); i++) {
			wayList.put(this.ways.get(i).log());
		}
		
		set.put("ways", wayList);
		
		return set;
	}
}
