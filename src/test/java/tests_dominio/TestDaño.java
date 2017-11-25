package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.Guerrero;
import dominio.Humano;
import dominio.MyRandomStub;
import dominio.Orco;

public class TestDaño {

    @Test
    public void testAtaqueComunYLaSaludNoBajeDe0() {
	Humano h = new Humano("Nico", 100, 100, 100, 20, 30, new Guerrero(0.2, 0.3, 1.5), 0, 1, 1);
	Orco o = new Orco("Nico", 100, 100, 15, 0, 30, new Guerrero(0.2, 0, 1.5), 0, 1, 1);
	h.setRandom(new MyRandomStub(0.49, 3));
	o.setRandom(new MyRandomStub(0.49, 3));
	Assert.assertTrue(o.getSalud() == 100);
	if (h.atacar(o) != 0) {
	    Assert.assertTrue(o.getSalud() == 0);
	    h.atacar(o);
	    Assert.assertTrue(o.getSalud() == 0);
	    h.atacar(o);
	    Assert.assertTrue(o.getSalud() == 0);
	} else
	    Assert.assertTrue(o.getSalud() == 0);
    }

    @Test
    public void testLosMuertosNoAtacan() {
	Humano h = new Humano("Nico", 100, 100, 25, 0, 30, new Guerrero(0.2, 0, 1.5), 0, 1, 1);
	Orco o = new Orco("Nico", 100, 100, 15, 0, 30, new Guerrero(0.2, 0, 1.5), 0, 1, 1);
	h.setRandom(new MyRandomStub(0.49, 3));
	o.setRandom(new MyRandomStub(0.49, 3));
	h.atacar(o);
	h.atacar(o);
	h.atacar(o);
	h.atacar(o);

	o.atacar(h);
	Assert.assertEquals(100, h.getSalud());
    }

    @Test
    public void testInvulnerabilidad() {
	Humano h = new Humano("Nico", new Guerrero(), 0);
	Assert.assertEquals(false, h.esInvulnerable());
	h.setInvulnerabilidad(true);
	Assert.assertEquals(true, h.esInvulnerable());
    }

    @Test
    public void testDaniarInvulnerable() {
	Humano h = new Humano("Nico", 100, 100, 100, 20, 30, new Guerrero(0, 0, 1.5), 0, 1, 1);
	Orco o = new Orco("Nico", 100, 100, 100, 0, 30, new Guerrero(0, 0, 1.5), 0, 1, 1);
	h.setRandom(new MyRandomStub(0.49, 3));
	o.setRandom(new MyRandomStub(0.49, 3));

	h.setInvulnerabilidad(true);
	Assert.assertEquals(0, o.atacar(h));
	Assert.assertEquals(100, h.atacar(o));
	o.serCurado(100);
	
	h.setInvulnerabilidad(false);
	o.setInvulnerabilidad(false);
	Assert.assertEquals(100, o.atacar(h));
	h.serCurado(100);
	Assert.assertEquals(100, h.atacar(o));
	o.serCurado(100);
    }
}