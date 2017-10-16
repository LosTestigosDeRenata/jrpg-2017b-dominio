package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.NonPlayableCharacter;
import dominio.NpcBandido;

public class TestNPC {

	@Test
	public void testDañarPeleable() {
		NonPlayableCharacter npc = new NpcBandido("Gigante", 1);
		NonPlayableCharacter npc2 = new NpcBandido("Gigante", 1);
		System.out.println(npc2.getSalud());
		npc.dañarPeleable(npc2, 15);
		
		int saludNpc2 = npc2.getSalud();
		Assert.assertTrue(saludNpc2 == 122 || saludNpc2 == 125);
	}
	
	/*@Test
	public void testOtorgarExp() {
		NonPlayableCharacter npc = new NonPlayableCharacter("Gigante", 1, -1);
		Assert.assertTrue(30 == npc.otorgarExp());
	}*/
}
