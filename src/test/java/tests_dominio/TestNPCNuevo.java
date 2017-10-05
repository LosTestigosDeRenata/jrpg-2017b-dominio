package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Humano;
import dominio.MyRandomStub;
import dominio.NonPlayableCharacter;
import dominio.Personaje;

public class TestNPCNuevo {

	@Test
	public void testNivelNPC1() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 1);
		Assert.assertEquals(4, npc.getDefensa());
		Assert.assertEquals(20, npc.getFuerza());
		Assert.assertEquals(60, npc.getSalud());
	}

	@Test
	public void testDañoMenorADef() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 1);
		Assert.assertEquals(0, npc.serAtacado(1));
	}
	
	@Test
	public void testGetFuerza() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 1);
		Assert.assertEquals(20, npc.getFuerza());
	}
	
	@Test
	public void testSetAtaque() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 1);
		// Set ataque suma su parametro a la fuerza actual, revisar
		npc.setAtaque(60);
		Assert.assertEquals(80, npc.getFuerza());
	}
	
	@Test
	public void testGetMagia() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 1);
		Assert.assertEquals(0, npc.getMagia());
	}
	
	@Test
	public void testAtacar() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 1);
		NonPlayableCharacter npc2 = new NonPlayableCharacter(1,"Pepe", 1, 1);
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		npc.setRandom(new MyRandomStub(0.49,3));
		npc2.setRandom(new MyRandomStub(0.49,3));
		p1.setRandom(new MyRandomStub(0.49,3));
		// Recordar que trunca porque es entero
		//NPC 20 - (5/2) = 18
		//P1 20 - 15 = 5
		Assert.assertEquals(18, npc.atacar(npc2));
		Assert.assertEquals(5, npc.atacar(p1));
	}
	
	@Test
	public void testSetSalud() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 1);
		npc.setSalud(80);
		Assert.assertEquals(80, npc.getSalud());
	}
	
	@Test
	public void testNivelNPCDefault() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 9);
		Assert.assertEquals(20, npc.getDefensa());
		Assert.assertEquals(100, npc.getFuerza());
		Assert.assertEquals(300, npc.getSalud());
	}
	
	@Test
	public void testNivel3NPC() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 2);
		Assert.assertEquals(6, npc.getDefensa());
		Assert.assertEquals(90, npc.getSalud());
		Assert.assertEquals(30, npc.getFuerza());
	}

	@Test
	public void testAtacar2() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Pepe", 1, 1);
		NonPlayableCharacter npc2 = new NonPlayableCharacter(1,"Pepe", 1, 1);
		Personaje p1 = new Humano("Ben Affleck",new Asesino(),2);
		npc.setRandom(new MyRandomStub(0.1,3));
		npc2.setRandom(new MyRandomStub(0.1,3));
		p1.setRandom(new MyRandomStub(0.49,3));
		// Recordar que trunca porque es entero
		//NPC 20 - (5/2) = 18
		//P1 20 - 15 = 5
		Assert.assertEquals(0, npc.atacar(npc2));
		Assert.assertEquals(15, npc.atacar(p1));
	}
}
