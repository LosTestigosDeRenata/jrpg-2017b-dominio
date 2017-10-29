package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.NpcBandido;

public class TestNpcBandido {
	@Test
	public void testInstanciación() {
		NpcBandido bandit = new NpcBandido("Jason", 1);

		Assert.assertEquals("Jason", bandit.getNombre());
		Assert.assertEquals(35, bandit.getAtaque());
		Assert.assertEquals(12, bandit.getDefensa());
		Assert.assertEquals(125, bandit.getSalud());
		Assert.assertEquals(50, bandit.getEnergia());
	}

	@Test
	public void testMultiplicadoresNivel() {
		NpcBandido bandit = new NpcBandido("Janson", 10);

		Assert.assertEquals(170, bandit.getAtaque());
		Assert.assertEquals(75, bandit.getDefensa());
		Assert.assertEquals(620, bandit.getSalud());
		Assert.assertEquals(140, bandit.getEnergia());
	}

	@Test
	public void testAtaqueNormal() {
		NpcBandido bandit = new NpcBandido("Janson", 10);
		NpcBandido objetivo = new NpcBandido("Riky", 1);

		Assert.assertEquals(true, bandit.ataqueNormal(objetivo));
	}

	@Test
	public void testAtaqueDoble() {
		NpcBandido bandit = new NpcBandido("Janson", 10);
		NpcBandido objetivo = new NpcBandido("Riky", 10);

		Assert.assertEquals(true, bandit.ataqueDoble(objetivo));
		bandit.setEnergia(0);
		Assert.assertEquals(false, bandit.ataqueDoble(objetivo));
	}

	@Test
	public void testEnergizarse() {
		NpcBandido bandit = new NpcBandido("Janson", 5);

		bandit.setEnergia(0);
		bandit.energizarse();
		Assert.assertTrue(bandit.getEnergia() > 0);
	}

	@Test
	public void testCurarse() {
		NpcBandido bandit = new NpcBandido("Janson", 5);

		bandit.setSalud(1);
		bandit.curarse();
		Assert.assertTrue(bandit.getSalud() > 1);
	}
}