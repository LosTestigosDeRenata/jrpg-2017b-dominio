
package dominio;

/** Interface implementada en las clases Personaje
 * y NPC.
 * Dependiendo qué clase las esté implementando
 * será como responderán
 */
public interface Peleable 
{
	/**
	 * Método implementado en Personaje y en NonPlayableCharacter.
	 * @param daño Valor el cual se descontará de la defensa.
	 * @return Retorna un entero con el daño causado al llamador.
	 */
	int serAtacado(int daño);

	/**
	 * Método implementado en Personaje y NonPlayableCharacter.
	 * @return Retorna un entero con la salud del llamador.
	 */
	int getSalud();
	
	/**
	 * Método implementado en Personaje y NonPlayableCharacter.
	 * @return Retorna un entero con la energia del llamador.
	 */
	int getMagia();
	
	/**
	 * Método implementado en Personaje y en NonPlayableCharacter.
	 * @param daño Valor de energía a reducir
	 * @return Retorna un entero con la energía reducida al llamador.
	 */
	int recibirDanioEnergia(int daño);
	
	/**
	 * Método implementado en Personaje y NonPlayableCharacter.
	 * @return Retorna un entero con la energia del llamador.
	 */
	int getEnergia();
	
	/**
	 * Método implementado en Personaje y NonPlayableCharacter.
	 * @param atacado Personaje al cual el llamador atacara.
	 * @return Retorna el daño causado del atacante al atacado.
	 */
	int atacar(Peleable atacado);
	
	/**
	 * Método implementado en Personaje y NonPlayableCharacter.
	 * @return Retorna el nivel del llamador multiplicado por la
	 * constante MULTIPLICADOREXP.
	 */
	int otorgarExp();
	
	/**
	 * Método implementado en Personaje y NonPlayableCharacter.
	 * @return Retorna un entero con el ataque del llamador.
	 */
	int getAtaque();
	
	/**
	 * Método implementado en Personaje y NonPlayableCharacter.
	 * @param ataque Entero que será el nuevo ataque del llamador.
	 */
	void setAtaque(int ataque);
	
	/**
	 * Método implementado en Personaje y NonPlayableCharacter.
	 *
	 * @return Retorna un boolean si esta vivo o no el llamador.
	 */
	boolean estaVivo();
	
	/**
	 * Método implementado en Personaje y NonPlayableCharacter.
	 * @return Retorna el nombre del Personaje o NPC.
	 */
	String getNombre();
}

