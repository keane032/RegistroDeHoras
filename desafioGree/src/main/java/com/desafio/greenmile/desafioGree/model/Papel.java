package com.desafio.greenmile.desafioGree.model;

public enum Papel{
    FUNCIONARIO("Funcionario"), SUPERVISOR("Supervisor");
    private String descricao;

    Papel(String valor) {
        descricao = valor;
    }

    public String getDescricao() {
        return descricao;
    }

   
}
