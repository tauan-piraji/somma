package com.tauan.somma.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import com.tauan.somma.database.DBOpenHelper;
import com.tauan.somma.database.model.GasolinaModel;
import com.tauan.somma.database.model.HospedagemModel;
import com.tauan.somma.database.model.TarifaAereaModel;
import com.tauan.somma.database.model.ViagemModel;

import java.util.ArrayList;
import java.util.List;

public class GasolinaDAO extends AbstractDAO{

    private final String[]
            colunas = {
            GasolinaModel.COLUNA_ID,
            GasolinaModel.COLUNA_TOTAL_KM,
            GasolinaModel.COLUNA_KM_L,
            GasolinaModel.COLUNA_CUSTO_L,
            GasolinaModel.COLUNA_TOTAL_CAR,
            GasolinaModel.COLUNA_PRECO_GASOLINA
    };

    public GasolinaDAO(final Context context) {
        helper = new DBOpenHelper(context);
        //chama o helper, e cria o banco de dados pelo DBOpenHelper
    }

    /**
     * Faz o Insert no banco de dados.
     * @param model
     * @return
     */
    public long Insert(@NonNull GasolinaModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(GasolinaModel.COLUNA_ID_VIAGEM, model.getIdViagem());
            values.put(GasolinaModel.COLUNA_TOTAL_KM, model.getTotalKm());
            values.put(GasolinaModel.COLUNA_KM_L, model.getKmL());
            values.put(GasolinaModel.COLUNA_CUSTO_L, model.getCustoL());
            values.put(GasolinaModel.COLUNA_TOTAL_CAR, model.getTotalCarros());
            values.put(GasolinaModel.COLUNA_PRECO_GASOLINA, model.getPrecoGasolina());

            linhasAfetadas = db.insert(GasolinaModel.TABELA_NOME, null, values);
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
            values.put(GasolinaModel.COLUNA_PRECO_GASOLINA, total);
            linhasAfetadas = db.update(GasolinaModel.TABELA_NOME, values, GasolinaModel.COLUNA_ID_VIAGEM + " = ?", new String[]{""+id});
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Executa o SELECT buscando pelo usuario.
     * @param id
     * @return
     */
    public GasolinaModel Select(final long id) {

        GasolinaModel model = null;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            GasolinaModel.TABELA_NOME,
                            colunas,
                            GasolinaModel.COLUNA_ID_VIAGEM + " = ?",
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
     * Transforma o cursor em GasolinaModel.
     * @param cursor
     * @return
     */
    public final GasolinaModel CursorToStructure(Cursor cursor) {
        GasolinaModel model = new GasolinaModel();
        model.setId(cursor.getLong(0));
        model.setIdViagem(cursor.getLong(1));
        model.setTotalKm(cursor.getFloat(2));
        model.setKmL(cursor.getFloat(3));
        model.setCustoL(cursor.getFloat(4));
        model.setTotalCarros(cursor.getFloat(5));
        model.setPrecoGasolina(cursor.getFloat(6));

        return model;
    }

}
