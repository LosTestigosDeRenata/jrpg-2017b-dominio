package dominio;

public class NpcBandido extends NonPlayableCharacter
{
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
	 * Porcentajes y multiplicadores que se utilizarán en las distintas habilidades del NPC.
	 */
	private static final double CHANCE_EVADIR = 0.15;
	/**
	 * Ataque normal
	 */
	private static final double ATAQUENORMAL_CHANCE_CRÍTICO = 0.25;
	private static final double ATAQUENORMAL_MULT_CRÍTICO = 2.5;
	private static final double ATAQUENORMAL_DESVÍO = 0.2;
	/**
	 * Ataque Doble
	 */
	private static final double ATAQUEDOBLE_COSTE_ENERGIA = 30;
	private static final double ATAQUEDOBLE_MULT_DAÑO = 2;
	private static final double ATAQUEDOBLE_CHANCE_CRÍTICO = 0.1;
	private static final double ATAQUEDOBLE_MULT_CRÍTICO = 2;
	private static final double ATAQUEDOBLE_DESVÍO = 0.15;
	/**
	 * Curarse
	 */
	private static final double CURARSE_COSTE_ENERGIA = 15;
	private static final double CURARSE_MULT_NIVEL = 20;
	private static final double CURARSE_DESVÍO = 0.6;
	/**
	 * Energizarse
	 */
	private static final double ENERGIZARSE_MULT_NIVEL = 10;
	private static final double ENERGIZARSE_DESVÍO = 0.35;
	
	/**
	 * Constructor de la clase. Coincide con los parámetros de la superclase.
	 * @param nombre
	 * @param nivel
	 */
	public NpcBandido (final String nombre, final int nivel) 
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
				this.salud -= daño;
		}
		return daño;
	}
	
	@Override
	public void jugarTurno (Peleable objetivo)
	{
		// Si el nivel es menor a 3 nunca llamo a energizarse
		// porque la cantidad de energia que recupera es muy poca como para ser útil.
		if (this.getNivel() < 3)
		{
			// 30% chance de que se quiera curar si no está con vida completa.
			if (this.getRandom().nextDouble() <= 0.3)
				curarse();
			else
			{
				// 25% chance de realizar un ataque doble
				if (this.getRandom().nextDouble() <= 0.25)
				{
					// intento realizar un ataque doble y si no puedo, ataque simple.
					if (!ataqueDoble(objetivo))
						ataqueNormal(objetivo);
				}
				else
					ataqueNormal(objetivo);
			}
		}
		// El bandido con nivel mayor a 3 es un poco más agresivo.
		else
		{
			// Si mi salud está por encima de la mitad, nunca voy a intentar curarme
			if (this.getSalud() >= this.getSaludTope() * 0.5)
			{
				// 35% chance de realizar un ataque doble
				if (this.getRandom().nextDouble() <= 0.35)
				{
					// intento realizar un ataque doble y si no puedo, energizarse.
					if (!ataqueDoble(objetivo))
						energizarse();
				}
				else
					ataqueNormal(objetivo);
			}
			else
			{
				// 40% chance de curarse
				if (this.getRandom().nextDouble() <= 0.4)
					curarse();
				else
				{
					// 50% chance de realizar un ataque doble
					if (this.getRandom().nextDouble() <= 0.5)
					{
						// intento realizar un ataque doble y si no puedo, energizarse.
						if (!ataqueDoble(objetivo))
							energizarse();
					}
					else
						ataqueNormal(objetivo);
				}
			}
		}
	}
	
	/*
	 * Ataques, devuelven true si el ataque se efectuó.
	 * Devuelven false si no se pudo efectuar porque no se cumplian las precondiciones.
	 */
	public boolean ataqueNormal (Peleable atacado)
	{
		int daño = this.getAtaque();
		
		if (this.getRandom().nextDouble() <= ATAQUENORMAL_CHANCE_CRÍTICO)
			daño = (int) (daño * ATAQUENORMAL_MULT_CRÍTICO);
		
		dañarPeleable(atacado, (int) this.getRandom().aplicarDesvío(daño, ATAQUENORMAL_DESVÍO));
		
		// El ataque normal para el bandido siempre se puede efectuar.
		return true;
	}
	
	public boolean ataqueDoble (Peleable atacado)
	{
		if (this.energia <= ATAQUEDOBLE_COSTE_ENERGIA)
			return false;
		
		int daño = (int) (this.getAtaque() * ATAQUEDOBLE_MULT_DAÑO);
		
		if (this.getRandom().nextDouble() <= ATAQUEDOBLE_CHANCE_CRÍTICO)
			daño = (int) (daño * ATAQUEDOBLE_MULT_CRÍTICO);
		
		dañarPeleable(atacado, (int) this.getRandom().aplicarDesvío(daño, ATAQUEDOBLE_DESVÍO));
		this.energia -= ATAQUEDOBLE_COSTE_ENERGIA;

		return true;
	}
	
	public boolean curarse ()
	{
		if (this.energia <= CURARSE_COSTE_ENERGIA)
			return false;
		
		int salud = (int) (this.getNivel() * CURARSE_MULT_NIVEL);
		this.aumentarSalud((int) this.getRandom().aplicarDesvío(salud, CURARSE_DESVÍO));
		this.energia -= CURARSE_COSTE_ENERGIA;
		
		return true;
	}
	
	public boolean energizarse ()
	{
		int energia = (int) (this.getNivel() * ENERGIZARSE_MULT_NIVEL);
		this.aumentarEnergia((int) this.getRandom().aplicarDesvío(energia, ENERGIZARSE_DESVÍO));
		
		// el bandido siempre podrá energizarse
		return true;
	}

}
