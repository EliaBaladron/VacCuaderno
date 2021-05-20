package com.example.Fragment.Animales;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.Activity.Animal.Activity_Animal;
import com.example.ClasesVO.Animales.Animal;
import com.example.GridAdapter.Animal.GridAdapter_Animal;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.poi.util.SystemOutLogger;

import java.util.ArrayList;

/**
 * Fragmento
 * 
 * @author Elia Baladrón Peral
 */
public class Fragment_Animales extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Animal> animales;

    /**
     * Constructor vacío del fragmento
     */
    public Fragment_Animales(){}
    public Fragment_Animales(MainActivity main) {
        this.main = main;
    }

    /**
     * Inicia una nueva instancia del fragmento
     * @return	La instancia del fragmento
     */
    public static Fragment_Animales newInstance() {
        return new Fragment_Animales();
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

        View view = inflater.inflate(R.layout.fragment_animales, container, false);

        gridView = view.findViewById(R.id.grid_animales);
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
        animales = main.bdAnimales.getDatosObjetos();

        GridAdapter_Animal adapter = new GridAdapter_Animal(this.getContext(), animales);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {

            Intent intent = new Intent(getContext(), Activity_Animal.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, animales.get(position));
            startActivity(intent);
        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Animal.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Animal());
            startActivity(intent);
        });
    }
}