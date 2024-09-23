package com.example.parcial_1_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoRespuestaActivity extends AppCompatActivity {

    private int preguntaActual;
    private int respuestasCorrectas;
    private int respuestasIncorrectas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_respuestas);

        TextView textViewRespuesta = findViewById(R.id.textViewRespuesta);
        boolean respuestaCorrecta = getIntent().getBooleanExtra("respuesta_correcta", false);
        preguntaActual = getIntent().getIntExtra("pregunta_actual", 0);
        respuestasCorrectas = getIntent().getIntExtra("respuestas_correctas", 0);
        respuestasIncorrectas = getIntent().getIntExtra("respuestas_incorrectas", 0);

        if (respuestaCorrecta) {
            textViewRespuesta.setText("¡Respuesta correcta!");
        } else {
            textViewRespuesta.setText("Respuesta incorrecta");
        }
    }

    public void siguientePregunta(View view) {
        if (respuestasIncorrectas >= 3) {
            Intent intent = new Intent(this, ResultadoActivity.class);
            intent.putExtra("respuestas_correctas", respuestasCorrectas);
            intent.putExtra("respuestas_incorrectas", respuestasIncorrectas);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, PreguntadosActivity.class);
            intent.putExtra("pregunta_actual", preguntaActual + 1);
            intent.putExtra("respuestas_correctas", respuestasCorrectas);
            intent.putExtra("respuestas_incorrectas", respuestasIncorrectas);
            startActivity(intent);
            finish();
        }
    }
}