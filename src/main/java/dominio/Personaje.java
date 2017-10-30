package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;

/** Clase Personaje.
 * La cual sirve de base para la creación de las distintas Razas.
 */

public abstract class Personaje extends MadreDeTodo implements Peleable, Serializable {
	/**
	 * Salud del personaje.
	 */
	private int salud;
	/**
	 * Energia del personaje.
	 */
	private int energia;
	/**
	 * Cantidad de habilidades que posee el personaje dada su casta.
	 */
	private static final int CANTHABILIDADESCASTA = 3;
	/**
	 * Cantidad de habilidades que posee el personaje dada su raza.
	 */
	private static final int CANTHABILIDADESRAZA = 2;
	/**
	 * Cantidad de niveles.
	 */
	private static final int CANTIDADNIVELES = 101;
	/**
	 * Constante que se suma al cargar la tabla de niveles.
	 */
	private static final int CONSTANTENIVEL = 50;
	/**
	 * Fuerza inicial del personaje.
	 */
	private static final int FUERZAINICIAL = 10;
	/**
	 * Destreza inicial del personaje.
	 */
	private static final int DESTREZAINICIAL = 10;
	/**
	 * Inteligencia inicial del personaje.
	 */
	private static final int INTELIGENCIANICIAL = 10;
	/**
	 * Defensa inicial del personaje.
	 */
	private static final int DEFENSAINICIAL = 0;
	/**
	 * Experiencia inicial del personaje.
	 */
	private static final int EXPERIENCIAINICIAL = 0;
	/**
	 * Nivel inicial del personaje.
	 */
	private static final int NIVELINICIAL = 1;
	/**
	 * Salud tope inicial del personaje.
	 */
	private static final int SALUDTOPEINICIAL = 100;
	/**
	 * Energia tope inicial del personaje.
	 */
	private static final int ENERGIATOPEINICIAL = 100;
	/**
	 * Fuerza maxima del personaje.
	 */
	private static final int FUERZAMAXIMA = 200;
	/**
	 * Defensa maxima del personaje.
	 */
	private static final int DEFENSAMAXIMA = 200;
	/**
	 * Inteligencia maxima del personaje.
	 */
	private static final int INTELIGENCIAMAXIMA = 200;
	/**
	 * Nivel maximo permitido.
	 */
	private static final int NIVELMAXIMO = 100;
	/**
	 * Aumento de salud tope al subir de nivel.
	 */
	private static final int SALUDTOPESUBIRN = 25;
	/**
	 * Aumento de energia tope al subir de nivel.
	 */
	private static final int ENERGIATOPESUBIRN = 20;
	/**
	 * Cantidad a multiplicar.
	 * Para obtener los puntos de ataque de personaje.
	 */
	private static final double MULTIPLICADORFZA = 1.5;
	/**
	 * Cantidad a multiplicar para obtener los puntos de magia de personaje.
	 */
	private static final double MULTIPLICADORMGA = 1.5;
	/**
	 * Cantidad a multiplicar.
	 * Para obtener la experiencia otorgada por el personaje
	 * cuando este es vencido.
	 */
	private static final int MULTIPLICADOREXP = 40;
	/**
	 * Energia minima necesaria para poder realizar una habilidad.
	 */
	private static final int ENERGIAMINIMA = 10;
	/**
	 * Numero por el cual se divide la destreza del personaje.
	 * Para calcular la posibilidad de evitar el dano
	 */
	private static final int DIVISORDEDESTREZA = 1000;
	/**
	 * Posicion inicial del Personaje en X.
	 */
	private static final int POSXI = 0;
	/**
	 * Posicion inicial del personaje en Y.
	 */
	private static final int POSYI = 0;

