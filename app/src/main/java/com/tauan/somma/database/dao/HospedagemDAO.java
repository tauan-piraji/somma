package com.tauan.somma.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tauan.somma.database.DBOpenHelper;
import com.tauan.somma.database.model.HospedagemModel;

import java.util.ArrayList;
import java.util.List;

public class HospedagemDAO extends AbstractDAO {

    private final String[]
            colunas = {
            HospedagemModel.COLUNA_ID,
            HospedagemModel.COLUNA_ID_VIAGEM,
            HospedagemModel.COLUNA_CUSTO,
            HospedagemModel.COLUNA_NUMERO_NOITES,
            HospedagemModel.COLUNA_QUANTIDADE_QUARTOS,
            HospedagemModel.COLUNA_PRECO_HOSPEDAGEM
    };

    public HospedagemDAO(final Context context) {
        helper = new DBOpenHelper(context);
        //chama o helper, e cria o banco de dados pelo DBOpenHelper
    }

    /**
     * Faz o Insert no banco de dados.
     * @param model
     * @return
     */
    public long Insert(HospedagemModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(HospedagemModel.COLUNA_ID_VIAGEM, model.getIdViagem());
            values.put(HospedagemModel.COLUNA_CUSTO, model.getCustoNoite());
            values.put(HospedagemModel.COLUNA_NUMERO_NOITES, model.getNumeroNoites());
            values.put(HospedagemModel.COLUNA_QUANTIDADE_QUARTOS, model.getQuantidadeQuartos());
            values.put(HospedagemModel.COLUNA_PRECO_HOSPEDAGEM, model.getPrecoHospedagem());

            linhasAfetadas = db.insert(HospedagemModel.TABELA_NOME, null, values);
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
        db.delete(HospedagemModel.TABELA_NOME, HospedagemModel.COLUNA_ID_VIAGEM + " = ?", new String[]{""+id});
        Close();
    }

    public int Update(final int id, final Float total) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(HospedagemModel.COLUNA_PRECO_HOSPEDAGEM, total);
            linhasAfetadas = db.update(HospedagemModel.TABELA_NOME, values, HospedagemModel.COLUNA_ID_VIAGEM + " = ?", new String[]{""+id});
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
    public HospedagemModel Select(final long id) {

        HospedagemModel model = null;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            HospedagemModel.TABELA_NOME,
                            colunas,
                            HospedagemModel.COLUNA_ID_VIAGEM+" = ?",
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
    public List<HospedagemModel> Select() {

        List<HospedagemModel> lista = new ArrayList<HospedagemModel>();

        try {
            Open();
            Cursor cursor = db.query(HospedagemModel.TABELA_NOME, colunas, null, null, null, null, null);
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
    public final HospedagemModel CursorToStructure(Cursor cursor) {
        HospedagemModel model = new HospedagemModel();
        model.setId(cursor.getLong(0));
        model.setIdViagem(cursor.getLong(1));
        model.setCustoNoite(cursor.getFloat(2));
        model.setNumeroNoites(cursor.getInt(3));
        model.setNumeroNoites(cursor.getInt(4));
        model.setPrecoHospedagem(cursor.getFloat(5));
        return model;
    }

}