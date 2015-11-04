package Utils;

public class Number {
	
	/**
	 * N�mero na base 10
	 */
	protected int baseTen;
	
	/**
	 * N�mero na base 2
	 */
	protected String baseTwo;
	
	/**
	 * Construtor com n�mero de base 10 como par�metro de entrada
	 * @param number
	 */
	public Number(int number) {
		this.baseTen = number;
		this.baseTwo = this.numberBaseTwo(number);
	}
	
	/**
	 * Construtor com n�mero de base 2(String) como par�metro de entrada
	 * @param number
	 */
	public Number(String number) {
		this.baseTen = this.numberBaseTen(number);
		this.baseTwo = number;
	}
	
	/**
	 * M�todo que recebe um n�mero na base 10 e o converte para base 2
	 * @param number
	 * @return N�mero na base 2
	 */
	private String numberBaseTwo(int number) {
		return this.recursiveDivision(number, "");
	}
	
	/**
	 * M�todo que recebe um n�mero na base 2 e o converte para base 10
	 * @param number
	 * @return N�mero na base 10
	 */
	private int numberBaseTen(String number) {
		int intNumber = 0;
		for (int i = number.length() - 1, j = 0; i >= 0; i--, j++) {
			if(Character.toString(number.charAt(i)).equals("1")) intNumber += Math.pow(2, (j));
		}
		return intNumber;
	}
	
	/**
	 * M�todo que faz uma divis�o recursiva para convers�o de n�mero na base 10 para base 2
	 * @param number
	 * @param baseTen
	 * @return N�mero bin�rio
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
	 * Retorna o n�mero na base 2
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
	 * Retorna o n�mero na base 10
	 * @return
	 */
	public int getBaseTen() {
		return this.baseTen;
	}
	
	
}
