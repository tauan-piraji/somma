package com.tauan.somma.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tauan.somma.database.DBOpenHelper;
import com.tauan.somma.database.model.RefeicaoModel;

import java.util.ArrayList;
import java.util.List;

public class RefeicaoDAO extends AbstractDAO {

    private final String[]
            colunas = {
            RefeicaoModel.COLUNA_ID,
            RefeicaoModel.COLUNA_ID_VIAGEM,
            RefeicaoModel.COLUNA_CUSTO,
            RefeicaoModel.COLUNA_QUANTIDADE,
            RefeicaoModel.COLUNA_PRECO_REFEICAO
    };

    public RefeicaoDAO(final Context context) {
        helper = new DBOpenHelper(context);
        //chama o helper, e cria o banco de dados pelo DBOpenHelper
    }

    /**
     * Faz o Insert no banco de dados.
     * @param model
     * @return
     */
    public long Insert(RefeicaoModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(RefeicaoModel.COLUNA_ID_VIAGEM, model.getIdViagem());
            values.put(RefeicaoModel.COLUNA_CUSTO, model.getCustoRefeicao());
            values.put(RefeicaoModel.COLUNA_QUANTIDADE, model.getQuantidadeRefeicoes());
            values.put(RefeicaoModel.COLUNA_PRECO_REFEICAO, model.getPrecoRefeicao());
            linhasAfetadas = db.insert(RefeicaoModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Deleta uma entity do banco de dados.
     * @param id
     */
    public void Delete(final long id) {
        Open();
        db.delete(RefeicaoModel.TABELA_NOME, RefeicaoModel.COLUNA_ID + " = ?", new String[]{""+id});
        Close();
    }

    public int Update(final int id, final Float total) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(RefeicaoModel.COLUNA_PRECO_REFEICAO, total);
            linhasAfetadas = db.update(RefeicaoModel.TABELA_NOME, values, RefeicaoModel.COLUNA_ID_VIAGEM + " = ?", new String[]{""+id});
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Executa o SELECT.
     * @param id
     * @return
     */
    public RefeicaoModel Select(final long id) {

        RefeicaoModel model = null;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            RefeicaoModel.TABELA_NOME,
                            colunas,
                            RefeicaoModel.COLUNA_ID_VIAGEM+" = ?",
                            new String[]{""+id},
                            null,
                            null,
                            null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                model = CursorToStructure(cursor);
                break;
            }
        }
        finally {
            Close();
        }

        return model;
    }

    /**
     * Executa o SELECT no banco de dados trazendo todos as entitys.
     * @return
     */
    public List<RefeicaoModel> Select() {

        List<RefeicaoModel> lista = new ArrayList<RefeicaoModel>();

        try {
            Open();
            Cursor cursor = db.query(RefeicaoModel.TABELA_NOME, colunas, null, null, null, null, null);
            cursor.moveToFirst(); //vai na primeira linha do cursor
            while (!cursor.isAfterLast()) {
                lista.add(CursorToStructure(cursor));
                cursor.moveToNext();
            }
        }
        finally {
            Close();
        }
        return lista;
    }

    /**
     * Transforma o cursor em UsuarioModel.
     * @param cursor
     * @return
     */
    public final RefeicaoModel CursorToStructure(Cursor cursor) {
        RefeicaoModel model = new RefeicaoModel();
        model.setId(cursor.getLong(0));
        model.setIdViagem(cursor.getLong(1));
        model.setCustoRefeicao(cursor.getFloat(2));
        model.setQuantidadeRefeicoes(cursor.getInt(3));
        model.setCustoRefeicao(cursor.getFloat(4));
        return model;
    }

}