package Cache;

import org.json.JSONException;
import org.json.JSONObject;

import Utils.Number;

public class Way {
	
	/**
	 * Rótulo que está nesta via
	 */
	protected Number tag;
	
	/**
	 * Endereço do dado que está na via
	 */
	protected Number data;
	
	/**
	 * Bit de validade
	 */
	protected boolean validity;
	
	/**
	 * Contador da via
	 */
	protected int counter;
	
	/**
	 * Método construtor que incializa os atributos
	 */
	public Way() {
		this.tag = null;
		this.data = null;
		this.validity = false;
		this.counter = 0;
	}

	/**
	 * @return the tag
	 */
	public Number getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(Number tag) {
		this.tag = tag;
	}

	/**
	 * @return the dataAddress
	 */
	public Number getData() {
		return data;
	}

	/**
	 * @param dataAddress the dataAddress to set
	 */
	public void setData(Number data) {
		this.data = data;
	}

	/**
	 * @return the validity
	 */
	public boolean getValidity() {
		return validity;
	}

	/**
	 * @param validity the validity to set
	 */
	public void setValidity(boolean validity) {
		this.validity = validity;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	/**
	 * Método que mostra a representação gráfica de uma via
	 */
	public void printWay() {
		if (this.validity)
			System.out.print("|" + 1 + "\t|"+ this.tag.getBaseTwo(3) + "\t|" + this.data.getBaseTwo(16) + "\t|" + this.counter + "\t|");
		else
			System.out.print("|" + 0 + "\t|"+ this.tag + "\t|" + this.data + "\t\t\t|" + this.counter + "\t|");
	}
	
	/**
	 * Seta como zero o contador
	 */
	public void hit() {
		this.counter = 0;
	}
	
	/**
	 * Incrementa o contador
	 */
	public void increasesCounter() {
		this.counter++;
	}
	
	/**
	 * Método que retorna um JSON com o estado atual da Via
	 * @return
	 * @throws JSONException
	 */
	public JSONObject log() throws JSONException {
		JSONObject way = new JSONObject();
		if (this.validity) {
			way.put("validity", this.validity);
			way.put("tag", this.tag.getBaseTwo(3));
			way.put("data", this.data.getBaseTwo(16));
			way.put("counter", this.counter);
		} else {
			way.put("validity", this.validity);
			way.put("tag", "null");
			way.put("data", "null");
			way.put("counter", this.counter);
		}
		return way;
	}
}
