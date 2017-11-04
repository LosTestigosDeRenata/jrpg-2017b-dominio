package dominio;

public class NpcBrujo extends NonPlayableCharacter {
    /**
     * Atributos base del NPC
     */
    private static final int FUERZA_BASE = 12;
    private static final int SALUD_BASE = 65;
    private static final int DEFENSA_BASE = 5;
    private static final int ENERGIA_BASE = 100;
    /**
     * Multiplicadores de stats del npc, es decir se le aumentarán los atributos
     * al NPC dependiendo de estos multiplicadores.
     */
    private static final int MULT_FUERZA = 19;
    private static final int MULT_SALUD = 50;
    private static final int MULT_DEFENSA = 6;
    private static final int MULT_ENERGIA = 55;
    /**
     * Porcentajes y multiplicadores que se utilizarán en las distintas
     * habilidades del NPC.
     */
    private static final double CHANCE_EVADIR = 0.05;
    /**
     * Bola de fuego
     */
    private static final int BOLA_FUEGO_COSTE_ENERGIA = 50;
    private static final double BOLA_FUEGO_MULT_DANIO = 2;
    private static final double BOLA_FUEGO_CHANCE_CRITICO = 0.1;
    private static final double BOLA_FUEGO_MULT_CRITICO = 2;
    private static final double BOLA_FUEGO_DESVIO = 0.15;
    /**
     * Envenenar
     */
    private static final int ENVENENAR_TURNOS = 3;
    private static final int ENVENENAR_COSTE_ENERGIA = 35;
    private static final int ENVENENAR_DANIO_BASE = 17;
    private static final int ENVENENAR_MULT_DANIO = 12;
    private int turnosEnvenenar;
    /**
     * Regenerarse
     */
    private static final int REGENERARSE_TURNOS = 5;
    private static final double REGENERARSE_PORCENTAJE_SALUD = 0.4;
    private static final double REGENERARSE_PORCENTAJE_ENERGIA = 0.3;
    private static final double REGENERARSE_DESVIO = 0.25;
    private int turnosRegenerarse;
    /**
     * Campo de Energía
     */
    private static final int CAMPO_ENERGIA_TURNOS = 4;
    private static final double CAMPO_ENERGIA_PORCENTAJE_NECESARIO = 0.15;
    private static final double CAMPO_ENERGIA_PORCENTAJE_BLOQUEADO = 0.5;
    private int turnosCampoEnergia;

