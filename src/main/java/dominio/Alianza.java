
package dominio;

import java.util.LinkedList;

/**
 * La clase Alianza tiene como función agrupar a los jugadores. mediante una
 * LinkedList de Personajes
 */
public class Alianza {
    /**
     * Nombre que tendra la alianza.
     */
    private final String nombre;
    /**
     * Lista la cual contendrá todos los integrantes de la alianza.
     */
    private LinkedList<Personaje> aliados;

    /**
     * @param nombre Nombre que se le dará a la alianza
     */
    public Alianza(final String nombre) {
	this.nombre = nombre;
	this.aliados = new LinkedList<Personaje>();
    }

    /**
     * Se encarga de retornar la lista. Que contiene los personajes
     * pertenecientes a la alianza.
     * @return Retorna la lista de aliados.
     */
    public final LinkedList<Personaje> getAliados() {
	return new LinkedList<Personaje>(aliados);
    }

    /**
     * Se encarga de retornar el nombre de la alianza.
     * @return Retorna nombre de la alianza.
     */
    public final String obtenerNombre() {
	return nombre;
    }

    /**
     * Se encarga de eliminar un personaje de la alianza.
     * @param pj personaje a remover de la alianza.
     */
    public final void eliminarPersonaje(final Personaje pj) {
	aliados.remove(pj);
    }

    /**
     * Método void que agrega un nuevo Personaje a la LinkedList aliados.
     * Utilizando el método add() de la LinkedList
     * @param pj Personaje a agregarse a la lista de aliados
     */
    public final void aniadirPersonaje(final Personaje pj) {
	aliados.add(pj);
    }
}
