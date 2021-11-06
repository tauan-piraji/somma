package com.tauan.somma.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tauan.somma.database.DBOpenHelper;
import com.tauan.somma.database.model.EntreterimentoModel;
import com.tauan.somma.database.model.GasolinaModel;

import java.util.ArrayList;
import java.util.List;

public class EntreterimentoDAO extends AbstractDAO {

    private final String[]
            colunas = {
            EntreterimentoModel.COLUNA_ID,
            EntreterimentoModel.COLUNA_ID_VIAGEM,
            EntreterimentoModel.COLUNA_VILA,
            EntreterimentoModel.COLUNA_ZOO,
            EntreterimentoModel.COLUNA_IMPREVISTO
    };

    public EntreterimentoDAO(final Context context) {
        helper = new DBOpenHelper(context);
        //chama o helper, e cria o banco de dados pelo DBOpenHelper
    }

    /**
     * Faz o Insert no banco de dados.
     * @param model
     * @return
     */
    public long Insert(EntreterimentoModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(EntreterimentoModel.COLUNA_ID_VIAGEM, model.getIdViagem());
            values.put(EntreterimentoModel.COLUNA_VILA, model.getVila());
            values.put(EntreterimentoModel.COLUNA_ZOO, model.getZoo());
            values.put(EntreterimentoModel.COLUNA_IMPREVISTO, model.getImprevisto());
            values.put(EntreterimentoModel.COLUNA_PRECO_ENTRETERIMENTO, model.getPrecoEntreterimento());

            linhasAfetadas = db.insert(EntreterimentoModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    public int Update(final int id, final Float total) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(EntreterimentoModel.COLUNA_PRECO_ENTRETERIMENTO, total);
            linhasAfetadas = db.update(EntreterimentoModel.TABELA_NOME, values, EntreterimentoModel.COLUNA_ID_VIAGEM + " = ?", new String[]{""+id});
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
        db.delete(EntreterimentoModel.TABELA_NOME, EntreterimentoModel.COLUNA_ID_VIAGEM + " = ?", new String[]{""+id});
        Close();
    }

    /**
     * Executa o SELECT.
     * @param id
     * @return
     */
    public EntreterimentoModel Select(final long id) {

        EntreterimentoModel model = null;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            EntreterimentoModel.TABELA_NOME,
                            colunas,
                            EntreterimentoModel.COLUNA_ID+" = ?",
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
    public List<EntreterimentoModel> Select() {

        List<EntreterimentoModel> lista = new ArrayList<EntreterimentoModel>();

        try {
            Open();
            Cursor cursor = db.query(EntreterimentoModel.TABELA_NOME, colunas, null, null, null, null, null);
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
     * Transforma o cursor da entity.
     * @param cursor
     * @return
     */
    public final EntreterimentoModel CursorToStructure(Cursor cursor) {
        EntreterimentoModel model = new EntreterimentoModel();
        model.setId(cursor.getLong(0));
        model.setIdViagem(cursor.getLong(1));
        model.setVila(cursor.getLong(2));
        model.setZoo(cursor.getLong(3));
        model.setImprevisto(cursor.getLong(3));
        return model;
    }

}