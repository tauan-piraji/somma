package com.tauan.somma.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import com.tauan.somma.database.DBOpenHelper;
import com.tauan.somma.database.model.ViagemModel;

import java.util.ArrayList;
import java.util.List;

public class ViagemDAO extends AbstractDAO {

    private final String[]
            colunas = {
            ViagemModel.COLUNA_ID,
            ViagemModel.COLUNA_DESTINO,
            ViagemModel.COLUNA_VALOR_TOTAL,
            ViagemModel.COLUNA_NOTA,
            ViagemModel.COLUNA_TOTAL_PESSOAS,
            ViagemModel.COLUNA_DIAS_VIAGEM,
            ViagemModel.COLUNA_ID_USER
    };

    public ViagemDAO(final Context context) {
        helper = new DBOpenHelper(context);
        //chama o helper, e cria o banco de dados pelo DBOpenHelper
    }

    /**
     * Faz o Insert no banco de dados.
     * @param model
     * @return
     */
    public long Insert(@NonNull ViagemModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(ViagemModel.COLUNA_DESTINO, model.getDestino());
            values.put(ViagemModel.COLUNA_ID_USER, model.getIdUser());
            values.put(ViagemModel.COLUNA_NOTA, "0");
            values.put(ViagemModel.COLUNA_VALOR_TOTAL, "0");
            values.put(ViagemModel.COLUNA_TOTAL_PESSOAS, model.getTotalPessoas());
            values.put(ViagemModel.COLUNA_DIAS_VIAGEM, model.getTotalDias());
            linhasAfetadas = db.insert(ViagemModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Deleta um destino do banco de dados.
     * @param destino
     */
    public void Delete(final String destino) {
        Open();
        db.delete(ViagemModel.TABELA_NOME, ViagemModel.COLUNA_DESTINO + " = ?", new String[]{destino});
        Close();
    }

    /**
     * coloca valorTotal em um destino.
     * @param destino
     * @param valor
     */
    public int UpdateValor(final String destino, final double valor) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(ViagemModel.COLUNA_VALOR_TOTAL, valor);
            linhasAfetadas = db.update(ViagemModel.TABELA_NOME, values, ViagemModel.COLUNA_DESTINO + " = ?", new String[]{destino});
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * coloca nota em um destino.
     * @param destino
     * @param nota
     */
    public int UpdateNota(final String destino, final double nota) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(ViagemModel.COLUNA_NOTA, nota);
            linhasAfetadas = db.update(ViagemModel.TABELA_NOME, values, ViagemModel.COLUNA_DESTINO + " = ?", new String[]{destino});
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Executa o SELECT buscando pelo usuario.
     * @param destino
     * @return
     */
    public ViagemModel Select(final String destino) {

        ViagemModel model = null;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            ViagemModel.TABELA_NOME,
                            colunas,
                            ViagemModel.COLUNA_DESTINO+" = ?",
                            new String[]{destino},
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
    public List<ViagemModel> SelectList(final long idUser) {

        List<ViagemModel> lista = new ArrayList<ViagemModel>();

        try {
            Open();
            Cursor cursor = db.query(ViagemModel.TABELA_NOME,
                    colunas,
                    ViagemModel.COLUNA_ID_USER + " = ?",
                    new String[]{""+idUser},
                    null,
                    null,
                    null,
                    null);
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
     * Transforma o cursor em viagemModel.
     * @param cursor
     * @return
     */
    public final ViagemModel CursorToStructure(Cursor cursor) {
        ViagemModel model = new ViagemModel();
        model.setId(cursor.getLong(0));
        model.setDestino(cursor.getString(1));
        model.setValorTotal(cursor.getFloat(2));
        model.setNota(cursor.getFloat(3));
        model.setTotalPessoas(cursor.getFloat(4));
        model.setTotalDias(cursor.getFloat(5));
        model.setIdUser(cursor.getLong(6));
        return model;
    }

}