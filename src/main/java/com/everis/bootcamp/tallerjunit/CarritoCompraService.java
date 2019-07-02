package com.everis.bootcamp.tallerjunit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarritoCompraService {
	
	List<Articulo> articulos = new ArrayList<Articulo>();
	BaseDeDatosService bbddService = new BaseDeDatosService();

	public void limpiarCesta() throws IOException{
		
		if(articulos.isEmpty()) {
			throw new IOException("Carrito vacio");
		}
		
		articulos = new ArrayList<Articulo>();
	}
	
	public void addArticulo(Articulo a){
		articulos.add(a);
	}
	
	public int getNumArticulo(){
		return articulos.size();
	}
	
	public Double totalPrice(){
		double precioTotal = articulos.stream().  	
			     mapToDouble(Articulo::getPrecio).
			     sum();
		return precioTotal;
	}
	
	public static Double calculadorDescuento(double precio, double porcentajeDescuento){
		return precio - (precio * (porcentajeDescuento/100));
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	//////////////////////
	
	public Double aplicarDescuento(Integer idArticulo, Double porcentajeDescuento) {
		Double result = null;
		Articulo artBD = bbddService.findArticuloById(idArticulo);
		if(null != artBD) {
			result = calculadorDescuento(artBD.getPrecio(), porcentajeDescuento);
		}else {
			System.out.println("No se ha encontrado el item " + idArticulo + " en BD");
		}
		return result;
	}


	public BaseDeDatosService getBbddService() {
		return bbddService;
	}

	public void setBbddService(BaseDeDatosService bbddService) {
		this.bbddService = bbddService;
	}

	///////////////////////
	
	public Integer guardaArticulo(Articulo a) {
		
		Integer idRecuperado = bbddService.insertArticulo(a);
		addArticulo(bbddService.findArticuloById(idRecuperado));
		System.out.println(idRecuperado);
		
		return idRecuperado;
	}
	

}
