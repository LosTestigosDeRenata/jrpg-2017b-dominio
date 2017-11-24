package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.MyRandomStub;
import dominio.RandomGenerator;

public class TestRandomStub {
	@Test
	public void testNextInt() {
		RandomGenerator ran = new MyRandomStub(3, 5);
		Assert.assertEquals(5, ran.nextInt(3));
	}

	@Test
	public void testNextDouble() {
		RandomGenerator ran = new MyRandomStub(9, 2);
		Assert.assertEquals(9, ran.nextDouble(), 0);
	}

	@Test
	public void testRangoInt() {
		RandomGenerator ran = new MyRandomStub(0, 0);
		Assert.assertEquals(5, ran.rangoInt(0, 10));
	}

	@Test
	public void testRangoDouble() {
		RandomGenerator ran = new MyRandomStub(0, 0);
		Assert.assertEquals(4.5, ran.rangoDouble(3, 6), 0.00001);
	}

	@Test
	public void testDispersión() {
		RandomGenerator ran = new MyRandomStub(0, 0);
		Assert.assertEquals(8, ran.aplicarDispersion(8, 0.2), 0.00001);
	}
}
