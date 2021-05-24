package com.example.Fragment.Rebano;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.Activity.Rebano.Activity_Rebano;
import com.example.ClasesVO.Otros.Rebaño;
import com.example.GridAdapter.Rebaño.GridAdapter_Rebano;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Rebano extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    public static ArrayList<Rebaño> rebanos;

    /**
     * Constructor vacío del fragmento
     */
    public Fragment_Rebano() {
        // Required empty public constructor
    }

    /**
     * Inicia una nueva instancia del fragmento
     * @return	La instancia del fragmento
     */
    public static Fragment_Rebano newInstance() {
        return new Fragment_Rebano();
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
        View view = inflater.inflate(R.layout.fragment_rebano, container, false);

        gridView = view.findViewById(R.id.grid_rebano);
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
        rebanos = MainActivity.obtenerRebanos();

        GridAdapter_Rebano adapter = new GridAdapter_Rebano(this.getContext(), rebanos);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(), Activity_Rebano.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, rebanos.get(position));
            startActivity(intent);
        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Rebano.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Rebaño());
            startActivity(intent);
        });
    }
}