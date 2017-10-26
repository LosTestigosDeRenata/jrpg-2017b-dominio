package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.MyRandomStub;
import dominio.NonPlayableCharacter;
import dominio.NpcBandido;
import dominio.RandomGenerator;

public class TestNPC 
{

	@Test
	public void testDañarPeleable() 
	{
		NonPlayableCharacter npc = new NpcBandido("Arturo", 1);
		NonPlayableCharacter npc2 = new NpcBandido("Arsenio", 1);
		
		RandomGenerator ran = new MyRandomStub(0.5, 5);
		npc.setRandom(ran);
		npc2.setRandom(ran);
		npc.dañarSalud(npc2, 15);
		
		Assert.assertEquals(122, npc2.getSalud());
	}
	
	@Test
	public void testOtorgarExp() 
	{
		NonPlayableCharacter npc = new NpcBandido("Arturo", 1);
		Assert.assertTrue(29 == npc.otorgarExp());
	}
}