	/**
	 * Puntos iniciales de los jugadores
	 */
	private static final int PUNTOSINICIAL = 3;
	/**
	 * Ataque del personaje.
	 */
	private int ataque;
	/**
	 * Magia del personaje.
	 */
	private int magia;
	/**
	 * Nombre de la Raza del Personaje.
	 */
	private String nombreRaza;
	/**
	 * Salud tope del Personaje.
	 */
	private int saludTope;
	/**
	 * Energia tope del Personaje.
	 */
	private int energiaTope;
	/**
	 * Destreza del Personaje.
	 */
	private int destreza;
	/**
	 * Inteligencia del Personaje.
	 */
	private int inteligencia;
	/**
	 * Casta del Personaje.
	 */
	private Casta casta;
	/**
	 * Experiencia del Personaje.
	 */
	private int experiencia;
	/**
	 * Puntos de skill del personaje.
	 */
	private int puntosSkill;
	/**
	 * Identificador del Personaje.
	 */
	private int idPersonaje;
	/**
	 * Alianza del Personaje.
	 */
	private Alianza clan = null;
	/**
	 * Variable estatica que contiene los niveles de personaje.
	 */
	private static int[] tablaDeNiveles;
	/**
	 * Habilidades obtenidas segun la raza del personaje.
	 */
	private String[] habilidadesRaza = new String[CANTHABILIDADESRAZA];
	/**
	 * Habilidades obtenidas segun la casta del personaje.
	 */
	private String[] habilidadesCasta = new String[CANTHABILIDADESCASTA];
	/**
	 * Nombre de la casta del personaje.
	 */
	private String nombreCasta;


	/**
	 * Metodo que retorna las habilidades que posee el personaje.
	 * Segun la casta del mismo.
	 * @return String[] de habilidades casta.
	 */

	public final String[] getHabilidadesCasta() {
		return casta.getHabilidadesCasta();
	}
	/**
	 * Metodo estatico.
	 * Que sirve para cargar la tabla de niveles de personaje.
	 */
	public static void cargarTablaNivel() {
		Personaje.setTablaDeNiveles(new int[CANTIDADNIVELES]);
		Personaje.getTablaDeNiveles()[0] = 0;
		Personaje.getTablaDeNiveles()[1] = 0;
		for (int i = 2; i < CANTIDADNIVELES; i++) {
			Personaje.getTablaDeNiveles()[i] = Personaje.getTablaDeNiveles()[i - 1] + CONSTANTENIVEL;
		}
	}

	/** La clase Personaje es la cual posee todos los atributos.
	 * Algunos serán completados por las clases hijo (Elfo,Humano,Orco)
	 * como por ejemplo el array habilidadesRaza[].
	 * Dependiendo de qué instancia es el parámetro casta,
	 * se incrementará en 5 un atributo del personaje
	 * @param nombre Indica el nombre el personaje
	 * @param casta Indica la casta(Raza) del personaje
	 * y con ella el incremento que tendrá cierto atributo
	 * @param id Identificador del personaje
	 */
	public Personaje(final String nombre, final Casta casta, final int id) {
		super(FUERZAINICIAL, DEFENSAINICIAL, NIVELINICIAL, nombre);


		this.casta = casta;
		this.idPersonaje = id;
		experiencia = EXPERIENCIAINICIAL;
		inteligencia = INTELIGENCIANICIAL;
		destreza = DESTREZAINICIAL;
		saludTope = SALUDTOPEINICIAL;
		energiaTope = ENERGIATOPEINICIAL;
		aumentarEnergiaTope(getEnergiaBonus());
		aumentarSaludTope(getSaludBonus());
		aumentarDestreza(casta.recibirDestrezaBonus());
		aumentarFuerza(casta.recibirFuerzaBonus());
		aumentarInteligencia(casta.recibirInteligenciaBonus());
		nombreRaza = getNombreRaza();
		nombreCasta = casta.getNombreCasta();
		habilidadesRaza = getHabilidadesRaza();
		habilidadesCasta = casta.getHabilidadesCasta();
		x = POSXI;
		y = POSYI;
		salud = saludTope;

		energia = energiaTope;
		ataque = this.calcularPuntosDeAtaque();
		magia = this.calcularPuntosDeMagia();
		this.aumentarDefensa(destreza);

	}
	/** La clase Personaje es la cual posee todos los atributos.
	 * De los personajes del juego.
	 * Algunos serán completados por las clases hijo
	 * (Elfo,Humano,Orco) como por ejemplo:
	 * El array habilidadesRaza[], a diferencia del constructor
	 * de sólo 3 parámetros,
	 * éste recibe la mayoría de los atributos.
	 * @param nombre Nombre del personaje
	 * @param salud Salud del personaje
	 * @param energia Energia del personaje
	 * @param fuerza Fuerza del Personaje
	 * @param destreza Destreza del personaje
	 * @param inteligencia Inteligencia del personaje
	 * @param casta Casta(Raza) del personaje
	 * @param experiencia Experiencia del personaje
	 * @param nivel Nivel del personaje
	 * @param idPersonaje Id del personaje
	 */
	public Personaje(final String nombre, final int salud, final int energia, final int fuerza,
			final int destreza, final int inteligencia, final Casta casta,
			final int experiencia, final int nivel,
			final int idPersonaje) {
		super(fuerza, 0, nivel, nombre);

		this.salud = salud;
		this.energia = energia;

		this.destreza = destreza;
		this.aumentarDefensa(destreza);
		this.inteligencia = inteligencia;
		this.casta = casta;

		this.experiencia = experiencia;


		this.saludTope = this.salud;
		this.energiaTope = this.energia;

		this.idPersonaje = idPersonaje;

		this.ataque = this.calcularPuntosDeAtaque();
		this.magia = this.calcularPuntosDeMagia();
	}
	/**Retorna un entero con el ataque del personaje.
	 * @return ataque del personaje.
	 */
	@Override
	public final int getAtaque() {
		return ataque;
	}
	/**Metodo void que sobreescribe el atributo de ataque
	 * con el parametro enviado.
	 * @param ataque nuevo valor del ataque del peronaje.
	 */
	@Override
	public final void setAtaque(final int ataque) {
		this.ataque = ataque;
	}

