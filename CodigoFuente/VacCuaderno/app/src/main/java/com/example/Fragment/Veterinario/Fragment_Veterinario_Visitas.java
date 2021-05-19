package com.example.Fragment.Veterinario;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Activity.Veterinario.Activity_Veterinario_Visitas;
import com.example.ClasesVO.Veterinario.Visitas;
import com.example.GridAdapter.Veterinario.GridAdapter_Visitas;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Veterinario_Visitas extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Visitas> visitas;

    public Fragment_Veterinario_Visitas() {
        // Required empty public constructor
    }
    public Fragment_Veterinario_Visitas(MainActivity main) {
        Fragment_Veterinario_Visitas.main = main;
    }

    public static Fragment_Veterinario_Visitas newInstance() {
        return new Fragment_Veterinario_Visitas();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_veterinario_visitas, container, false);

        gridView = view.findViewById(R.id.grid_veterinario_visitas);
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
        visitas = main.bdVeterinarioVisitas.getDatosobjetos();

        GridAdapter_Visitas adapter = new GridAdapter_Visitas(this.getContext(), visitas);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            //Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), Activity_Veterinario_Visitas.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, visitas.get(position));
            startActivity(intent);

        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Veterinario_Visitas.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Visitas());
            startActivity(intent);
        });
    }

    public static void actualizar(Visitas visitas){
        main.bdVeterinarioVisitas.actualizarBD(visitas);
    }
    public static void eliminar(Visitas visitas){
        main.bdVeterinarioVisitas.borrarDatos(visitas);
    }
    public static void anadir(Visitas visitas){
        main.bdVeterinarioVisitas.insertarDatos(visitas);
    }
}