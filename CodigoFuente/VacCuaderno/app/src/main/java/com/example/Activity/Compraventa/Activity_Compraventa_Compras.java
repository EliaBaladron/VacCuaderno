package com.example.Activity.Compraventa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.CompraVenta.Compra;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author Elia Baladrón Peral
 */
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

    /**
     * En la creación de la actividad obtiene los datos a mostrar,
     * inicia los componentes de la vista y muestra los datos pasado,
     * y hace los componentes editables o no editabbles según el caso
     * @param savedInstanceState	sis
     */
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
    /**
     * Elimina el objeto mostrado
     */
    void eliminar(){
        MainActivity.eliminar(this.compra);
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

        fab_editar = findViewById(R.id.compra_editar);
        fab_eliminar = findViewById(R.id.compra_eliminar);
        fab_volver = findViewById(R.id.compra_volver);
        fab_aceptar = findViewById(R.id.compra_aceptar);
        fab_cancelar = findViewById(R.id.compra_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> editar());

        fab_eliminar.setOnClickListener(view -> eliminar());

        fab_volver.setOnClickListener(view -> volver());

        fab_aceptar.setOnClickListener(view -> aceptar());

        fab_cancelar.setOnClickListener(view -> cancelar());
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
        MainActivity.actualizar(this.compra);
    }
    void anadir(){
        MainActivity.insertar(this.compra);
        Intent intent = new Intent(getApplicationContext(), Pregunta_Alta.class);
        intent.putExtra(MainActivity.CROTAL, compra.getCrotal());
        startActivity(intent);
    }
}