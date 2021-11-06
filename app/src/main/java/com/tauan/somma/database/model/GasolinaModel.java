package com.tauan.somma.database.model;

public class GasolinaModel {

    public static final String
            TABELA_NOME = "tb_gasolina";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_VIAGEM = "idViagem",
            COLUNA_TOTAL_KM = "totalKm",
            COLUNA_KM_L = "kmL",
            COLUNA_CUSTO_L = "custoL",
            COLUNA_TOTAL_CAR = "totalCarros",
            COLUNA_PRECO_GASOLINA = "precoGasolina";

    public static final String
            CREATE_TABLE =
            "create table "+TABELA_NOME
                    +"("
                    +   COLUNA_ID + " integer primary key autoincrement, "
                    +   COLUNA_ID_VIAGEM + " long not null, "
                    +   COLUNA_TOTAL_KM + " float not null, "
                    +   COLUNA_KM_L + " float not null, "
                    +   COLUNA_CUSTO_L + " float not null, "
                    +   COLUNA_TOTAL_CAR + " float not null, "
                    +   COLUNA_PRECO_GASOLINA + " float"
                    +");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    /*=========================================================

    ATRIBUTOS DE MANIPULAÇÃO DA GASOLINA

    ===========================================================*/

    private long id;
    private long idViagem;
    private float totalKm;
    private float kmL;
    private float custoL;
    private float totalCarros;
    private float precoGasolina;

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

    public float getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(float totalKm) {
        this.totalKm = totalKm;
    }

    public float getKmL() {
        return kmL;
    }

    public void setKmL(float kmL) {
        this.kmL = kmL;
    }

    public float getCustoL() {
        return custoL;
    }

    public void setCustoL(float custoL) {
        this.custoL = custoL;
    }

    public float getTotalCarros() {
        return totalCarros;
    }

    public void setTotalCarros(float totalCarros) {
        this.totalCarros = totalCarros;
    }

    public float getPrecoGasolina() {
        return precoGasolina;
    }

    public void setPrecoGasolina(float precoGasolina) {
        this.precoGasolina = precoGasolina;
    }
}