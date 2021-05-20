package com.example.Activity.Animal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.Animales.Animal;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Animal_Toro extends AppCompatActivity {

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

    /**
     * En la creación de la actividad obtiene los datos a mostrar,
     * inicia los componentes de la vista y muestra los datos pasado,
     * y hace los componentes editables o no editabbles según el caso
     * @param savedInstanceState	sis
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_toro);
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
    /**
     * Elimina el objeto mostrado
     */
    void eliminar(){
        MainActivity.eliminar(this.animal);
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
        viewNombre = findViewById(R.id.layout_animal_nombre);
        viewCrotal = findViewById(R.id.layout_animal_crotal);
        viewNacimiento = findViewById(R.id.layout_animal_fechaNac);
        viewSexo = findViewById(R.id.layout_animal_sexo);
        viewRaza = findViewById(R.id.layout_animal_raza);
        viewMadre = findViewById(R.id.layout_animal_codMadre);
        viewReb = findViewById(R.id.layout_animal_reb);


        fab_editar = findViewById(R.id.animal_toro_editar);
        fab_eliminar = findViewById(R.id.animal_toro_eliminar);
        fab_volver = findViewById(R.id.animal_toro_volver);
        fab_aceptar = findViewById(R.id.animal_toro_aceptar);
        fab_cancelar = findViewById(R.id.animal_toro_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> editar());

        fab_eliminar.setOnClickListener(view -> eliminar());

        fab_volver.setOnClickListener(view -> volver());

        fab_aceptar.setOnClickListener(view -> aceptar());

        fab_cancelar.setOnClickListener(view -> cancelar());
    }
    void iniciarDatos(){
        viewNombre.setText(animal.getNombre());
        viewNacimiento.setText(animal.getFechaNacString());
        viewSexo.setText(animal.getSexo());
        viewRaza.setText(animal.getRaza());
        viewMadre.setText(animal.getCodMadre());
        viewReb.setText(animal.getIdReb());
    }
    void obtenerDatos(){
        animal.setNombre(viewNombre.getText().toString());
        animal.setFechaNac(viewNacimiento.getText().toString());
        animal.setSexo(viewSexo.getText().toString());
        animal.setRaza(viewRaza.getText().toString());
        animal.setCodMadre(viewMadre.getText().toString());
        animal.setIdReb(viewReb.getText().toString());

        Log.println(Log.INFO, "Datos obtenidos ", animal.toString());
    }
    void hacerEditable(){
        viewNombre.setEnabled(true);
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

    void actualizar(){
        MainActivity.actualizar(this.animal);
    }
    void anadir(){
        MainActivity.anadir(this.animal);
    }
}