package com.ems.aula_3103;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // aqui vamos criar as variáveis para receber os
    // objetos que estão dispostos na View
    EditText nome, sobrenome;
    Button btConcatenar;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // a linha baixo mostra a View para o usuário
        setContentView(R.layout.activity_main);

        // aqui vamos relacionar os objetos da View com
        // as variáveis locais dos objetos criados nas
        // linhas 13-15
        nome = findViewById(R.id.editNome);
        sobrenome = findViewById(R.id.editSobrenome);
        btConcatenar = findViewById(R.id.buttonConcatenar);
        txtResultado = findViewById(R.id.textViewResultado);

        // agora chegou a hora de pegar os valores digitados
        // na View e atribuir às variáveis locais, linhas
        // 26-29
        // a lógica e AO CLICAR no botão, capturar os valores
        // digitados e atribuir às variáveis
        btConcatenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // o método setText() atribui um valor do tipo texto (String)
                // ao objeto que estão sendo manipulado
                txtResultado.setText(nome.getText() + " " + sobrenome.getText());
            }
        });
    }
}
