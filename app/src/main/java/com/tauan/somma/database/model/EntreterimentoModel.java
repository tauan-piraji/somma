package com.tauan.somma.database.model;

import java.math.BigDecimal;

public class EntreterimentoModel {

    public static final String
            TABELA_NOME = "tb_entreterimento";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_VIAGEM = "idViagem",
            COLUNA_VILA = "vila",
            COLUNA_ZOO = "zoo",
            COLUNA_IMPREVISTO = "imprevisto",
            COLUNA_PRECO_ENTRETERIMENTO = "precoEntreterimento";

    public static final String
            CREATE_TABLE =
            "create table "+TABELA_NOME
                    +"("
                    +   COLUNA_ID + " integer primary key autoincrement, "
                    +   COLUNA_ID_VIAGEM + " long not null, "
                    +   COLUNA_VILA + " float, "
                    +   COLUNA_ZOO + " float, "
                    +   COLUNA_IMPREVISTO + " float, "
                    +   COLUNA_PRECO_ENTRETERIMENTO + " float"
                    +");";

    public static final String
            DROP_TABLE = "drop table if exists "+TABELA_NOME;

    /*=========================================================

    ATRIBUTOS DE MANIPULAÇÃO DO ENTRETERIMENTO

    ===========================================================*/

    private long id;
    private long idViagem;
    private float vila;
    private float zoo;
    private float imprevisto;
    private float precoEntreterimento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(long idViagem) {
        this.idViagem = idViagem;
    }

    public float getVila() {
        return vila;
    }

    public void setVila(float vila) {
        this.vila = vila;
    }

    public float getZoo() {
        return zoo;
    }

    public void setZoo(float zoo) {
        this.zoo = zoo;
    }

    public float getImprevisto() {
        return imprevisto;
    }

    public void setImprevisto(float imprevisto) {
        this.imprevisto = imprevisto;
    }

    public float getPrecoEntreterimento() {
        return precoEntreterimento;
    }

    public void setPrecoEntreterimento(float precoEntreterimento) {
        this.precoEntreterimento = precoEntreterimento;
    }
}
