package tests_dominio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dominio.RandomGenerator;

public class TestRandomGenerator {
	RandomGenerator ran;

	@Before
	public void before() {
		ran = new RandomGenerator();
	}

	@Test
	public void testNextInt() {
		Assert.assertEquals(0, ran.nextInt(3));
	}

	@Test
	public void testNextDouble() {
		Assert.assertEquals(0, ran.nextDouble(), 0);
	}

	@Test
	public void testRangoInt() {
		Assert.assertEquals(0, ran.rangoInt(0, 10));
	}

	@Test
	public void testRangoDouble() {
		Assert.assertEquals(0, ran.rangoDouble(3, 6), 0.00001);
	}

	@Test
	public void testDispersión() {
		Assert.assertEquals(0, ran.aplicarDispersion(8, 0.2), 0.00001);
	}

}
