package com.ems.aula_0704;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nome, sobrenome;
    Button btOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // conecta as variáveis locais com os objetos da View
        nome = findViewById(R.id.editTextNome);
        sobrenome = findViewById(R.id.editTextSobrenome);
        btOk = findViewById(R.id.buttonOk);

        // configurar o botão para o Listener
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // transição de tela (Intent ou Intenção)
                String txtNome = nome.getText().toString();
                String txtSobrenome = sobrenome.getText().toString();

                // cria um objeto Intent para enviar os dados coletados para outra View
                // parâmetros da Intent: (nome da Activity atual, nome da Activity que vai receber)
                Intent intent = new Intent(getApplicationContext(), Resultado.class);

                // cria um pacote para encapsular os dados
                Bundle pacote = new Bundle();

                // colocar os dados dentro do pacote
                pacote.putString("nome", txtNome);
                pacote.putString("sobrenome", txtSobrenome);

                // colocar o pacote dentro da Intent
                intent.putExtras(pacote);

                // chamar a segunda Activity (atividade)
                startActivity(intent);
            }
        });

    }
}
