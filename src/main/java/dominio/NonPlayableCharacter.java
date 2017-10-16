
package dominio;

/**
 * La clase NonPlayableCharacter (NPC).
 * Representa a los personajes del juego que no son
 * controlados por humanos.
 * Implementa la Interfaz Peleable.
 */

public abstract class NonPlayableCharacter extends MadreDeTodo implements Peleable
{
	/**
	 * Dificultad aleatoria NPC.
	 */
	private static final int MULTIPLICADOREXPNPC = 29;
	/**
	 * Salud tope del NPC.
	 */
	protected int saludTope;
	/**
	 * Salud del NPC.
	 */
	protected int salud;
	/**
	 * Energía tope del NPC.
	 */
	protected int energiaTope;
	/**
	 * Energía del NPC.
	 */
	protected int energia;

	/**
	 * Constructor de la clase.
	 * @param nombre Nombre que se le otorga al NPC
	 * @param nivel Nivel que se le otorga al NPC
	 */
	public NonPlayableCharacter(final String nombre, final int nivel) 
	{
		super(0, 0, nivel, nombre);
	}

	/**
	 * Retorna un entero que representa la experiencia que
	 * otorgará el NPC al personaje que lo derrote.
	 * @return Cantidad de experiencia a otorgar
	 */
	@Override
	public final int otorgarExp() {
		return this.getNivel() * MULTIPLICADOREXPNPC;
	}

	/**
	 * Retorna un booleano.
	 * Que indica si el NPC esta vivo, evaluando si el mismo tiene
	 * salud mayor a 0.
	 * @return true si el NPC está vivo, false para lo contrario.
	 */
	@Override
	public final boolean estaVivo() {
		return salud > 0;
	}

	/**
	 * Retorna un entero que representa los puntos de salud del NPC.
	 * @return Devuelve la cantidad de vida actual del NPC
	 */
	@Override
	public final int getSalud() {
		return salud;
	}

	/**
	 * Asigna un valor entero que representará la salud del NPC.
	 * @param salud Entero que indica la nueva salud del NPC.
	 */
	public final void setSalud(final int salud) {
		this.salud = salud;
	}

	/*
	 * A continuación, se definen acciones básicas para que el NPC las use mientras batalla.
	 * Quedará a critero de cada subclase y cómo utilizarlos durante la batalla.
	 */
	
	/**
	 * Método que, dependiendo de MyRandom.nextdouble() y NUMEROPARAATACAR.
	 * Puede ejecutar un ataque
	 * mejorado por el atributo MULTIPLICADORFUERZA
	 * @param atacado Peleable que recibe el ataque
	 * @return Retorna un entero que representa
	 * los puntos de daño realizados
	 */
	@Override
	public final int atacar(final Peleable atacado) 
	{
		return atacado.serAtacado(this.getAtaque());
	}

	// El método de arriba es medio trucho porque no le puedo pasar el daño como parámetros.
	// No me sirve como método genérico para las subclases porque no es suficientemente básico,
	// ni me sirve como método único porque no es lo suficientemente versátil.
	// El tema es que no quiero modificarlo porque hacerlo me obligaría a modificar la interfaz
	// peleable y se rompería el juego en todos lados, así que prefiero utilizar este nuevo método:
	
	public final void dañarPeleable (final Peleable atacado, int daño)
	{
		atacado.serAtacado(daño);
	}
	
	/**
	 * Dependiendo de MyRandom.nextdouble() y NUMEROPARASERATACADO.
	 * Puede disminuir el daño dependiendo del atributo DIVISORDEDEFENSA.
	 * @param danio valor a ser descontado del atributo salud.
	 * @return Retorna 0 si el ataque no fue realizado con exito
	 */
	/*@Override
	public final int serAtacado(int danio) {
		if (this.getRandom().nextDouble() >= NUMEROPARASERATACADO) {
			danio -= this.getDefensa() / DIVISORDEDEFENSA;
			if (danio > 0) {
				salud -= danio;
				return danio;
			}
			return 0;
		}
		return 0;
	}*/
	
	// Voy a dejar que cada subclase implemente su propio método serAtacado
	/**
	 * Método abstracto que determina como recibirá daño el NPC.
	 * @param daño valor a ser descontado del atributo salud.
	 */
	public abstract int serAtacado(int daño);
	
	/**
	 * Método abstracto que determina que es lo que hará el NPC cuando sea su turno en la batalla.
	 * Cada subclase lo implementará a su propia manera, ya que aquí se definirá la IA
	 * del NPC.
	 */
	public abstract void jugarTurno (Peleable objetivo);

	/**
	 * Retorna un entero que representa el atributo de Fuerza del NPC.
	 * @return retorna el ataque del NPC.
	 */
	@Override
	public final int getAtaque() {
		return this.getFuerza();
	}

	/**
	 * Asigna un valor entero que representará el ataque del NPC.
	 * @param ataque Entero que indica la nueva fuerza del NPC.
	 */
	@Override
	public final void setAtaque(final int ataque) {
		this.aumentarFuerza(ataque);
	}

	/**
	 * Aumenta la salud del NPC.
	 * A diferencia de setSalud, este método valida que no supere la salud tope.
	 * @param salud Entero con la salud a aumentar.
	 */
	public void aumentarSalud (int salud)
	{
		this.salud += this.salud + salud > saludTope ? 0 : salud;
	}
	
	public int getSaludTope()
	{
		return saludTope;
	}

	public void setSaludTope(int saludTope)
	{
		this.saludTope = saludTope;
	}

	/**
	 * Aumenta la energia del NPC.
	 * A diferencia de setenergia, este método valida que no supere la energia tope.
	 * @param energia Entero con la energia a aumentar.
	 */
	public void aumentarEnergia (int energia)
	{
		this.energia += this.energia + energia > energiaTope ? 0 : energia;
	}
	
	public int getEnergiaTope()
	{
		return energiaTope;
	}

	public void setEnergiaTope(int energiaTope)
	{
		this.energiaTope = energiaTope;
	}

	public void setEnergia(int energia)
	{
		this.energia = energia;
	}
	
	public int getEnergia()
	{
		return energia;
	}
	/**
	 * Devuelve siempre 0.
	 * @return energia Entero siempre 0.
	 */
	@Override
	public final int getMagia() {
		return 0;
	}
}


