package com.tauan.somma.database.model;

public class CarModel {

    public static final String
            TABELA_NOME = "tb_car";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_NAME = "name",
            COLUNA_KML = "kml";

    public static final String
            CREATE_TABLE =
            "create table "+TABELA_NOME
                    +"("
                    +   COLUNA_ID + " integer primary key autoincrement, "
                    +   COLUNA_NAME + " text not null, "
                    +   COLUNA_KML + " float not null "
                    +");";

    public static final String
            DROP_TABLE = "drop table if exists "+TABELA_NOME;

    /*=========================================================

    ATRIBUTOS DE MANIPULAÇÃO DO CARRO

    ===========================================================*/

    private long id;
    private String name;
    private float kml;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getKml() {
        return kml;
    }

    public void setKml(float kml) {
        this.kml = kml;
    }
}