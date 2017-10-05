
package dominio;

import java.util.ArrayList;

/** Clase abstracta.
 * Que tiene como funcion tener los atributos que
 * comparten las clases NPC y Personaje
 */
public abstract class MadreDeTodo implements Peleable{

	
	protected int id;
	/**
	 * Salud del personaje.
	 */
	protected int salud;
	/**
	 * Salud tope del Personaje.
	 */
	protected int saludTope;
	protected int energia;
	protected int energiaTope;
	/**
	 * Fuerza que recibira el personaje o npc.
	 */
	protected int fuerza;
	/**
	 * Defensa que recibira el personaje o npc.
	 */
	protected int defensa;
	/**
	 * Nivel que recibira el personaje o npc.
	 */
	protected int nivel;
	/**
	 * Nombre que recibira el personaje o npc.
	 */
	protected String nombre;
	/**
	 * Objeto para obtener valores aleatorios.
	 */
	private RandomGenerator random;
	
	protected boolean esNPC = false;

	protected ArrayList<Item> items = new ArrayList<Item>();



	/** Clase abstracta.
	 * Que tiene como
	 * funcion tener los atributos que
	 * comparten las clases NPC y Personaje
	 * @param fuerza valor otorgado por
	 * el constructor de NPC o Personaje.
	 * @param defensa valor otorgado por
	 * el constructor de NPC o Personaje.
	 * @param nivel valor otorgado por
	 * el constructor de NPC o Personaje.
	 * @param nombre valor otorgado por
	 * el constructor de NPC o Personaje.
	 */
	public MadreDeTodo(final int id, final int energiaTope, final int fuerza, final int defensa, final int saludTope, final int nivel, final String nombre) {
		this.id = id;
		energia = energiaTope;
		this.energiaTope = energiaTope;
		this.fuerza = fuerza;
		this.defensa = defensa;
		this.saludTope = saludTope;
		salud = saludTope;
		this.nivel = nivel;
		this.nombre = nombre;
		this.random = new MyRandom();
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	
	public int getEnergia() {
		return energia;
	}


	public void setEnergia(int energia) {
		this.energia = energia;
	}


	public int getEnergiaTope() {
		return energiaTope;
	}


	public void setEnergiaTope(int energiaTope) {
		this.energiaTope = energiaTope;
	}


	/** Método que devuelve la fuerza del personaje o NPC.
	 * @return fuerza del personaje o NPC.
	 */
	public final int getFuerza() {
		return fuerza;
	}

	/** Método que devuelve la defensa del personaje o NPC.
	 * @return defensa del personaje o NPC.
	 */
	public final int getDefensa() {
		return defensa;
	}
	
	public int getSaludTope() {
		return saludTope;
	}

	/** Método que devuelve el nivel del personaje o NPC.
	 * @return nivel del personaje o NPC.
	 */
	public final int getNivel() {
		return nivel;
	}

	/** Método que devuelve el nombre del personaje o NPC.
	 * @return nombre del personaje o NPC.
	 */
	public final String getNombre() {
		return nombre;
	}
	/** Método void que sobreescribe el atributo nombre.
	 * Con el valor que se ingresa por parámetro.
	 * @param nombre valor a sobreescribir
	 */
	public final void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	/** Metodo que retorna boolean heredado de la interface Peleable.
	 * Si la salud del MadreDeTodos es mayor a 0 este está vivo.
	 *
	 * @return Retorna si esta vivo o no el MadreDeTodos.
	 */
	public final boolean estaVivo() {
		return salud > 0;
	}
	/**Retorna entero con la salud del personaje.
	 * @return Salud del personaje
	 */
	public final int getSalud() {
		return salud;
	}
	/** Método void que aumenta la fuerza del personaje o NPC.
	 * Con el valor que se ingresa por parámetro.
	 * @param aumento fuerza a aumentar.
	 */
	public final void aumentarFuerza(final int aumento) {
		fuerza += aumento;
	}
	/** Método void que aumenta el nivel del personaje o NPC.
	 */
	public final void aumentarNivel() {
		nivel++;
	}

	/**
	 * Getter del Randomizador.
	 * @return Retorna el randomizador.
	 */
	public final RandomGenerator getRandom() {
		return random;
	}

	/**
	 * Setter del Randomizador.
	 * @param random Randomizador que reemplazará al actual.
	 */
	public final void setRandom(final RandomGenerator random) {
		this.random = random;
	}
	/**
	 * Aumenta la defensa del personaje / npc.
	 * @param bonus valor que se le agrega a la defensa.
	 */
	public final void aumentarDefensa(final int bonus) {
		defensa += bonus;
	}
	/**
	 * Reduce la defensa del personaje.
	 * @param reduc Valor que se reduce la defensa.
	 */
	public final void reducirDefensa(final int reduc) {
		defensa -= reduc;
	}
	/**
	 * Metodo para anadir items sin violar el encapsulamiento.
	 * @param i Item a agregar.
	 */
	public final void anadirItem(final Item i) {
		items.add(i);
	}
	/**
	 * Metodo para remover items sin violar el encapsulamiento.
	 * @param i Item a eliminar.
	 */
	public final void removerItem(final Item i) {
		items.remove(i);
	}
	/**
	 * Método para obtener la lista de items.
	 * @return Lista de items del personaje.
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	
}
