
package dominio;

/**
 * Interface implementada en las clases Personaje y NPC. Dependiendo qué clase
 * las esté implementando será como responderán
 */
public interface Peleable {
    /**
     * Metodo implementado en Personaje y en NonPlayableCharacter.
     * @param danio Valor el cual se descontará de la defensa.
     * @return Retorna un entero con el danio causado al llamador.
     */
    int serAtacado(int danio);

    /**
     * Metodo implementado en Personaje y en NonPlayableCharacter.
     * @return Retornara un entero con la salud del llamador.
     */
    int getSalud();

    /**
     * Metodo implementado en Personaje y en NonPlayableCharacter.
     * @return Retornara un entero con la energia del llamador.
     */
    int getMagia();

    /**
     * Método implementado en Personaje y en NonPlayableCharacter.
     * @param danio Valor de energía a reducir
     * @return Retorna un entero con la energía reducida al llamador.
     */
    int recibirDanioEnergia(int danio);

    /**
     * Metodo void implementado en Personaje y en NonPlayableCharacter.
     */
    int getEnergia();

    /**
     * Metodo implementado en Personaje y en NonPlayableCharacter.
     * @param atacado Personaje al cual el llamador atacara.
     * @return Retorna el danio causado del atacante al atacado.
     */
    int atacar(Peleable atacado);

    /**
     * Metodo implementado en Personaje y en NonPlayableCharacter.
     * @return Retorna el nivel del llamador multiplicado por la constante
     *         MULTIPLICADOREXP.
     */
    int otorgarExp();

    /**
     * Metodo implementado en Personaje y en NonPlayableCharacter.
     * @return Retorna un entero con el ataque del llamador.
     */
    int getAtaque();

    /**
     * Metodo implementado en Personaje y en NonPlayableCharacter.
     * @param ataque Entero que será el nuevo ataque del llamador.
     */
    void setAtaque(int ataque);

    /**
     * Metodo implementado en Personaje y en NonPlayableCharacter.
     * @return Retorna un boolean si esta vivo o no el llamador.
     */
    boolean estaVivo();
    
    /**
     * Metodo implementado en Personaje y en NonPlayableCharacter.
     * @return Retorna un boolean si el jugador tiene el cheat de invulnerabilidad.
     */
    boolean esInvulnerable();

    /**
     * Método implementado en Personaje y NonPlayableCharacter.
     * @return Retorna el nombre del Personaje o NPC.
     */
    String getNombre();
}
