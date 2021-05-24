package com.example.Fragment.Animales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.Activity.Animal.Activity_Animal_Vaca;
import com.example.ClasesVO.Animales.Vaca;
import com.example.ClasesVO.Animales.Animal;
import com.example.GridAdapter.Animal.GridAdapter_Vaca;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Fragmento
 * 
 * @author Elia Baladrón Peral
 */
public class Fragment_Animales_Vacas extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    public static ArrayList<Vaca> vacas;

    /**
     * Constructor vacío del fragmento
     */
    public Fragment_Animales_Vacas() {
        // Required empty public constructor
    }

    /**
     * Inicia una nueva instancia del fragmento
     * @return	La instancia del fragmento
     */
    public static Fragment_Animales_Vacas newInstance() {
        return new Fragment_Animales_Vacas();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_animales_vacas, container, false);

        gridView = (GridView)view.findViewById(R.id.grid_animales_vacas);
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
        vacas = MainActivity.obtenerVacas();

        GridAdapter_Vaca adapter = new GridAdapter_Vaca(this.getContext(), vacas);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            //Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), Activity_Animal_Vaca.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, vacas.get(position));
            startActivity(intent);

        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Animal_Vaca.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Vaca());
            startActivity(intent);
        });
    }
}