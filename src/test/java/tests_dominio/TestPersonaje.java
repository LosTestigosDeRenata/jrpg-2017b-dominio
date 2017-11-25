package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;

public class TestPersonaje {

    @Test
    public void testHumano() {
	Humano h = new Humano("Nicolas", new Guerrero(), 1);
	Assert.assertEquals(105, h.getSalud());
	Assert.assertEquals(105, h.getEnergia());
	Assert.assertEquals(15, h.getFuerza());
	Assert.assertEquals(10, h.getDestreza());
	Assert.assertEquals(10, h.getInteligencia());

	Humano h2 = new Humano("Lautaro", new Hechicero(), 2);
	Assert.assertEquals(105, h2.getSalud());
	Assert.assertEquals(105, h2.getEnergia());
	Assert.assertEquals(10, h2.getFuerza());
	Assert.assertEquals(10, h2.getDestreza());
	Assert.assertEquals(15, h2.getInteligencia());

	Humano h3 = new Humano("Hernan", new Asesino(), 3);
	Assert.assertEquals(105, h3.getSalud());
	Assert.assertEquals(105, h3.getEnergia());
	Assert.assertEquals(10, h3.getFuerza());
	Assert.assertEquals(15, h3.getDestreza());
	Assert.assertEquals(10, h3.getInteligencia());

	Humano h4 = new Humano("Ricky", 10, 15, 5, 4, 3, new Asesino(), 100, 5, 2, 150, 200);
	Assert.assertEquals("Ricky", h4.getNombre());
	Assert.assertEquals(10, h4.getSalud());
	Assert.assertEquals(15, h4.getEnergia());
	Assert.assertEquals(5, h4.getFuerza());
	Assert.assertEquals(4, h4.getDestreza());
	Assert.assertEquals(3, h4.getInteligencia());
	Assert.assertEquals(100, h4.getExperiencia());
	Assert.assertEquals(5, h4.getNivel());
	Assert.assertEquals(2, h4.getIdPersonaje());
	Assert.assertEquals(150, h4.getSaludTope());
	Assert.assertEquals(200, h4.getEnergiaTope());
    }

    @Test
    public void testElfo() {
	Elfo e = new Elfo("Nicolas", new Guerrero(), 1);
	Assert.assertEquals(100, e.getSalud());
	Assert.assertEquals(110, e.getEnergia());
	Assert.assertEquals(15, e.getFuerza());
	Assert.assertEquals(10, e.getDestreza());
	Assert.assertEquals(10, e.getInteligencia());

	Elfo e2 = new Elfo("Lautaro", new Hechicero(), 2);
	Assert.assertEquals(100, e2.getSalud());
	Assert.assertEquals(110, e2.getEnergia());
	Assert.assertEquals(10, e2.getFuerza());
	Assert.assertEquals(10, e2.getDestreza());
	Assert.assertEquals(15, e2.getInteligencia());

	Elfo e3 = new Elfo("Hernan", new Asesino(), 3);
	Assert.assertEquals(100, e3.getSalud());
	Assert.assertEquals(110, e3.getEnergia());
	Assert.assertEquals(10, e3.getFuerza());
	Assert.assertEquals(15, e3.getDestreza());
	Assert.assertEquals(10, e3.getInteligencia());

	Elfo e4 = new Elfo("Ricky", 10, 15, 5, 4, 3, new Asesino(), 100, 5, 2, 150, 200);
	Assert.assertEquals("Ricky", e4.getNombre());
	Assert.assertEquals(10, e4.getSalud());
	Assert.assertEquals(15, e4.getEnergia());
	Assert.assertEquals(5, e4.getFuerza());
	Assert.assertEquals(4, e4.getDestreza());
	Assert.assertEquals(3, e4.getInteligencia());
	Assert.assertEquals(100, e4.getExperiencia());
	Assert.assertEquals(5, e4.getNivel());
	Assert.assertEquals(2, e4.getIdPersonaje());
	Assert.assertEquals(150, e4.getSaludTope());
	Assert.assertEquals(200, e4.getEnergiaTope());
    }

    @Test
    public void testOrco() {
	Orco o = new Orco("Nicolas", new Guerrero(), 1);
	Assert.assertEquals(110, o.getSalud());
	Assert.assertEquals(100, o.getEnergia());
	Assert.assertEquals(15, o.getFuerza());
	Assert.assertEquals(10, o.getDestreza());
	Assert.assertEquals(10, o.getInteligencia());

	Orco o2 = new Orco("Lautaro", new Hechicero(), 2);
	Assert.assertEquals(110, o2.getSalud());
	Assert.assertEquals(100, o2.getEnergia());
	Assert.assertEquals(10, o2.getFuerza());
	Assert.assertEquals(10, o2.getDestreza());
	Assert.assertEquals(15, o2.getInteligencia());

	Orco o3 = new Orco("Hernan", new Asesino(), 3);
	Assert.assertEquals(110, o3.getSalud());
	Assert.assertEquals(100, o3.getEnergia());
	Assert.assertEquals(10, o3.getFuerza());
	Assert.assertEquals(15, o3.getDestreza());
	Assert.assertEquals(10, o3.getInteligencia());

	Orco o4 = new Orco("Ricky", 10, 15, 5, 4, 3, new Asesino(), 100, 5, 2, 150, 200);
	Assert.assertEquals("Ricky", o4.getNombre());
	Assert.assertEquals(10, o4.getSalud());
	Assert.assertEquals(15, o4.getEnergia());
	Assert.assertEquals(5, o4.getFuerza());
	Assert.assertEquals(4, o4.getDestreza());
	Assert.assertEquals(3, o4.getInteligencia());
	Assert.assertEquals(100, o4.getExperiencia());
	Assert.assertEquals(5, o4.getNivel());
	Assert.assertEquals(2, o4.getIdPersonaje());
	Assert.assertEquals(150, o4.getSaludTope());
	Assert.assertEquals(200, o4.getEnergiaTope());
    }
}
