package com.alrussy.idantityservice.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> sqlIntegrityConstraintViolationException(
			SQLIntegrityConstraintViolationException exception){
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
}
