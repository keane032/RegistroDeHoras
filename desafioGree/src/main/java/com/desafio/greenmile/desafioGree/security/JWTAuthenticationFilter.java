package com.desafio.greenmile.desafioGree.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.desafio.greenmile.desafioGree.repository.UsuarioRepository;

public class JWTAuthenticationFilter extends GenericFilterBean {

	private UsuarioRepository repository;

	public JWTAuthenticationFilter(UsuarioRepository repository) {
		this.repository = repository;
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		Authentication authentication = TokenAuthenticationService
				.getAuthentication((HttpServletRequest) request, this.repository);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

}