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
public class Fragment_Datos_Destete extends Fragment {

    MainActivity main;

    public Fragment_Datos_Destete() {
        // Required empty public constructor
    }
    public Fragment_Datos_Destete(MainActivity main) {
        this.main = main;
    }

    public static Fragment_Datos_Destete newInstance() {
        return new Fragment_Datos_Destete();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos_destete, container, false);
    }
}