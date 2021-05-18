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
import com.example.Activity.Animal.Activity_Animal_Vaca;
import com.example.ClasesVO.Animales.Vaca;
import com.example.ClasesVO.Animales.Animal;
import com.example.GridAdapter.Animal.GridAdapter_Vaca;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Animales_Vacas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Animales_Vacas extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static int seleccionado;
    public static ArrayList<Vaca> vacas;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Animales_Vacas() {
        // Required empty public constructor
    }
    public Fragment_Animales_Vacas(MainActivity main) {
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
    public static Fragment_Animales_Vacas newInstance(String param1, String param2) {
        Fragment_Animales_Vacas fragment = new Fragment_Animales_Vacas();
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

        //iniciarAnimales();
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
            ArrayList t = main.bdAnimales.getDatosObjeto(vaca.getCrotal());
            if(t.size() > 0)
                vaca.setAnimal((Animal) t.get(0));
        }

        GridAdapter_Vaca adapter = new GridAdapter_Vaca(this.getContext(), vacas);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            seleccionado = position;
            Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

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

    public static void actualizar(Vaca vaca){
        main.bdAnimalesVacas.actualizarBD(vaca);
    }
    public static void eliminar(Vaca vaca){
        main.bdAnimalesVacas.borrarDatos(vaca);
    }
    public static void anadir(Vaca vaca){
        main.bdAnimalesVacas.insertarDatos(vaca);
    }
}