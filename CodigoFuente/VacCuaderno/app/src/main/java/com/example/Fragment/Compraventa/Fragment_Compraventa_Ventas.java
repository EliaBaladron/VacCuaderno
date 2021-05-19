package com.example.Fragment.Compraventa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Activity.Compraventa.Activity_Compraventa_Ventas;
import com.example.ClasesVO.CompraVenta.Venta;
import com.example.GridAdapter.Compraventa.GridAdapter_Compraventa;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Fragment_Compraventa_Ventas extends Fragment {

    GridView gridView;
    FloatingActionButton fab_add;

    static MainActivity main;

    public static ArrayList<Venta> ventas;


    public Fragment_Compraventa_Ventas() {
        // Required empty public constructor
    }
    public Fragment_Compraventa_Ventas(MainActivity main) {
        Fragment_Compraventa_Ventas.main = main;
    }

    public static Fragment_Compraventa_Ventas newInstance() {
        return new Fragment_Compraventa_Ventas();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compraventa_ventas, container, false);

        gridView = view.findViewById(R.id.grid_ventas);
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
        ventas = main.bdCompraventaVentas.getDatosObjetos();

        GridAdapter_Compraventa adapter = new GridAdapter_Compraventa(this.getContext(), ventas);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            //Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), Activity_Compraventa_Ventas.class);
            intent.putExtra(MainActivity.EDITAR, true);
            intent.putExtra(MainActivity.DATOS, ventas.get(position));
            startActivity(intent);

        });
        fab_add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), Activity_Compraventa_Ventas.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, new Venta());
            startActivity(intent);
        });
    }

    public static void actualizar(Venta venta){
        main.bdCompraventaVentas.actualizarBD(venta);
    }
    public static void eliminar(Venta venta){
        main.bdCompraventaVentas.borrarDatos(venta);
    }
    public static void anadir(Venta venta){
        main.bdCompraventaVentas.insertarDatos(venta);
    }
}