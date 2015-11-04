package Memory;

import java.util.ArrayList;
import java.util.List;
import Utils.Number;

public class MainMemory {
	
	/**
	 * Lista onde est�o todas as posi��es da mem�ria e os dados
	 */
	protected List<Line> memory = new ArrayList<Line>();
	
	/**
	 * Construtor sem par�metro
	 */
	public MainMemory() {
		
	}
	
	/**
	 * Construtor onde � passado o n�mero de posi��es da mem�ria principal
	 * @param positions
	 */
	public MainMemory(int positions) {
		while (this.memory.size() < positions)
			this.memory.add(new Line());
	}
	
	/**
	 * M�todo que insere dado na mem�ria principal
	 * @param position
	 * @param data
	 */
	public void insert(int position, Number data) {
		this.memory.get(position).setData(data);
	}
	
	/**
	 * M�todo que retorna o dado da mem�ria principal
	 * @param position
	 * @return
	 */
	public Number read(int position) {
		return this.memory.get(position).getData();
	}
	
	/**
	 * M�todo que printa no console a mem�ria principal
	 */
	public void printMemory() {
		System.out.println("|Pos\t|Dado\t\t\t|");
		for(int i = 0; i < this.memory.size(); i++) {
			System.out.println("|" + i + "\t|" + this.read(i).getBaseTwo(16) + "\t|");
		}
	}

}
