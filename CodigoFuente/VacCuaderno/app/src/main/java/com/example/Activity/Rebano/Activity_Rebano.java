package com.example.Activity.Rebano;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.Otros.Rebaño;
import com.example.Fragment.Rebano.Fragment_Rebano;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Activity_Rebano extends AppCompatActivity {

    Rebaño rebano;

    boolean editar;

    EditText viewId;
    EditText viewNombre;

    FloatingActionButton fab_editar;
    FloatingActionButton fab_eliminar;
    FloatingActionButton fab_volver;
    FloatingActionButton fab_aceptar;
    FloatingActionButton fab_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebano);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        rebano = (Rebaño) intent.getSerializableExtra(MainActivity.DATOS);
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
        Fragment_Rebano.eliminar(this.rebano);
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
        viewId = findViewById(R.id.layout_rebano_id);
        viewNombre = findViewById(R.id.layout_rebano_nombre);

        fab_editar = findViewById(R.id.rebano_editar);
        fab_eliminar = findViewById(R.id.rebano_eliminar);
        fab_volver = findViewById(R.id.rebano_volver);
        fab_aceptar = findViewById(R.id.rebano_aceptar);
        fab_cancelar = findViewById(R.id.rebano_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> {
            Snackbar.make(view, "Editar "+rebano.getNombre(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            editar();
        });

        fab_eliminar.setOnClickListener(view -> {
            Snackbar.make(view, "Eliminar "+rebano.getNombre(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            eliminar();
        });

        fab_volver.setOnClickListener(view -> {
            Snackbar.make(view, "Volver "+rebano.getNombre(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            volver();
        });

        fab_aceptar.setOnClickListener(view -> {
            Snackbar.make(view, "Aceptar "+rebano.getNombre(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            aceptar();
        });

        fab_cancelar.setOnClickListener(view -> {
            Snackbar.make(view, "Cancelar "+rebano.getNombre(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            cancelar();
        });
    }
    void iniciarDatos(){
        viewNombre.setText(rebano.getNombre());
    }void obtenerDatos(){
        rebano.setNombre(viewNombre.getText().toString());

        Log.println(Log.INFO, "Datos obtenidos ", rebano.toString());
    }
    void hacerEditable(){
        viewId.setEnabled(false);
        viewNombre.setEnabled(true);

        fab_editar.setVisibility(View.INVISIBLE);
        fab_eliminar.setVisibility(View.INVISIBLE);
        fab_volver.setVisibility(View.INVISIBLE);

        fab_aceptar.setVisibility(View.VISIBLE);
        fab_cancelar.setVisibility(View.VISIBLE);
    }
    void hacerNoEditable(){
        viewId.setEnabled(false);
        viewNombre.setEnabled(false);

        fab_editar.setVisibility(View.VISIBLE);
        fab_eliminar.setVisibility(View.VISIBLE);
        fab_volver.setVisibility(View.VISIBLE);

        fab_aceptar.setVisibility(View.INVISIBLE);
        fab_cancelar.setVisibility(View.INVISIBLE);
    }

    void actualizar(){
        Fragment_Rebano.actualizar(this.rebano);
    }
    void anadir(){
        Fragment_Rebano.anadir(this.rebano);
    }
}