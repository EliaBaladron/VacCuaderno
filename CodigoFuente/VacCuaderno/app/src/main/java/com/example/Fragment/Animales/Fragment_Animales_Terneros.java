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
 * @author Elia Baladrón Peral
 */
public class Fragment_Animales_Terneros extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Ternero> terneros;

    public Fragment_Animales_Terneros() {
        // Required empty public constructor
    }
    public Fragment_Animales_Terneros(MainActivity main) {
        this.main = main;
    }

    public static Fragment_Animales_Terneros newInstance() {
        return new Fragment_Animales_Terneros();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_animales_terneros, container, false);

        gridView = (GridView)view.findViewById(R.id.grid_animales_terneros);
        fab_add = view.findViewById(R.id.fab_add);
        iniciarVista();

        return view;
    }

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
            Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

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

    /*public static void actualizar(Ternero ternero){
        main.bdAnimalesTerneros.actualizarBD(ternero);
    }
    public static void eliminar(Ternero ternero){
        main.bdAnimalesTerneros.borrarDatos(ternero);
    }*/

    /**
     * Si existe un ternero con el crotal pasado se elimina de la BD
     * @param crotal    Crotal a comprobar y borrar si existe
     */
    /*public static void eliminar(String crotal){
        ArrayList<Ternero> terneros = main.bdAnimalesTerneros.getDatosObjeto(crotal);
        if(terneros.size() > 0)
            main.bdAnimalesTerneros.borrarDatos(terneros.get(0));
    }
    public static void anadir(Ternero ternero){
        main.bdAnimalesTerneros.insertarDatos(ternero);
    }*/
}