	/**Retorna un entero con la magia del personaje.
	 * @return Magia del personaje.
	 */
	@Override
	public final int getMagia() {
		return magia;
	}

	/**Metodo void que sobreescribe el atributo de magia.
	 * con el parámatero.
	 * @param magia Nuevo valor de magia del personaje.
	 */
	public final void setMagia(final int magia) {
		this.magia = magia;
	}
	/**Retorna un String con la alianza del personaje.
	 * @return Alianza del personaje.
	 */
	public final Alianza getClan() {
		return clan;
	}
	/**Metodo void que sobreescribe el atributo clan.
	 * Añade al personaje llamador al clan enviado
	 * como parámetro.
	 * @param clan Nueva del personaje.
	 */
	public final void setClan(final Alianza clan) {
		this.clan = clan;
		clan.aniadirPersonaje(this);
	}
	/**Retorna entero con la salud del personaje.
	 * @return Salud del personaje
	 */
	@Override
	public final int getSalud() {
		return salud;
	}

	/**Retorna entero con la energia del personaje.
	 * @return Energia del personaje
	 */
	public final int getEnergia() {
		return energia;
	}

	/**Retorna un entero con la destreza del personaje.
	 * @return Destreza del personaje
	 */
	public final int getDestreza() {
		return destreza;
	}


	/** Retorna un entero con la inteligencia del personaje.
	 * @return Inteligencia del personaje
	 */
	public final int getInteligencia() {
		return inteligencia;
	}

	/**Retorna una Casta con la casta del personaje.
	 * @return Casta del personaje
	 */
	public final Casta getCasta() {
		return casta;
	}

	/**Retorna un entero con la experiencia del personaje.
	 * @return Experiencia del personaje
	 */
	public final int getExperiencia() {
		return experiencia;
	}

	/** Retorna un entero con el Id del personaje.
	 * @return Identificacion del personaje
	 */
	public final int getIdPersonaje() {
		return idPersonaje;
	}

	/**Retorna un entero.
	 * Con el maximo de salud que tiene el personaje
	 * @return saludTope del personaje
	 */
	public final int getSaludTope() {
		return saludTope;
	}
	/**Retorna un entero.
	 * Con la energia Maxima que puede
	 * tener el personaje.
	 * @return Energia maxima del personaje.
	 * */
	public final int getEnergiaTope() {
		return energiaTope;
	}
	/**
	 * Método que retorna un entero.
	 * Dependiendo del resultado de las comparaciones entre
	 *  el Personaje llamador y el argumento que puede ser instancia
	 *  de Personaje o de NPC (NonPlayableCharacter)
	 * La probabilidad de golpe critico depende de la casta del Personaje
	 * y de la destreza del mismo. Si la probabilidad junto con la
	 * destreza es mayor o igual al número generado de manera aleatoria
	 * entonces se atacará con golpe crítico, de lo contrario
	 * será atacado con el valor del atributo ataque.
	 * @param atacado Instancia de Persona o NPC la cual será atacada
	 * @return Retorna si el ataque fue realizado con éxito o no.
	 */
	@Override
	public final int atacar(final Peleable atacado) {
		if (salud == 0) {
			return 0;
		}
		if (atacado.getSalud() > 0) {

			if (this.getRandom().nextDouble() <= this.casta.getProbabilidadGolpeCritico()
					+ this.destreza / DIVISORDEDESTREZA) {
				return atacado.serAtacado(this.golpeCritico());
			} else {
				return atacado.serAtacado(this.ataque);
			}
		}
		return 0;
	}

