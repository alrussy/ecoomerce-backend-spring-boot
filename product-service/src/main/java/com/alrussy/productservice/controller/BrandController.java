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

import com.alrussy.productservice.dto.brand_dto.BrandRequest;
import com.alrussy.productservice.dto.brand_dto.BrandResponse;
import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.service.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/brands")
@RequiredArgsConstructor
public class BrandController {

	private final BrandService brandService;
	
	
	@GetMapping
	public ResponseEntity<List<BrandResponse>> findAll(){
		return ResponseEntity.ok(brandService.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<BrandResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok(brandService.findById(id));
	}
	@GetMapping("/category/{id}")
	public ResponseEntity<List<BrandResponse>> findByCategory(@PathVariable Long id){
		return ResponseEntity.ok(brandService.findByCategory(id));
	}
	@GetMapping("/name/{name}")
	public ResponseEntity<List<BrandResponse>> findByName(@PathVariable String name){
		return ResponseEntity.ok(brandService.findByName(name));
	}
	@PostMapping
	public ResponseEntity<BrandResponse> save(@RequestBody BrandRequest brand){
		return ResponseEntity.ok(brandService.save(brand));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BrandResponse> update(@PathVariable Long id ,@RequestBody BrandRequest brand){
		return ResponseEntity.ok(brandService.update(id,brand));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		brandService.delete(id);
	return ResponseEntity.ok("delete By "+id+" is successfuly");
	}
	
	
}
