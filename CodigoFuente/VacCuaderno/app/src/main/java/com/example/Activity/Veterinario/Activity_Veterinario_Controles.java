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
import com.google.android.material.snackbar.Snackbar;

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
    void eliminar(){
        Fragment_Veterinario_Controles.eliminar(this.controles);
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
        fab_editar.setOnClickListener(view -> {
            Snackbar.make(view, "Editar "+controles.getTitulo(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            editar();
        });

        fab_eliminar.setOnClickListener(view -> {
            Snackbar.make(view, "Eliminar "+controles.getTitulo(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            eliminar();
        });

        fab_volver.setOnClickListener(view -> {
            Snackbar.make(view, "Volver "+controles.getTitulo(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            volver();
        });

        fab_aceptar.setOnClickListener(view -> {
            Snackbar.make(view, "Aceptar "+controles.getTitulo(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            aceptar();
        });

        fab_cancelar.setOnClickListener(view -> {
            Snackbar.make(view, "Cancelar "+controles.getTitulo(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            cancelar();
        });
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
        Fragment_Veterinario_Controles.actualizar(this.controles);
    }
    void anadir(){
        Fragment_Veterinario_Controles.anadir(this.controles);
    }
}