	/** Metodo que retorna un entero.
	 * Que depende a que casta pertenece el
	 * personaje y que ataque poseaa. El daño critico se
	 * obtiene de la clase casta.
	 * El entero surge de la multiplicacion del ataque del personaje y
	 * el daño critico de la casta que pertenece.
	 * @return Retorna el golpe critico que puede realizar el personaje.
	 */
	public final int golpeCritico() {
		return (int) (this.ataque * this.getCasta().getDanioCritico());
	}
	/**
	 * Metodo no implementado.
	 */
	@Override
	public void despuesDeTurno() {

	}
	/** Metodo que retorna un boolean si el personaje puede atacar o no.
	 * Devuelve true si la energia es mayor a la ENERGIAMINIMA,
	 * puede atacar, y falso si la primera es menor a la ENERGIAMINIMA.
	 * ENERGIAMINIMA atributo static de la clase Personaje.
	 * @return Si el personaje puede o no atacar.
	 */
	public final boolean puedeAtacar() {
		return energia > ENERGIAMINIMA;
	}

	/** Metodo que retorna un entero que representa los puntos de
	 * ataque que realizara el personaje. Estos puntos dependen de la
	 * fuerza del personaje y MULTIPLICADORFZA.
	 * MULTIPLICADORFZA atributo static de la clase Personaje.
	 * @return Los puntos de ataque del personaje.
	 */
	public final int calcularPuntosDeAtaque() {
		return (int) (this.getFuerza() * MULTIPLICADORFZA);
	}
	/**Metodo que retorna un entero con los puntos de defensa.
	 * Estos puntos son iguales a los puntos de destreza
	 * del personaje.
	 * @return Los puntos de destreza del personaje.
	 */
	public final int calcularPuntosDeDefensa() {
		return (this.getDestreza());
	}

	/**Metodo que retorna un entero con los puntos de magia
	 * del personaje. Estos puntos dependen de la multiplicacion
	 * de la inteligencia del personaje y MULTIPLICADORMGA.
	 * MULTIPLICADORMGA atributo static de la clase Personaje.
	 * @return Puntos de magia del personaje
	 */
	public final int calcularPuntosDeMagia() {
		return (int) (this.getInteligencia() * MULTIPLICADORMGA);
	}

	/** Metodo void que establece la salud actual del personaje
	 * como la maxima posible que puede tener.
	 */
	public final void restablecerSalud() {
		this.salud = this.saludTope;
	}
	/** Metodo void que establece la energia del personaje
	 * como la maxima posible que puede tener.
	 */
	public final void restablecerEnergia() {
		this.energia = this.energiaTope;
	}
	/** Metodo void que modifica los atributos de ataque,
	 * defensa y magia del personaje.
	 * Ataque depende de la fuerza del personaje y
	 * de MULTIPLICADORFZA(constante).
	 * Defensa depende de la destreza.
	 * Magia depende de la inteligencia y de MULTIPLICADORMGA (constante).
	 */
	public final void modificarAtributos() {
		/*
		 * Tener en cuenta para cuando implementemos asignarPuntosSkills
		 */
		this.ataque = this.calcularPuntosDeAtaque();
		this.aumentarDefensa(destreza);
		this.magia = this.calcularPuntosDeMagia();
	}

