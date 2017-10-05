package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.NonPlayableCharacter;

public class TestNPC {

	@Test
	public void testOtorgarExp() {
		NonPlayableCharacter npc = new NonPlayableCharacter(1,"Gigante", 1, -1);
		Assert.assertTrue(30 == npc.otorgarExp());
	}
}
