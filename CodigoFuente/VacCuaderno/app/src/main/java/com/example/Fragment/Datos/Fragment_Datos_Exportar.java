package com.example.Fragment.Datos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.Activity.Datos.Activity_Exportar;
import com.example.MainActivity;
import com.example.prueba03.R;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Datos_Exportar extends Fragment {

    Button button;
    MainActivity main;

    /**
     * Constructor vacío del fragmento
     */
    public Fragment_Datos_Exportar() {
        // Required empty public constructor
    }
    public Fragment_Datos_Exportar(MainActivity main) {
        this.main = main;
    }

    /**
     * Inicia una nueva instancia del fragmento
     * @return	La instancia del fragmento
     */
    public static Fragment_Datos_Exportar newInstance() {
        return new Fragment_Datos_Exportar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos_exportar, container, false);

        button = view.findViewById(R.id.bt_exportar);

        iniciarVista();

        return view;
    }
    void iniciarVista(){
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Exportar.class);
            startActivity(intent);
        });
    }
}