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
	public final double nextDouble() 
	{
		return new Random().nextDouble();
	}
	
	/** El método nextInt() decrementa en 1 el argumento que se le pasó.
	 * @param val número entero
	 * @return Retorna un número menor al parámetro
	 */
	@Override
	public final int nextInt(final int val) 
	{
		return new Random().nextInt(val);
	}
	
	/**
	 * Devuelve un entero entre un rango determinado.
	 * @param min Valor mínimo.
	 * @param max Valor máximo.
	 * @return Retorna un entero entre el valor máximo y el valor mínimo.
	 */
	@Override
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
	@Override
	public double rangoDouble (double min, double max)
	{
		return this.nextDouble() * (max - min) + min;
	}
	
	/**
	 * Permite aplicar una dispersión a un valor según un porcentaje determinado.
	 * @param valor Valor medio
	 * @param desvío Porcentaje que representa que tan alejado puede estar el valor final de la media.
	 * @return Retorna un double que se encuentra entre valor * (1 - desvío) y valor * (1 + desvío)
	 */
	@Override
	public double aplicarDispersión (double valor, double desvío)
	{
		return valor * this.rangoDouble(1 - desvío, 1 + desvío);
	}

}
