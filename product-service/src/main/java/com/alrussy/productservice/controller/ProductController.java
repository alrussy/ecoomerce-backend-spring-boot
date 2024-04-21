package com.alrussy.productservice.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.AbstractServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.productservice.dto.product_dto.PriceFilter;
import com.alrussy.productservice.dto.product_dto.ProductFilter;
import com.alrussy.productservice.dto.product_dto.ProductRequest;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.service.ProductService;
import com.google.common.net.HttpHeaders;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

	private final ProductService productService;
	private final HttpServletRequest httpServletRequest;
	
	
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(productService.findAll());
	}
	@GetMapping("/filter")
	
	public ResponseEntity<?> filter(@RequestBody ProductFilter filter){
	log.info(httpServletRequest.getHeader("X-User-Details"));
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
