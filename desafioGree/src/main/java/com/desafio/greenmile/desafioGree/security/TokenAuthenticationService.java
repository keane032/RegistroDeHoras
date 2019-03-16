package com.desafio.greenmile.desafioGree.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.desafio.greenmile.desafioGree.model.Usuario;
import com.desafio.greenmile.desafioGree.repository.UsuarioRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	static final long EXPIRATION_TIME = 604_800_000L;
	static final String SECRET = "60CFC40C49E10DB8E9DA8C0B9769DB450938899085264D2D337FC5C13A212E05";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	static void addAuthentication(HttpServletResponse response, String email,UsuarioRepository repository) {
		Claims claims = Jwts.claims().setSubject(email);
		Usuario user = repository.findUsuarioByEmail(email);
		
		if (user != null) {
			claims.put("role", user.getPapel());
			claims.put("user_id", user.getId());
			
		}
		String JWT = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}
	
	static Authentication getAuthentication(HttpServletRequest request,UsuarioRepository repository) {
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			Claims body = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
			if (body != null && body.getSubject() != null) {
				Usuario user = repository.findUsuarioByEmail(body.getSubject());
				Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
				if(user != null) {
					authorities = user.getAuthorities();
				}

				return new UsernamePasswordAuthenticationToken(body.getSubject(), null, authorities);
			}
		}
		return null;
	}
	
}
