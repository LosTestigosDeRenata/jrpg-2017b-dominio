package dominio;

import java.util.Random;

/**
 *
 * Clase que se utiliza para obtener números aleatoreos.
 */
public class MyRandom extends RandomGenerator 
{
	/**El método nextDobule() retorna siempre el número 0.49.
	 * @return retorna un número double que varía entre 0.0 y 1.0
	 */
	@Override
	public final double nextDouble() {
		return new Random().nextDouble();
	}
	/** El método nextInt() decrementa en 1 el argumento que se le pasó.
	 * @param val número entero
	 * @return Retorna un número menor al parámetro
	 */
	@Override
	public final int nextInt(final int val) {
		return new Random().nextInt(val);
	}

}
