package tallerJUnit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.everis.bootcamp.tallerjunit.Articulo;
import com.everis.bootcamp.tallerjunit.BaseDeDatosService;
import com.everis.bootcamp.tallerjunit.CarritoCompraService;

public class carritoCompraServiceTestMockito2 {
	
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
	public void testFindByIdTest2() {
		
		Articulo a = new Articulo("Zapatos", 20.0);
		a.setId((long) 99);
		Integer idDevuelto = 0; 
		Articulo aNuevo = null; 
		
		idDevuelto = carritoCompraService.guardaArticulo(a);
				
		System.out.println(idDevuelto);
		Mockito.when(baseDeDatosService.findArticuloById(idDevuelto)).thenReturn(new Articulo("Articulo de prueba", 10.0));
				
		//Comprobar que articulo se ha insertado en BBDD
		//assertTrue("El artículo NO esta en la BBDD", baseDeDatosService.storage.containsKey(idDevuelto));

		//Comprobando que articulo esta en carrito de la compra
		assertTrue("El artículo NO esta en el carrito", carritoCompraService.getArticulos().contains(aNuevo));
		System.out.println(aNuevo.getDescripcion());



		/*
		Mockito.when(baseDeDatosService.findArticuloById(3)).thenReturn(new Articulo("Articulo de prueba", 8.8));
		Double resultado = carritoCompraService.aplicarDescuento(3, 0.0);
		
		Mockito.verify(baseDeDatosService, Mockito.times(1)).findArticuloById(3);
		
		Assert.assertEquals(8.8, resultado, 0);
		System.out.println("Precio resultado: " + resultado);*/
		
	}

}
