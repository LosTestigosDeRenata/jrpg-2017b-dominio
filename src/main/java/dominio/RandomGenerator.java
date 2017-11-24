package dominio;

/**
 * Clase que se utiliza para generar números randoms
 */
public class RandomGenerator {
    /**
     * Método que siempre retorna 0. Será sobreescrito por las clases que
     * heredan.
     * @param val No se usa
     * @return Retorna 0
     */
    public int nextInt(final int val) {
	return 0;
    }

    /**
     * Método que siempre retorna 0. Será sobreescrito por las clases que
     * heredan.
     * @return Retorna 0
     */
    public double nextDouble() {
	return 0;
    }

    /**
     * Método que siempre retorna 0. Será sobreescrito por las clases que
     * heredan.
     * @param min Valor mínimo.
     * @param max Valor máximo.
     * @return Retorna siempre 0.
     */
    public int rangoInt(final int min, final int max) {
	return 0;
    }

    /**
     * Método que siempre retorna 0. Será sobreescrito por las clases que
     * heredan.
     * @param min Valor mínimo.
     * @param max Valor máximo.
     * @return Retorna siempre 0.
     */
    public double rangoDouble(final double min, final double max) {
	return 0;
    }

    /**
     * Método que siempre retorna 0. Será sobreescrito por las clases que
     * heredan.
     * @param valor Valor medio
     * @param desvio Porcentaje del valor medio que se desviará
     * @return @return Retorna siempre 0.
     */
    public double aplicarDispersion(final double valor, final double desvio) {
	return 0;
    }

    /**
     * Constructor de la clase.
     */
    public RandomGenerator() {
	// ?
    }
}
