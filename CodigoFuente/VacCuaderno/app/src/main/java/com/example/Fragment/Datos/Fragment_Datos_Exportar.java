package com.example.Fragment.Datos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.Activity.Animal.Activity_Animal;
import com.example.Activity.Datos.Activity_Exportar;
import com.example.ClasesVO.Animales.Animal;
import com.example.MainActivity;
import com.example.prueba03.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Datos_Exportar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Datos_Exportar extends Fragment {

    Button button;
    MainActivity main;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Datos_Exportar() {
        // Required empty public constructor
    }
    public Fragment_Datos_Exportar(MainActivity main) {
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
    public static Fragment_Datos_Exportar newInstance(String param1, String param2) {
        Fragment_Datos_Exportar fragment = new Fragment_Datos_Exportar();
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
        View view = inflater.inflate(R.layout.fragment_datos_exportar, container, false);

        button = view.findViewById(R.id.bt_exportar);

        return view;
    }
    void iniciarVista(){
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Exportar.class);
            startActivity(intent);
        });
    }
}