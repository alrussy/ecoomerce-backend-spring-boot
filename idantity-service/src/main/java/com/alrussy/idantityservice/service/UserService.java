package com.alrussy.idantityservice.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alrussy.idantityservice.dto.RegisterationRequest;
import com.alrussy.idantityservice.dto.TokenResponse;
import com.alrussy.idantityservice.dto.UserCredential;
import com.alrussy.idantityservice.entity.Role;
import com.alrussy.idantityservice.entity.User;
import com.alrussy.idantityservice.repository.UserRepository;
import com.alrussy.idantityservice.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
	

	private final PasswordEncoder encoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private  UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repository.findByEmail(username).orElseThrow(() -> new  UsernameNotFoundException("user not found !!..."));
	}
	
	public String register(RegisterationRequest request) {
		
		if(!request.getPassword().equals(request.getConfirmPassword())) {
			throw new IllegalStateException("ConfirmPassword is not equel password");
		}
		request.setPassword(encoder.encode(request.getPassword()));
		User user= request.mapToUser();
		user.setIsEnabled(true);
		user.setRoles(Set.of(Role.builder().id(1L).build()));
		repository.save(user);
		return "register is successfuly..";
	}
	
	public TokenResponse authenticated(UserCredential  credential) {
	Authentication authentication=	authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(credential.getEmail(), credential.getPassword()));
	log.info("======================================="+authentication.isAuthenticated());
	if(authentication.isAuthenticated()) {
		 String tokenAccess= JwtUtils.generateTokin(credential.getEmail());
		 return TokenResponse.builder().tokenAccess(tokenAccess).build();
		}
	else	
	throw new IllegalStateException("invalid cerdential");
	}

	public Boolean tokenValid(String token) {
		
		return JwtUtils.isValid(token);
	}
}
