package dominio;

/**
 * Clase con proposito de testing ya que se fijan sus valores.
 */
public class MyRandomStub extends RandomGenerator {
    /**
     * Valor que devolverá nextDouble().
     */
    private final double valDouble;

    /**
     * Valor que devolverá nextInt();
     */
    private final int valInt;

    /**
     * Constructor del MyRandomStub.
     * @param valDouble Double que se fijará en el objeto.
     * @param valInt Entero que se fijará en el objeto.
     */
    public MyRandomStub(final double valDouble, final int valInt) {
	this.valDouble = valDouble;
	this.valInt = valInt;
    }

    /**
     * Método que retorna el valor entero que se pasa en el constructor.
     * @param val valor que se pasa por parametro.
     * @return Entero que se pasa por parámetro en el constructor.
     */
    @Override
    public final int nextInt(final int val) {
	return valInt;
    }

    /**
     * Método que retorna el valor decimal que se pasa en el constructor.
     * @return Double que se pasa por parámetro en el constructor.
     */
    @Override
    public final double nextDouble() {
	return valDouble;
    }

    /**
     * Método que retorna un entero que es el punto medio de los 2 valores.
     * @param min Valor mínimo.
     * @param max Valor máximo.
     * @return Retorna un entero que es el punto medio de los 2 valores. De
     *         haber decimales son truncados.
     */
    @Override
    public int rangoInt(final int min, final int max) {
	return (min + max) / 2;
    }

    /**
     * Método que retorna un decimal que es el punto medio de los 2 valores.
     * @param min Valor mínimo.
     * @param max Valor máximo.
     * @return Retorna un decimal que es el punto medio de los 2 valores.
     */
    @Override
    public double rangoDouble(final double min, final double max) {
	return (min + max) / 2;
    }

    /**
     * Método que retorna el parámetro valor.
     * @param valor Valor medio
     * @param desvio Porcentaje que representa que tan alejado puede estar el
     *            valor final de la media.
     * @return Retorna el parámetro valor.
     */
    @Override
    public double aplicarDispersion(final double valor, final double desvio) {
	return valor;
    }

}
