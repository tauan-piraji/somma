package com.tauan.somma.database.model;

public class TarifaAereaModel {

    public static final String
            TABELA_NOME = "tb_tarifa_aerea";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_VIAGEM = "idViagem",
            COLUNA_CUSTO_PESSOA = "custoPessoa",
            COLUNA_ALUGA_CARRO = "alugaCarro",
            COLUNA_PRECO_TARIFA = "precoTarifa";

    public static final String
            CREATE_TABLE =
            "create table "+TABELA_NOME
                    +"("
                    +   COLUNA_ID + " integer primary key autoincrement, "
                    +   COLUNA_ID_VIAGEM + " long not null, "
                    +   COLUNA_CUSTO_PESSOA + " double, "
                    +   COLUNA_ALUGA_CARRO + " double, "
                    +   COLUNA_PRECO_TARIFA + " float"
                    +");";

    public static final String
            DROP_TABLE = "drop table if exists "+TABELA_NOME;

    /*=========================================================

    ATRIBUTOS DE MANIPULAÇÃO DA TARIFA AEREA

    ===========================================================*/

    private long id;
    private long idViagem;
    private float custoPessoa;
    private float alugaCarro;
    private Float precoTarifa;

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

    public float getCustoPessoa() {
        return custoPessoa;
    }

    public void setCustoPessoa(float custoPessoa) {
        this.custoPessoa = custoPessoa;
    }

    public float getAlugaCarro() {
        return alugaCarro;
    }

    public void setAlugaCarro(float alugaCarro) {
        this.alugaCarro = alugaCarro;
    }

    public Float getPrecoTarifa() {
        return precoTarifa;
    }

    public void setPrecoTarifa(Float precoTarifa) {
        this.precoTarifa = precoTarifa;
    }
}
