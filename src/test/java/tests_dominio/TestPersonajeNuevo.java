package tests_dominio;


import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Hechicero;
import dominio.Humano;
import dominio.MyRandomStub;
import dominio.Personaje;


public class TestPersonajeNuevo {

	@Test
	public void testDistancia() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		Personaje p2 = new Humano("Link",new Hechicero(),2);
		Assert.assertEquals(0, p1.distanciaCon(p2),0);
	}
	
	@Test
	public void testAliarYaTeniendoAlianza() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		Personaje p2 = new Humano("Link",new Hechicero(),2);
		p1.crearAlianza("AndaPls");
		p2.crearAlianza("El 30 vuelve House of Cards");
		Assert.assertFalse(p1.aliar(p2));
	}
	
	@Test
	public void testAliarPorPrimeraVez() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		
		p1.crearAlianza("AndaPls");
		Personaje p2 = new Humano("Link",new Hechicero(),2);
		Assert.assertTrue(p1.aliar(p2));
	}
	
	@Test
	public void testSalirAlianza() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		p1.salirDeAlianza();
		p1.crearAlianza("AndaPls");
		Assert.assertNotNull(p1.getClan());
		p1.salirDeAlianza();
		Assert.assertNull(p1.getClan());
	}
	
	@Test
	public void testSerRobado() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		// No le hace nada porque al defensa es mayor al dano
		Assert.assertEquals(0, p1.serRobadoSalud(3));
		// 105 de vida + 15 de defensa = 130
		Assert.assertEquals(105, p1.serRobadoSalud(130));
		Assert.assertEquals(0, p1.getSalud());
	}
	
	@Test
	public void testSerDesenergizado() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		// No le hace nada porque al defensa es mayor al dano
		Assert.assertEquals(0, p1.serDesernegizado(3));
		// 105 de energia + 15 de defensa = 130
		Assert.assertEquals(105, p1.serDesernegizado(130));
		Assert.assertEquals(0, p1.getEnergia());
	}
	
	@Test
	public void testPuedoAtacar() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		Assert.assertTrue(p1.puedeAtacar());
		p1.aumentarEnergia(-96);
		Assert.assertEquals(9, p1.getEnergia());
		Assert.assertFalse(p1.puedeAtacar());
	}
	
	@Test
	public void testAtacarGolpeCritico() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		Personaje p2 = new Humano("Link",new Hechicero(),2);
		p1.setRandom(new MyRandomStub(0.49,3));
		p2.setRandom(new MyRandomStub(0.49,3));
		p1.aumentarDestreza(985);
		Assert.assertEquals(1000, p1.getDestreza());
		//Me aseguro golpe critico, 12 porque 22.5 truncado 
		// es 22 y la defensa de p2 es 10
		Assert.assertEquals(12, p1.atacar(p2));
	}
	
	@Test
	public void testRestablecerSalud() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		p1.reducirSalud(102);
		Assert.assertEquals(3, p1.getSalud());
		p1.restablecerSalud();
		Assert.assertEquals(105, p1.getSalud());
	}
	
	@Test
	public void testRestablecerEnergia() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		p1.aumentarEnergia(-102);
		Assert.assertEquals(3, p1.getEnergia());
		p1.restablecerEnergia();
		Assert.assertEquals(105, p1.getEnergia());
	}
	
	@Test
	public void testSerAtacado() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		Assert.assertEquals(0, p1.serAtacado(3));
		p1.getCasta().aumentarEvitarDanio(8);
		// Le aumento evitardano y vuelvo a probar con un dano 
		// mucho mayor a la defensa y asi me devuelve 0
		// porque no entra al if
		Assert.assertEquals(0, p1.serAtacado(900));
	}
	
	@Test
	public void testSerEnergizado() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		p1.aumentarEnergia(-97);
		Assert.assertEquals(8, p1.getEnergia());
		p1.serEnergizado(900);
		Assert.assertEquals(105, p1.getEnergia());
	}
	
	@Test
	public void testNombreRaza() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		Assert.assertEquals("Humano", p1.getNombreRaza());
	}
	
	@Test
	public void testSetMagia() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		p1.setMagia(20);
		Assert.assertEquals(20, p1.getMagia());
	}


	
	@Test
	public void testGetIdPersonaje() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		Assert.assertEquals(2, p1.getIdPersonaje());
	}

	@Test
	public void testAumentarSalud() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		p1.aumentarSalud(10);
		Assert.assertEquals(115, p1.getSalud());
	}
	
	@Test
	public void testActualizarAtributos() {
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		HashMap<String, Number> mapaso = new HashMap<String, Number>();
		mapaso.put("salud", 20);
		mapaso.put("energia", 10);
		mapaso.put("defensa", 10);
		mapaso.put("probEvitarDanio", 0.15);
		p1.actualizarAtributos(mapaso);
		Assert.assertEquals(20, p1.getSalud());
		Assert.assertEquals(10, p1.getEnergia());
		Assert.assertTrue(p1.calcularPuntosDeDefensa() == p1.getDestreza());
		
	}
}
