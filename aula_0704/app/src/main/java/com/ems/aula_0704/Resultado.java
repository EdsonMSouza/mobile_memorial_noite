package com.ems.aula_0704;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resultado extends AppCompatActivity {
    TextView nome, sobrenome;
    Button btVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        nome = findViewById(R.id.textViewNome);
        sobrenome = findViewById(R.id.textViewSobrenome);
        btVoltar = findViewById(R.id.buttonVoltar);

        // capturar os dados que foram enviados
        Intent intent = getIntent();

        // pegar o valores que foram enviados
        Bundle parametros = intent.getExtras();

        nome.setText(parametros.getString("nome"));
        sobrenome.setText(parametros.getString("sobrenome"));

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
