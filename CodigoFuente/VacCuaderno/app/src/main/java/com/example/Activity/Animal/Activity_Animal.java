package com.example.Activity.Animal;

import android.content.Intent;
import android.os.Bundle;

import com.example.ClasesVO.Animales.Animal;
import com.example.Fragment.Animales.Fragment_Animales;
import com.example.Fragment.Animales.Fragment_Animales_Terneros;
import com.example.Fragment.Animales.Fragment_Animales_Vacas;
import com.example.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.prueba03.R;

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Animal extends AppCompatActivity {

    Animal animal;

    boolean editar;

    EditText viewNombre;
    EditText viewCrotal;
    EditText viewNacimiento;
    EditText viewSexo;
    EditText viewRaza;
    EditText viewMadre;
    EditText viewReb;

    FloatingActionButton fab_editar;
    FloatingActionButton fab_eliminar;
    FloatingActionButton fab_volver;
    FloatingActionButton fab_aceptar;
    FloatingActionButton fab_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        animal = (Animal) intent.getSerializableExtra(MainActivity.DATOS);
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
        //Fragment_Animales.eliminar(this.animal);
        //Fragment_Animales_Vacas.eliminar(this.animal.getCrotal());
        //Fragment_Animales_Terneros.eliminar(this.animal.getCrotal());
        MainActivity.eliminar(this.animal);
        MainActivity.eliminarVaca(this.animal.getCrotal());
        MainActivity.eliminarTernero(this.animal.getCrotal());

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
        viewNombre = findViewById(R.id.layout_animal_nombre);
        viewCrotal = findViewById(R.id.layout_animal_crotal);
        viewNacimiento = findViewById(R.id.layout_animal_fechaNac);
        viewSexo = findViewById(R.id.layout_animal_sexo);
        viewRaza = findViewById(R.id.layout_animal_raza);
        viewMadre = findViewById(R.id.layout_animal_codMadre);
        viewReb = findViewById(R.id.layout_animal_reb);

        fab_editar = findViewById(R.id.animal_editar);
        fab_eliminar = findViewById(R.id.animal_eliminar);
        fab_volver = findViewById(R.id.animal_volver);
        fab_aceptar = findViewById(R.id.animal_aceptar);
        fab_cancelar = findViewById(R.id.animal_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> {
            Snackbar.make(view, "Editar "+animal.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            editar();
        });

        fab_eliminar.setOnClickListener(view -> {
            Snackbar.make(view, "Eliminar "+animal.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            eliminar();
        });

        fab_volver.setOnClickListener(view -> {
            Snackbar.make(view, "Volver "+animal.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            volver();
        });

        fab_aceptar.setOnClickListener(view -> {
            Snackbar.make(view, "Aceptar "+animal.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            aceptar();
        });

        fab_cancelar.setOnClickListener(view -> {
            Snackbar.make(view, "Cancelar "+animal.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            cancelar();
        });
    }
    void iniciarDatos(){
        viewNombre.setText(animal.getNombre());
        viewCrotal.setText(animal.getCrotal());
        viewNacimiento.setText(animal.getFechaNacString());
        viewSexo.setText(animal.getSexo());
        viewRaza.setText(animal.getRaza());
        viewMadre.setText(animal.getCodMadre());
        viewReb.setText(animal.getIdReb());
    }
    void obtenerDatos(){
        animal.setNombre(viewNombre.getText().toString());
        animal.setCrotal(viewCrotal.getText().toString());
        animal.setFechaNac(viewNacimiento.getText().toString());
        animal.setSexo(viewSexo.getText().toString());
        animal.setRaza(viewRaza.getText().toString());
        animal.setCodMadre(viewMadre.getText().toString());
        animal.setIdReb(viewReb.getText().toString());

        Log.println(Log.INFO, "Datos obtenidos ", animal.toString());
    }
    void hacerEditable(){
        viewNombre.setEnabled(true);
        viewCrotal.setEnabled(true);
        viewNacimiento.setEnabled(true);
        viewSexo.setEnabled(true);
        viewRaza.setEnabled(true);
        viewMadre.setEnabled(true);
        viewReb.setEnabled(true);

        fab_editar.setVisibility(View.INVISIBLE);
        fab_eliminar.setVisibility(View.INVISIBLE);
        fab_volver.setVisibility(View.INVISIBLE);

        fab_aceptar.setVisibility(View.VISIBLE);
        fab_cancelar.setVisibility(View.VISIBLE);
    }
    void hacerNoEditable(){
        viewNombre.setEnabled(false);
        viewCrotal.setEnabled(false);
        viewNacimiento.setEnabled(false);
        viewSexo.setEnabled(false);
        viewRaza.setEnabled(false);
        viewMadre.setEnabled(false);
        viewReb.setEnabled(false);

        fab_editar.setVisibility(View.VISIBLE);
        fab_eliminar.setVisibility(View.VISIBLE);
        fab_volver.setVisibility(View.VISIBLE);

        fab_aceptar.setVisibility(View.INVISIBLE);
        fab_cancelar.setVisibility(View.INVISIBLE);
    }

    /*
    void actualizar(){
        Fragment_Animales.actualizar(this.animal);
    }
    void anadir(){
        Fragment_Animales.anadir(this.animal);
    }*/
    void actualizar(){
        MainActivity.actualizar(this.animal);
    }
    void anadir(){
        MainActivity.anadir(this.animal);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}