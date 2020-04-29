package com.ems.aula_2804;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ra, nome, curso;
    Button btInserir;
    ListView lvAlunos;

    // criar uma variável que permita colocar vários objetos
    // dentro dela, ou seja, é um array (lista)
    ArrayList<Aluno> alunos = new ArrayList<>();

    // criar um adaptador (não é gambiarra) para colocar os dados
    // da lista de "alunos" dentro da ListView
    ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ra = findViewById(R.id.editTextRa);
        nome = findViewById(R.id.editTextNome);
        curso = findViewById(R.id.editTextCurso);
        btInserir = findViewById(R.id.btInserir);
        lvAlunos = findViewById(R.id.listViewAlunos);

        // criar o adaptador (adapter)
        adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                alunos);

        // Associando o adaptador com a ListView
        lvAlunos.setAdapter(adapter);

        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alunos.add(
                       new Aluno(
                               ra.getText().toString(),
                               nome.getText().toString(),
                               curso.getText().toString()
                       )
                );
                // avisar o adapter que os dados mudaram
                adapter.notifyDataSetChanged();
            }
        });

    }
}
