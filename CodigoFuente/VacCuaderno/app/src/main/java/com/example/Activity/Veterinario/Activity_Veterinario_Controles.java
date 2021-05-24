package com.example.Activity.Veterinario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.Veterinario.Controles;
import com.example.Fragment.Veterinario.Fragment_Veterinario_Controles;
import com.example.GridAdapter.GridAdapter_Lista_Crotales;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Veterinario_Controles extends AppCompatActivity {

    Controles controles;

    boolean editar;

    EditText viewTitulo;
    EditText viewDescripcion;
    EditText viewFecha;
    GridView gridView;

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
        setContentView(R.layout.activity_veterinario_controles);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        controles = (Controles) intent.getSerializableExtra(MainActivity.DATOS);
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
        MainActivity.eliminar(this.controles);
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
        viewTitulo = findViewById(R.id.layout_veterinario_controles_titulo);
        viewDescripcion = findViewById(R.id.layout_veterinario_controles_descripcion);
        viewFecha = findViewById(R.id.layout_veterinario_controles_fecha);
        gridView = findViewById(R.id.layout_veterinario_controles_animales);

        fab_editar = findViewById(R.id.veterinario_c_editar);
        fab_eliminar = findViewById(R.id.veterinario_c_eliminar);
        fab_volver = findViewById(R.id.veterinario_c_volver);
        fab_aceptar = findViewById(R.id.veterinario_c_aceptar);
        fab_cancelar = findViewById(R.id.veterinario_c_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> editar());

        fab_eliminar.setOnClickListener(view -> eliminar());

        fab_volver.setOnClickListener(view -> volver());

        fab_aceptar.setOnClickListener(view -> aceptar());

        fab_cancelar.setOnClickListener(view -> cancelar());
    }
    void iniciarDatos(){
        viewTitulo.setText(controles.getTitulo());
        viewDescripcion.setText(controles.getDescripcion());
        viewFecha.setText(controles.getFechaString());

        GridAdapter_Lista_Crotales adapter = new GridAdapter_Lista_Crotales(this.getApplicationContext(), controles.getAnimales());
        gridView.setAdapter(adapter);
    }
    void obtenerDatos(){
        controles.setTitulo(viewTitulo.getText().toString());
        controles.setDescripcion(viewDescripcion.getText().toString());
        controles.setFecha(viewFecha.getText().toString());

        Log.println(Log.INFO, "Datos obtenidos ", controles.toString());
    }
    void hacerEditable(){
        viewTitulo.setEnabled(true);
        viewDescripcion.setEnabled(true);
        viewFecha.setEnabled(true);

        fab_editar.setVisibility(View.INVISIBLE);
        fab_eliminar.setVisibility(View.INVISIBLE);
        fab_volver.setVisibility(View.INVISIBLE);

        fab_aceptar.setVisibility(View.VISIBLE);
        fab_cancelar.setVisibility(View.VISIBLE);
    }
    void hacerNoEditable(){
        viewTitulo.setEnabled(false);
        viewDescripcion.setEnabled(false);
        viewFecha.setEnabled(false);

        fab_editar.setVisibility(View.VISIBLE);
        fab_eliminar.setVisibility(View.VISIBLE);
        fab_volver.setVisibility(View.VISIBLE);

        fab_aceptar.setVisibility(View.INVISIBLE);
        fab_cancelar.setVisibility(View.INVISIBLE);
    }

    void actualizar(){
        MainActivity.actualizar(this.controles);
    }
    void anadir(){
        MainActivity.insertar(this.controles);
    }
}