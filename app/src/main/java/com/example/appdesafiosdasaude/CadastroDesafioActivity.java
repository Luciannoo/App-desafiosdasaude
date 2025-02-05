package com.example.appdesafiosdasaude;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdesafiosdasaude.DesafioDAO;

public class CadastroDesafioActivity extends AppCompatActivity {
    private EditText editNome, editDescricao, editDuracao;
    private Button btnSalvar;
    private DesafioDAO desafioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_desafio);

        editNome = findViewById(R.id.editNome);
        editDescricao = findViewById(R.id.editDescricao);
        editDuracao = findViewById(R.id.editDuracao);
        btnSalvar = findViewById(R.id.btnSalvar);

        desafioDAO = new DesafioDAO(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString();
                String descricao = editDescricao.getText().toString();
                String duracaoStr = editDuracao.getText().toString();

                if (nome.isEmpty() || duracaoStr.isEmpty()) {
                    Toast.makeText(CadastroDesafioActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int duracao = Integer.parseInt(duracaoStr); // Converte para número

                boolean sucesso = desafioDAO.inserirDesafio(nome, descricao, duracao);
                if (sucesso) {
                    Toast.makeText(CadastroDesafioActivity.this, "Desafio cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    editNome.setText(""); // Limpa os campos após o cadastro
                    editDescricao.setText("");
                    editDuracao.setText("");
                } else {
                    Toast.makeText(CadastroDesafioActivity.this, "Erro ao cadastrar desafio!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}