package com.tauan.somma.database.model;

public class ViagemModel {

    public static final String
            TABELA_NOME = "tb_viagem";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_DESTINO = "destino",
            COLUNA_VALOR_TOTAL = "valorTotal",
            COLUNA_NOTA = "nota",
            COLUNA_TOTAL_PESSOAS = "totalPessoas",
            COLUNA_DIAS_VIAGEM = "totalDias",
            COLUNA_ID_USER = "idUser";

    public static final String
            CREATE_TABLE =
            "create table "+TABELA_NOME
                    +"("
                    +   COLUNA_ID + " integer primary key autoincrement, "
                    +   COLUNA_DESTINO + " text not null, "
                    +   COLUNA_VALOR_TOTAL + " float, "
                    +   COLUNA_NOTA + " float, "
                    +   COLUNA_TOTAL_PESSOAS + " float, "
                    +   COLUNA_DIAS_VIAGEM + " float, "
                    +   COLUNA_ID_USER + " long not null "
                    +");";

    public static final String
            DROP_TABLE = "drop table if exists "+TABELA_NOME;

    /*=========================================================

    ATRIBUTOS DE MANIPULAÇÃO DA VIAGEM

    ===========================================================*/

    private long id;
    private String destino;
    private float valorTotal;
    private float nota;
    private float totalPessoas;
    private float totalDias;
    private long idUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public float getTotalPessoas() {
        return totalPessoas;
    }

    public void setTotalPessoas(float totalPessoas) {
        this.totalPessoas = totalPessoas;
    }

    public float getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(float totalDias) {
        this.totalDias = totalDias;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}