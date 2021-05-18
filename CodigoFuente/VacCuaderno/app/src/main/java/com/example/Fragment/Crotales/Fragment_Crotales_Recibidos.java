package com.example.Fragment.Crotales;

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
import com.example.Activity.Crotal.Activity_Crotal_Recibidos;
import com.example.ClasesVO.Animales.Animal;
import com.example.ClasesVO.Otros.Crotal;
import com.example.GridAdapter.Crotal.GridAdapter_Crotal_Recibidos;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Crotales_Recibidos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Crotales_Recibidos extends Fragment {


    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static int seleccionado;
    public static ArrayList<Crotal> crotales;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Crotales_Recibidos() {
        // Required empty public constructor
    }
    public Fragment_Crotales_Recibidos(MainActivity main) {
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
    public static Fragment_Crotales_Recibidos newInstance(String param1, String param2) {
        Fragment_Crotales_Recibidos fragment = new Fragment_Crotales_Recibidos();
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

        View view = inflater.inflate(R.layout.fragment_crotales_faltan, container, false);

        gridView = view.findViewById(R.id.grid_crotales_faltan);
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
        crotales = main.bdCrotalesRecibidos.getDatosObjetos();

        GridAdapter_Crotal_Recibidos adapter = new GridAdapter_Crotal_Recibidos(this.getContext(), crotales);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), Activity_Crotal_Recibidos.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, crotales.get(position));
            startActivity(intent);

        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Crotal_Recibidos.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Crotal());
            startActivity(intent);
        });
    }

    public static void actualizar(Crotal crotal){
        main.bdCrotalesRecibidos.actualizarBD(crotal);
    }
    public static void eliminar(Crotal crotal){
        main.bdCrotalesRecibidos.borrarDatos(crotal);
    }
    public static void anadir(Crotal crotal){
        main.bdCrotalesRecibidos.insertarDatos(crotal);
    }
}