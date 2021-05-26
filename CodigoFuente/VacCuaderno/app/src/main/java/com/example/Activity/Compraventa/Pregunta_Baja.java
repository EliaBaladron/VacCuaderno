package com.example.Activity.Compraventa;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MainActivity;
import com.example.prueba03.R;

public class Pregunta_Baja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compraventa_baja);

        String crotal = getIntent().getStringExtra(MainActivity.CROTAL);

        Button si = findViewById(R.id.baja_si);
        Button no = findViewById(R.id.baja_no);

        si.setOnClickListener(v -> {
            MainActivity.eliminarAnimal(crotal);
            finish();
        });
        no.setOnClickListener(v -> {
            finish();
        });
    }
}