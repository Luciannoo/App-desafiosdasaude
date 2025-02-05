package com.example.appdesafiosdasaude;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import com.example.appdesafiosdasaude.DesafioDAO;

public class ListaDesafiosActivity extends AppCompatActivity {
    private ListView listViewDesafios;
    private DesafioDAO desafioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_desafios);

        listViewDesafios = findViewById(R.id.listViewDesafios);
        desafioDAO = new DesafioDAO(this);

        List<String> listaDesafios = desafioDAO.listarDesafios();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDesafios);
        listViewDesafios.setAdapter(adapter);
    }
}