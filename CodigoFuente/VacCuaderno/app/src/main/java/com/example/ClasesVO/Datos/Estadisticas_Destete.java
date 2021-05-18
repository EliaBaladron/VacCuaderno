package com.example.ClasesVO.Datos;

import java.sql.Date;
import java.util.ArrayList;

import com.example.ClasesVO.Animales.Ternero;

/**
 * @author Elia Baladrón Peral
 */
public class Estadisticas_Destete {
	
    //TODO: Mostrar los datos
    //TODO: Mostrar los objetos con un grid Adapter
	
	ArrayList<Destete> datos;
	int media = 0;
	
	public Estadisticas_Destete() {
		datos = new ArrayList<>();
		calcular();
	}
	
	void calcular() {
	    //TODO: Obtener terneros
		ArrayList<Ternero> terneros = new ArrayList<Ternero>();
		for(Ternero t: terneros) {
			if(t.getFechaDestete() != null)
				comprobarDiferencia(t);
		}
		calcularMedia();
	}
	void comprobarDiferencia(Ternero ternero) {
		Date nac = ternero.getAnimal().getFechaNac();
		Date dest = ternero.getFechaDestete();
		
		int dif = (int)(nac.getTime() - dest.getTime())/86400000;
		
		datos.add(new Destete(ternero.getCrotal(), dif, nac, dest));
		System.out.println(dif);
	}
	void calcularMedia() {
		int dias = 0;
		for(Destete d: datos) {
			dias += d.getDiasDiferencia();
		}
		media = dias/datos.size();
	}
}
