package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.MyRandom;
import dominio.RandomGenerator;

public class TestMyRandom {
	@Test
	public void testRandomFijo() {
		RandomGenerator ran = new MyRandom();
		Assert.assertTrue(ran.nextDouble() <= 1 && ran.nextDouble() >= 0);
		Assert.assertTrue(ran.nextInt(100) <= 100 && ran.nextDouble() >= 0);
	}

	@Test
	public void testRandomRango() {
		RandomGenerator ran = new MyRandom();

		Assert.assertTrue(validarRangoInt(ran, 10, 100));
		Assert.assertTrue(validarRangoInt(ran, 0, 1));
		Assert.assertTrue(validarRangoInt(ran, 2, 2));

		Assert.assertTrue(validarRangoDouble(ran, 200, 500));
		Assert.assertTrue(validarRangoDouble(ran, 0.5, 1));
		Assert.assertTrue(validarRangoDouble(ran, 0, 0));
	}

	@Test
	public void testDispersión() {
		RandomGenerator ran = new MyRandom();

		double val = ran.aplicarDispersión(1, 0.2);
		Assert.assertTrue(val >= 0.8 && val <= 1.2);

		val = ran.aplicarDispersión(8, 0.5);
		Assert.assertTrue(val >= 4 && val <= 12);
	}

	private boolean validarRangoInt(RandomGenerator ran, int min, int max) {
		int val = ran.rangoInt(min, max);

		return val >= min && val <= max;
	}

	private boolean validarRangoDouble(RandomGenerator ran, double min, double max) {
		double val = ran.rangoDouble(min, max);

		return val >= min && val <= max;
	}

}