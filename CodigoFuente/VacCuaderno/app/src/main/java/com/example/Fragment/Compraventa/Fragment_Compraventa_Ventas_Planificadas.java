package com.example.Fragment.Compraventa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.Activity.Compraventa.Activity_Compraventa_Ventas_Planificadas;
import com.example.ClasesVO.CompraVenta.Venta;
import com.example.GridAdapter.Compraventa.GridAdapter_Compraventa;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Compraventa_Ventas_Planificadas extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    MainActivity main;

    public static ArrayList<Venta> ventas;

    /**
     * Constructor vacío del fragmento
     */
    public Fragment_Compraventa_Ventas_Planificadas() {
        // Required empty public constructor
    }
    public Fragment_Compraventa_Ventas_Planificadas(MainActivity main) {
        this.main = main;
    }

    /**
     * Inicia una nueva instancia del fragmento
     * @return	La instancia del fragmento
     */
    public static Fragment_Compraventa_Ventas_Planificadas newInstance() {
        return new Fragment_Compraventa_Ventas_Planificadas();
    }

    /**
     * Método invocado en la creación del fragmento
     * @param savedInstanceState	sis
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método invocado en la creación de la vista
     * @param inflater	inflater
     * @param container	Contenedor de la vista
     * @param savedInstanceState	sis
     * @return	La vista creada
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compraventa_ventas_planificadas, container, false);

        gridView = view.findViewById(R.id.grid_ventas_planificadas);
        fab_add = view.findViewById(R.id.fab_add);
        iniciarVista();

        return view;
    }

    /**
     * Inicia la vista al iniciar el fragmento
     */
    @Override
    public void onStart() {
        super.onStart();
        iniciarVista();
    }

    void iniciarVista(){
        ventas = main.bdCompraventaVentas.getDatosObjetos();

        GridAdapter_Compraventa adapter = new GridAdapter_Compraventa(this.getContext(), ventas);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(), Activity_Compraventa_Ventas_Planificadas.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, ventas.get(position));
            startActivity(intent);

        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Compraventa_Ventas_Planificadas.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Venta());
            startActivity(intent);
        });
    }
}