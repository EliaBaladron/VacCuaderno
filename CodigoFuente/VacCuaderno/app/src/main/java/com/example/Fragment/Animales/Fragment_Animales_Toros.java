package com.example.Fragment.Animales;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Activity.Animal.Activity_Animal;
import com.example.Activity.Animal.Activity_Animal_Toro;
import com.example.ClasesVO.Animales.Animal;
import com.example.GridAdapter.Animal.GridAdapter_Toro;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Animales_Toros extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Animal> toros;

    public Fragment_Animales_Toros() {
        // Required empty public constructor
    }
    public Fragment_Animales_Toros(MainActivity main) {
        this.main = main;
    }

    public static Fragment_Animales_Toros newInstance() {
        return new Fragment_Animales_Toros();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_animales_toros, container, false);

        gridView = view.findViewById(R.id.grid_animales_toros);
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
        toros = new ArrayList<>();

        ArrayList<String> crotalesA = main.bdAnimales.getDatosCrotales();
        ArrayList<String> crotalesV = main.bdAnimalesVacas.getDatosCrotales();
        ArrayList<String> crotalesT = main.bdAnimalesTerneros.getDatosCrotales();

        for(String crotal: crotalesV)
            crotalesA.remove(crotal);
        for(String crotal: crotalesT)
            crotalesA.remove(crotal);

        Log.println(Log.INFO, "INFO", Integer.toString(crotalesA.size()));

        for(String crotal: crotalesA){
            ArrayList<Animal> t = main.bdAnimales.getDatosObjeto(crotal);
            toros.add((Animal) t.get(0));
        }


        GridAdapter_Toro adapter = new GridAdapter_Toro(this.getContext(), toros);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            //Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), Activity_Animal_Toro.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, toros.get(position));
            startActivity(intent);

        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Animal_Toro.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Animal());
            startActivity(intent);
        });
    }
}