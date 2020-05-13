package com.ems.bdsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlite.crud.Listar;
import com.ems.bdsqlite.crud.Novo;

public class MainActivity extends AppCompatActivity {
    ImageButton btNovo, btListar, btSair;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btNovo = findViewById(R.id.btNovo);
        btListar = findViewById(R.id.btListar_main);
        btSair = findViewById(R.id.btSair);

        btNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itNovo = new Intent(getApplicationContext(), Novo.class);
                startActivity(itNovo);
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itListar = new Intent(getApplicationContext(), Listar.class);
                startActivity(itListar);
            }
        });

        // Associa e configura o botão para sair da aplicação
        btSair = findViewById(R.id.btSair);
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finaliza a aplicação e remove da pilha
                finishAffinity();
            }
        });
    }
}
