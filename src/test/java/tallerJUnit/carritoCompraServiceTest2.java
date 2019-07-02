package tallerJUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;

import com.everis.bootcamp.tallerjunit.Articulo;
import com.everis.bootcamp.tallerjunit.CarritoCompraService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Orden alfabetico 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //Orden determinado Order()
public class carritoCompraServiceTest2 {
	
	static CarritoCompraService carritoCompraService;
	
	@BeforeClass
	public static void antes() {
		carritoCompraService = new CarritoCompraService();
		System.out.println("Entrando en el metodo con @Before");	
		
	}
	
	@Test
    @Order(1)
	public void testAddArticulo() {
		Articulo a = new Articulo("Descripci�n de art�culo", 1.0);
		
		assertTrue("La lista NO est� vacia", carritoCompraService.getArticulos().isEmpty());
		
		carritoCompraService.addArticulo(a);
		
		assertTrue("El art�culo NO esta en la lista", carritoCompraService.getArticulos().contains(a));
		assertFalse("La lista NO est� llena", carritoCompraService.getArticulos().isEmpty());
	}
	
	@Test
    @Order(2)
	public void testLimpiaCesta() throws IOException {
		carritoCompraService.limpiarCesta();
		assertTrue("La lista NO est� vacia", carritoCompraService.getArticulos().isEmpty());
		
		carritoCompraService.setArticulos(carritoCompraService.getArticulos());
		assertEquals("La lista NO est� vacia", 0, carritoCompraService.getNumArticulo());

	}
	
	@Test
    @Order(3)
	public void testTotalPrice() {
		
		double precioTotalCalculado = 0.0;
		
		carritoCompraService.addArticulo(new Articulo("Descripci�n de art�culo 1", 1.0));
		carritoCompraService.addArticulo(new Articulo("Descripci�n de art�culo 2", 1.0));
		carritoCompraService.addArticulo(new Articulo("Descripci�n de art�culo 3", 1.0));
		carritoCompraService.addArticulo(new Articulo("Descripci�n de art�culo 4", 1.0));

		for (Articulo a: carritoCompraService.getArticulos()) {
			precioTotalCalculado = precioTotalCalculado + a.getPrecio();
		}
		
		assertEquals("La suma no coincide", precioTotalCalculado, carritoCompraService.totalPrice(), 0);
	}
	

}