	/** Metodo que retorna boolean heredado de la interface Peleable.
	 * Si la salud del personaje es mayor a 0 este está vivo.
	 *
	 * @return Retorna si esta vivo o no el personaje.
	 */
	@Override
	public final boolean estaVivo() {
		return salud > 0;
	}
	/** Método implementado de la Interface Peleable.
	 * Retornará un valor entero dependiendo del resultado
	 * de las comparaciones, si el número generado con la
	 * clase MyRandom es mayor a la probabilidad de evitar daño,
     * La cual depende de la casta del Personaje, entonces no podrá
	 * evitarse el ataque, se descontará el valor del argumento
	 * daño al atributo salud.
	 * Si el valor del atributo salud es menor al valor
	 * del argumento daño, se procederá a igualar el
	 * atributo salud a 0 y retornar el daño realziado
	 * (que será igual a la salud antes de que esté en 0)
	 * @param danioParam valor a descontarse del atributo salud
	 * @return Retorna si el Personaje peude ser atacado.
	 */
	@Override
	public final int serAtacado(final int danioParam) {
		int danio = danioParam;
		if (this.getRandom().nextDouble() >= this.getCasta().getProbabilidadEvitarDanio()) {
			danio -= this.getDefensa();
			if (danio > 0) {
				if (salud <= danio) {
					danio = salud;
					salud = 0;
				} else {
					salud -= danio;
				}
				return danio;
			}
			return 0;
		}
		return 0;
	}

	/**Metodo que retorna un entero de los puntos de salud quitados
	 * al personaje. Al daño total recibido se le resta
	 * la defensa del personaje. Si este daño es menor o igual
	 * a la salud del personaje se le quita daño puntos de salud.
	 * Si el daño es mayor a la salud se establece la salud del
	 * personaje en 0.
	 * @param danioParam Daño causado al personaje
	 * @return Retorna los puntos de vida quitados al personaje
	 */
	public final int serRobadoSalud(final int danioParam) {
		int danio = danioParam;
		danio -= this.getDefensa();
		if (danio <= 0) {
			return 0;
		}
		if ((salud - danio) >= 0) {
			salud -= danio;
		} else {
			danio = salud;
			salud = 0;
		}
		return danio;
	}
	/**Metodo que retorna los puntos de energia quitados
	 * al personaje. Al daño total ejercido al personaje
	 * se le resta la defensa del mismo. Si este daño es
	 * menor o igual a la energia del personaje se le
	 * resta al mismo, sino se establece energia como 0.
	 * @param danioParam Daño causado al personaje
	 * @return Retorna los puntos de energia quitados al personaje.
	 */
	public final int serDesernegizado(final int danioParam) {
		int danio = danioParam;
		danio -= this.getDefensa();
		if (danio <= 0) {
			return 0;
		}
		if ((energia - danio) >= 0) {
			energia -= danio;
		} else {
			danio = energia;
			energia = 0;
		}
		return danio;
	}
	/** Metodo void que aumenta la salud actual del personaje.
	 * Si este aumento es mayor al tope, establece como salud
	 * actual la maxima que puede tener el personaje.
	 * @param saludParam Puntos de salud a sumar al personaje.
	 */
	public final void serCurado(final int saludParam) {
		if ((this.salud + saludParam) <= this.saludTope) {
			this.salud += saludParam;
		} else {
			this.salud = this.saludTope;
		}
	}
	/**Metodo void que aumenta la energia actual del personaje.
	 * Si este aumento es mayor al tope establece como energia
	 * actual la maxima que puede tener el personaje.
	 * @param energiaParam Puntos de energia a sumar al Personaje.
	 */
	public final void serEnergizado(final int energiaParam) {
		if ((this.energia + energiaParam) <= this.energiaTope) {
			this.energia += energiaParam;
		} else {
			this.energia = this.energiaTope;
		}
	}
	/**Metodo void que crea una nueva alianza. Asigna a ésta
	 * al clan actual del personaje y lo añade a la lista de
	 * Personajes que integran la alianza.
	 * @param nombreAlianza Nombre de la alianza
	 */
	public final void crearAlianza(final String nombreAlianza) {
		this.clan = new Alianza(nombreAlianza);
		this.clan.aniadirPersonaje(this);
	}
	/**Metodo void que desvincula al personaje de la alianza
	 * y establece que el personaje no pertenece a ninguna.
	 */
	public final void salirDeAlianza() {
		if (this.clan != null) {
			this.clan.eliminarPersonaje(this);
			this.clan = null;
		}
	}
	/**Metodo que retorna un boolean si pudo añadir un nuevo
	 * aliado a la alianza en la que se encuentra el personaje.
	 * Si el personaje llamador no pertenece a ninguna alianza
	 * se crea una con el nombre "Alianza 1" por defecto.
	 * Luego se agrega al nuevo aliado enviado como parametro
	 * a la alianza del personaje llamador.
	 * @param nuevoAliado Personaje que se añadira al clan del llamador
	 * @return	Boolean si pudo agregar al nuevo aliado
	 */
	public final boolean aliar(final Personaje nuevoAliado) {
		if (this.clan == null) {
			Alianza a = new Alianza("Alianza 1");
			this.clan = a;
			a.aniadirPersonaje(this);
		}

		if (nuevoAliado.clan == null) {
			nuevoAliado.clan = this.clan;
			this.clan.aniadirPersonaje(nuevoAliado);
			return true;
		} else {
			return false;
		}
	}
	/**Metodo void utilizado para aumentar los puntos
	 * de fuerza, destreza y de inteligencia del personaje.
	 * Nunca estos atributos superan los maximos.
	 * Una vez modificados los atributos mencionados actualiza
	 * los nuevos puntos de ataque,defensa y magia del personaje.
	 * @param fuerzaParam Nueva fuerza del personaje
	 * @param destrezaParam Nueva destreza del personaje
	 * @param inteligenciaParam Nueva inteligencia del personaje
	 */
	public final void asignarPuntosSkills(final int fuerzaParam,
			final int destrezaParam, final int inteligenciaParam) {
		if (this.getFuerza() + fuerzaParam <= FUERZAMAXIMA) {
			this.aumentarFuerza(fuerzaParam);
		}
		if (this.destreza + destrezaParam <= DEFENSAMAXIMA) {
			this.destreza += destrezaParam;
		}
		if (this.inteligencia + inteligenciaParam <= INTELIGENCIAMAXIMA) {
			this.inteligencia += inteligenciaParam;
		}
		this.modificarAtributos();
	}

