package com.example.Fragment.Datos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ClasesVO.Datos.Estadisticas_Destete;
import com.example.GridAdapter.Animal.GridAdapter_Animal;
import com.example.GridAdapter.Datos.GridAdapter_Datos_Destete;
import com.example.MainActivity;
import com.example.prueba03.R;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Datos_Destete extends Fragment {

    TextView viewDias;
    GridView gridView;

    Estadisticas_Destete estadisticasDestete;

    public Fragment_Datos_Destete() {
        // Required empty public constructor
    }
    public Fragment_Datos_Destete(MainActivity main) {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_datos_destete, container, false);


        viewDias = view.findViewById(R.id.destete_dias);
        gridView = view.findViewById(R.id.grid_datos_destete);

        iniciarVista();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        iniciarVista();
    }

    void iniciarVista(){
        estadisticasDestete = new Estadisticas_Destete();

        viewDias.setText(estadisticasDestete.getMediaString());

        GridAdapter_Datos_Destete adapter = new GridAdapter_Datos_Destete(this.getContext(), estadisticasDestete.getDatos());
        gridView.setAdapter(adapter);
    }
}