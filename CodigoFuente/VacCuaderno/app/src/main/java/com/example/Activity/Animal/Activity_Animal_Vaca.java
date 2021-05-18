package com.example.Activity.Animal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.Animales.Vaca;
import com.example.Fragment.Animales.Fragment_Animales;
import com.example.Fragment.Animales.Fragment_Animales_Vacas;
import com.example.GridAdapter.GridAdapter_Lista_Crotales;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Animal_Vaca extends AppCompatActivity {

    Vaca vaca;

    boolean editar;

    EditText viewNombre;
    EditText viewCrotal;
    EditText viewNacimiento;
    EditText viewSexo;
    EditText viewRaza;
    EditText viewMadre;
    EditText viewReb;
    EditText viewEmbarazo;

    GridView gridView;

    FloatingActionButton fab_editar;
    FloatingActionButton fab_eliminar;
    FloatingActionButton fab_volver;
    FloatingActionButton fab_aceptar;
    FloatingActionButton fab_cancelar;

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
    void eliminar(){
        Fragment_Animales.eliminar(this.vaca.getAnimal());
        Fragment_Animales_Vacas.eliminar(this.vaca);
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
        viewEmbarazo = findViewById(R.id.layout_animal_vaca_embarazo);

        gridView = findViewById(R.id.layout_animal_vaca_terneros);

        fab_editar = findViewById(R.id.animal_vaca_editar);
        fab_eliminar = findViewById(R.id.animal_vaca_eliminar);
        fab_volver = findViewById(R.id.animal_vaca_volver);
        fab_aceptar = findViewById(R.id.animal_vaca_aceptar);
        fab_cancelar = findViewById(R.id.animal_vaca_cancelar);
    }
    void iniciarListenersBotones(){
        fab_editar.setOnClickListener(view -> {
            Snackbar.make(view, "Editar "+vaca.getAnimal().getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            editar();
        });

        fab_eliminar.setOnClickListener(view -> {
            Snackbar.make(view, "Eliminar "+vaca.getAnimal().getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            eliminar();
        });

        fab_volver.setOnClickListener(view -> {
            Snackbar.make(view, "Volver "+vaca.getAnimal().getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            volver();
        });

        fab_aceptar.setOnClickListener(view -> {
            Snackbar.make(view, "Aceptar "+vaca.getAnimal().getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            aceptar();
        });

        fab_cancelar.setOnClickListener(view -> {
            Snackbar.make(view, "Cancelar "+vaca.getAnimal().getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            cancelar();
        });
    }
    void iniciarDatos(){
        viewNombre.setText(vaca.getAnimal().getNombre());
        viewCrotal.setText(vaca.getAnimal().getCrotal());
        viewNacimiento.setText(vaca.getAnimal().getFechaNacString());
        viewSexo.setText(vaca.getAnimal().getSexo());
        viewRaza.setText(vaca.getAnimal().getRaza());
        viewMadre.setText(vaca.getAnimal().getCodMadre());
        viewReb.setText(vaca.getAnimal().getIdReb());
        viewEmbarazo.setText(vaca.getFechaEmbarazoString());

        GridAdapter_Lista_Crotales adapter = new GridAdapter_Lista_Crotales(this.getApplicationContext(), vaca.getTerneros());
        gridView.setAdapter(adapter);
    }
    void obtenerDatos(){
        vaca.getAnimal().setNombre(viewNombre.getText().toString());
        vaca.getAnimal().setCrotal(viewCrotal.getText().toString());
        vaca.getAnimal().setFechaNac(viewNacimiento.getText().toString());
        vaca.getAnimal().setSexo(viewSexo.getText().toString());
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
        viewSexo.setEnabled(true);
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
        viewSexo.setEnabled(false);
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
        Fragment_Animales.actualizar(this.vaca.getAnimal());
        Fragment_Animales_Vacas.actualizar(this.vaca);
    }
    void anadir(){
        Fragment_Animales.anadir(this.vaca.getAnimal());
        Fragment_Animales_Vacas.anadir(this.vaca);
    }
}