package com.tauan.somma.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tauan.somma.database.DBOpenHelper;
import com.tauan.somma.database.model.CarModel;

import java.util.ArrayList;
import java.util.List;

public class CarDAO extends AbstractDAO{

    private final String[]
            colunas = {
            CarModel.COLUNA_ID,
            CarModel.COLUNA_NAME,
            CarModel.COLUNA_KML
    };

    public CarDAO(final Context context) {
        helper = new DBOpenHelper(context);
        //chama o helper, e cria o banco de dados pelo DBOpenHelper
    }

    /**
     * Faz o Insert no banco de dados.
     * @param model
     * @return
     */
    public long Insert(CarModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(CarModel.COLUNA_NAME, model.getName());
            values.put(CarModel.COLUNA_KML, model.getKml());
            linhasAfetadas = db.insert(CarModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Deleta um carro do banco de dados.
     * @param name
     */
    public void Delete(final String name) {
        Open();
        db.delete(CarModel.TABELA_NOME, CarModel.COLUNA_NAME + " = ?", new String[]{name});
        Close();
    }

    public int Update(final String name, final Integer kml) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(CarModel.COLUNA_NAME, name);
            values.put(CarModel.COLUNA_KML, kml);
            linhasAfetadas = db.update(CarModel.TABELA_NOME, values, CarModel.COLUNA_NAME + " = ?", new String[]{name});
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Executa o SELECT buscando pelo nome e Km/L.
     * @param name
     * @return
     */
    public CarModel SelectByName(final String name) {

        CarModel model = null;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            CarModel.TABELA_NOME,
                            colunas,
                            CarModel.COLUNA_NAME + " = ?",
                            new String[]{name},
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
    public List<CarModel> SelectAll() {

        List<CarModel> lista = new ArrayList<CarModel>();

        try {
            Open();
            Cursor cursor = db.query(CarModel.TABELA_NOME, colunas, null, null, null, null, null);
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
     * Transforma o cursor em CarModel.
     * @param cursor
     * @return
     */
    public final CarModel CursorToStructure(Cursor cursor) {
        CarModel model = new CarModel();
        model.setId(cursor.getLong(0));
        model.setName(cursor.getString(1));
        model.setKml(cursor.getInt(2));
        return model;
    }
}