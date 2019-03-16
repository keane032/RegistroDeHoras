package com.desafio.greenmile.desafioGree.model;

import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority{
    FUNCIONARIO("Funcionario"), SUPERVISOR("Supervisor");
    private String descricao;

    Papel(String valor) {
        descricao = valor;
    }

    public String getDescricao() {
        return descricao;
    }

	@Override
	public String getAuthority() {
		return this.name();
	}

   
}
