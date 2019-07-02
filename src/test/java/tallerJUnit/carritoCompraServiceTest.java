package tallerJUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.everis.bootcamp.tallerjunit.Articulo;
import com.everis.bootcamp.tallerjunit.CarritoCompraService;

public class carritoCompraServiceTest {
	
	CarritoCompraService carritoCompraService;
	
	@Before
	public void antes() {
		carritoCompraService = new CarritoCompraService();
		System.out.println("Entrando en el metodo con @Before");	
		
	}
	
	@Test
	public void pruebaInical() {
		System.out.println("Entrando en el metodo PRIMER @Test");
	}
	
	@Test
	public void testAddArticulo() {
		Articulo a = new Articulo("Descripción de artículo", 1.0);
		
		assertTrue("La lista NO está vacia", carritoCompraService.getArticulos().isEmpty());
		
		carritoCompraService.addArticulo(a);
		
		assertTrue("El artículo NO esta en la lista", carritoCompraService.getArticulos().contains(a));
		assertFalse("La lista NO está llena", carritoCompraService.getArticulos().isEmpty());
	}
	
	@Test(expected = IOException.class)
	public void testLimpiaCesta() throws IOException {
		carritoCompraService.limpiarCesta();
		assertTrue("La lista NO está vacia", carritoCompraService.getArticulos().isEmpty());
		
		carritoCompraService.setArticulos(carritoCompraService.getArticulos());
		assertEquals("La lista NO está vacia", 0, carritoCompraService.getNumArticulo());

	}
	
	@Test
	public void testTotalPrice() {
		
		double precioTotalCalculado = 0.0;
		
		carritoCompraService.addArticulo(new Articulo("Descripción de artículo 1", 1.0));
		carritoCompraService.addArticulo(new Articulo("Descripción de artículo 2", 1.0));
		carritoCompraService.addArticulo(new Articulo("Descripción de artículo 3", 1.0));
		carritoCompraService.addArticulo(new Articulo("Descripción de artículo 4", 1.0));

		
		for (Articulo a: carritoCompraService.getArticulos()) {
			precioTotalCalculado = precioTotalCalculado + a.getPrecio();
		}
		
		/*for (int i=0; i < carritoCompraService.getArticulos().size(); i++) {
			precioTotalCalculado = precioTotalCalculado + carritoCompraService.getArticulos().get(i).getPrecio();
		}*/
		
		assertEquals("La suma no coincide", precioTotalCalculado, carritoCompraService.totalPrice(), 0);
	}
	
	@Test
	public void testCalculadorDescuento() {
		
		double porcentajeDescuento = 5;
		double precio = 5.0;
		
		double precioCalculado =  precio - (precio * (porcentajeDescuento/100));
	
		assertEquals("Los descuentos no coinciden", precioCalculado, CarritoCompraService.calculadorDescuento(precio, porcentajeDescuento), 0);
	
	}
	
	@Test(expected = IOException.class)
	public void testLimpiaCesta2() throws IOException  {
		carritoCompraService.limpiarCesta();
		assertFalse("La lista NO está vacia", carritoCompraService.getArticulos().isEmpty());
		
	}

}
