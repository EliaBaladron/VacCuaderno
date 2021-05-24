package com.example.Activity.Crotal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.Otros.Crotal;
import com.example.Fragment.Crotales.Fragment_Crotales_Recibidos;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Crotal_Recibidos extends AppCompatActivity {

    Crotal crotal;

    boolean editar;

    EditText viewCrotal;
    EditText viewUnidades;

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
        setContentView(R.layout.activity_crotal_recibidos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        crotal = (Crotal) intent.getSerializableExtra(MainActivity.DATOS);
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
        MainActivity.eliminarRecibidos(this.crotal);
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
        viewCrotal = findViewById(R.id.layout_crotal_crotal);
        viewUnidades = findViewById(R.id.layout_crotal_unidades);

        fab_editar = findViewById(R.id.crotal_r_editar);
        fab_eliminar = findViewById(R.id.crotal_r_eliminar);
        fab_volver = findViewById(R.id.crotal_r_volver);
        fab_aceptar = findViewById(R.id.crotal_r_aceptar);
        fab_cancelar = findViewById(R.id.crotal_r_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> editar());

        fab_eliminar.setOnClickListener(view -> eliminar());

        fab_volver.setOnClickListener(view -> volver());

        fab_aceptar.setOnClickListener(view -> aceptar());

        fab_cancelar.setOnClickListener(view -> cancelar());
    }
    void iniciarDatos(){
        viewCrotal.setText(crotal.getCrotal());
        viewUnidades.setText(crotal.getUnidadesString());
    }
    void obtenerDatos(){
        crotal.setCrotal(viewCrotal.getText().toString());
        crotal.setUnidades(viewUnidades.getText().toString());

        Log.println(Log.INFO, "Datos obtenidos ", crotal.toString());
    }
    void hacerEditable(){
        viewCrotal.setEnabled(true);
        viewUnidades.setEnabled(true);

        fab_editar.setVisibility(View.INVISIBLE);
        fab_eliminar.setVisibility(View.INVISIBLE);
        fab_volver.setVisibility(View.INVISIBLE);

        fab_aceptar.setVisibility(View.VISIBLE);
        fab_cancelar.setVisibility(View.VISIBLE);
    }
    void hacerNoEditable(){
        viewCrotal.setEnabled(false);
        viewUnidades.setEnabled(false);

        fab_editar.setVisibility(View.VISIBLE);
        fab_eliminar.setVisibility(View.VISIBLE);
        fab_volver.setVisibility(View.VISIBLE);

        fab_aceptar.setVisibility(View.INVISIBLE);
        fab_cancelar.setVisibility(View.INVISIBLE);
    }

    void actualizar(){
        MainActivity.actualizarRecibidos(this.crotal);
    }
    void anadir(){
        MainActivity.insertarRecibidos(this.crotal);
    }
}