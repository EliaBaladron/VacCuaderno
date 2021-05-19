package com.example.Fragment.Animales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

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
 * @author Elia Baladrón Peral
 */
public class Fragment_Animales_Vacas extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Vaca> vacas;

    public Fragment_Animales_Vacas() {
        // Required empty public constructor
    }
    public Fragment_Animales_Vacas(MainActivity main) {
        this.main = main;
    }

    public static Fragment_Animales_Vacas newInstance() {
        return new Fragment_Animales_Vacas();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_animales_vacas, container, false);

        gridView = (GridView)view.findViewById(R.id.grid_animales_vacas);
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
        vacas = main.bdAnimalesVacas.getDatosObjetos();

        for(Vaca vaca: vacas){
            ArrayList<Animal> t = main.bdAnimales.getDatosObjeto(vaca.getCrotal());
            if(t.size() > 0)
                vaca.setAnimal((Animal) t.get(0));
        }

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

    /*public static void actualizar(Vaca vaca){
        main.bdAnimalesVacas.actualizarBD(vaca);
    }
    public static void eliminar(Vaca vaca){
        main.bdAnimalesVacas.borrarDatos(vaca);
    }*/

    /**
     * Si existe una vaca con el crotal pasado se elimina de la BD
     * @param crotal    Crotal a comprobar y borrar si existe
     */
    /*public static void eliminar(String crotal){
        ArrayList<Vaca> vacas = main.bdAnimalesVacas.getDatosObjeto(crotal);

        if(vacas.size() > 0)
            main.bdAnimalesVacas.borrarDatos(vacas.get(0));
    }
    public static void anadir(Vaca vaca){
        main.bdAnimalesVacas.insertarDatos(vaca);
    }*/
}