package Memory;

import Utils.Number;

public class Line {

	/**
	 * Atributo onde consta o dado
	 */
	protected Number data;
	
	/**
	 * Método construtor que inicializa uma linha da memória principal
	 */
	public Line() {
		this.data = null;
	}

	/**
	 * @return the data
	 */
	public Number getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Number data) {
		this.data = data;
	}
	
}
