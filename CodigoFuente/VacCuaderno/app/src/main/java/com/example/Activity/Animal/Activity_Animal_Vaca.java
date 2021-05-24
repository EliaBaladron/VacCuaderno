package com.example.Activity.Animal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.Animales.Vaca;
import com.example.GridAdapter.GridAdapter_Lista_Crotales;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Animal_Vaca extends AppCompatActivity {

    Vaca vaca;

    boolean editar;

    EditText viewNombre;
    EditText viewCrotal;
    EditText viewNacimiento;
    //EditText viewSexo;
    EditText viewRaza;
    EditText viewMadre;
    EditText viewReb;
    EditText viewEmbarazo;

    GridView gridView;

    Spinner spinner;

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
        setContentView(R.layout.activity_animal_vaca);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        vaca = (Vaca) intent.getSerializableExtra(MainActivity.DATOS);
        editar = intent.getBooleanExtra(MainActivity.EDITAR, true);

        iniciarComponentes();
        iniciarListenersBotones();
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
        MainActivity.eliminar(this.vaca.getAnimal());
        MainActivity.eliminar(this.vaca);
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
        //viewSexo = findViewById(R.id.layout_animal_sexo);
        iniciarSpinner();
        viewRaza = findViewById(R.id.layout_animal_raza);
        viewMadre = findViewById(R.id.layout_animal_codMadre);
        viewReb = findViewById(R.id.layout_animal_reb);
        viewEmbarazo = findViewById(R.id.layout_animal_vaca_embarazo);

        gridView = findViewById(R.id.layout_animal_vaca_terneros);

        fab_editar = findViewById(R.id.animal_vaca_editar);
        fab_eliminar = findViewById(R.id.animal_vaca_eliminar);
        fab_volver = findViewById(R.id.animal_vaca_volver);
        fab_aceptar = findViewById(R.id.animal_vaca_aceptar);
        fab_cancelar = findViewById(R.id.animal_vaca_cancelar);
    }
    void iniciarSpinner(){
        spinner = (Spinner) findViewById(R.id.layout_animal_sexo);
        //Crea un ArrayAdapter usando un string-array guardado en strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sexos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    void iniciarListenersBotones(){
        fab_editar.setOnClickListener(view -> editar());
        fab_eliminar.setOnClickListener(view -> eliminar());
        fab_volver.setOnClickListener(view -> volver());
        fab_aceptar.setOnClickListener(view -> aceptar());
        fab_cancelar.setOnClickListener(view -> cancelar());
    }
    void iniciarDatos(){
        viewNombre.setText(vaca.getAnimal().getNombre());
        viewCrotal.setText(vaca.getAnimal().getCrotal());
        viewNacimiento.setText(vaca.getAnimal().getFechaNacString());
        //viewSexo.setText(vaca.getAnimal().getSexo());
        viewRaza.setText(vaca.getAnimal().getRaza());
        viewMadre.setText(vaca.getAnimal().getCodMadre());
        viewReb.setText(vaca.getAnimal().getIdReb());
        viewEmbarazo.setText(vaca.getFechaEmbarazoString());

        if(vaca.getAnimal().getSexo().equals("M"))
            spinner.setSelection(0);
        else
            spinner.setSelection(1);

        GridAdapter_Lista_Crotales adapter = new GridAdapter_Lista_Crotales(this.getApplicationContext(), vaca.getTerneros());
        gridView.setAdapter(adapter);
    }
    void obtenerDatos(){
        vaca.getAnimal().setNombre(viewNombre.getText().toString());
        vaca.getAnimal().setCrotal(viewCrotal.getText().toString());
        vaca.getAnimal().setFechaNac(viewNacimiento.getText().toString());
        //vaca.getAnimal().setSexo(viewSexo.getText().toString());
        vaca.getAnimal().setSexo(spinner.getSelectedItem().toString());
        vaca.getAnimal().setRaza(viewRaza.getText().toString());
        vaca.getAnimal().setCodMadre(viewMadre.getText().toString());
        vaca.getAnimal().setIdReb(viewReb.getText().toString());

        vaca.setCrotal(viewCrotal.getText().toString());
        vaca.setFechaEmbarazo(viewEmbarazo.getText().toString());

        Log.println(Log.INFO, "Datos obtenidos ", vaca.toString());
    }
    void hacerEditable(){
        viewNombre.setEnabled(true);
        viewCrotal.setEnabled(true);
        viewNacimiento.setEnabled(true);
        //viewSexo.setEnabled(true);
        spinner.setEnabled(true);
        viewRaza.setEnabled(true);
        viewMadre.setEnabled(true);
        viewReb.setEnabled(true);
        viewEmbarazo.setEnabled(true);

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
        //viewSexo.setEnabled(false);
        spinner.setEnabled(false);
        viewRaza.setEnabled(false);
        viewMadre.setEnabled(false);
        viewReb.setEnabled(false);
        viewEmbarazo.setEnabled(false);

        fab_editar.setVisibility(View.VISIBLE);
        fab_eliminar.setVisibility(View.VISIBLE);
        fab_volver.setVisibility(View.VISIBLE);

        fab_aceptar.setVisibility(View.INVISIBLE);
        fab_cancelar.setVisibility(View.INVISIBLE);
    }

    void actualizar(){
        MainActivity.actualizar(this.vaca.getAnimal());
        MainActivity.actualizar(this.vaca);
    }
    void anadir(){
        MainActivity.insertar(this.vaca.getAnimal());
        MainActivity.insertar(this.vaca);
    }
}