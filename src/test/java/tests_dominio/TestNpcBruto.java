package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.NpcBruto;

public class TestNpcBruto
{
	@Test
	public void testInstanciación () 
	{
		NpcBruto bruto = new NpcBruto("Bohr", 1);
		
		Assert.assertEquals("Bohr", bruto.getNombre());
		Assert.assertEquals(50, bruto.getAtaque());
		Assert.assertEquals(20, bruto.getDefensa());
		Assert.assertEquals(215, bruto.getSalud());
		Assert.assertEquals(30, bruto.getEnergia());
	}
	
	@Test
	public void testMultiplicadoresNivel () 
	{
		NpcBruto bruto = new NpcBruto("Bohr", 10);
		
		Assert.assertEquals(230, bruto.getAtaque());
		Assert.assertEquals(92, bruto.getDefensa());
		Assert.assertEquals(1250, bruto.getSalud());
		Assert.assertEquals(75, bruto.getEnergia());
	}
	
	@Test
	public void testAtaqueNormal () 
	{
		NpcBruto bruto = new NpcBruto("Bohr", 10);
		NpcBruto objetivo = new NpcBruto("Gson", 10);
		
		Assert.assertTrue(bruto.ataqueNormal(objetivo));
	}
	
	@Test
	public void testPielDePiedraCosto () 
	{
		NpcBruto bruto = new NpcBruto("Bohr", 10);
		
		Assert.assertEquals(true, bruto.pielDePiedra());
		bruto.setEnergia(0);
		Assert.assertEquals(false, bruto.pielDePiedra());
	}
	
	@Test
	public void testPielDePiedraTurnos () 
	{
		NpcBruto bruto = new NpcBruto("Bohr", 10);
		NpcBruto objetivo = new NpcBruto("Gson", 10);
		
		bruto.pielDePiedra();
		Assert.assertEquals(3, bruto.getTurnosPielPiedra());
		bruto.jugarTurno(objetivo);
		bruto.jugarTurno(objetivo);
		bruto.jugarTurno(objetivo);
		Assert.assertEquals(0, bruto.getTurnosPielPiedra());
	}
	
	@Test
	public void testEnergizarse () 
	{
		NpcBruto bruto = new NpcBruto("Bohr", 10);
		
		bruto.setEnergia(0);
		bruto.energizarse();
		Assert.assertTrue(bruto.getEnergia() > 0);
	}
	
}