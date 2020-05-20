package com.ems.bdsqlite.crud;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlite.MainActivity;
import com.ems.bdsqlite.R;
import com.ems.bdsqlite.pojo.Aluno;
import com.ems.bdsqlite.utils.Banco;

public class Excluir extends AppCompatActivity {

    TextView id, ra, nome, curso;
    ImageButton btConfirmaExcusao, btVoltar;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Exclusão de Aluno");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = findViewById(R.id.textViewId_excluir);
        ra = findViewById(R.id.textViewRa_excluir);
        nome = findViewById(R.id.textViewNome_excluir);
        curso = findViewById(R.id.textViewCurso_excluir);
        btConfirmaExcusao = findViewById(R.id.btExcluir);
        btVoltar = findViewById(R.id.btVoltar);

        // recuperar os dados do objeto Aluno
        Intent itAluno = getIntent();
        final Aluno aluno = (Aluno) itAluno.getExtras().
                getSerializable("objAluno");

        id.setText(String.valueOf(aluno.getId()));
        ra.setText(aluno.getRa());
        nome.setText(aluno.getNome());
        curso.setText(aluno.getCurso());

        btConfirmaExcusao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openOrCreateDatabase(
                        Banco.banco(),
                        Context.MODE_PRIVATE,
                        null);

                db.execSQL("DELETE FROM " + Banco.tabela() +
                        " WHERE id=" + aluno.getId());

                Toast.makeText(Excluir.this, "Registro excluído com sucesso", Toast.LENGTH_LONG).show();
                Intent itMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(itMain);
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