package com.example.parcial_1_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MensajeActivity extends AppCompatActivity {
    private String nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mensaje);

        TextView textViewDatos = findViewById(R.id.textView);
        String nombre = getIntent().getStringExtra("nombre");
        String datos = "Bienvenido: " + nombre +
                "\nEl juego consiste en reponder 5 preguntas, si respondes mal 3 preguntas pierdes automaticamente " +
                "\nBuena suerte :D";
        textViewDatos.setText(datos);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void finalizar(View view) {
        finish();

    }

    public void iniciarPreguntados(View view) {
        Intent intent = new Intent(this, PreguntadosActivity.class);
        intent.putExtra("nombre", nombre);
        startActivity(intent);
    }

}