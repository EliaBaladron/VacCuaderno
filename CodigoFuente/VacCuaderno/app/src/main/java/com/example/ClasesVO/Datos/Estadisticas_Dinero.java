package com.example.ClasesVO.Datos;

import java.util.ArrayList;

import com.example.ClasesVO.CompraVenta.Compra;
import com.example.ClasesVO.CompraVenta.Venta;

/**
 * @author Elia Baladrón Peral
 */
public class Estadisticas_Dinero {
	
    //TODO: Crear activity para mostrarlo
    //TODO: Crear vista
    //TODO: Añadir los datos a la vista
	
	ArrayList<Compra> compras;
	Double precioCompras;
	
	ArrayList<Venta> ventas;
	Double precioVentas;
	
	Double diferencia;
	
	public Estadisticas_Dinero() {
	    //TODO: Obtener compras
		compras = new ArrayList<Compra>();
	    //TODO: Obtener ventas
		ventas = new ArrayList<Venta>();
		
		calcularPrecioCompras();
		calcularPrecioVentas();
		
		diferencia = precioVentas - precioCompras;
	}
	void calcularPrecioCompras() {
		for(Compra v: compras) {
			precioCompras += v.getPrecio();
		}
	}
	void calcularPrecioVentas() {
		for(Venta v: ventas) {
			precioVentas += v.getPrecio();
		}
	}
}
