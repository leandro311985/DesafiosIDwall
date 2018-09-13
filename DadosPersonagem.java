package com.cotacao.leandro.testeapp;

public class DadosPersonagem {
    private int icone;
    private String titulo;
    private String descricao;

    public DadosPersonagem(int icone, String titulo, String descricao) {
        this.icone = icone;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getIcone() {
        return icone;
    }

    public String getNome() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}

