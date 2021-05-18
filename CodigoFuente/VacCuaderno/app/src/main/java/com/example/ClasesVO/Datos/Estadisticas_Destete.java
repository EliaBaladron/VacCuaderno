package com.example.ClasesVO.Datos;

import java.sql.Date;
import java.util.ArrayList;

import com.example.ClasesVO.Animales.Ternero;
import com.example.MainActivity;

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
		ArrayList<Ternero> terneros = MainActivity.obtenerTerneros();
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

	public ArrayList<Destete> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<Destete> datos) {
		this.datos = datos;
	}

	public int getMedia() {
		return media;
	}
	public String getMediaString() {
		return Integer.toString(media);
	}

	public void setMedia(int media) {
		this.media = media;
	}
}