    /**
     * Constructor de la clase. Coincide con los parámetros de la superclase.
     * @param nombre
     * @param nivel
     */
    public NpcBrujo(final String nombre, final int nivel) {
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
	    // si está bajo los efectos de campo de energía
	    // aplico una reducción al danio recibido
	    if (this.turnosCampoEnergia > 0) {
		danio = (int) (danio * CAMPO_ENERGIA_PORCENTAJE_BLOQUEADO);
	    }
	    if (danio > 0) {
		this.salud -= danio;
	    }

	}
	return danio;
    }

    @Override
    public void jugarTurno(final Peleable objetivo) {
	// descuento turnos restantes de habilidades
	if (turnosRegenerarse > 0) {
	    double proporcion = ((double) turnosRegenerarse) / REGENERARSE_TURNOS;

	    int salud = (int) (this.getSaludTope() * REGENERARSE_PORCENTAJE_SALUD * proporcion);
	    this.aumentarSalud((int) this.getRandom().aplicarDispersión(salud, REGENERARSE_DESVIO));
	    int energia = (int) (this.getSaludTope() * REGENERARSE_PORCENTAJE_ENERGIA * proporcion);
	    this.aumentarEnergia((int) this.getRandom().aplicarDispersión(energia, REGENERARSE_DESVIO));

	    turnosRegenerarse--;
	}
	if (turnosEnvenenar > 0) {
	    int danio = ENVENENAR_DANIO_BASE + ENVENENAR_MULT_DANIO * this.getNivel();
	    daniarSalud(objetivo, danio);

	    turnosEnvenenar--;
	}
	if (turnosCampoEnergia > 0) {
	    turnosCampoEnergia--;
	}

	if (this.getSalud() / this.getSaludTope() > 0.85) {
	    efectuarAlgunAtaque(objetivo);
	}

	else {
	    if (this.getRandom().nextDouble() >= 0.5) {
		efectuarAlgunAtaque(objetivo);
	    } else {
		if (this.getRandom().nextDouble() >= 0.6) {
		    if (!regenerarse()) {
			if (!campoDeEnergia()) {
			    efectuarAlgunAtaque(objetivo);
			}

		    }
		} else {
		    if (!campoDeEnergia()) {
			if (!regenerarse()) {
			    efectuarAlgunAtaque(objetivo);
			}

		    }
		}
	    }
	}
    }

    /**
     * Realiza un ataque con bola de fuego o envenenar.
     * @param atacado Peleable que será víctima del ataque
     */
    private void efectuarAlgunAtaque(final Peleable atacado) {
	// utilizo alguna habilidad ofensiva, si no pude me regenero
	boolean pudeAtacar;

	if (this.getRandom().nextDouble() >= 0.5) {
	    pudeAtacar = bolaDeFuego(atacado);
	} else {
	    pudeAtacar = envenenar(atacado);
	}

	if (!pudeAtacar) {
	    regenerarse();
	}

    }

    /**
     * Ataque muy poderoso que cuesta energía.
     * @param atacado Objetivo a atacar
     * @return Devuelve true si se pudo efectuar el ataque
     */
    public boolean bolaDeFuego(final Peleable atacado) {
	if (this.energia <= BOLA_FUEGO_COSTE_ENERGIA) {
	    return false;
	}

	int danio = (int) (this.getAtaque() * BOLA_FUEGO_MULT_DANIO);

	if (this.getRandom().nextDouble() <= BOLA_FUEGO_CHANCE_CRITICO) {
	    danio = (int) (danio * BOLA_FUEGO_MULT_CRITICO);
	}

	daniarSalud(atacado, (int) this.getRandom().aplicarDispersión(danio, BOLA_FUEGO_DESVIO));
	this.energia -= BOLA_FUEGO_COSTE_ENERGIA;

	return true;
    }

    /**
     * Ataque que causa danio al rival a lo largo de una determinada cantidad de
     * turnos.
     * @param atacado Objetivo a atacar
     * @return Devuelve true si se pudo efectuar el ataque
     */
    public boolean envenenar(final Peleable atacado) {
	if (this.energia <= ENVENENAR_COSTE_ENERGIA) {
	    return false;
	}

	int danio = ENVENENAR_DANIO_BASE + ENVENENAR_MULT_DANIO * this.getNivel();
	daniarSalud(atacado, danio);
	this.energia -= ENVENENAR_COSTE_ENERGIA;
	// el -1 es porque el primer danio lo aplica en el momento
	turnosEnvenenar = ENVENENAR_TURNOS - 1;

	return true;
    }

    /**
     * Habilidad que permite al brujo recuperar salud y energía. No puede
     * utilizarse si ya la ha empleado recientemente.
     * @return Devuelve true si se pudo utilizar la habilidad.
     */
    public boolean regenerarse() {
	// no se puede utilizar si ya está bajo los efectos de regenerarse
	if (this.turnosRegenerarse > 0) {
	    return false;
	}

	int salud = (int) (this.getSaludTope() * REGENERARSE_PORCENTAJE_SALUD);
	this.aumentarSalud((int) this.getRandom().aplicarDispersión(salud, REGENERARSE_DESVIO));
	int energia = (int) (this.getSaludTope() * REGENERARSE_PORCENTAJE_ENERGIA);
	this.aumentarEnergia((int) this.getRandom().aplicarDispersión(energia, REGENERARSE_DESVIO));
	// el -1 es porque la primera recuperación de salud y energía la hace en
	// el momento
	this.turnosRegenerarse = REGENERARSE_TURNOS - 1;

	return true;
    }

    /**
     * Habilidad que permite al brujo reducir el danio recibido en un
     * porcentaje.
     * @return Devuelve true si se pudo utilizar la habilidad.
     */
    public boolean campoDeEnergia() {
	// no se puede utilizar si la energía actual está por debajo de un
	// porcentaje determinado
	if (this.getEnergia() / this.getEnergiaTope() < CAMPO_ENERGIA_PORCENTAJE_NECESARIO) {
	    return false;
	}

	this.turnosCampoEnergia = CAMPO_ENERGIA_TURNOS;

	return true;
    }

}
