package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.MyRandomStub;
import dominio.NpcBandido;
import dominio.RandomGenerator;

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
    public void testJugarTurno() {
	NpcBandido bandit = new NpcBandido("Janson", 3);
	NpcBandido objetivo = new NpcBandido("Riky", 3);
	RandomGenerator ran = new MyRandomStub(1, 5);
	bandit.setRandom(ran);
	objetivo.setRandom(ran);

	objetivo.setSalud(1000);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(961, objetivo.getSalud());

	bandit.setSalud(100);
	ran = new MyRandomStub(0.2, 5);
	bandit.setRandom(ran);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(160, bandit.getSalud());

	ran = new MyRandomStub(0.24, 5);
	bandit.setRandom(ran);
	objetivo.setSalud(1000);
	bandit.setEnergia(bandit.getEnergiaTope());
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(896, objetivo.getSalud());

	bandit.setEnergia(0);
	objetivo.setSalud(1000);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(864, objetivo.getSalud());

	bandit = new NpcBandido("Janson", 10);

	ran = new MyRandomStub(0.35, 5);
	bandit.setRandom(ran);
	objetivo.setSalud(1000);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(686, objetivo.getSalud());
	bandit.setEnergia(0);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(100, bandit.getEnergia());

	ran = new MyRandomStub(0.5, 5);
	bandit.setRandom(ran);
	objetivo.setSalud(1000);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(856, objetivo.getSalud());

	bandit.setSalud(100);
	ran = new MyRandomStub(0.4, 5);
	bandit.setRandom(ran);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(300, bandit.getSalud());

	ran = new MyRandomStub(0.45, 5);
	bandit.setRandom(ran);
	objetivo.setSalud(1000);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(686, objetivo.getSalud());
	bandit.setEnergia(0);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(100, bandit.getEnergia());

	ran = new MyRandomStub(0.6, 5);
	bandit.setRandom(ran);
	objetivo.setSalud(1000);
	bandit.jugarTurno(objetivo);
	Assert.assertEquals(856, objetivo.getSalud());
    }

    @Test
    public void testAtaqueNormal() {
	NpcBandido bandit = new NpcBandido("Janson", 10);
	NpcBandido objetivo = new NpcBandido("Riky", 1);
	RandomGenerator ran = new MyRandomStub(1, 5);
	bandit.setRandom(ran);
	objetivo.setRandom(ran);

	Assert.assertEquals(true, bandit.ataqueNormal(objetivo));
	ran = new MyRandomStub(0.15, 5);
	bandit.setRandom(ran);
	objetivo.setSalud(1000);
	bandit.ataqueNormal(objetivo);
	Assert.assertEquals(587, objetivo.getSalud());
    }

    @Test
    public void testAtaqueDoble() {
	NpcBandido bandit = new NpcBandido("Janson", 10);
	NpcBandido objetivo = new NpcBandido("Riky", 10);
	RandomGenerator ran = new MyRandomStub(1, 5);
	bandit.setRandom(ran);
	objetivo.setRandom(ran);

	Assert.assertEquals(true, bandit.ataqueDoble(objetivo));
	ran = new MyRandomStub(0.1, 5);
	bandit.setRandom(ran);
	objetivo.setSalud(1000);
	bandit.ataqueDoble(objetivo);
	Assert.assertEquals(395, objetivo.getSalud());
	bandit.setEnergia(0);
	Assert.assertEquals(false, bandit.ataqueDoble(objetivo));
    }

    @Test
    public void testEnergizarse() {
	NpcBandido bandit = new NpcBandido("Janson", 5);
	RandomGenerator ran = new MyRandomStub(1, 5);
	bandit.setRandom(ran);

	bandit.setEnergia(0);
	bandit.energizarse();
	Assert.assertEquals(50, bandit.getEnergia());
    }

    @Test
    public void testCurarse() {
	NpcBandido bandit = new NpcBandido("Janson", 5);
	RandomGenerator ran = new MyRandomStub(1, 5);
	bandit.setRandom(ran);

	bandit.setSalud(1);
	bandit.curarse();
	Assert.assertEquals(101, bandit.getSalud());

	bandit.setEnergia(0);
	Assert.assertEquals(false, bandit.curarse());
    }

    @Test
    public void testEvasión() {
	NpcBandido bandit = new NpcBandido("Janson", 10);
	NpcBandido objetivo = new NpcBandido("Riky", 10);
	RandomGenerator ran = new MyRandomStub(1, 5);
	bandit.setRandom(ran);

	Assert.assertEquals(620, objetivo.getSalud());
	ran = new MyRandomStub(0.05, 5);
	objetivo.setRandom(ran);
	bandit.atacar(objetivo);
	Assert.assertEquals(620, objetivo.getSalud());
    }

    @Test
    public void testDanioInfimo() {
	NpcBandido bandit = new NpcBandido("Janson", 1);
	NpcBandido objetivo = new NpcBandido("Riky", 10);

	bandit.daniarSalud(objetivo, 1);
	Assert.assertEquals(620, objetivo.getSalud());
    }
}