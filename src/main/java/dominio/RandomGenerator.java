package dominio;
/**
 * Clase que se utiliza para generar números randoms
 */
public  class RandomGenerator {
	/**
	 * Método que siempre retorna 0.
	 * Será sobreescrito por las clases que heredan.
	 * @param val No se usa
	 * @return Retorna 0
	 */
	public int nextInt(final int val) {
		return 0;
	}
	/**
	 * Método que siempre retorna 0.
	 * Será sobreescrito por las clases que heredan.
	 * @return Retorna 0
	 */
	public double nextDouble() {
		return 0;
	}
	
	/**
	 * Devuelve un entero entre un rango determinado.
	 * @param min Valor mínimo.
	 * @param max Valor máximo.
	 * @return Retorna un entero entre el valor máximo y el valor mínimo.
	 */
	public int rangoInt (int min, int max)
	{
		return this.nextInt(max - min + 1) + min;
	}
	
	/**
	 * Devuelve un double entre un rango determinado.
	 * @param min Valor mínimo.
	 * @param max Valor máximo.
	 * @return Retorna un double entre el valor máximo y el valor mínimo.
	 */
	public double rangoDouble (double min, double max)
	{
		return this.nextDouble() * (max - min) + min;
	}
	
	public double aplicarDesvío (double valor, double desvío)
	{
		return valor * this.rangoDouble(1 - desvío, 1 + desvío);
	}
	
	/**
	 * Constructor de la clase.
	 */
	public RandomGenerator() {

	}
}
