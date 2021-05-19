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
import com.google.android.material.snackbar.Snackbar;

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
    void eliminar(){
        Fragment_Crotales_Recibidos.eliminar(this.crotal);
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
        viewCrotal = findViewById(R.id.layout_crotal_crotal);
        viewUnidades = findViewById(R.id.layout_crotal_unidades);

        fab_editar = findViewById(R.id.crotal_r_editar);
        fab_eliminar = findViewById(R.id.crotal_r_eliminar);
        fab_volver = findViewById(R.id.crotal_r_volver);
        fab_aceptar = findViewById(R.id.crotal_r_aceptar);
        fab_cancelar = findViewById(R.id.crotal_r_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> {
            //Snackbar.make(view, "Editar "+crotal.getCrotal(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

            editar();
        });

        fab_eliminar.setOnClickListener(view -> {
            //Snackbar.make(view, "Eliminar "+crotal.getCrotal(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

            eliminar();
        });

        fab_volver.setOnClickListener(view -> {
            //Snackbar.make(view, "Volver "+crotal.getCrotal(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

            volver();
        });

        fab_aceptar.setOnClickListener(view -> {
            //Snackbar.make(view, "Aceptar "+crotal.getCrotal(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

            aceptar();
        });

        fab_cancelar.setOnClickListener(view -> {
            //Snackbar.make(view, "Cancelar "+crotal.getCrotal(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

            cancelar();
        });
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
        Fragment_Crotales_Recibidos.actualizar(this.crotal);
    }
    void anadir(){
        Fragment_Crotales_Recibidos.anadir(this.crotal);
    }
}