	/** Metodo void que aumenta el nivel del personaje
	 * Si ya se encuentra en el nivel maximo no realiza cambios.
	 * Pero si no se alcanzo al nivel maximo se actualizara la salud tope,
	 * la energia tope y el nivel del personaje hasta que su experiencia
	 * sea menor a la de un nivel preestablecido. Luego se le descuenta
	 * al atributo experiencia la experiencia que se utilizo para aumentar
	 * el nivel del personaje.
	 */
	public final void subirNivel() {

		int acumuladorExperiencia = 0;
		if (this.getNivel() == NIVELMAXIMO) {
			return;
		}
		while (this.getNivel() != NIVELMAXIMO
				&& (this.experiencia >= Personaje.
				getTablaDeNiveles()[this.getNivel() + 1] + acumuladorExperiencia)) {
			acumuladorExperiencia += Personaje.getTablaDeNiveles()[this.getNivel() + 1];
			this.aumentarNivel();
			this.modificarAtributos();
			this.saludTope += SALUDTOPESUBIRN;
			this.energiaTope += ENERGIATOPESUBIRN;
			this.puntosSkill += PUNTOSINICIAL;

		}
		this.experiencia -= acumuladorExperiencia;
	}
	/**Metodo que retorna un boolean significando éste si
	 * el personaje tiene la cantidad suficiente de experiencia
	 * para aumentar el nivel o no.
	 * Si retorna true aumento de nivel y false no.
	 * @param exp Cantidad de experiencia que aumento el personaje
	 * @return Si aumento o no de nivel el personaje
	 */
	public final boolean ganarExperiencia(final int exp) {
		this.experiencia += exp;

		if (experiencia >= Personaje.getTablaDeNiveles()[this.getNivel() + 1]) {
			subirNivel();
			return true;
		}
		return false;
	}
	/** Metodo que retorna un entero con la experiencia
	 * equivalente del personaje que depende del nivel
	 * del mismo y de MULTIPLICADOREXP.
	 * MULTIPLICADOREXP atributo estatico de la clase
	 * personaje.
	 * @return retorna la experiencia brindada por el Personaje
	 */
	@Override
	public final int otorgarExp() {
		return this.getNivel() * MULTIPLICADOREXP;
	}

