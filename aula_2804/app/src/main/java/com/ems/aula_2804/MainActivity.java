package com.ems.aula_2804;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

                // limpar os campos
                ra.setText("");
                nome.setText("");
                curso.setText("");

                // posicionar o cursor no campo ra
                ra.requestFocus();

                // fechar o teclado virtual após inserir
                ((InputMethodManager) MainActivity.this.getSystemService(
                        Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                        getCurrentFocus().getWindowToken(), 0);

            }
        });

        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // (Aluno) é conhecido como CAST ou Conversão de Tipo
                Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(position);

                Toast.makeText(MainActivity.this,
                        aluno.getDados(),
                        Toast.LENGTH_LONG).show();
            }
        });

        lvAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // resgatando o objeto que está dentro do ArrayAdpater na ListView
                Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(position);

                // criar um novo ArrayList para acomodar o objeto
                ArrayList<Aluno> al = new ArrayList<>();

                // coloca o objeto dentro do ArrayList
                al.add(aluno);

                Intent it = new Intent(MainActivity.this, Resultado.class);

                // inserir o ArrayList com o objeto dentro da Intent
                it.putExtra("objAluno", al);
                startActivity(it);

                return false;
            }
        });
    }
}
