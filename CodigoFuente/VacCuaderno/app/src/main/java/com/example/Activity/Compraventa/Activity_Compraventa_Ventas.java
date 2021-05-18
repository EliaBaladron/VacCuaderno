package com.example.Activity.Compraventa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.CompraVenta.Venta;
import com.example.Fragment.Compraventa.Fragment_Compraventa_Ventas;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Compraventa_Ventas extends AppCompatActivity {

    Venta venta;

    boolean editar;

    EditText viewCrotal;
    EditText viewPrecio;
    EditText viewFecha;

    FloatingActionButton fab_editar;
    FloatingActionButton fab_eliminar;
    FloatingActionButton fab_volver;
    FloatingActionButton fab_aceptar;
    FloatingActionButton fab_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compraventa_ventas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        venta = (Venta) intent.getSerializableExtra(MainActivity.DATOS);
        editar = intent.getBooleanExtra(MainActivity.EDITAR, true);

        iniciarComponentes();
        iniciarListenerBotones();
        iniciarDatos();

        if(editar){
            hacerNoEditable();
        }else{
            hacerEditable();
        }
    }

    //Metodos de los botones
    void eliminar(){
        Fragment_Compraventa_Ventas.eliminar(this.venta);
        volver();
    }
    void volver(){
        onBackPressed();
    }
    void aceptar(){
        obtenerDatos();
        hacerNoEditable();
        if(editar){
            actualizar();
        }else{
            anadir();
        }
    }
    void cancelar(){
        hacerNoEditable();
        if(editar){
            iniciarDatos();
        }else{
            volver();
        }
    }
    void editar(){
        hacerEditable();
    }

    void iniciarComponentes(){
        viewCrotal = findViewById(R.id.layout_compraventa_crotal);
        viewPrecio = findViewById(R.id.layout_compraventa_precio);
        viewFecha = findViewById(R.id.layout_compraventa_fecha);

        fab_editar = findViewById(R.id.venta_editar);
        fab_eliminar = findViewById(R.id.venta_eliminar);
        fab_volver = findViewById(R.id.venta_volver);
        fab_aceptar = findViewById(R.id.venta_aceptar);
        fab_cancelar = findViewById(R.id.venta_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> {
            Snackbar.make(view, "Editar "+venta.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            editar();
        });

        fab_eliminar.setOnClickListener(view -> {
            Snackbar.make(view, "Eliminar "+venta.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            eliminar();
        });

        fab_volver.setOnClickListener(view -> {
            Snackbar.make(view, "Volver "+venta.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            volver();
        });

        fab_aceptar.setOnClickListener(view -> {
            Snackbar.make(view, "Aceptar "+venta.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            aceptar();
        });

        fab_cancelar.setOnClickListener(view -> {
            Snackbar.make(view, "Cancelar "+venta.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            cancelar();
        });
    }
    void iniciarDatos(){
        viewCrotal.setText(venta.getCrotal());
        viewPrecio.setText(venta.getPrecioString());
        viewFecha.setText(venta.getFechaString());
    }
    void obtenerDatos(){
        venta.setCrotal(viewCrotal.getText().toString());
        venta.setPrecio(viewPrecio.getText().toString());
        venta.setFecha(viewFecha.getText().toString());

        Log.println(Log.INFO, "Datos obtenidos ", venta.toString());
    }
    void hacerEditable(){
        viewCrotal.setEnabled(true);
        viewPrecio.setEnabled(true);
        viewFecha.setEnabled(true);

        fab_editar.setVisibility(View.INVISIBLE);
        fab_eliminar.setVisibility(View.INVISIBLE);
        fab_volver.setVisibility(View.INVISIBLE);

        fab_aceptar.setVisibility(View.VISIBLE);
        fab_cancelar.setVisibility(View.VISIBLE);
    }
    void hacerNoEditable(){
        viewCrotal.setEnabled(false);
        viewPrecio.setEnabled(false);
        viewFecha.setEnabled(false);

        fab_editar.setVisibility(View.VISIBLE);
        fab_eliminar.setVisibility(View.VISIBLE);
        fab_volver.setVisibility(View.VISIBLE);

        fab_aceptar.setVisibility(View.INVISIBLE);
        fab_cancelar.setVisibility(View.INVISIBLE);
    }

    void actualizar(){
        Fragment_Compraventa_Ventas.actualizar(this.venta);
    }
    void anadir(){
        Fragment_Compraventa_Ventas.anadir(this.venta);
    }
}