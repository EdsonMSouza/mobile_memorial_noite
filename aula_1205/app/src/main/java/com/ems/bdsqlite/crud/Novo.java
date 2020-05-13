package com.ems.bdsqlite.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlite.R;
import com.ems.bdsqlite.pojo.Aluno;
import com.ems.bdsqlite.utils.Banco;
import com.ems.bdsqlite.utils.Mensagem;

public class Novo extends AppCompatActivity {
    EditText ra, nome, curso;
    ImageButton btConfirma, btVoltar;

    // declarando um variável para acessar o banco de dados
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo);

        // abertura e/ou criação do Banco de Dados
        db = openOrCreateDatabase(
                Banco.banco(),
                Context.MODE_PRIVATE,
                null);

        // Cria a tabela caso não exista
        db.execSQL(Banco.criaTabela());

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Incluir Aluno");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ra = findViewById(R.id.editTextRa);
        nome = findViewById(R.id.editTextNome);
        curso = findViewById(R.id.editTextCurso);
        btConfirma = findViewById(R.id.btConfirma);
        btVoltar = findViewById(R.id.btVoltar);

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cria um objeto Aluno para receber os dados dos campos
                Aluno aluno = new Aluno();
                aluno.setRa(ra.getText().toString());
                aluno.setNome(nome.getText().toString());
                aluno.setCurso(curso.getText().toString());

                // Criar um container para informar ao banco os campos/dados
                ContentValues values = new ContentValues();
                values.put("ra", aluno.getRa());
                values.put("nome", aluno.getNome());
                values.put("curso", aluno.getCurso());

                // insere os dados na tabela
                db.insert(
                        Banco.tabela(),
                        null,
                        values);

                // mostrando uma mensagem personalizada
                Mensagem mensagem = new Mensagem(Novo.this);
                mensagem.show(
                        "Dados Incluídos com Sucesso!",
                        aluno.getDados(),
                        R.drawable.ic_add);

                // se não funcionar a mensagem acima, descomente
                // a linha abaixo e comente as linhas 73 até 77
                //finish();
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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