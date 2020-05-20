package com.ems.bdsqlite.crud;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlite.R;
import com.ems.bdsqlite.pojo.Aluno;
import com.ems.bdsqlite.utils.Banco;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {
    ListView listViewAlunos;
    ImageButton btVoltar;

    SQLiteDatabase db;
    ArrayList<Aluno> alunos = new ArrayList<>();
    ArrayAdapter<Aluno> adaptador;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Listagem dos Alunos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        db = openOrCreateDatabase(
                Banco.banco(),
                Context.MODE_PRIVATE,
                null);

        listViewAlunos = findViewById(R.id.lvAlunos);
        btVoltar = findViewById(R.id.btVoltar);

        // popular a nossa lista ListView
        // limpar a lista
        alunos.clear();

        // carregar os registros em ordem alfabética
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + Banco.tabela() + " ORDER BY nome ASC",
                null);

        // vamos percorrrer o Cursor e atribuir os valores ao nosso ArrayList<Aluno>
        while (cursor.moveToNext()) {
            alunos.add(new Aluno(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3))
            );
        }

        // configurar o adaptador
        adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                alunos);

        // anexar o adaptador na lista
        listViewAlunos.setAdapter(adaptador);

        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) listViewAlunos.getItemAtPosition(position);

                Intent itAluno = new Intent(
                        getApplicationContext(),
                        Detalhes.class);

                itAluno.putExtra("objAluno", aluno);

                startActivity(itAluno);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    // Configura o botão (seta) na ActionBar (Barra Superior)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}