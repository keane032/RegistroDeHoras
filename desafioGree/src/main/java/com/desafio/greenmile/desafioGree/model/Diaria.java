package com.desafio.greenmile.desafioGree.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.desafio.greenmile.desafioGree.View.DiariaView.DadosDiarias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Diaria implements Serializable {

   private static final long serialVersionUID = 2952630017127173988L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	@NotNull
	private String horas;
	@ManyToOne
	private Usuario usuario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonView(DadosDiarias.class)
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario=usuario;
	}
	@JsonView(DadosDiarias.class)
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horas) {
		this.horas = horas;
	}
}
