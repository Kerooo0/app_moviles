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

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);

        TextView textViewResultado=findViewById(R.id.textViewResultado);
        int respuestasCorrectas = getIntent().getIntExtra("respuestas_correctas", 0);
        int respuestasIncorrectas = getIntent().getIntExtra("respuestas_incorrectas", 0);

        String resultadoDerrota = "Lamentablemente, has perdido. " +
                "\nRespuestas correctas: " + respuestasCorrectas
                + "\nRespuestas incorrectas: " + respuestasIncorrectas;

        String resultadoVictoria = "Felicidades, has ganado " + "\nRespuestas correctas: " + respuestasCorrectas
                + "\nRespuestas incorrectas: " + respuestasIncorrectas;

        if (respuestasIncorrectas >= 3) {
            textViewResultado.setText(resultadoDerrota);
        } else {
            textViewResultado.setText(resultadoVictoria);
            
        }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

        }
        public void salir(View view) {
        finishAffinity();
    }

    public void volverAJugar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}