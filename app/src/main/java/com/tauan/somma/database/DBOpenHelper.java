package com.tauan.somma.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tauan.somma.database.model.CarModel;
import com.tauan.somma.database.model.EntreterimentoModel;
import com.tauan.somma.database.model.GasolinaModel;
import com.tauan.somma.database.model.HospedagemModel;
import com.tauan.somma.database.model.RefeicaoModel;
import com.tauan.somma.database.model.TarifaAereaModel;
import com.tauan.somma.database.model.UserModel;
import com.tauan.somma.database.model.ViagemModel;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NOME = "bancosomma.db";

    public static final int DATABASE_VERSAO = 1;

    public DBOpenHelper (final Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CarModel.CREATE_TABLE);
        db.execSQL(EntreterimentoModel.CREATE_TABLE);
        db.execSQL(GasolinaModel.CREATE_TABLE);
        db.execSQL(HospedagemModel.CREATE_TABLE);
        db.execSQL(RefeicaoModel.CREATE_TABLE);
        db.execSQL(TarifaAereaModel.CREATE_TABLE);
        db.execSQL(ViagemModel.CREATE_TABLE);
        db.execSQL(UserModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CarModel.DROP_TABLE);
        db.execSQL(EntreterimentoModel.DROP_TABLE);
        db.execSQL(GasolinaModel.DROP_TABLE);
        db.execSQL(HospedagemModel.DROP_TABLE);
        db.execSQL(RefeicaoModel.DROP_TABLE);
        db.execSQL(TarifaAereaModel.DROP_TABLE);
        db.execSQL(ViagemModel.DROP_TABLE);
        db.execSQL(UserModel.DROP_TABLE);
        db.execSQL(CarModel.CREATE_TABLE);
        db.execSQL(EntreterimentoModel.CREATE_TABLE);
        db.execSQL(GasolinaModel.CREATE_TABLE);
        db.execSQL(HospedagemModel.CREATE_TABLE);
        db.execSQL(RefeicaoModel.CREATE_TABLE);
        db.execSQL(TarifaAereaModel.CREATE_TABLE);
        db.execSQL(ViagemModel.CREATE_TABLE);
        db.execSQL(UserModel.CREATE_TABLE);
    }

}