package com.example.Activity.Compraventa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.CompraVenta.Compra;
import com.example.Fragment.Compraventa.Fragment_Compraventa_Compras;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Activity_Compraventa_Compras extends AppCompatActivity {

    Compra compra;

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
        setContentView(R.layout.activity_compraventa_compras);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        compra = (Compra) intent.getSerializableExtra(MainActivity.DATOS);
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
        Fragment_Compraventa_Compras.eliminar(this.compra);
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

        fab_editar = findViewById(R.id.compra_editar);
        fab_eliminar = findViewById(R.id.compra_eliminar);
        fab_volver = findViewById(R.id.compra_volver);
        fab_aceptar = findViewById(R.id.compra_aceptar);
        fab_cancelar = findViewById(R.id.compra_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> {
            Snackbar.make(view, "Editar "+compra.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            editar();
        });

        fab_eliminar.setOnClickListener(view -> {
            Snackbar.make(view, "Eliminar "+compra.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            eliminar();
        });

        fab_volver.setOnClickListener(view -> {
            Snackbar.make(view, "Volver "+compra.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            volver();
        });

        fab_aceptar.setOnClickListener(view -> {
            Snackbar.make(view, "Aceptar "+compra.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            aceptar();
        });

        fab_cancelar.setOnClickListener(view -> {
            Snackbar.make(view, "Cancelar "+compra.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            cancelar();
        });
    }
    void iniciarDatos(){
        viewCrotal.setText(compra.getCrotal());
        viewPrecio.setText(compra.getPrecioString());
        viewFecha.setText(compra.getFechaString());
    }
    void obtenerDatos(){
        compra.setCrotal(viewCrotal.getText().toString());
        compra.setPrecio(viewPrecio.getText().toString());
        compra.setFecha(viewFecha.getText().toString());

        Log.println(Log.INFO, "Datos obtenidos ", compra.toString());
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
        Fragment_Compraventa_Compras.actualizar(this.compra);
    }
    void anadir(){
        Fragment_Compraventa_Compras.anadir(this.compra);
    }
}