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

import com.alrussy.productservice.dto.details_name_dto.DetailsNameRequest;
import com.alrussy.productservice.dto.details_name_dto.DetailsNameResponse;
import com.alrussy.productservice.service.DetailsNameService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/details-names")
@RequiredArgsConstructor
public class DetailsNameController {

	private final DetailsNameService detailsNameService;
	
	
	@GetMapping
	public ResponseEntity<List<DetailsNameResponse>> findAll(){
		return ResponseEntity.ok(detailsNameService.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<DetailsNameResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok(detailsNameService.findById(id));
	}

	@PostMapping
	public ResponseEntity<DetailsNameResponse> save(@RequestBody DetailsNameRequest detailsName){
		return ResponseEntity.ok(detailsNameService.save(detailsName));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DetailsNameResponse> update(@PathVariable Long id ,@RequestBody DetailsNameRequest detailsName){
		return ResponseEntity.ok(detailsNameService.update(id,detailsName));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		detailsNameService.delete(id);
	return ResponseEntity.ok("delete By "+id+" is successfuly");
	}
	
	
}
