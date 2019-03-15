package com.desafio.greenmile.desafioGree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.greenmile.desafioGree.model.Diaria;
import com.desafio.greenmile.desafioGree.model.Usuario;
import com.desafio.greenmile.desafioGree.service.UsuarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	private ResponseEntity<String> cadastrarUsuario(@RequestBody Usuario user ){
		if (user != null) {
			service.addUsuario(user);
			return new ResponseEntity<String>("",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	  @ApiOperation(
	            value = "Lista todos usuario para o admin",
	            notes = "Este metodo faz a listagem de todos os usuario"
	            )
	@GetMapping("/todos")
	private ResponseEntity<List<Usuario>> listaUsuarios(){
		List<Usuario> usuarios = service.listaTodos();
		return new ResponseEntity<>(usuarios,HttpStatus.OK);
	}

	  
	  @ApiOperation(
	            value = "Adiciona horas ao usuario pelo id",
	            notes = "Este metodo adiciona horas ao usuario"
	            )	
	@PostMapping("/{id}/add")
	private ResponseEntity<String> registrarHoras(@RequestBody Diaria date,@PathVariable Integer id){ 
		if (service.adicionarHoras(id, date)) {	
			return new ResponseEntity<String>("",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("",HttpStatus.EXPECTATION_FAILED);
		
	}
		  
	@ApiOperation(
	            value = "Lista todos horarios de um usuari pelo id",
	            notes = "Este metodo faz a listagem de os horarios de um detremindo usuario"
	            )
	@GetMapping("/{id}/horas")
	private ResponseEntity<List<Diaria>> ListaHorasUsuario(@PathVariable Integer id){
	
		List<Diaria> diarias = service.getDiariasById(id); 
		return new ResponseEntity<List<Diaria>>(diarias,HttpStatus.OK);
	
	}

}