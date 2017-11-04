
package dominio;

/**
 * La clase Hechicero es una casta de Personaje, hereda de la clase Casta. Posee
 * dos constructores El otro constructor, llama al constructor de la clase padre
 * (Casta), pasándole los argumentos recibidos por el constructor hijo.
 */
public class Hechicero extends Casta {
    /**
     * Energia minima para realizar una habilidad.
     */
    private static final int ENERGIAMINIMA = 10;
    /**
     * Numero por el cual seran multiplicados los puntos de magia.
     */
    private static final double MULTIPLICADORMAGIA = 1.5;
    /**
     * Numero por el cual se dividiran los puntos de magia.
     */
    private static final int DIVISORDEMAGIA = 2;
    /**
     * Bonus de inteligencia por ser de casta Hechicero.
     */
    private static final int BONUSINTELIGENCIA = 5;

    private static final boolean NO_SE_EJECUTO = false;

    private static final boolean HABILIDAD_EJECUTADA = true;

    /**
     * @param probCrit Probabilidad de que el personaje realice un golpe crítico
     * @param evasion Probabilidad de que el personaje evite un golpe crítico
     * @param danioCrit Valor por el cual será multiplicado el golpe básico
     */

    public Hechicero(final double probCrit, final double evasion, final double danioCrit) {
	super(probCrit, evasion, danioCrit);

    }

    /**
     * El constructor por defecto, llama al constructor por defecto de la clase
     * padre (Casta), inicializando probabilidadGolpeCritico,
     * probabilidadEvitarDaño, dañoCritico, con valores predeterminados.
     */
    public Hechicero() {
	super();
    }

    /**
     * Retorna un booleano dependiendo de si se realizó exitosamente o no el
     * ataque. La primera condición para que el ataque pueda realizarse es que
     * el atacante(caster) posea 10 o más del atributo energia ya que estos se
     * descuentan seguido de comprobar que los posee, de lo contrario el ataque
     * no será posible y se retornará false. El parámetro caster a su vez llama
     * al método calcularPuntosDeMagia() el cual luego se multiplica por 1.5
     * @param caster Personaje que realiza la habilidad
     * @param atacado puede ser una instancia de Persona o NPC dependiendo de la
     *            misma, variará lo que retornará serAtacado()
     * @return booleano que determina si el ataque fue exitoso o no.
     */
    @Override
    public final boolean habilidad1(final Personaje caster, final Peleable atacado) {
	if (caster.getEnergia() >= ENERGIAMINIMA) {
	    caster.reducirEnergia(ENERGIAMINIMA);
	    return (atacado.serAtacado((int) (caster.calcularPuntosDeMagia() * MULTIPLICADORMAGIA)) > 0);
	}

	return NO_SE_EJECUTO;
    }

    /**
     * Retorna un booleano dependiendo de si se realizó exitosamente o no el
     * ataque. La primera condición para que el ataque pueda realizarse es que
     * el atacante(caster) posea 10 o más del atributo energia ya que estos se
     * descuentan seguido de comprobar que los posee, de lo contrario el ataque
     * no será posible y se retornará false. Para que éste método tenga
     * posibilidad de retornar true, aliado debe ser de la instancia Personaje
     * @param caster Personaje que realiza la habilidad
     * @param aliado atacado puede ser una instancia de Persona o NPC
     *            dependiendo de la misma podrá o no retornar true el método.
     * @return booleano que determina si el ataque fue exitoso o no.
     */
    @Override
    public final boolean habilidad2(final Personaje caster, final Peleable aliado) {
	if (caster.getEnergia() >= ENERGIAMINIMA) {
	    caster.reducirEnergia(ENERGIAMINIMA);
	    if (aliado instanceof Personaje) {
		((Personaje) aliado).serCurado(caster.calcularPuntosDeMagia());
		return HABILIDAD_EJECUTADA;
	    }
	}
	return NO_SE_EJECUTO;
    }

    /**
     * Retorna un booleano dependiendo de si se realizó exitosamente o no el
     * ataque. La primera condición para que el ataque pueda realizarse es que
     * el atacante(caster) posea 10 o más del atributo energia ya que estos se
     * descuentan seguido de comprobar que los posee, de lo contrario el ataque
     * no será posible y se retornará false. Para que éste método tenga
     * posibilidad de retornar true, el Atacado debe ser una instancia Personaje
     * @param caster Personaje que realiza la habilidad
     * @param atacado El atacado puede ser una instancia de Persona o NPC
     *            dependiendo de la misma podrá o no retornar true el método.
     * @return retorna si se ejecutó correctamente la habilidad.
     */
    @Override
    public final boolean habilidad3(final Personaje caster, final Peleable atacado) {
	if (caster.getEnergia() >= ENERGIAMINIMA) {
	    caster.reducirEnergia(ENERGIAMINIMA);
	    if (atacado instanceof Personaje) {
		int energiaRobada = ((Personaje) atacado).serDesernegizado(caster.calcularPuntosDeMagia());
		int saludRobada = ((Personaje) atacado).serRobadoSalud(caster.calcularPuntosDeMagia() / DIVISORDEMAGIA);
		caster.serEnergizado(energiaRobada);
		caster.serCurado(saludRobada);
		return HABILIDAD_EJECUTADA;
	    }
	}
	return NO_SE_EJECUTO;
    }

    /**
     * Retorna un entero que otorga el bonus de inteligencia perteneciente a
     * esta casta.
     * @return Retorna la inteligencia extra de la casta.
     */
    @Override
    public final int recibirInteligenciaBonus() {
	return BONUSINTELIGENCIA;
    }

    /**
     * Retorna un entero que otorga el bonus de destreza perteneciente a esta
     * casta.
     * @return Retorna 0 para esta casta.
     */
    @Override
    public final int recibirDestrezaBonus() {
	return 0;
    }

    /**
     * Retorna un entero que otorga el bonus de fuerza perteneciente a esta
     * casta.
     * @return Retorna 0 para esta casta.
     */
    @Override
    public final int recibirFuerzaBonus() {
	return 0;
    }

    /**
     * Retorna una string con el nombre de la casta.
     * @return Retorna el nombre de la casta.
     */
    @Override
    public final String getNombreCasta() {
	return "Hechiero";
    }

    /**
     * Retorna un vector de string con los nombres de las habilidades de la
     * casta.
     * @return Retorna nombres de las habilidades propias de la casta.
     */
    @Override
    public final String[] getHabilidadesCasta() {
	return new String[] {"Bola de Fuego", "Curar Aliado", "Robar Energia y Salud" };
    }

}
