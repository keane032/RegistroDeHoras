package com.desafio.greenmile.desafioGree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.greenmile.desafioGree.model.Diaria;

@Repository
public interface DiariaRepository extends JpaRepository<Diaria, Integer>{
	
}
