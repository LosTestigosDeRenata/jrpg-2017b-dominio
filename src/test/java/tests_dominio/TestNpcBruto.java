package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.MyRandomStub;
import dominio.NpcBruto;
import dominio.RandomGenerator;

public class TestNpcBruto {
	@Test
	public void testInstanciación() {
		NpcBruto bruto = new NpcBruto("Bohr", 1);

		Assert.assertEquals("Bohr", bruto.getNombre());
		Assert.assertEquals(50, bruto.getAtaque());
		Assert.assertEquals(20, bruto.getDefensa());
		Assert.assertEquals(215, bruto.getSalud());
		Assert.assertEquals(30, bruto.getEnergia());
	}

	@Test
	public void testMultiplicadoresNivel() {
		NpcBruto bruto = new NpcBruto("Bohr", 10);

		Assert.assertEquals(230, bruto.getAtaque());
		Assert.assertEquals(92, bruto.getDefensa());
		Assert.assertEquals(1250, bruto.getSalud());
		Assert.assertEquals(75, bruto.getEnergia());
	}

	@Test
	public void testAtaqueNormal() {
		NpcBruto bruto = new NpcBruto("Bohr", 10);
		NpcBruto objetivo = new NpcBruto("Gson", 10);

		Assert.assertTrue(bruto.ataqueNormal(objetivo));
	}

	@Test
	public void testPielDePiedraCosto() {
		NpcBruto bruto = new NpcBruto("Bohr", 10);

		Assert.assertEquals(true, bruto.pielDePiedra());
		bruto.setEnergia(0);
		Assert.assertEquals(false, bruto.pielDePiedra());
	}

	@Test
	public void testPielDePiedraTurnos() {
		NpcBruto bruto = new NpcBruto("Bohr", 10);
		NpcBruto objetivo = new NpcBruto("Gson", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		bruto.setRandom(ran);
		objetivo.setRandom(ran);
		
		Assert.assertEquals(1250, objetivo.getSalud());
		bruto.atacar(objetivo);
		Assert.assertEquals(1112, objetivo.getSalud());
		objetivo.setSalud(1250);
		objetivo.pielDePiedra();
		bruto.atacar(objetivo);
		Assert.assertEquals(1181, objetivo.getSalud());
	}

	@Test
	public void testEnergizarse() {
		NpcBruto bruto = new NpcBruto("Bohr", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		bruto.setRandom(ran);
		bruto.setEnergia(0);
		bruto.energizarse();
		Assert.assertTrue(bruto.getEnergia() > 0);
		
		ran = new MyRandomStub(0.15, 5);
		bruto.setRandom(ran);
		bruto.setEnergia(0);
		bruto.energizarse();
		Assert.assertTrue(bruto.getEnergia() == 0);
	}

}