package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.MyRandomStub;
import dominio.NonPlayableCharacter;
import dominio.NpcBandido;
import dominio.NpcBrujo;
import dominio.NpcBruto;
import dominio.NpcVampiro;
import dominio.RandomGenerator;

public class TestNPC {

	@Test
	public void testDañarPeleable() {
		NonPlayableCharacter npc = new NpcBandido("Arturo", 1);
		NonPlayableCharacter npc2 = new NpcBandido("Arsenio", 1);

		RandomGenerator ran = new MyRandomStub(0.5, 5);
		npc.setRandom(ran);
		npc2.setRandom(ran);
		npc.daniarSalud(npc2, 15);

		Assert.assertEquals(122, npc2.getSalud());
	}

	@Test
	public void testOtorgarExp() {
		NonPlayableCharacter npc = new NpcBandido("Arturo", 1);
		Assert.assertEquals(npc.otorgarExp(), 29);
	}
	
	@Test
	public void testFuerza() {
		NonPlayableCharacter npc = new NpcBandido("Arturo", 1);

		// el nombre de este método confunde, ya que en realidad aumenta el ataque...
		npc.setAtaque(100);
		Assert.assertEquals(135, npc.getAtaque());
	}
	
	@Test
	public void testSalud() {
		NonPlayableCharacter npc = new NpcBrujo("Arturo", 1);
		
		npc.setSaludTope(1000);
		Assert.assertEquals(1000, npc.getSaludTope());
		npc.setSalud(100);
		npc.aumentarSalud(10000);
		Assert.assertEquals(1000, npc.getSalud());
		npc.reducirSalud(9999);
		Assert.assertEquals(0, npc.getSalud());
		Assert.assertEquals(false, npc.estaVivo());
	}
	
	@Test
	public void testEnergia() {
		NonPlayableCharacter npc = new NpcBruto("Arturo", 1);
		
		npc.setEnergiaTope(1000);
		Assert.assertEquals(1000, npc.getEnergiaTope());
		
		npc.setEnergia(100);
		npc.aumentarEnergia(10000);
		Assert.assertEquals(1000, npc.getEnergia());
		
		npc.recibirDanioEnergia(100);
		Assert.assertEquals(900, npc.getEnergia());
		npc.recibirDanioEnergia(0);
		Assert.assertEquals(900, npc.getEnergia());
		
		npc.reducirEnergia(9999);
		Assert.assertEquals(0, npc.getEnergia());
	}
	
	@Test
	public void testMagia() {
		NonPlayableCharacter npc = new NpcVampiro("Arturo", 1);
		
		/* No estoy seguro de que se supone que hace el atributo magia.
		 * Asumo que se supone que debería afectar el daño mágico?
		 * De un modo u otro nunca fue implementado.
		 */
		Assert.assertEquals(0, npc.getMagia());
	}

}
