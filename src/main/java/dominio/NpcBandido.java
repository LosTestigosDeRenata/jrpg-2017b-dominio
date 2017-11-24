package dominio;

/**
 * NPC agresivo con altas chances de efectuar ataques críticos.
 */
public class NpcBandido extends NonPlayableCharacter {
    /**
     * Atributos base del NPC
     */
    private static final int FUERZA_BASE = 20;
    private static final int SALUD_BASE = 70;
    private static final int DEFENSA_BASE = 5;
    private static final int ENERGIA_BASE = 40;
    /**
     * Multiplicadores de stats del npc, es decir se le aumentarán los atributos
     * al NPC dependiendo de estos multiplicadores.
     */
    private static final int MULT_FUERZA = 15;
    private static final int MULT_SALUD = 55;
    private static final int MULT_DEFENSA = 7;
    private static final int MULT_ENERGIA = 10;
    /**
     * Porcentajes y multiplicadores que se utilizarán en las distintas
     * habilidades del NPC.
     */
    private static final double CHANCE_EVADIR = 0.15;
    /**
     * Ataque normal
     */
    private static final double ATAQUENORMAL_CHANCE_CRITICO = 0.25;
    private static final double ATAQUENORMAL_MULT_CRITICO = 2.5;
    private static final double ATAQUENORMAL_DESVIO = 0.2;
    /**
     * Ataque Doble
     */
    private static final double ATAQUEDOBLE_COSTE_ENERGIA = 30;
    private static final double ATAQUEDOBLE_MULT_DANIO = 2;
    private static final double ATAQUEDOBLE_CHANCE_CRITICO = 0.1;
    private static final double ATAQUEDOBLE_MULT_CRITICO = 2;
    private static final double ATAQUEDOBLE_DESVIO = 0.15;
    /**
     * Curarse
     */
    private static final double CURARSE_COSTE_ENERGIA = 15;
    private static final double CURARSE_MULT_NIVEL = 20;
    private static final double CURARSE_DESVIO = 0.6;
    /**
     * Energizarse
     */
    private static final double ENERGIZARSE_MULT_NIVEL = 10;
    private static final double ENERGIZARSE_DESVIO = 0.35;

    /**
     * Constructor de la clase. Coincide con los parámetros de la superclase.
     * @param nombre Nombre del npc.
     * @param nivel Nivel que tendrá el npc y determinará que tan poderoso es.
     */
    public NpcBandido(final String nombre, final int nivel) {
	super(nombre, nivel);

	this.aumentarFuerza(FUERZA_BASE + nivel * MULT_FUERZA);
	this.saludTope = this.salud = SALUD_BASE + nivel * MULT_SALUD;
	this.aumentarDefensa(DEFENSA_BASE + nivel * MULT_DEFENSA);
	this.energiaTope = this.energia = ENERGIA_BASE + nivel * MULT_ENERGIA;
    }

    @Override
    public int serAtacado(int danio) {
	if (this.getRandom().nextDouble() <= CHANCE_EVADIR) {
	    danio = 0;
	} else {
	    danio -= this.getDefensa();
	    if (danio > 0) {
		this.salud -= danio;
	    }
	}
	return danio;
    }

