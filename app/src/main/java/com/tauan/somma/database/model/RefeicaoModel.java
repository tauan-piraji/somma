package com.tauan.somma.database.model;

public class RefeicaoModel {

    public static final String
            TABELA_NOME = "tb_refeicao";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_VIAGEM = "idViagem",
            COLUNA_CUSTO = "custoRefeicao",
            COLUNA_QUANTIDADE = "quantidadeRefeicoes",
            COLUNA_PRECO_REFEICAO = "precoRefeicao";

    public static final String
            CREATE_TABLE =
            "create table "+TABELA_NOME
                    +"("
                    +   COLUNA_ID + " integer primary key autoincrement, "
                    +   COLUNA_ID_VIAGEM + " long not null, "
                    +   COLUNA_CUSTO + " double not null, "
                    +   COLUNA_QUANTIDADE + " integer not null, "
                    +   COLUNA_PRECO_REFEICAO + " float"
                    +");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    /*=========================================================

    ATRIBUTOS DE MANIPULAÇÃO DA REFEIÇÂO

    ===========================================================*/

    private long id;
    private long idViagem;
    private float custoRefeicao;
    private Integer quantidadeRefeicoes;
    private float precoRefeicao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCustoRefeicao() {
        return custoRefeicao;
    }

    public long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(long idViagem) {
        this.idViagem = idViagem;
    }

    public void setCustoRefeicao(float custoRefeicao) {
        this.custoRefeicao = custoRefeicao;
    }

    public Integer getQuantidadeRefeicoes() {
        return quantidadeRefeicoes;
    }

    public void setQuantidadeRefeicoes(Integer quantidadeRefeicoes) {
        this.quantidadeRefeicoes = quantidadeRefeicoes;
    }

    public float getPrecoRefeicao() {
        return precoRefeicao;
    }

    public void setPrecoRefeicao(float precoRefeicao) {
        this.precoRefeicao = precoRefeicao;
    }
}