package com.example.Activity.Compraventa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Activity.Animal.Activity_Animal;
import com.example.ClasesVO.Animales.Animal;
import com.example.MainActivity;
import com.example.prueba03.R;

public class Pregunta_Alta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compraventa_alta);

        String crotal = getIntent().getStringExtra(MainActivity.CROTAL);

        Button si = findViewById(R.id.alta_si);
        Button no = findViewById(R.id.alta_no);

        si.setOnClickListener(v -> {
            finish();

            Animal animal = new Animal();
            animal.setCrotal(crotal);

            Intent intent = new Intent(this.getBaseContext(), Activity_Animal.class);
            intent.putExtra(MainActivity.EDITAR, false);
            intent.putExtra(MainActivity.DATOS, animal);
            startActivity(intent);
        });
        no.setOnClickListener(v -> {
            finish();
        });
    }
}