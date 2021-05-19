package com.example.Fragment.Crotales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Activity.Crotal.Activity_Crotal_Recibidos;
import com.example.ClasesVO.Otros.Crotal;
import com.example.GridAdapter.Crotal.GridAdapter_Crotal;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Crotales_Recibidos extends Fragment {


    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Crotal> crotales;

    public Fragment_Crotales_Recibidos() {
        // Required empty public constructor
    }
    public Fragment_Crotales_Recibidos(MainActivity main) {
        Fragment_Crotales_Recibidos.main = main;
    }

    public static Fragment_Crotales_Recibidos newInstance() {
        return new Fragment_Crotales_Recibidos();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        //GridAdapter_Crotal_Recibidos adapter = new GridAdapter_Crotal_Recibidos(this.getContext(), crotales);
        GridAdapter_Crotal adapter = new GridAdapter_Crotal(this.getContext(), crotales);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            //Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

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