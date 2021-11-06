package com.tauan.somma.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Sharad {

    public static final String KEY_NUMERO_PESSOAS = "KEY_NUMERO_PESSOAS";
    public static final String KEY_DIAS = "KEY_DIAS";
    public static final String KEY_ID_USER = "KEY_ID_USER";
    public static final String KEY_USER = "KEY_USER";
    public static final String KEY_SENHA = "KEY_SENHA";
    public static final String KEY_ID_VIAGEM = "KEY_ID_VIAGEM";
    public static final String KEY_CARRO = "KEY_CARRO";
    public static final String KEY_DESTINO = "KEY_DESTINO";
    public static final String KEY_GASOLINA_TOTAL = "KEY_GASOLINA_TOTAL";
    public static final String KEY_REFEICAO_TOTAL = "KEY_REFEICAO_TOTAL";
    public static final String KEY_TARIFA_TOTAL = "KEY_TARIFA_TOTAL";
    public static final String KEY_ENTRETERIMENTO_TOTAL = "KEY_ENTRETERIMENTO_TOTAL";
    public static final String KEY_HOSPEDAGEM_TOTAL = "KEY_HOSPEDAGEM_TOTAL";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public Sharad(final Context activity) {
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
    }

    public final void put(final String key, final Object value) {

        if (value instanceof String) {
            editor.putString(key, (String)value);
        }else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean)value);
        }else if (value instanceof Long) {
            editor.putLong(key, (Long)value);
        }else if (value instanceof Integer) {
            editor.putInt(key, (Integer)value);
        }else if (value instanceof Float) {
            editor.putFloat(key, (Float)value);
        }else{
            //Toast.makeText(activity, "tipo de dado inválido", Toast.LENGTH_LONG).show();
        }

        editor.apply();
    }

    public final String getString(final String key) {
        return preferences.getString(key, "");
    }

    public final int getInt(final String key) {
        return preferences.getInt(key, 0);
    }

    public final long getLong(final String key) {
        return preferences.getLong(key, 0);
    }

    public final float getFloat(final String key) {
        return preferences.getFloat(key, 0);
    }

    public final void remove(final String key) {
        editor.remove(key);
        editor.apply();
    }

}