package com.desafio.greenmile.desafioGree.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.greenmile.desafioGree.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public Usuario findUsuarioByEmail(String email);
	public Usuario findUsuarioById(Integer id);
	
	
}
