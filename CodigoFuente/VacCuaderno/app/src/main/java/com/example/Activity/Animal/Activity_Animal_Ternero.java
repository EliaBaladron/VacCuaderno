package com.example.Activity.Animal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ClasesVO.Animales.Ternero;
import com.example.Fragment.Animales.Fragment_Animales;
import com.example.Fragment.Animales.Fragment_Animales_Terneros;
import com.example.MainActivity;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Date;

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Animal_Ternero extends AppCompatActivity {

    Ternero ternero;

    boolean editar;

    EditText viewNombre;
    EditText viewCrotal;
    EditText viewNacimiento;
    EditText viewSexo;
    EditText viewRaza;
    EditText viewMadre;
    EditText viewReb;
    EditText viewDestete;
    EditText viewProposito;

    FloatingActionButton fab_editar;
    FloatingActionButton fab_eliminar;
    FloatingActionButton fab_volver;
    FloatingActionButton fab_aceptar;
    FloatingActionButton fab_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_ternero);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        ternero = (Ternero) intent.getSerializableExtra(MainActivity.DATOS);
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
        Fragment_Animales.eliminar(this.ternero.getAnimal());
        Fragment_Animales_Terneros.eliminar(this.ternero);
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

        viewDestete = findViewById(R.id.layout_animal_ternero_fecDestet);
        viewProposito = findViewById(R.id.layout_animal_ternero_proposito);

        fab_editar = findViewById(R.id.animal_ternero_editar);
        fab_eliminar = findViewById(R.id.animal_ternero_eliminar);
        fab_volver = findViewById(R.id.animal_ternero_volver);
        fab_aceptar = findViewById(R.id.animal_ternero_aceptar);
        fab_cancelar = findViewById(R.id.animal_ternero_cancelar);
    }
    void iniciarListenerBotones(){
        fab_editar.setOnClickListener(view -> {
            Snackbar.make(view, "Editar "+ternero.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            editar();
        });

        fab_eliminar.setOnClickListener(view -> {
            Snackbar.make(view, "Eliminar "+ternero.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            eliminar();
        });

        fab_volver.setOnClickListener(view -> {
            Snackbar.make(view, "Volver "+ternero.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            volver();
        });

        fab_aceptar.setOnClickListener(view -> {
            Snackbar.make(view, "Aceptar "+ternero.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            aceptar();
        });

        fab_cancelar.setOnClickListener(view -> {
            Snackbar.make(view, "Cancelar "+ternero.getCrotal(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            cancelar();
        });
    }
    void iniciarDatos(){
        viewNombre.setText(ternero.getAnimal().getNombre());
        viewCrotal.setText(ternero.getAnimal().getCrotal());
        viewNacimiento.setText(ternero.getAnimal().getFechaNacString());
        viewSexo.setText(ternero.getAnimal().getSexo());
        viewRaza.setText(ternero.getAnimal().getRaza());
        viewMadre.setText(ternero.getAnimal().getCodMadre());
        viewReb.setText(ternero.getAnimal().getIdReb());

        if(ternero.getFechaDestete() == null)
            //viewDestete.setText(new Date(System.currentTimeMillis()).toString());
            viewDestete.setText("");
        else
            viewDestete.setText(ternero.getFechaDestete().toString());
        viewProposito.setText(ternero.getProposito());
    }
    void obtenerDatos(){
        ternero.getAnimal().setNombre(viewNombre.getText().toString());
        ternero.getAnimal().setCrotal(viewCrotal.getText().toString());
        ternero.getAnimal().setFechaNac(viewNacimiento.getText().toString());
        ternero.getAnimal().setSexo(viewSexo.getText().toString());
        ternero.getAnimal().setRaza(viewRaza.getText().toString());
        ternero.getAnimal().setCodMadre(viewMadre.getText().toString());
        ternero.getAnimal().setIdReb(viewReb.getText().toString());

        ternero.setCrotal(viewCrotal.getText().toString());
        ternero.setFechaDestete(viewDestete.getText().toString());
        ternero.setProposito(viewProposito.getText().toString());

        Log.println(Log.INFO, "Datos obtenidos ", ternero.toString());
    }
    void hacerEditable(){
        viewNombre.setEnabled(true);
        viewCrotal.setEnabled(true);
        viewNacimiento.setEnabled(true);
        viewSexo.setEnabled(true);
        viewRaza.setEnabled(true);
        viewMadre.setEnabled(true);
        viewReb.setEnabled(true);
        viewDestete.setEnabled(true);
        viewProposito.setEnabled(true);

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
        viewDestete.setEnabled(false);
        viewProposito.setEnabled(false);

        fab_editar.setVisibility(View.VISIBLE);
        fab_eliminar.setVisibility(View.VISIBLE);
        fab_volver.setVisibility(View.VISIBLE);

        fab_aceptar.setVisibility(View.INVISIBLE);
        fab_cancelar.setVisibility(View.INVISIBLE);
    }

    void actualizar(){
        Fragment_Animales.actualizar(this.ternero.getAnimal());
        Fragment_Animales_Terneros.actualizar(this.ternero);
    }
    void anadir(){
        Fragment_Animales.anadir(this.ternero.getAnimal());
        Fragment_Animales_Terneros.anadir(this.ternero);
    }
}