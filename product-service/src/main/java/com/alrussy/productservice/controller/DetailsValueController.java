package com.alrussy.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.productservice.dto.details_value_dto.DetailsValueRequest;
import com.alrussy.productservice.service.DetailsValueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailsValues")
@RequiredArgsConstructor
public class DetailsValueController {

	private final DetailsValueService detailsValueService;
	
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(detailsValueService.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(detailsValueService.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody DetailsValueRequest detailsValue){
		return ResponseEntity.ok(detailsValueService.save(detailsValue));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id ,@RequestBody DetailsValueRequest detailsValue){
		return ResponseEntity.ok(detailsValueService.update(id,detailsValue));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		detailsValueService.delete(id);
	return ResponseEntity.ok("delete By "+id+" is successfuly");
	}
	
	
}
