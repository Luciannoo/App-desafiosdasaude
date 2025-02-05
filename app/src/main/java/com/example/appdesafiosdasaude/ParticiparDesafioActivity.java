package com.example.appdesafiosdasaude;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appdesafiosdasaude.DesafioDAO;

public class ParticiparDesafioActivity extends AppCompatActivity {
    private EditText editIdUsuario, editIdDesafio;
    private Button btnParticipar;
    private DesafioDAO desafioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participar_desafio);

        editIdUsuario = findViewById(R.id.editIdUsuario);
        editIdDesafio = findViewById(R.id.editIdDesafio);
        btnParticipar = findViewById(R.id.btnParticipar);

        desafioDAO = new DesafioDAO(this);

        btnParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idUsuario = Integer.parseInt(editIdUsuario.getText().toString());
                int idDesafio = Integer.parseInt(editIdDesafio.getText().toString());

                boolean sucesso = desafioDAO.participarDesafio(idUsuario, idDesafio, "pendente");
                if (sucesso) {
                    Toast.makeText(ParticiparDesafioActivity.this, "Participação registrada!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ParticiparDesafioActivity.this, "Erro ao participar!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}