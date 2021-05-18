package com.example.Fragment.Animales;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Animales_Terneros() {
        // Required empty public constructor
    }
    public Fragment_Animales_Terneros(MainActivity main) {
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
    public static Fragment_Animales_Terneros newInstance(String param1, String param2) {
        Fragment_Animales_Terneros fragment = new Fragment_Animales_Terneros();
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
            ArrayList t = main.bdAnimales.getDatosObjeto(ternero.getCrotal());
            ternero.setAnimal((Animal) t.get(0));
        }

        GridAdapter_Ternero adapter = new GridAdapter_Ternero(this.getContext(), terneros);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), Activity_Animal_Ternero.class);
                intent.putExtra(MainActivity.EDITAR, true);
                intent.putExtra(MainActivity.DATOS, terneros.get(position));
                startActivity(intent);
            }
        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Animal_Ternero.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Ternero());
            startActivity(intent);
        });
    }

    public static void actualizar(Ternero ternero){
        main.bdAnimalesTerneros.actualizarBD(ternero);
    }
    public static void eliminar(Ternero ternero){
        main.bdAnimalesTerneros.borrarDatos(ternero);
    }
    public static void anadir(Ternero ternero){
        main.bdAnimalesTerneros.insertarDatos(ternero);
    }
}