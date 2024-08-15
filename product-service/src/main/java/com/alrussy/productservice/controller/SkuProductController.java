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
import org.springframework.web.reactive.function.server.EntityResponse;

import com.alrussy.productservice.dto.sku_product_dto.SkuProductRequest;
import com.alrussy.productservice.dto.sku_product_dto.SkuProductResponse;
import com.alrussy.productservice.service.SkuProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/sku")
@RequiredArgsConstructor

public class SkuProductController {

	private final SkuProductService skuProductService;

	@GetMapping
	public ResponseEntity<List<SkuProductResponse>> findAll() {
		return ResponseEntity.ok(skuProductService.findAll());
	}

	@GetMapping("/{skuCode}")
	public ResponseEntity<SkuProductResponse> findById(@PathVariable String skuCode) {
		return ResponseEntity.ok(skuProductService.findById(skuCode));
	}

	@GetMapping("/product-id/{id}")
	public ResponseEntity<?> findByProductId(@PathVariable("id") Long productId) {
		return ResponseEntity.ok(skuProductService.findByProduct(productId));
	}
	@PostMapping
	public ResponseEntity<SkuProductResponse> save(@RequestBody SkuProductRequest request) {
		return ResponseEntity.ok(skuProductService.save(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SkuProductResponse> update(String skuCode, @RequestBody SkuProductRequest request) {
		return ResponseEntity.ok(skuProductService.update(skuCode,request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String skuCode) {
		return ResponseEntity.ok(skuProductService.delete(skuCode));
	}
	
	@GetMapping("/test/save")
	public ResponseEntity<SkuProductResponse> testSave(){
		return ResponseEntity.ok(skuProductService.testSave());
	}
	@GetMapping("/delete/{sku}")
	public ResponseEntity<?> testdelete(@PathVariable String sku){
		return ResponseEntity.ok(skuProductService.testdelete(sku));
	}

}
