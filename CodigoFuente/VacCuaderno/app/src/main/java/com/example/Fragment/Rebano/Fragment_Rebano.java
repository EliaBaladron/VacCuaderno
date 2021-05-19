package com.example.Fragment.Rebano;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Activity.Rebano.Activity_Rebano;
import com.example.ClasesVO.Otros.Rebaño;
import com.example.GridAdapter.Rebaño.GridAdapter_Rebano;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Rebano extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Rebaño> rebanos;

    public Fragment_Rebano() {
        // Required empty public constructor
    }
    public Fragment_Rebano(MainActivity main) {
        Fragment_Rebano.main = main;
    }

    public static Fragment_Rebano newInstance() {
        return new Fragment_Rebano();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rebano, container, false);

        gridView = view.findViewById(R.id.grid_rebano);
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
        rebanos = main.bdRebaños.getDatosObjetos();

        GridAdapter_Rebano adapter = new GridAdapter_Rebano(this.getContext(), rebanos);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            //Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), Activity_Rebano.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, rebanos.get(position));
            startActivity(intent);
        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Rebano.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Rebaño());
            startActivity(intent);
        });
    }

    public static void actualizar(Rebaño rebano){
        main.bdRebaños.actualizarBD(rebano);
    }
    public static void eliminar(Rebaño rebano){
        main.bdRebaños.borrarDatos(rebano);
    }
    public static void anadir(Rebaño rebano){
        main.bdRebaños.insertarDatos(rebano);
    }
}