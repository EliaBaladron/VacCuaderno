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

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Compraventa_Ventas_Realizadas extends AppCompatActivity {

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

    /**
     * En la creación de la actividad obtiene los datos a mostrar,
     * inicia los componentes de la vista y muestra los datos pasado,
     * y hace los componentes editables o no editabbles según el caso
     * @param savedInstanceState	sis
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compraventa_ventas_realizadas);
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
    /**
     * Elimina el objeto mostrado
     */
    void eliminar(){
        Fragment_Compraventa_Ventas.eliminar(this.venta);
        volver();
    }
    /**
     * Vuelve a la vista anterior
     */
    void volver(){
        onBackPressed();
    }
    /**
     * Edita el objeto mostrado o lo añade a la BD
     */
    void aceptar(){
        obtenerDatos();
        hacerNoEditable();
        if(editar){
            actualizar();
        }else{
            anadir();
        }
    }
    /**
     * Cancela la edición o la introducción en la BD del objeto mostrado
     */
    void cancelar(){
        hacerNoEditable();
        if(editar){
            iniciarDatos();
        }else{
            volver();
        }
    }
    /**
     * Inicia la edición de los datos mostrados
     */
    void editar(){
        hacerEditable();
    }

    void iniciarComponentes(){
        viewCrotal = findViewById(R.id.layout_compraventa_crotal);
        viewPrecio = findViewById(R.id.layout_compraventa_precio);
        viewFecha = findViewById(R.id.layout_compraventa_fecha);

        fab_editar = findViewById(R.id.venta_real_editar);
        fab_eliminar = findViewById(R.id.venta_real_eliminar);
        fab_volver = findViewById(R.id.venta_real_volver);
        fab_aceptar = findViewById(R.id.venta_real_aceptar);
        fab_cancelar = findViewById(R.id.venta_real_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> editar());

        fab_eliminar.setOnClickListener(view -> eliminar());

        fab_volver.setOnClickListener(view -> volver());

        fab_aceptar.setOnClickListener(view -> aceptar());

        fab_cancelar.setOnClickListener(view -> cancelar());
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