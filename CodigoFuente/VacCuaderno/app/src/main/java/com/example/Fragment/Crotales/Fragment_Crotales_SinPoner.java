package com.example.Fragment.Crotales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.Activity.Crotal.Activity_Crotal_SinPoner;
import com.example.ClasesVO.Otros.Crotal;
import com.example.GridAdapter.Crotal.GridAdapter_Crotal;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Crotales_SinPoner extends Fragment {


    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Crotal> crotales;

    /**
     * Constructor vacío del fragmento
     */
    public Fragment_Crotales_SinPoner() {
        // Required empty public constructor
    }
    public Fragment_Crotales_SinPoner(MainActivity main) {
        Fragment_Crotales_SinPoner.main = main;
    }

    /**
     * Inicia una nueva instancia del fragmento
     * @return	La instancia del fragmento
     */
    public static Fragment_Crotales_SinPoner newInstance() {
        return new Fragment_Crotales_SinPoner();
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

        View view = inflater.inflate(R.layout.fragment_crotales_sinponer, container, false);

        gridView = view.findViewById(R.id.grid_crotales_sinponer);
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
        crotales = main.bdCrotalesSinPoner.getDatosObjetos();

        GridAdapter_Crotal adapter = new GridAdapter_Crotal(this.getContext(), crotales);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(), Activity_Crotal_SinPoner.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, crotales.get(position));
            startActivity(intent);

        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Crotal_SinPoner.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Crotal());
            startActivity(intent);
        });
    }

    public static void actualizar(Crotal crotal){
        main.bdCrotalesSinPoner.actualizarBD(crotal);
    }
    public static void eliminar(Crotal crotal){
        main.bdCrotalesSinPoner.borrarDatos(crotal);
    }
    public static void anadir(Crotal crotal){
        main.bdCrotalesSinPoner.insertarDatos(crotal);
    }
}