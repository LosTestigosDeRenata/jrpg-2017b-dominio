package dominio;

public class NpcVampiro extends NonPlayableCharacter
{
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
	 * Porcentajes y multiplicadores que se utilizarán en las distintas habilidades del NPC.
	 */
	private static final double CHANCE_EVADIR = 0.2;
	/**
	 * Ataque normal
	 */
	private static final double ATAQUENORMAL_DESVÍO = 0.4;
	/**
	 * Drenar salud
	 */
	private static final double DRENARSALUD_COSTE_ENERGIA = 45;
	private static final double DRENARSALUD_MULT_DAÑO = 1.5;
	private static final double DRENARSALUD_PORCENTAJE_SALUD_CURADA = 0.75;
	private static final double DRENARSALUD_CHANCE_CRÍTICO = 0.3;
	private static final double DRENARSALUD_MULT_CRÍTICO = 1.75;
	private static final double DRENARSALUD_DESVÍO = 0.25;
	/**
	 * Drenar energía
	 */
	private static final double DRENARENERGIA_MULT = 1;
	private static final double DRENARENERGIA_CHANCE_CRÍTICO = 0.15;
	private static final double DRENARENERGIA_MULT_CRÍTICO = 1.75;
	private static final double DRENARENERGIA_DESVÍO = 0.25;
	
	/**
	 * Constructor de la clase. Coincide con los parámetros de la superclase.
	 * @param nombre
	 * @param nivel
	 */
	public NpcVampiro (final String nombre, final int nivel) 
	{
		super(nombre, nivel);

		this.aumentarFuerza(FUERZA_BASE + nivel * MULT_FUERZA);
		this.saludTope = this.salud = SALUD_BASE + nivel * MULT_SALUD;
		this.aumentarDefensa(DEFENSA_BASE + nivel * MULT_DEFENSA);
		this.energiaTope = this.energia = ENERGIA_BASE + nivel * MULT_ENERGIA;
	}

	@Override
	public int serAtacado(int daño)
	{
		if (this.getRandom().nextDouble() <= CHANCE_EVADIR) 
			daño = 0;
		else
		{
			daño -= this.getDefensa();
			if (daño > 0) 
				reducirSalud(daño);
		}
		return daño;
	}
	
	@Override
	public void jugarTurno (Peleable objetivo)
	{
		if (this.salud < this.saludTope)
		{
			if(!drenarSalud(objetivo))
			{
				// se fija si tiene energía substancial como para robarle, sino ni intenta
				if (objetivo.getEnergia() > DRENARSALUD_COSTE_ENERGIA)
					drenarEnergía(objetivo);
				else
					ataqueNormal(objetivo);
			}
		}
		else
			ataqueNormal(objetivo);

	}
	
	public boolean ataqueNormal (Peleable atacado)
	{
		int daño = this.getAtaque();
		
		dañarSalud(atacado, (int) this.getRandom().aplicarDispersión(daño, ATAQUENORMAL_DESVÍO));
		
		// El ataque normal para el bandido siempre se puede efectuar.
		return true;
	}
	
	public boolean drenarSalud (Peleable atacado)
	{
		if (this.energia <= DRENARSALUD_COSTE_ENERGIA)
			return false;
		
		int daño = (int) (this.getAtaque() * DRENARSALUD_MULT_DAÑO);
		
		if (this.getRandom().nextDouble() <= DRENARSALUD_CHANCE_CRÍTICO)
			daño = (int) (daño * DRENARSALUD_MULT_CRÍTICO);
			
		
		int dañoCausado = dañarSalud(atacado, (int) this.getRandom().aplicarDispersión(daño, DRENARSALUD_DESVÍO));
		
		// el vampiro se cura un porcentaje del daño causado
		this.aumentarSalud((int) (dañoCausado * DRENARSALUD_PORCENTAJE_SALUD_CURADA));
		this.energia -= DRENARSALUD_COSTE_ENERGIA;

		return true;
	}
	
	public boolean drenarEnergía (Peleable atacado)
	{
		int daño = (int) (this.getAtaque() * DRENARENERGIA_MULT);
		
		if (this.getRandom().nextDouble() <= DRENARENERGIA_CHANCE_CRÍTICO)
			daño = (int) (daño * DRENARENERGIA_MULT_CRÍTICO);
		
		int dañoEnergia = (int) this.getRandom().aplicarDispersión(daño, DRENARENERGIA_DESVÍO);
		
		// robo la energía del atacado
		this.aumentarEnergia(dañarEnergia(atacado, dañoEnergia));

		// el vampiro siempre podrá drenar energía.
		return true;
	}

}