    @Override
    public void jugarTurno(final Peleable objetivo) {
	// Si el nivel es menor a 4 nunca llamo a energizarse
	// porque la cantidad de energia que recupera es muy poca como para ser
	// útil.
	if (this.getNivel() < 4) {
	    // 20% chance de que se quiera curar.
	    if (this.getRandom().nextDouble() <= 0.20) {
		curarse();
	    } else {
		// 25% chance de realizar un ataque doble
		if (this.getRandom().nextDouble() <= 0.25) {
		    // intento realizar un ataque doble y si no puedo, ataque
		    // simple.
		    if (!ataqueDoble(objetivo)) {
			ataqueNormal(objetivo);
		    }
		} else {
		    ataqueNormal(objetivo);
		}
	    }
	}
	// El bandido con nivel mayor a 4 es un poco más agresivo.
	else {
	    // Si mi salud está por encima de la mitad, nunca voy a intentar
	    // curarme
	    if (this.getSalud() >= this.getSaludTope() * 0.5) {
		// 35% chance de realizar un ataque doble
		if (this.getRandom().nextDouble() <= 0.35) {
		    // intento realizar un ataque doble y si no puedo,
		    // energizarse.
		    if (!ataqueDoble(objetivo)) {
			energizarse();
		    }
		} else {
		    ataqueNormal(objetivo);
		}
	    } else {
		// 40% chance de curarse
		if (this.getRandom().nextDouble() <= 0.4) {
		    curarse();
		} else {
		    // 50% chance de realizar un ataque doble
		    if (this.getRandom().nextDouble() <= 0.5) {
			// intento realizar un ataque doble y si no puedo,
			// energizarse.
			if (!ataqueDoble(objetivo)) {
			    energizarse();
			}

		    } else {
			ataqueNormal(objetivo);
		    }
		}
	    }
	}
    }

    /*
     * Ataques, devuelven true si el ataque se efectuó. Devuelven false si no se
     * pudo efectuar porque no se cumplian las precondiciones.
     */

    /**
     * Ataque simple que solo causa poco danio al enemigo y puede utilizarse
     * siempre.
     * @param atacado Peleable que será víctima del ataque.
     * @return Devuelve true si se pudo efectuar el ataque.
     */
    public boolean ataqueNormal(final Peleable atacado) {
	int danio = this.getAtaque();

	if (this.getRandom().nextDouble() <= ATAQUENORMAL_CHANCE_CRITICO) {
	    danio = (int) (danio * ATAQUENORMAL_MULT_CRITICO);
	}

	daniarSalud(atacado, (int) this.getRandom().aplicarDispersion(danio, ATAQUENORMAL_DESVIO));

	// El ataque normal para el bandido siempre se puede efectuar.
	return true;
    }

    /**
     * Ataque causa bastante danio al enemigo pero cuesta energía.
     * @param atacado Peleable que será víctima del ataque.
     * @return Devuelve true si se pudo efectuar el ataque.
     */
    public boolean ataqueDoble(final Peleable atacado) {
	if (this.energia <= ATAQUEDOBLE_COSTE_ENERGIA) {
	    return false;
	}

	int danio = (int) (this.getAtaque() * ATAQUEDOBLE_MULT_DANIO);

	if (this.getRandom().nextDouble() <= ATAQUEDOBLE_CHANCE_CRITICO) {
	    danio = (int) (danio * ATAQUEDOBLE_MULT_CRITICO);
	}

	daniarSalud(atacado, (int) this.getRandom().aplicarDispersion(danio, ATAQUEDOBLE_DESVIO));
	this.energia -= ATAQUEDOBLE_COSTE_ENERGIA;

	return true;
    }

    /**
     * Habilidad que permite al bandido recuperar salud.
     * @return Devuelve true si se pudo utilizar la habilidad.
     */
    public boolean curarse() {
	if (this.energia <= CURARSE_COSTE_ENERGIA) {
	    return false;
	}

	int salud = (int) (this.getNivel() * CURARSE_MULT_NIVEL);
	this.aumentarSalud((int) this.getRandom().aplicarDispersion(salud, CURARSE_DESVIO));
	this.energia -= CURARSE_COSTE_ENERGIA;

	return true;
    }

    /**
     * Habilidad que permite al bandido recuperar energía.
     * @return Devuelve true si se pudo utilizar la habilidad.
     */
    public boolean energizarse() {
	int energia = (int) (this.getNivel() * ENERGIZARSE_MULT_NIVEL);
	this.aumentarEnergia((int) this.getRandom().aplicarDispersion(energia, ENERGIZARSE_DESVIO));

	// el bandido siempre podrá energizarse
	return true;
    }

}
