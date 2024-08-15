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

import com.alrussy.productservice.dto.details_dto.DetailsRequest;
import com.alrussy.productservice.dto.details_dto.DetailsResponse;
import com.alrussy.productservice.service.DetailsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/details")
@RequiredArgsConstructor
public class DetailsController {

	private final DetailsService detailsService;
	
	
	@GetMapping
	public ResponseEntity<List<DetailsResponse>> findAll(){
		return ResponseEntity.ok(detailsService.findAll());
	}
	
	@GetMapping("/details-name/{id}")
	public ResponseEntity<List<DetailsResponse>> findByDetailsName(@PathVariable Long id){
		return ResponseEntity.ok(detailsService.findByDetailsName(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailsResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok(detailsService.findById(id));
	}

	@PostMapping
	public ResponseEntity<DetailsResponse> save(@RequestBody DetailsRequest request){
		return ResponseEntity.ok(detailsService.save(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DetailsResponse> update(@PathVariable Long id ,@RequestBody DetailsRequest request){
		return ResponseEntity.ok(detailsService.update(id,request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		detailsService.delete(id);
	return ResponseEntity.ok("delete By "+id+" is successfuly");
	}
	
	
}
