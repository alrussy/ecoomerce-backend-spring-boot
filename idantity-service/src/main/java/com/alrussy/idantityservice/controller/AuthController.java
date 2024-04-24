package com.alrussy.idantityservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.idantityservice.dto.OtpActivation;
import com.alrussy.idantityservice.dto.RegisterationRequest;
import com.alrussy.idantityservice.dto.UserCredential;
import com.alrussy.idantityservice.dto.UserDetailsResponse;
import com.alrussy.idantityservice.service.OtpService;
import com.alrussy.idantityservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;
	private final OtpService otpService;

	@PostMapping("/register")	public ResponseEntity<?> register(@RequestBody RegisterationRequest request) {
		return ResponseEntity.ok(userService.register(request));
	}

	@PostMapping("/token")
	public ResponseEntity<?> authenticated(@RequestBody UserCredential credential) {
		
		return ResponseEntity.ok(userService.authenticated(credential));
	}
	
	@PostMapping("/activate-account")
	public ResponseEntity<String> activation(@RequestBody OtpActivation otpActivation) {
		
		return ResponseEntity.ok(otpService.activateAccount(otpActivation));
	}
}
