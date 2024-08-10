package com.alrussy.productservice.controller;

import java.util.List;

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
import com.alrussy.productservice.dto.details_value_dto.DetailsValueResponse;
import com.alrussy.productservice.service.DetailsValueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/detailsValues")
@RequiredArgsConstructor
public class DetailsValueController {

	private final DetailsValueService detailsValueService;
	
	
	@GetMapping
	public ResponseEntity<List<DetailsValueResponse>> findAll(){
		return ResponseEntity.ok(detailsValueService.findAll());
	}
	
	@GetMapping("/details-name/{id}")
	public ResponseEntity<List<DetailsValueResponse>> findByDetailsName(@PathVariable Long id){
		return ResponseEntity.ok(detailsValueService.findByDetailsName(id));
	}
	@GetMapping("/{id}")
	public ResponseEntity<DetailsValueResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok(detailsValueService.findById(id));
	}

	@PostMapping
	public ResponseEntity<DetailsValueResponse> save(@RequestBody DetailsValueRequest detailsValue){
		return ResponseEntity.ok(detailsValueService.save(detailsValue));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DetailsValueResponse> update(@PathVariable Long id ,@RequestBody DetailsValueRequest detailsValue){
		return ResponseEntity.ok(detailsValueService.update(id,detailsValue));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		detailsValueService.delete(id);
	return ResponseEntity.ok("delete By "+id+" is successfuly");
	}
	
	
}