	/**Metodo que retorna un Objetc utilizado para
	 * clonar a un personaje.
	 * @throws CloneNotSupportedException Excepción de clonación cuando no está implementada
	 * @return Retorna un Objetc con los atributos del
	 * personaje llamador.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**Metodo que retorna un double con la distancia radial
	 * entre el personaje llamador y el personaje parametro.
	 * @param p Personaje a calcular la distancia
	 * @return La distancia entre los dos Personajes
	 */
	public final double distanciaCon(final Personaje p) {
		return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
	}
	/**Metodo que retorna un boolean.
	 * Si pudo realizar exitosamente o no
	 * la habilidad 1. Esta habilidad dependerá de
	 * la casta al que pertenece
	 * el personaje Asesino, Guerrero o Hechicero.
	 * La energia del personaje debe
	 * ser mayor a la minima para lograr la habilidad.
	 * @param atacado Es el personaje al cual le realizará
	 * la habilidad el personaje llamador.
	 * @return Boolean si pudo o no realizar la habilidad 1 de la casta.
	 */
	public final boolean habilidadCasta1(final Peleable atacado) {
		return this.getCasta().habilidad1(this, atacado);
	}

	/**Metodo que retorna un boolean.
	 * Si pudo realizar exitosamente o no
	 * la habilidad 2. Esta habilidad dependerá de
	 * la casta al que pertenece
	 * el personaje Asesino, Guerrero o Hechicero.
	 * La energia del personaje
	 * debe ser mayor a la minima para lograr la habilidad.
	 * @param atacado Es el personaje al cual le realizará la habilidad el
	 * personaje llamador.
	 * @return Boolean si pudo o no realizar la habilidad 2 de la casta.
	 */
	public final boolean habilidadCasta2(final Peleable atacado) {
		return this.getCasta().habilidad2(this, atacado);
	}
	/**Metodo que retorna un boolean.
	 * Si pudo realizar exitosamente o no
	 * la habilidad 2. Esta habilidad dependerá
	 * de la casta Asesino,Guerrero o Hechicero
	 * al que pertenece el personaje. La energia del personaje
	 * debe ser mayor a la minima para lograr la habilidad.
	 * @param atacado Es el personaje al cual le realizará
	 * la habilidad el personaje llamador.
	 * @return Boolean si pudo o no realizar la habilidad 3 de la casta.
	 */
	public final boolean habilidadCasta3(final Peleable atacado) {
		return this.getCasta().habilidad3(this, atacado);
	}
	/**Metodo abstracto que retorna un boolean.
	 * Si pudo realizar exitosamente o no
	 * la habilidad. Esta habilidad dependerá de la raza
	 * al que pertenece el personaje Humano, Orco o Elfo.
	 * La energia del personaje debe ser mayor a la minima para
	 * lograr la habilidad, independientemente de la raza que sea.
	 * @param atacado Es el personaje al cual
	 * le realizará la habilidad el personaje llamador.
	 * @return Boolean si pudo o no realizar la habilidad 1 de la Raza.
	 */
	public abstract boolean habilidadRaza1(Peleable atacado);
	/** Metodo abstracto implementado en cada raza.
	 * Que retorna un vector String con los nombres de las habilidades
	 * de esa raza.
	 * Depende de la raza que sea el personaje
	 * llamador, Humano, Orco o Elfo.
	 * @param atacado Es el personaje al cual
	 * le realizará la habilidad el personaje llamador.
	 * @return Un array de Strings con los nombres de las habilidades.
	 */
	public abstract boolean habilidadRaza2(Peleable atacado);
	/** Metodo abstracto implementado en cada raza.
	 * Que retorna un vector String con los nombres de las habilidades
	 * de esa raza.
	 * Depende de la raza que sea el personaje
	 * llamador, Humano, Orco o Elfo.
	 * @return Un array de Strings con los nombres de las habilidades.
	 */
	public abstract String[] getHabilidadesRaza();
	/**Metodo abstracto implementado en cada raza.
	 * Que retorna un entero con el bonus de salud.
	 * Depende de la raza que sea el personaje llamador
	 * Humano, Orco o Elfo.
	 * @return Retorna el entero con el bonus de salud.
	 */
	public abstract int getSaludBonus();
	/**Metodo abstracto implementado en cada raza.
	 * Que retorna un entero con el bonus de energia.
	 * Depende de la raza que sea el personaje llamador,
	 * Humano, Orco o Elfo.
	 * @return Retorna el entero con el bonus de energia.
	 */
	public abstract int getEnergiaBonus();
	/**Metodo abstracto implementado en cada raza.
	 * Que retorna un String con el nombre de la raza que pertenece
	 * el personaje llamador
	 * Depende de la raza que sea el personaje llamador
	 * Humano, Orco o Elfo.
	 * @return Retorna el String con el nombre de la Raza del personaje.
	 */
	public abstract String getNombreRaza();
	/**
	 * Aumenta la inteligencia del personaje.
	 * Según la cantidad otorgada
	 * @param bonus Cantidad a sumar a inteligencia.
	 */
	public final void aumentarInteligencia(final int bonus) {
		inteligencia += bonus;
	}
	/**
	 * Aumenta la destreza del personaje.
	 * Según la cantidad otorgada
	 * @param bonus Cantidad a sumar a Destreza.
	 */
	public final void aumentarDestreza(final int bonus) {
		destreza += bonus;
	}
	/**
	 * Aumenta la saludTope del personaje.
	 * Según la cantidad otorgada
	 * @param bonus Cantidad a sumar a saludTope.
	 */
	public final void aumentarSaludTope(final int bonus) {
		saludTope += bonus;
	}
	/**
	 * Aumenta la energiaTope del personaje.
	 * Según la cantidad otorgada
	 * @param bonus Cantidad a sumar a energiaTope.
	 */
	public final void aumentarEnergiaTope(final int bonus) {
		energiaTope += bonus;
	}
	/**
	 * Devuelve la tabla de niveles.
	 * @return	Devuelve la tabla de niveles
	 */
	public static final  int[] getTablaDeNiveles() {
		return tablaDeNiveles;
	}
	/**
	 * Reemplaza a la tabla de niveles por otra.
	 * @param tablaDeNiveles tabla de niveles que reemplazará a la anterior.
	 */
	private static void setTablaDeNiveles(final int[] tablaDeNiveles) {
		Personaje.tablaDeNiveles = tablaDeNiveles;
	}
	/**
	 * Método void que aumenta la energía.
	 * @param bonus	monto entero que será agregado a la energía
	 */
	public final void aumentarEnergia(final int bonus) {
		energia += bonus;
	}
	/**
	 * Método void que reduce la energía.
	 * @param monto	monto entero que será reducido a la energía
	 */
	public final void reducirEnergia(final int monto) {
		if (energia > monto) {
			energia -= monto;
		} else {
			energia = 0;
		}

	}
	/**
	 * Método void que reduce la salud.
	 * @param reduc	monto entero que será reducido a la energía
	 */
	public final void reducirSalud(final int reduc) {
		salud -= reduc;
	}
	/**
	 * Método void que aumenta la salud.
	 * @param bonus	monto entero que será agregado a la salud
	 */
	public final void aumentarSalud(final int bonus) {
		salud += bonus;
	}
	/**
	 * Actualiza la salud y la energía del personaje en batalla.
	 * @param map contenedor de los atributos a actualizar.
	 */
	public final void actualizarAtributos(final HashMap<String, Number> map) {
		salud = map.get("salud").intValue();
		energia = map.get("energia").intValue();
		defensa = map.get("defensa").intValue();
		casta.setProbabilidadEvitarDanio(map.get("probEvitarDanio").doubleValue());
	}
	/**
	 * Método que realiza el trueque de items.
	 * @param misItems Items que posee el personaje actualmente.
	 * @param aPoner items a recibir.
	 * @param aSacar items a eliminar.
	 */
	public void trueque(final ArrayList<Item> misItems,
			final ArrayList<Item> aPoner, final DefaultListModel<String> aSacar) {
		int j = 0;
		boolean loop = true;
		ArrayList<Item> aux = misItems;
		while (aSacar.size() > 0) {
			while (loop) {
				if (misItems.get(j).getNombre().equals(aSacar.get(0))) {
					aSacar.remove(0);
					aux.remove(misItems.get(j));
					loop = false;
				}
				j++;
			}
			j = 0;
			loop = true;
		}
		for (Item item : aPoner) {
			aux.add(item);
		}
		this.items = aux;
	}
	/**Retorna un entero con los puntos de skill del personaje.
	 * @return puntos skill del personaje.
	 */
	public final int getPuntosSkill() {
		return puntosSkill;
	}	
	/**Metodo void que sobreescribe el atributo de puntos skill
	 * con el parametro enviado.
	 * @param puntos la cantidad de puntos a asignar.
	 */
	public final void setPuntosSkill(final int puntos) {
		puntosSkill = puntos;
	}
	
}
