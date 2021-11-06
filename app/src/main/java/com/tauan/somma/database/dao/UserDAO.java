package com.tauan.somma.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tauan.somma.database.DBOpenHelper;
import com.tauan.somma.database.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO{

    private final String[]
            colunas = {
            UserModel.COLUNA_ID,
            UserModel.COLUNA_NAME,
            UserModel.COLUNA_EMAIL,
            UserModel.COLUNA_ID_CARRO,
            UserModel.COLUNA_SENHA
    };

    public UserDAO(final Context context) {
        helper = new DBOpenHelper(context);
        //chama o helper, e cria o banco de dados pelo DBOpenHelper
    }

    /**
     * Faz o Insert no banco de dados.
     * @param model
     * @return
     */
    public long Insert(UserModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(UserModel.COLUNA_NAME, model.getName());
            values.put(UserModel.COLUNA_EMAIL, model.getEmail());
            values.put(UserModel.COLUNA_ID_CARRO, 0);
            values.put(UserModel.COLUNA_SENHA, model.getSenha());
            linhasAfetadas = db.insert(UserModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Deleta uma entity do banco de dados.
     * @param name
     */
    public void Delete(final String name) {
        Open();
        db.delete(UserModel.TABELA_NOME, UserModel.COLUNA_NAME + " = ?", new String[]{name});
        Close();
    }

    public int Update(final String name, final String email, final  long idCarro, final String senha) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(UserModel.COLUNA_NAME, name);
            values.put(UserModel.COLUNA_EMAIL, email);
            values.put(UserModel.COLUNA_ID_CARRO, idCarro);
            values.put(UserModel.COLUNA_SENHA, senha);
            linhasAfetadas = db.update(UserModel.TABELA_NOME, values, UserModel.COLUNA_NAME + " = ?", new String[]{name});
        }
        finally {
            Close();
        }

        return linhasAfetadas;
    }

    /**
     * Executa o SELECT buscando pelo nome.
     * @param name
     * @param senha
     * @return
     */
    public UserModel Select(final String name, final String senha) {

        UserModel model = null;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            UserModel.TABELA_NOME,
                            colunas,
                            UserModel.COLUNA_NAME + " = ? and "+UserModel.COLUNA_SENHA+" = ?",
                            new String[]{name, senha},
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
     * Executa o SELECT no banco de dados trazendo todas as entitys.
     * @return
     */
    public List<UserModel> SelectAll() {

        List<UserModel> lista = new ArrayList<UserModel>();

        try {
            Open();
            Cursor cursor = db.query(UserModel.TABELA_NOME, colunas, null, null, null, null, null);
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
     * Transforma o cursor em UserModel.
     * @param cursor
     * @return
     */
    public final UserModel CursorToStructure(Cursor cursor) {
        UserModel model = new UserModel();
        model.setId(cursor.getLong(0));
        model.setName(cursor.getString(1));
        model.setEmail(cursor.getString(2));
        model.setIdCarro(cursor.getLong(3));
        model.setSenha(cursor.getString(4));
        return model;
    }
}