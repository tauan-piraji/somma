package com.tauan.somma.database.model;

public class UserModel {

    public static final String
            TABELA_NOME = "tb_user";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_NAME = "name",
            COLUNA_EMAIL = "email",
            COLUNA_ID_CARRO = "idCarro",
            COLUNA_SENHA = "senha";

    public static final String
            CREATE_TABLE =
            "create table "+TABELA_NOME
                    +"("
                    +   COLUNA_ID + " integer primary key autoincrement, "
                    +   COLUNA_NAME + " text not null, "
                    +   COLUNA_EMAIL + " text not null, "
                    +   COLUNA_ID_CARRO + " long, "
                    +   COLUNA_SENHA + " text not null "
                    +");";

    public static final String
            DROP_TABLE = "drop table if exists "+TABELA_NOME;

    /*=========================================================

    ATRIBUTOS DE MANIPULAÇÃO DO USUÁRIO

    ===========================================================*/

    private long id;
    private String name;
    private String email;
    private long idCarro;
    private String senha;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Long idCarro) {
        this.idCarro = idCarro;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}