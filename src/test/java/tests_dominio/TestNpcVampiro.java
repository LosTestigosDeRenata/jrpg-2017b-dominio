package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.MyRandomStub;
import dominio.NpcVampiro;
import dominio.RandomGenerator;

public class TestNpcVampiro {
	@Test
	public void testInstanciación() {
		NpcVampiro vampiro = new NpcVampiro("Nosferatu", 1);

		Assert.assertEquals("Nosferatu", vampiro.getNombre());
		Assert.assertEquals(27, vampiro.getAtaque());
		Assert.assertEquals(15, vampiro.getDefensa());
		Assert.assertEquals(150, vampiro.getSalud());
		Assert.assertEquals(95, vampiro.getEnergia());
	}

	@Test
	public void testMultiplicadoresNivel() {
		NpcVampiro vampiro = new NpcVampiro("Nosferatu", 10);

		Assert.assertEquals(135, vampiro.getAtaque());
		Assert.assertEquals(87, vampiro.getDefensa());
		Assert.assertEquals(960, vampiro.getSalud());
		Assert.assertEquals(410, vampiro.getEnergia());
	}
	
	@Test
	public void testJugarTurno() {
		NpcVampiro vampiro = new NpcVampiro("Janson", 10);
		NpcVampiro objetivo = new NpcVampiro("Riky", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		vampiro.setRandom(ran);
		objetivo.setRandom(ran);
		
		vampiro.setSalud(960);
		objetivo.setSalud(1000);
		vampiro.jugarTurno(objetivo);
		Assert.assertEquals(952, objetivo.getSalud());
		
		vampiro.setSalud(500);
		objetivo.setSalud(1000);
		vampiro.jugarTurno(objetivo);
		Assert.assertEquals(586, vampiro.getSalud());
		Assert.assertEquals(885, objetivo.getSalud());
		
		vampiro.setEnergia(0);
		objetivo.setEnergia(1000);
		vampiro.jugarTurno(objetivo);
		Assert.assertEquals(135, vampiro.getEnergia());
		Assert.assertEquals(865, objetivo.getEnergia());
		
		vampiro.setEnergia(0);
		objetivo.setEnergia(0);
		vampiro.setSalud(1000);
		objetivo.setSalud(1000);
		vampiro.jugarTurno(objetivo);
		Assert.assertEquals(0, vampiro.getEnergia());
		Assert.assertEquals(0, objetivo.getEnergia());
	}

	@Test
	public void testAtaqueNormal() {
		NpcVampiro vampiro = new NpcVampiro("Janson", 10);
		NpcVampiro objetivo = new NpcVampiro("Riky", 1);
		RandomGenerator ran = new MyRandomStub(1, 5);
		vampiro.setRandom(ran);
		objetivo.setRandom(ran);

		Assert.assertEquals(true, vampiro.ataqueNormal(objetivo));
		Assert.assertEquals(30, objetivo.getSalud());
	}

	@Test
	public void testDrenarSalud() {
		NpcVampiro vampiro = new NpcVampiro("Janson", 10);
		NpcVampiro objetivo = new NpcVampiro("Riky", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		vampiro.setRandom(ran);
		objetivo.setRandom(ran);

		Assert.assertEquals(960, vampiro.getSalud());
		Assert.assertEquals(true, vampiro.drenarSalud(objetivo));
		Assert.assertEquals(845, objetivo.getSalud());
		Assert.assertEquals(960, vampiro.getSalud());

		vampiro.setSalud(1);
		Assert.assertEquals(true, vampiro.drenarSalud(objetivo));
		Assert.assertEquals(87, vampiro.getSalud());
		Assert.assertEquals(320, vampiro.getEnergia());

		objetivo.setSalud(1000);
		ran = new MyRandomStub(0.3, 5);
		vampiro.setRandom(ran);
		vampiro.drenarSalud(objetivo);
		Assert.assertEquals(734, objetivo.getSalud());
		
		vampiro.setEnergia(0);
		Assert.assertEquals(false, vampiro.drenarSalud(objetivo));
	}

	@Test
	public void testDrenarEnergía() {
		NpcVampiro vampiro = new NpcVampiro("Janson", 10);
		NpcVampiro objetivo = new NpcVampiro("Riky", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		vampiro.setRandom(ran);
		objetivo.setRandom(ran);

		Assert.assertEquals(410, vampiro.getEnergia());
		Assert.assertEquals(true, vampiro.drenarEnergía(objetivo));
		Assert.assertEquals(275, objetivo.getEnergia());
		Assert.assertEquals(410, vampiro.getEnergia());

		vampiro.setEnergia(0);
		Assert.assertEquals(true, vampiro.drenarEnergía(objetivo));
		Assert.assertEquals(135, vampiro.getEnergia());

		objetivo.setEnergia(1000);
		ran = new MyRandomStub(0.15, 5);
		vampiro.setRandom(ran);
		vampiro.drenarEnergía(objetivo);
		Assert.assertEquals(764, objetivo.getEnergia());
	}

	@Test
	public void testEvasión() {
		NpcVampiro vampiro = new NpcVampiro("Janson", 10);
		NpcVampiro objetivo = new NpcVampiro("Riky", 10);
		RandomGenerator ran = new MyRandomStub(1, 5);
		vampiro.setRandom(ran);

		ran = new MyRandomStub(0.2, 5);
		objetivo.setRandom(ran);

		vampiro.ataqueNormal(objetivo);
		Assert.assertEquals(960, objetivo.getSalud());
	}
	
	@Test
	public void testDanioInfimo() {
		NpcVampiro vampiro = new NpcVampiro("Janson", 10);
		NpcVampiro objetivo = new NpcVampiro("Riky", 10);

		vampiro.daniarSalud(objetivo, 1);
		Assert.assertEquals(960, objetivo.getSalud());
	}

}