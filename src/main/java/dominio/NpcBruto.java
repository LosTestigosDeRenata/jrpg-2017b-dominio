package dominio;

/**
 * NPC defensivo con mucha salud y resistencia a los ataques.
 */
public class NpcBruto extends NonPlayableCharacter {
    /**
     * Atributos base del NPC
     */
    private static final int FUERZA_BASE = 30;
    private static final int SALUD_BASE = 100;
    private static final int DEFENSA_BASE = 12;
    private static final int ENERGIA_BASE = 25;
    /**
     * Multiplicadores de stats del npc, es decir se le aumentarán los atributos
     * al NPC dependiendo de estos multiplicadores.
     */
    private static final int MULT_FUERZA = 20;
    private static final int MULT_SALUD = 115;
    private static final int MULT_DEFENSA = 8;
    private static final int MULT_ENERGIA = 5;
    /**
     * Porcentajes y multiplicadores que se utilizarán en las distintas
     * habilidades del NPC.
     */
    private static final double CHANCE_EVADIR = 0.05;
    /**
     * Ataque normal
     */
    private static final double ATAQUENORMAL_CHANCE_CRITICO = 0.10;
    private static final double ATAQUENORMAL_MULT_CRITICO = 2;
    private static final double ATAQUENORMAL_DESVIO = 0.2;
    /**
     * Energizarse
     */
    private static final double ENERGIZARSE_CHANCE_FRACASAR = 0.15;
    private static final double ENERGIZARSE_MULT_NIVEL = 6;
    private static final double ENERGIZARSE_DESVIO = 0.15;
    /**
     * Piel de piedra
     */
    private static final int PIELPIEDRA_COSTE_ENERGIA = 50;
    private static final int PIELPIEDRA_TURNOS_INICIALES = 3;
    private int turnosPielPiedra;

    /**
     * Constructor de la clase. Coincide con los parámetros de la superclase.
     * @param nombre Nombre del npc.
     * @param nivel Nivel que tendrá el npc y determinará que tan poderoso es.
     */
    public NpcBruto(final String nombre, final int nivel) {
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

	    // aplico reducciones si el npc está bajo los efectos de piel de
	    // piedra
	    if (danio > 0) {
		this.salud -= turnosPielPiedra > 0 ? (int) (danio * 0.5) : danio;
	    }

	}
	return danio;
    }

    @Override
    public void jugarTurno(final Peleable objetivo) {
	// reduzco en 1 los turnos restantes de piel de piedra si es que hay
	if (turnosPielPiedra > 0) {
	    turnosPielPiedra--;
	}

	// 70% chances de usar ataque normal en cada turno
	// 30% de usar piel de piedra si tengo energía suficiente
	// de lo contrario, energizarse.
	if (this.getRandom().nextDouble() <= 0.7) {
	    ataqueNormal(objetivo);
	} else if (!pielDePiedra()) {
	    energizarse();
	}

    }

    /**
     * Ataque simple que causa poco danio al enemigo y se puede utilizar
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

	// El ataque normal para el bruto siempre se puede efectuar.
	return true;
    }

    /**
     * Habilidad que permite al bruto reducir el danio recibido durante una
     * determinada cantidad de turnos.
     * @return Devuelve true si se pudo utilizar la habilidad.
     */
    public boolean pielDePiedra() {
	if (this.energia <= PIELPIEDRA_COSTE_ENERGIA) {
	    return false;
	}

	// cuando utilice piel de piedra, el npc recibirá sólo el 50% del danio
	// recibido
	// durante una determinada cantidad de turnos
	turnosPielPiedra = PIELPIEDRA_TURNOS_INICIALES;
	this.energia -= PIELPIEDRA_COSTE_ENERGIA;

	return true;
    }

    /**
     * Habilidad que permite al bruto recuperar energía, sujeta a una chance de
     * no hacer nada.
     * @return Devuelve true si se pudo utilizar la habilidad.
     */
    public boolean energizarse() {
	// para el bruto, energizarse tiene una determinada chance de no hacer
	// nada
	if (this.getRandom().nextDouble() <= ENERGIZARSE_CHANCE_FRACASAR) {
	    return false;
	}

	int energia = (int) (this.getNivel() * ENERGIZARSE_MULT_NIVEL);
	this.aumentarEnergia((int) this.getRandom().aplicarDispersion(energia, ENERGIZARSE_DESVIO));

	// el bandido siempre podrá energizarse
	return true;
    }

}
