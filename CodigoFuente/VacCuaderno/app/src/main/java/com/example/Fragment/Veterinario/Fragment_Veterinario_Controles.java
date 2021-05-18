package com.example.Fragment.Veterinario;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Activity.Animal.Activity_Animal;
import com.example.Activity.Veterinario.Activity_Veterinario_Controles;
import com.example.ClasesVO.Animales.Animal;
import com.example.ClasesVO.Veterinario.Controles;
import com.example.GridAdapter.Veterinario.GridAdapter_Controles;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Veterinario_Controles extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Controles> controles;

    public Fragment_Veterinario_Controles() {
        // Required empty public constructor
    }
    public Fragment_Veterinario_Controles(MainActivity main) {
        Fragment_Veterinario_Controles.main = main;
    }

    public static Fragment_Veterinario_Controles newInstance() {
        return new Fragment_Veterinario_Controles();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_veterinario_controles, container, false);

        gridView = view.findViewById(R.id.grid_veterinario_controles);
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
        controles = main.bdVeterinarioControles.getDatosobjetos();

        GridAdapter_Controles adapter = new GridAdapter_Controles(this.getContext(), controles);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), Activity_Veterinario_Controles.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, controles.get(position));
            startActivity(intent);

        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Veterinario_Controles.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Controles());
            startActivity(intent);
        });
    }

    public static void actualizar(Controles controles){
        main.bdVeterinarioControles.actualizarBD(controles);
    }
    public static void eliminar(Controles controles){
        main.bdVeterinarioControles.borrarDatos(controles);
    }
    public static void anadir(Controles controles){
        main.bdVeterinarioControles.insertarDatos(controles);
    }
}