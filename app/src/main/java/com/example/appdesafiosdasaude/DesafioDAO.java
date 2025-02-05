package com.example.appdesafiosdasaude;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class DesafioDAO {
    private SQLiteDatabase banco;
    private DatabaseHelper dbHelper;

    public DesafioDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean inserirDesafio(String nome, String descricao, int duracao) {
        banco = dbHelper.getWritableDatabase(); // Abre o banco para escrita
        ContentValues valores = new ContentValues();

        valores.put("nome", nome);
        valores.put("descricao", descricao);
        valores.put("duracao", duracao);

        try {
            long resultado = banco.insert("Desafios", null, valores);
            banco.close();
            return resultado != -1; // Se resultado for -1, a inserção falhou
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<String> listarDesafios() {
        List<String> lista = new ArrayList<>();
        banco = dbHelper.getReadableDatabase(); // Abre o banco para leitura

        Cursor cursor = banco.rawQuery("SELECT nome, descricao, duracao FROM Desafios", null);
        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(0);
                String descricao = cursor.getString(1);
                int duracao = cursor.getInt(2);
                lista.add(nome + " - " + descricao + " (" + duracao + " dias)");
            } while (cursor.moveToNext());
        }
        cursor.close();
        banco.close();
        return lista;
    }
    public boolean participarDesafio(int idUsuario, int idDesafio, String status) {
        banco = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id_usuario", idUsuario);
        valores.put("id_desafio", idDesafio);
        valores.put("status", status);

        long resultado = banco.insert("Participacoes", null, valores);
        banco.close();
        return resultado != -1;
    }
    public boolean atualizarDesafio(int id, String nome, String descricao, int duracao) {
        banco = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("descricao", descricao);
        valores.put("duracao", duracao);

        int linhasAfetadas = banco.update("Desafios", valores, "id=?", new String[]{String.valueOf(id)});
        banco.close();
        return linhasAfetadas > 0;
    }

    public void excluirDesafio(int id) {
        banco = dbHelper.getWritableDatabase();
        banco.delete("Desafios", "id=?", new String[]{String.valueOf(id)});
        banco.close();
    }

}