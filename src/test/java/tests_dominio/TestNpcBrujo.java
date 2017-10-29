package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.MyRandomStub;
import dominio.NpcBrujo;
import dominio.RandomGenerator;

public class TestNpcBrujo {

	@Test
	public void testInstanciación() {
		NpcBrujo brujo = new NpcBrujo("Merlin", 1);

		Assert.assertEquals("Merlin", brujo.getNombre());
		Assert.assertEquals(31, brujo.getAtaque());
		Assert.assertEquals(11, brujo.getDefensa());
		Assert.assertEquals(115, brujo.getSalud());
		Assert.assertEquals(155, brujo.getEnergia());
	}
	
	@Test
	public void testMultiplicadoresNivel() {
		NpcBrujo brujo = new NpcBrujo("Merlin", 10);

		Assert.assertEquals(202, brujo.getAtaque());
		Assert.assertEquals(65, brujo.getDefensa());
		Assert.assertEquals(565, brujo.getSalud());
		Assert.assertEquals(650, brujo.getEnergia());
	}

	@Test
	public void testBolaDeFuego() {
		NpcBrujo brujo = new NpcBrujo("Janson", 10);
		NpcBrujo objetivo = new NpcBrujo("Riky", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		brujo.setRandom(ran);
		objetivo.setRandom(ran);

		Assert.assertEquals(true, brujo.bolaDeFuego(objetivo));
		Assert.assertEquals(226, objetivo.getSalud());
	}

	@Test
	public void testEnvenenar() {
		NpcBrujo brujo = new NpcBrujo("Janson", 10);
		NpcBrujo objetivo = new NpcBrujo("Riky", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		brujo.setRandom(ran);
		objetivo.setRandom(ran);

		brujo.envenenar(objetivo);
		Assert.assertEquals(493, objetivo.getSalud());
	}
	
	@Test
	public void testCampoDeEnergia() {
		NpcBrujo brujo = new NpcBrujo("Janson", 10);
		NpcBrujo objetivo = new NpcBrujo("Riky", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		brujo.setRandom(ran);
		objetivo.setRandom(ran);
		objetivo.campoDeEnergia();
		
		Assert.assertEquals(565, objetivo.getSalud());
		brujo.bolaDeFuego(objetivo);
		Assert.assertEquals(396, objetivo.getSalud());
		brujo.setEnergia(10);
		Assert.assertEquals(false, brujo.campoDeEnergia());
	}
	
	@Test
	public void testRegenerarse() {
		NpcBrujo brujo = new NpcBrujo("Janson", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		brujo.setRandom(ran);
		
		brujo.setSalud(1);
		brujo.setEnergia(0);
		brujo.regenerarse();
		Assert.assertEquals(227, brujo.getSalud());
		Assert.assertEquals(169, brujo.getEnergia());
	}
	
	@Test
	public void testEvasión() {
		NpcBrujo brujo = new NpcBrujo("Janson", 10);
		NpcBrujo objetivo = new NpcBrujo("Riky", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		brujo.setRandom(ran);

		ran = new MyRandomStub(0.05, 5);
		objetivo.setRandom(ran);
		brujo.bolaDeFuego(objetivo);
		Assert.assertEquals(565, objetivo.getSalud());
	}

}