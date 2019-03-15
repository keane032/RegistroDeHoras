package com.desafio.greenmile.desafioGree.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.desafio.greenmile.desafioGree.model.Usuario;
import com.desafio.greenmile.desafioGree.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private UsuarioRepository repository;
	
	protected JWTLoginFilter(String url, AuthenticationManager authManager,UsuarioRepository repository) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.repository = repository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		AppUser user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		Usuario usuario = repository.findUsuarioByEmail(user.getEmail());
	
		if(usuario == null) {
			throw new UsernameNotFoundException("Invalid username/password");
		}
		
		else if(!BCrypt.checkpw(user.getPassword(),usuario.getSenha())) {	
			throw new BadCredentialsException("Invalid username/password");
		}
		
	    return new UsernamePasswordAuthenticationToken(user.getEmail(), usuario.getSenha(), Collections.emptyList());
		
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
		TokenAuthenticationService.addAuthentication(response, auth.getName(),this.repository);
	}
	
	
}