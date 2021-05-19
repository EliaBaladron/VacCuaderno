package com.example.Ayudas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.prueba03.R;

public class Ayuda_Animales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda__animales);

        TextView ayuda = findViewById(R.id.ayuda);
        ayuda.setText("Dentro del área Animales se puede acceder a las pestañas Animales, Terneros, Vacas y Toros clicando en su pestaña correspondiente");
    }
}