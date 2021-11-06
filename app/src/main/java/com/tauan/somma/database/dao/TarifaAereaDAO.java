package com.tauan.somma.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tauan.somma.database.DBOpenHelper;
import com.tauan.somma.database.model.TarifaAereaModel;

import java.util.ArrayList;
import java.util.List;

public class TarifaAereaDAO extends AbstractDAO{

    private final String[]
    colunas = {
            TarifaAereaModel.COLUNA_ID,
            TarifaAereaModel.COLUNA_ID_VIAGEM,
            TarifaAereaModel.COLUNA_CUSTO_PESSOA,
            TarifaAereaModel.COLUNA_ALUGA_CARRO,
            TarifaAereaModel.COLUNA_PRECO_TARIFA
    };

    public TarifaAereaDAO(final Context context) {
        helper = new DBOpenHelper(context);
        //chama o helper, e cria o banco de dados pelo DBOpenHelper
    }

    /**
     * Faz o Insert no banco de dados.
     * @param model
     * @return
     */
    public long Insert(TarifaAereaModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(TarifaAereaModel.COLUNA_ID_VIAGEM, model.getIdViagem());
            values.put(TarifaAereaModel.COLUNA_CUSTO_PESSOA, model.getCustoPessoa());
            values.put(TarifaAereaModel.COLUNA_ALUGA_CARRO, model.getAlugaCarro());
            values.put(TarifaAereaModel.COLUNA_PRECO_TARIFA, model.getPrecoTarifa());

            linhasAfetadas = db.insert(TarifaAereaModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Deleta um usuário do banco de dados.
     * @param id
     */
    public void Delete(final long id) {
        Open();
        db.delete(TarifaAereaModel.TABELA_NOME, TarifaAereaModel.COLUNA_ID + " = ?", new String[]{""+id});
        Close();
    }

    public int Update(final int id, final Float total) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(TarifaAereaModel.COLUNA_PRECO_TARIFA, total);
            linhasAfetadas = db.update(TarifaAereaModel.TABELA_NOME, values, TarifaAereaModel.COLUNA_ID_VIAGEM + " = ?", new String[]{""+id});
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Executa o SELECT buscando pelo usuario e senha.
     * @param id
     * @return
     * n sei se preciso do select ainda !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public TarifaAereaModel Select(final long id) {

        TarifaAereaModel model = null;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            TarifaAereaModel.TABELA_NOME,
                            colunas,
                            TarifaAereaModel.COLUNA_ID_VIAGEM +" = ?",
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
     * Executa o SELECT no banco de dados trazendo todos os usuários.
     * @return
     */
    public List<TarifaAereaModel> Select() {

        List<TarifaAereaModel> lista = new ArrayList<TarifaAereaModel>();

        try {
            Open();
            Cursor cursor = db.query(TarifaAereaModel.TABELA_NOME, colunas, null, null, null, null, null);
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
    public final TarifaAereaModel CursorToStructure(Cursor cursor) {
        TarifaAereaModel model = new TarifaAereaModel();
        model.setId(cursor.getLong(0));
        model.setIdViagem(cursor.getLong(1));
        model.setCustoPessoa(cursor.getLong(2));
        model.setAlugaCarro(cursor.getLong(3));
        model.setPrecoTarifa(cursor.getFloat(4));
        return model;
    }

}