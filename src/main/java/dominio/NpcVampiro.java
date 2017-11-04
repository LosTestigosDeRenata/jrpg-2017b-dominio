package dominio;

public class NpcVampiro extends NonPlayableCharacter {
    /**
     * Atributos base del NPC
     */
    private static final int FUERZA_BASE = 15;
    private static final int SALUD_BASE = 60;
    private static final int DEFENSA_BASE = 7;
    private static final int ENERGIA_BASE = 60;
    /**
     * Multiplicadores de stats del npc, es decir se le aumentarán los atributos
     * al NPC dependiendo de estos multiplicadores.
     */
    private static final int MULT_FUERZA = 12;
    private static final int MULT_SALUD = 90;
    private static final int MULT_DEFENSA = 8;
    private static final int MULT_ENERGIA = 35;
    /**
     * Porcentajes y multiplicadores que se utilizarán en las distintas
     * habilidades del NPC.
     */
    private static final double CHANCE_EVADIR = 0.2;
    /**
     * Ataque normal
     */
    private static final double ATAQUENORMAL_DESVIO = 0.4;
    /**
     * Drenar salud
     */
    private static final double DRENARSALUD_COSTE_ENERGIA = 45;
    private static final double DRENARSALUD_MULT_DANIO = 1.5;
    private static final double DRENARSALUD_PORCENTAJE_SALUD_CURADA = 0.75;
    private static final double DRENARSALUD_CHANCE_CRITICO = 0.3;
    private static final double DRENARSALUD_MULT_CRITICO = 1.75;
    private static final double DRENARSALUD_DESVIO = 0.25;
    /**
     * Drenar energía
     */
    private static final double DRENARENERGIA_MULT = 1;
    private static final double DRENARENERGIA_CHANCE_CRITICO = 0.15;
    private static final double DRENARENERGIA_MULT_CRITICO = 1.75;
    private static final double DRENARENERGIA_DESVIO = 0.25;

    /**
     * Constructor de la clase. Coincide con los parámetros de la superclase.
     * @param nombre
     * @param nivel
     */
    public NpcVampiro(final String nombre, final int nivel) {
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
		reducirSalud(danio);
	    }

	}
	return danio;
    }

    @Override
    public void jugarTurno(final Peleable objetivo) {
	if (this.salud < this.saludTope) {
	    if (!drenarSalud(objetivo)) {
		// se fija si tiene energía substancial como para robarle, sino
		// ni intenta
		if (objetivo.getEnergia() > DRENARSALUD_COSTE_ENERGIA) {
		    drenarEnergia(objetivo);
		} else {
		    ataqueNormal(objetivo);
		}

	    }
	} else {
	    ataqueNormal(objetivo);
	}

    }

    /**
     * Ataque simple que solo causa poco danio al enemigo y puede utilizarse
     * siempre.
     * @param atacado Peleable que será víctima del ataque.
     * @return Devuelve true si se pudo efectuar el ataque.
     */
    public boolean ataqueNormal(final Peleable atacado) {
	int danio = this.getAtaque();

	daniarSalud(atacado, (int) this.getRandom().aplicarDispersión(danio, ATAQUENORMAL_DESVIO));

	// El ataque normal para el vampiro siempre se puede efectuar.
	return true;
    }

    /**
     * Ataque que cura al vampiro un porcentaje del danio realizado.
     * @param atacado Peleable que será víctima del ataque.
     * @return Devuelve true si se pudo efectuar el ataque.
     */
    public boolean drenarSalud(final Peleable atacado) {
	if (this.energia <= DRENARSALUD_COSTE_ENERGIA) {
	    return false;
	}

	int danio = (int) (this.getAtaque() * DRENARSALUD_MULT_DANIO);

	if (this.getRandom().nextDouble() <= DRENARSALUD_CHANCE_CRITICO) {
	    danio = (int) (danio * DRENARSALUD_MULT_CRITICO);
	}

	int danioCausado = daniarSalud(atacado, (int) this.getRandom().aplicarDispersión(danio, DRENARSALUD_DESVIO));

	// el vampiro se cura un porcentaje del danio causado
	this.aumentarSalud((int) (danioCausado * DRENARSALUD_PORCENTAJE_SALUD_CURADA));
	this.energia -= DRENARSALUD_COSTE_ENERGIA;

	return true;
    }

    /**
     * Ataque que roba energía del enemigo.
     * @param atacado Peleable que será víctima del ataque.
     * @return Devuelve true si se pudo efectuar el ataque.
     */
    public boolean drenarEnergia(final Peleable atacado) {
	int danio = (int) (this.getAtaque() * DRENARENERGIA_MULT);

	if (this.getRandom().nextDouble() <= DRENARENERGIA_CHANCE_CRITICO) {
	    danio = (int) (danio * DRENARENERGIA_MULT_CRITICO);
	}

	int danioEnergia = (int) this.getRandom().aplicarDispersión(danio, DRENARENERGIA_DESVIO);

	// robo la energía del atacado
	this.aumentarEnergia(daniarEnergia(atacado, danioEnergia));

	// el vampiro siempre podrá drenar energía.
	return true;
    }

}
