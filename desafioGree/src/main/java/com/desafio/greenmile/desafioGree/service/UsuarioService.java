package com.desafio.greenmile.desafioGree.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.greenmile.desafioGree.model.Diaria;
import com.desafio.greenmile.desafioGree.model.Usuario;
import com.desafio.greenmile.desafioGree.repository.DiariaRepository;
import com.desafio.greenmile.desafioGree.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository uRepo;
	
	@Autowired
	private DiariaRepository diariaRepository;
	
	public boolean addUsuario(Usuario usuario) {
		if (usuario != null) {
			uRepo.save(usuario);
			return true;
		}
		return false;
	}
	
	public List<Usuario> listaTodos() {
		return uRepo.findAll();
	}
	
	public List<Diaria> getDiariasById(Integer id){ 
		Usuario usuario = uRepo.findUsuarioById(id);
		
			return usuario.getDiarias();
		
	}
	
	public boolean adicionarHoras(Integer id, Diaria date) {
		Usuario usuario = uRepo.getOne(id);
		if(usuario != null) {
			date.setUsuario(usuario);
			usuario.addDiaria(date);
			diariaRepository.save(date);
			return true;
		}
		return false;
	}
}
