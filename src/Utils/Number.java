package Utils;

public class Number {
	
	/**
	 * Número na base 10
	 */
	protected int baseTen;
	
	/**
	 * Número na base 2
	 */
	protected String baseTwo;
	
	/**
	 * Construtor com número de base 10 como parâmetro de entrada
	 * @param number
	 */
	public Number(int number) {
		this.baseTen = number;
		this.baseTwo = this.numberBaseTwo(number);
	}
	
	/**
	 * Construtor com número de base 2(String) como parâmetro de entrada
	 * @param number
	 */
	public Number(String number) {
		this.baseTen = this.numberBaseTen(number);
		this.baseTwo = number;
	}
	
	/**
	 * Método que recebe um número na base 10 e o converte para base 2
	 * @param number
	 * @return Número na base 2
	 */
	private String numberBaseTwo(int number) {
		return this.recursiveDivision(number, "");
	}
	
	/**
	 * Método que recebe um número na base 2 e o converte para base 10
	 * @param number
	 * @return Número na base 10
	 */
	private int numberBaseTen(String number) {
		int intNumber = 0;
		for (int i = number.length() - 1, j = 0; i >= 0; i--, j++) {
			if(Character.toString(number.charAt(i)).equals("1")) intNumber += Math.pow(2, (j));
		}
		return intNumber;
	}
	
	/**
	 * Método que faz uma divisão recursiva para conversão de número na base 10 para base 2
	 * @param number
	 * @param baseTen
	 * @return Número binário
	 */
	private String recursiveDivision(int number, String baseTen) {
		if (number == 0) {
			baseTen += "0";
			return new StringBuilder(baseTen).reverse().toString();
		} else if (number == 1) {
			baseTen += "1";
			return new StringBuilder(baseTen).reverse().toString();
		}
		
		int rest = number % 2;
		
		if (rest == 1) {
			baseTen += "1";
		} else {
			baseTen += "0";
		}
		
		int division = (int) Math.floor(number / 2);
		
		return this.recursiveDivision(division, baseTen);
	}
	
	/**
	 * Retorna o número na base 2
	 * @param bits
	 * @return
	 */
	public String getBaseTwo(int bits) {
		String zeros = "";
		if (bits != 0) {
			for (int i = this.baseTwo.length() + 0; i < bits; i++){
				zeros += "0";
			}
		}
		return zeros + this.baseTwo;
	}
	
	/**
	 * Retorna o número na base 10
	 * @return
	 */
	public int getBaseTen() {
		return this.baseTen;
	}
	
	
}
