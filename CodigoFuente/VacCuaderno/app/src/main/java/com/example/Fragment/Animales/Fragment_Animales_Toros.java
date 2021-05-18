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
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Animales_Toros#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Animales_Toros extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static int seleccionado;
    public static ArrayList<Animal> toros;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Animales_Toros() {
        // Required empty public constructor
    }
    public Fragment_Animales_Toros(MainActivity main) {
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
    public static Fragment_Animales_Toros newInstance(String param1, String param2) {
        Fragment_Animales_Toros fragment = new Fragment_Animales_Toros();
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
        toros = new ArrayList<Animal>();

        ArrayList<String> crotalesA = main.bdAnimales.getDatosCrotales();
        ArrayList<String> crotalesV = main.bdAnimalesVacas.getDatosCrotales();
        ArrayList<String> crotalesT = main.bdAnimalesTerneros.getDatosCrotales();

        for(String crotal: crotalesV)
            crotalesA.remove(crotal);
        for(String crotal: crotalesT)
            crotalesA.remove(crotal);

        Log.println(Log.INFO, "INFO", Integer.toString(crotalesA.size()));

        for(String crotal: crotalesA){
            ArrayList t = main.bdAnimales.getDatosObjeto(crotal);
            toros.add((Animal) t.get(0));
        }


        GridAdapter_Toro adapter = new GridAdapter_Toro(this.getContext(), toros);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

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