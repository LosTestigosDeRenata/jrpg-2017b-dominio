package tests_dominio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

import dominio.Item;

public class TestItem {

    @Test
    public void testInstanciacia() throws IOException {
	Item item = new Item(20, "NombrePrueba", 0, 15, 20, 25, 30, 35, "fotoPrueba.jpg", "fotoPrueba.jpg");

	BufferedImage foto = ImageIO.read(new File("recursos//fotoPrueba.jpg"));
	Assert.assertEquals(20, item.getIdItem());
	Assert.assertEquals("NombrePrueba", item.getNombre());
	Assert.assertEquals(15, item.getBonusSalud());
	Assert.assertEquals(20, item.getBonusEnergia());
	Assert.assertEquals(25, item.getBonusFuerza());
	Assert.assertEquals(30, item.getBonusDestreza());
	Assert.assertEquals(35, item.getBonusInteligencia());
	Assert.assertEquals(foto.getHeight(), item.getFoto().getHeight());
	Assert.assertEquals(foto.getWidth(), item.getFoto().getWidth());
    }

}
