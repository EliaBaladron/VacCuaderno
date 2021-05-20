package com.example.Fragment.Animales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Activity.Animal.Activity_Animal_Ternero;
import com.example.ClasesVO.Animales.Animal;
import com.example.ClasesVO.Animales.Ternero;
import com.example.GridAdapter.Animal.GridAdapter_Ternero;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Fragmento
 * 
 * @author Elia Baladrón Peral
 */
public class Fragment_Animales_Terneros extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Ternero> terneros;

    /**
     * Constructor vacío del fragmento
     */
    public Fragment_Animales_Terneros() {
        // Required empty public constructor
    }
    public Fragment_Animales_Terneros(MainActivity main) {
        this.main = main;
    }

    /**
     * Inicia una nueva instancia del fragmento
     * @return	La instancia del fragmento
     */
    public static Fragment_Animales_Terneros newInstance() {
        return new Fragment_Animales_Terneros();
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

        View view = inflater.inflate(R.layout.fragment_animales_terneros, container, false);

        gridView = (GridView)view.findViewById(R.id.grid_animales_terneros);
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
        terneros = main.bdAnimalesTerneros.getDatosObjetos();

        for(Ternero ternero: terneros){
            ArrayList<Animal> t = main.bdAnimales.getDatosObjeto(ternero.getCrotal());
            ternero.setAnimal((Animal) t.get(0));
        }

        GridAdapter_Ternero adapter = new GridAdapter_Ternero(this.getContext(), terneros);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(), Activity_Animal_Ternero.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, terneros.get(position));
            startActivity(intent);
        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Animal_Ternero.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Ternero());
            startActivity(intent);
        });
    }
}