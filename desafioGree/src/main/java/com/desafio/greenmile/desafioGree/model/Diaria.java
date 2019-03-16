package com.desafio.greenmile.desafioGree.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.desafio.greenmile.desafioGree.View.DiariaView.DadosDiarias;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Diaria implements Serializable {

   private static final long serialVersionUID = 2952630017127173988L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull 
	private LocalDateTime data;
	@ManyToOne
	private Usuario usuario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonView(DadosDiarias.class)
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario=usuario;
	}
	
}
