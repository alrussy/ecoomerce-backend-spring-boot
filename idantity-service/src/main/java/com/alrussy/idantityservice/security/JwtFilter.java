package com.alrussy.idantityservice.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alrussy.idantityservice.entity.User;
import com.alrussy.idantityservice.service.UserService;
import com.google.common.net.HttpHeaders;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private final UserService service;

	@Override
	protected void doFilterInternal(@Nonnull HttpServletRequest request,@Nonnull HttpServletResponse response,@Nonnull FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		String token = "";
		String userName = "";

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		token = authHeader.substring(7);
		userName =JwtUtils.extractUserName(token);

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = service.loadUserByUsername(userName);
			if (JwtUtils.isValid(token, user)) {

				UsernamePasswordAuthenticationToken authenticationToken =  UsernamePasswordAuthenticationToken.authenticated(user,null, user.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}

		}
		
		filterChain.doFilter(request, response);
	}

}
