package tallerJUnit;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.everis.bootcamp.tallerjunit.Articulo;
import com.everis.bootcamp.tallerjunit.BaseDeDatosService;
import com.everis.bootcamp.tallerjunit.CarritoCompraService;

public class carritoCompraServiceTestMockito {
	
	CarritoCompraService carritoCompraService;
	BaseDeDatosService baseDeDatosService; //base de datos simuladora

	
	@Before
	public void setUp(){
		System.out.println("Inicializamos el servicio");
		carritoCompraService = new CarritoCompraService();
		
		baseDeDatosService = Mockito.mock(BaseDeDatosService.class);
		carritoCompraService.setBbddService(baseDeDatosService);
		
		//Se puede añadir aqui... Mockito.when(... siempre que sean con IDs diferentes
		
	}
	
	@Test
	public void testFindByIdTest() {
		Mockito.when(baseDeDatosService.findArticuloById(1)).thenReturn(new Articulo("Articulo de prueba", 10.0));
		Double resultado = carritoCompraService.aplicarDescuento(1, 50.0);
		
		Assert.assertEquals(5.0, resultado, 0);
		System.out.println("Precio resultado: " + resultado);
	}
	
	@Test
	public void testFindNullByIdTest() {
		Mockito.when(baseDeDatosService.findArticuloById(2)).thenReturn(null);
		Double resultado= carritoCompraService.aplicarDescuento(2, 50.0);
		
		Assert.assertNull(resultado);
		System.out.println("Precio resultado: " + resultado);
	}
	
	@Test(expected = Exception.class)
	public void testFindExceptionByIdTest() {
		Mockito.when(baseDeDatosService.findArticuloById(0)).thenThrow(new Exception());
		Double resultado= carritoCompraService.aplicarDescuento(0, 50.0);
		
		System.out.println("Precio resultado: " + resultado);
	}
	
	@Test
	public void testFindByIdTest2() {
		Mockito.when(baseDeDatosService.findArticuloById(3)).thenReturn(new Articulo("Articulo de prueba", 8.8));
		Double resultado = carritoCompraService.aplicarDescuento(3, 0.0);
		
		Mockito.verify(baseDeDatosService, Mockito.times(1)).findArticuloById(3);
		
		Assert.assertEquals(8.8, resultado, 0);
		System.out.println("Precio resultado: " + resultado);
		
	}

}
