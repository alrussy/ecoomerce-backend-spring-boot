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

import com.alrussy.productservice.dto.product_dto.PriceFilter;
import com.alrussy.productservice.dto.product_dto.ProductFilter;
import com.alrussy.productservice.dto.product_dto.ProductRequest;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(productService.findAll());
	}
	@GetMapping("/filter")
	public ResponseEntity<?> filter(@RequestBody ProductFilter filter){
		return ResponseEntity.ok(productService.filter(filter));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(productService.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProductRequest product){
		return ResponseEntity.ok(productService.save(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(Long id, @RequestBody ProductRequest product){
		return ResponseEntity.ok(productService.save(product));
	}
	
	@DeleteMapping("/{id}")
	public String postMethodName(@PathVariable Long id) {
		//TODO: process POST request
		
		return productService.delete(id);
	}
	
	
}
