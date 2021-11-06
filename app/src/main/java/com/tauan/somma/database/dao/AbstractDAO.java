package com.tauan.somma.database.dao;

import android.database.sqlite.SQLiteDatabase;

import com.tauan.somma.database.DBOpenHelper;

public abstract class AbstractDAO {

    protected SQLiteDatabase db;
    protected DBOpenHelper helper;

    protected final void Open() {
        db = helper.getWritableDatabase();
    }

    protected final void Close() {
        helper.close();
    }
}