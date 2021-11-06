package com.tauan.somma.database.model;

public class HospedagemModel {

    public static final String
            TABELA_NOME = "tb_hospedagem";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_VIAGEM = "idViagem",
            COLUNA_CUSTO = "custoNoite",
            COLUNA_NUMERO_NOITES = "numeroNoites",
            COLUNA_QUANTIDADE_QUARTOS = "quantidadeQuartos",
            COLUNA_PRECO_HOSPEDAGEM = "precoHospedagem";

    public static final String
            CREATE_TABLE =
            "create table " + TABELA_NOME
                    + "("
                    +  COLUNA_ID + " integer primary key autoincrement, "
                    +  COLUNA_ID_VIAGEM + " long not null, "
                    +  COLUNA_CUSTO + " double not null, "
                    +  COLUNA_NUMERO_NOITES + " integer not null, "
                    +  COLUNA_QUANTIDADE_QUARTOS + " integer not null, "
                    +  COLUNA_PRECO_HOSPEDAGEM + " float "
                    + ");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    /*=========================================================

    ATRIBUTOS DE MANIPULAÇÃO DA HOSPEDAGEM

    ===========================================================*/

    private long id;
    private long idViagem;
    private float custoNoite;
    private Integer numeroNoites;
    private Integer quantidadeQuartos;
    private Float precoHospedagem;

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

    public float getCustoNoite() {
        return custoNoite;
    }

    public void setCustoNoite(float custoNoite) {
        this.custoNoite = custoNoite;
    }

    public Integer getNumeroNoites() {
        return numeroNoites;
    }

    public void setNumeroNoites(Integer numeroNoites) {
        this.numeroNoites = numeroNoites;
    }

    public Integer getQuantidadeQuartos() {
        return quantidadeQuartos;
    }

    public void setQuantidadeQuartos(Integer quantidadeQuartos) {
        this.quantidadeQuartos = quantidadeQuartos;
    }

    public Float getPrecoHospedagem() {
        return precoHospedagem;
    }

    public void setPrecoHospedagem(Float precoTarifa) {
        this.precoHospedagem = precoHospedagem;
    }
}