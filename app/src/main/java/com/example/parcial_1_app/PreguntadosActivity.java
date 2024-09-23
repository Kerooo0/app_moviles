package com.example.parcial_1_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PreguntadosActivity extends AppCompatActivity {
    private TextView textViewPregunta;
    private RadioGroup radioGroupOpciones;
    private RadioButton radioButtonOpcion1, radioButtonOpcion2, radioButtonOpcion3;
    private int preguntaActual = 0;
    private int respuestasCorrectas = 0;
    private int respuestasIncorrectas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntados);

        textViewPregunta = findViewById(R.id.textViewPregunta);
        radioGroupOpciones = findViewById(R.id.radioGroupOpciones);
        radioButtonOpcion1 = findViewById(R.id.radioButtonOpcion1);
        radioButtonOpcion2 = findViewById(R.id.radioButtonOpcion2);
        radioButtonOpcion3 = findViewById(R.id.radioButtonOpcion3);

        // Obtener los datos de la actividad anterior si existen
        Intent intent = getIntent();
        preguntaActual = intent.getIntExtra("pregunta_actual", 0);
        respuestasCorrectas = intent.getIntExtra("respuestas_correctas", 0);
        respuestasIncorrectas = intent.getIntExtra("respuestas_incorrectas", 0);

        mostrarPregunta();
    }

    private void mostrarPregunta() {
        if (preguntaActual < Preguntados.preguntas.length) {
            textViewPregunta.setText(Preguntados.preguntas[preguntaActual]);
            radioButtonOpcion1.setText(Preguntados.opciones[preguntaActual][0]);
            radioButtonOpcion2.setText(Preguntados.opciones[preguntaActual][1]);
            radioButtonOpcion3.setText(Preguntados.opciones[preguntaActual][2]);
        } else {
            mostrarResultado();
        }
    }

    public void verificarRespuesta(View view) {
        int idSeleccionado = radioGroupOpciones.getCheckedRadioButtonId();
        if (idSeleccionado == -1) {
            Toast.makeText(this, "Por favor, seleccione una respuesta", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton radioButtonSeleccionado = findViewById(idSeleccionado);
        String respuestaSeleccionada = radioButtonSeleccionado.getText().toString();
        boolean respuestaCorrecta = respuestaSeleccionada.equals(Preguntados.respuestas[preguntaActual]);
        if (respuestaCorrecta) {
            respuestasCorrectas++;
        } else {
            respuestasIncorrectas++;
        }

        if (respuestasIncorrectas >= 3) {
            mostrarResultado();
        } else {
            Intent intent = new Intent(this, ResultadoRespuestaActivity.class);
            intent.putExtra("respuesta_correcta", respuestaCorrecta);
            intent.putExtra("pregunta_actual", preguntaActual);
            intent.putExtra("respuestas_correctas", respuestasCorrectas);
            intent.putExtra("respuestas_incorrectas", respuestasIncorrectas);
            startActivity(intent);
        }
    }

    private void mostrarResultado() {
        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("respuestas_correctas", respuestasCorrectas);
        intent.putExtra("respuestas_incorrectas", respuestasIncorrectas);
        startActivity(intent);
        finish();
    }
}