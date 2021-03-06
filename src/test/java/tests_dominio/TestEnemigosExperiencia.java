package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.Guerrero;
import dominio.Humano;
import dominio.MyRandomStub;
import dominio.NonPlayableCharacter;
import dominio.NpcBandido;
import dominio.NpcBruto;
import dominio.Personaje;

public class TestEnemigosExperiencia {
    @Test
    public void testPjvsNPC() {
	Humano h = new Humano("Nicolas", new Guerrero(), 1);
	NonPlayableCharacter npc = new NpcBruto("Gigante", 1);
	Personaje.cargarTablaNivel();
	Assert.assertTrue(h.getExperiencia() == 0);

	h.ganarExperiencia(npc.otorgarExp());
	Assert.assertTrue(h.getExperiencia() == 29);
    }

    @Test
    public void testNpcMultExperiencia() {
	NonPlayableCharacter npc = new NpcBruto("Gigante", 1);
	NonPlayableCharacter npc2 = new NpcBandido("Smuggler", 2);

	Assert.assertTrue(npc.otorgarExp() < npc2.otorgarExp());
    }

    @Test
    public void testPjvsPj() {
	Humano h = new Humano("Nicolas", new Guerrero(), 1);
	Humano h2 = new Humano("Lautaro", new Guerrero(), 2);
	Personaje.cargarTablaNivel();
	h.setRandom(new MyRandomStub(0.49, 3));
	h2.setRandom(new MyRandomStub(0.49, 3));
	Assert.assertTrue(h.getExperiencia() == 0);
	Assert.assertTrue(h2.getExperiencia() == 0);

	while (h2.estaVivo())
	    h.atacar(h2);

	h.ganarExperiencia(h2.otorgarExp());
	Assert.assertTrue(h.getExperiencia() == 40);
	Assert.assertTrue(h2.getExperiencia() == 0);
    }
}
