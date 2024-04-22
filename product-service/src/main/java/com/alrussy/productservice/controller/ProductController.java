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

import com.alrussy.productservice.dto.product_dto.ProductFilter;
import com.alrussy.productservice.dto.product_dto.ProductRequest;
import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

	private final ProductService productService;
	
	
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> findAll(){
		return ResponseEntity.ok(productService.findAll());
	}
	@GetMapping("/filter")
	public ResponseEntity<List<ProductResponse>> filter(@RequestBody ProductFilter filter){
		return ResponseEntity.ok(productService.filter(filter));
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok(productService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest product){
		return ResponseEntity.ok(productService.save(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> update(Long id, @RequestBody ProductRequest product){
		return ResponseEntity.ok(productService.save(product));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> postMethodName(@PathVariable Long id) {
		return ResponseEntity.ok(productService.delete(id));
	}
	
	
}
