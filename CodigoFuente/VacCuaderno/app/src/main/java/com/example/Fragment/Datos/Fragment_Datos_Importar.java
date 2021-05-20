package com.example.Fragment.Datos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.MainActivity;
import com.example.prueba03.R;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Datos_Importar extends Fragment {

    MainActivity main;

    /**
     * Constructor vacío del fragmento
     */
    public Fragment_Datos_Importar() {
        // Required empty public constructor
    }
    public Fragment_Datos_Importar(MainActivity main) {
        this.main = main;
    }

    /**
     * Inicia una nueva instancia del fragmento
     * @return	La instancia del fragmento
     */
    public static Fragment_Datos_Importar newInstance() {
        return new Fragment_Datos_Importar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_datos_importar, container, false);
    }
}