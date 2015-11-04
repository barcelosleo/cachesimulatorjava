package Memory;

import java.util.ArrayList;
import java.util.List;
import Utils.Number;

public class MainMemory {
	
	/**
	 * Lista onde estão todas as posições da memória e os dados
	 */
	protected List<Line> memory = new ArrayList<Line>();
	
	/**
	 * Construtor sem parâmetro
	 */
	public MainMemory() {
		
	}
	
	/**
	 * Construtor onde é passado o número de posições da memória principal
	 * @param positions
	 */
	public MainMemory(int positions) {
		while (this.memory.size() < positions)
			this.memory.add(new Line());
	}
	
	/**
	 * Método que insere dado na memória principal
	 * @param position
	 * @param data
	 */
	public void insert(int position, Number data) {
		this.memory.get(position).setData(data);
	}
	
	/**
	 * Método que retorna o dado da memória principal
	 * @param position
	 * @return
	 */
	public Number read(int position) {
		return this.memory.get(position).getData();
	}
	
	/**
	 * Método que printa no console a memória principal
	 */
	public void printMemory() {
		System.out.println("|Pos\t|Dado\t\t\t|");
		for(int i = 0; i < this.memory.size(); i++) {
			System.out.println("|" + i + "\t|" + this.read(i).getBaseTwo(16) + "\t|");
		}
	}

}
