package com.example.Fragment.Compraventa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Activity.Compraventa.Activity_Compraventa_Compras;
import com.example.ClasesVO.CompraVenta.Compra;
import com.example.GridAdapter.Compraventa.GridAdapter_Compraventa;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Compraventa_Compras extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Compra> compras;

    public Fragment_Compraventa_Compras() {
        // Required empty public constructor
    }

    public Fragment_Compraventa_Compras(MainActivity main) {
        Fragment_Compraventa_Compras.main = main;
    }

    public static Fragment_Compraventa_Compras newInstance() {
        return new Fragment_Compraventa_Compras();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compraventa_compras, container, false);

        gridView = view.findViewById(R.id.grid_compras);
        fab_add = view.findViewById(R.id.fab_add);
        iniciarVista();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        iniciarVista();
    }

    void iniciarVista() {
        compras = main.bdCompraventaCompras.getDatosObjetos();

        GridAdapter_Compraventa adapter = new GridAdapter_Compraventa(this.getContext(), compras);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            //Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), Activity_Compraventa_Compras.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, compras.get(position));
            startActivity(intent);

        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Compraventa_Compras.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Compra());
            startActivity(intent);
        });
    }

    public static void actualizar(Compra compra){
        main.bdCompraventaCompras.actualizarBD(compra);
    }
    public static void eliminar(Compra compra){
        main.bdCompraventaCompras.borrarDatos(compra);
    }
    public static void anadir(Compra compra){
        main.bdCompraventaCompras.insertarDatos(compra);
    }
}