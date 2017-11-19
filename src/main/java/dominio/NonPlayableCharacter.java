
package dominio;

/**
 * La clase NonPlayableCharacter (NPC). Representa a los personajes del juego
 * que no son controlados por humanos. Implementa la Interfaz Peleable.
 */

public abstract class NonPlayableCharacter extends MadreDeTodo implements Peleable {
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
    public NonPlayableCharacter(final String nombre, final int nivel) {
	super(0, 0, nivel, nombre);
    }

    /**
     * Retorna un entero que representa la experiencia que otorgará el NPC al
     * personaje que lo derrote.
     * @return Cantidad de experiencia a otorgar
     */
    @Override
    public final int otorgarExp() {
	return this.getNivel() * MULTIPLICADOREXPNPC;
    }

    /**
     * Retorna un booleano. Que indica si el NPC esta vivo, evaluando si el
     * mismo tiene salud mayor a 0.
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
     * A continuación, se definen acciones básicas para que el NPC las use
     * mientras batalla. Quedará a critero de cada subclase y cómo utilizarlos
     * durante la batalla.
     */

    /**
     * Método que, dependiendo de MyRandom.nextdouble() y NUMEROPARAATACAR.
     * Puede ejecutar un ataque mejorado por el atributo MULTIPLICADORFUERZA
     * @param atacado Peleable que recibe el ataque
     * @return Retorna un entero que representa los puntos de danio realizados
     */
    @Override
    public final int atacar(final Peleable atacado) {
	return atacado.serAtacado(this.getAtaque());
    }

    /**
     * Método que reduce la salud de un peleable atacado.
     * @param atacado Peleable a dañar
     * @param danio Daño a efectuar
     * @return devuelve el daño causado
     */
    public final int daniarSalud(final Peleable atacado, final int danio) {
	return atacado.serAtacado(danio);
    }

    /**
     * Método que reduce la energía de un peleable atacado.
     * @param atacado Peleable a dañar
     * @param danio Daño en la energía a efectuar
     * @return devuelve la energía dañada
     */
    public final int daniarEnergia(final Peleable atacado, final int danio) {
	return atacado.recibirDanioEnergia(danio);
    }

    // Voy a dejar que cada subclase implemente su propio método serAtacado
    /**
     * Método abstracto que determina como recibirá danio el NPC.
     * @param danio Valor a ser descontado del atributo salud.
     */
    @Override
    public abstract int serAtacado(int danio);

    /**
     * Reduce la energía del Npc.
     * @param danio Valor a ser descontado del atributo energía
     */
    @Override
    public int recibirDanioEnergia(final int danio) {
	if (danio > 0) {
	    reducirEnergia(danio);
	}

	return danio;
    }

    /**
     * Método abstracto que determina que es lo que hará el NPC cuando sea su
     * turno en la batalla. Cada subclase lo implementará a su propia manera, ya
     * que aquí se definirá la IA del NPC.
     * @param objetivo
     */
    public abstract void jugarTurno(Peleable objetivo);

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
     * Aumenta la salud del NPC. A diferencia de setSalud, este método valida
     * que no supere la salud tope.
     * @param saludParam Entero con la salud a aumentar.
     */
    public void aumentarSalud(final int saludParam) {
	if (this.salud + saludParam >= saludTope) {
	    this.salud = saludTope;
	} else {
	    this.salud += saludParam;
	}
    }

    /**
     * Reduce la salud del NPC. A diferencia de setSalud, este método valida que
     * no caiga por debajo de 0.
     * @param saludParam Entero con la salud a reducir.
     */
    public void reducirSalud(final int saludParam) {
	if (this.salud - saludParam <= 0) {
	    this.salud = 0;
	} else {
	    this.salud -= saludParam;
	}
    }

    /**
     * @return salud maxima del npc
     */
    public int getSaludTope() {
	return saludTope;
    }

    /**
     * @param saludTopeParam Nueva salud maxima del npc
     */
    public void setSaludTope(final int saludTopeParam) {
	this.saludTope = saludTopeParam;
    }

    /**
     * Aumenta la energía del NPC. A diferencia de setEnergia, este método
     * valida que no supere la energía tope.
     * @param energiaParam Entero con la energía a aumentar.
     */
    public void aumentarEnergia(final int energiaParam) {
	if (this.energia + energiaParam >= energiaTope) {
	    this.energia = energiaTope;
	} else {
	    this.energia += energiaParam;
	}
    }

    /**
     * Reduce la energía del NPC. A diferencia de setEnergia, este método valida
     * que no caiga por debajo de 0.
     * @param energiaParam Entero con la energía a reducir.
     */
    public void reducirEnergia(final int energiaParam) {
	if (this.energia - energiaParam <= 0) {
	    this.energia = 0;
	} else {
	    this.energia -= energiaParam;
	}
    }

    /**
     * @return energiaTope retorna tope
     */
    public int getEnergiaTope() {
	return energiaTope;
    }

    /**
     * @param energiaTopeParam maximo energia
     */
    public void setEnergiaTope(final int energiaTopeParam) {
	this.energiaTope = energiaTopeParam;
    }

    /**
     * @param energiaParam energia nueva
     */
    public void setEnergia(final int energiaParam) {
	this.energia = energiaParam;
    }

    @Override
    public int getEnergia() {
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
    
    /**
     * Devuelve siempre false, un NPC nunca será invulnerable.
     * @return devuelve false
     */
    public boolean esInvulnerable() {
	return false;
    }
}
