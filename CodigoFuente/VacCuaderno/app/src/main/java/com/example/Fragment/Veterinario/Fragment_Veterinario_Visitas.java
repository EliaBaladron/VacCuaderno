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
import com.example.Activity.Veterinario.Activity_Veterinario_Visitas;
import com.example.ClasesVO.Animales.Animal;
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

    public static int seleccionado;
    public static ArrayList<Visitas> visitas;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Veterinario_Visitas() {
        // Required empty public constructor
    }
    public Fragment_Veterinario_Visitas(MainActivity main) {
        this.main = main;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment First.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Veterinario_Visitas newInstance(String param1, String param2) {
        Fragment_Veterinario_Visitas fragment = new Fragment_Veterinario_Visitas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
            Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

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